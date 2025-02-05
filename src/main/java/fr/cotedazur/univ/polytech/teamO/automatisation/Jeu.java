package fr.cotedazur.univ.polytech.teamO.automatisation;

import fr.cotedazur.univ.polytech.teamO.bot.*;
import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.*;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.LogFormatter;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Jeu {
    private static final Logger logger = Logger.getLogger(Jeu.class.getName());

    static boolean displayLogs = true;
    RobotRoute robotBleu;
    RobotAleatoire robotJaune;
    ArrayList<Robot> robots;
    Ranking ranking;
    MoteurDeJeu moteurDeJeu;
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    PiocheDestinationCourte piocheDestinationCourte;
    PiocheDestinationLongue piocheDestinationLongue;
    GameMap map;
    GestionEtatJeu gestionEtatJeu;
    GestionTrajets gestionTrajets;
    Robot winner;

    static{
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
    }

    public void setup(boolean Logs) throws NotEnoughWagonCard {
        Random random = new Random();
        robotBleu = new RobotRoute(Couleur.Bleu);
        robotJaune = new RobotAleatoire(Couleur.Jaune, random);
        robots = new ArrayList<>();
        robots.add(robotBleu);
        robots.add(robotJaune);

        pioche = new Pioche();
        pioche.creerPiocheComplete();
        map = new GameMap();
        piocheSurTable = new PiocheSurTable();
        piocheDestinationCourte = new PiocheDestinationCourte();
        piocheDestinationLongue = new PiocheDestinationLongue();
        gestionTrajets = new GestionTrajets(map);
        moteurDeJeu = new MoteurDeJeu(pioche, piocheSurTable, piocheDestinationLongue, piocheDestinationCourte);
        piocheSurTable.initPiocheSurTable(moteurDeJeu);
        gestionEtatJeu = new GestionEtatJeu(gestionTrajets);
        if(!Logs) gestionEtatJeu.DLogsOff();

        for (int i = 0; i < 4; i++) {
            robotBleu.piocher(moteurDeJeu);
            robotJaune.piocher(moteurDeJeu);
        }
        robotJaune.init(moteurDeJeu);
        robotBleu.init(moteurDeJeu);

        logger.info("---------------------- Le jeu est prêt à démarrer ! ----------------------");
    }

    public void endofGame() throws EmptyListException {
        logger.info("\n---------------------- Le jeu est fini ! ----------------------");
        logger.info("Voici la mise à jour par rapport aux cartes destinations !");
        gestionEtatJeu.isDestinationsCardCompleted(robots);
        for (Robot robot : robots) {
            gestionEtatJeu.updateScoreCarteDestination(robot);
        }
        gestionEtatJeu.endOfGameUpdate(robots);
        ranking = new Ranking(robots);
        winner = ranking.first();
        logger.info(ranking.toString());
    }

    public void play(boolean Logs) throws NotEnoughWagonCard, EmptyListException {
        if(!Logs) DLogsOff();
        int tour = 1;
        setup(Logs);
        robots = new ArrayList<>();
        robots.add(robotJaune);
        robots.add(robotBleu);

        while (!(moteurDeJeu.isFinished(robots))) {
            logger.info("---------------------- Début du tour numéro : " + tour++ + " ----------------------");
            try {
                robotBleu.choixAction(gestionTrajets, moteurDeJeu);
                robotJaune.chooseaction(moteurDeJeu, map, gestionTrajets);
            } catch (EmptyStackException e) {
                logger.warning("Fin du jeu prématurée : Pioche vide.");
                break;
            }
            logger.info("\n");
        }
        endofGame();
    }

    public Robot getRobotAleatoire(){
        return robotJaune;
    }

    public Robot getRobotRoute(){
        return robotBleu;
    }

    public Robot getWinner(){
        return winner;
    }

    public static void DLogsOn(){
        logger.setLevel(Level.INFO);
    }

    public static void DLogsOff(){
        logger.setLevel(Level.OFF);
    }
}