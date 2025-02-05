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
    private int nombreTour;
    static boolean displayLogs = true;
    RobotRoute robotBleu;
    RobotAleatoire robotJaune;
    RobotNewStrategy robotRouge;
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
        robotRouge= new RobotNewStrategy(Couleur.Rouge);
        robots = new ArrayList<>();
        robots.add(robotBleu);
        robots.add(robotJaune);
        robots.add(robotRouge);
        this.nombreTour=0;
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
            robotRouge.piocher(moteurDeJeu);
        }
        robotJaune.init(moteurDeJeu);
        robotBleu.init(moteurDeJeu);
        robotRouge.init(moteurDeJeu);

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
        nombreTour = 1;
        setup(Logs);
        robots = new ArrayList<>();
        robots.add(robotJaune);
        robots.add(robotBleu);
        robots.add(robotRouge);

        while (!(moteurDeJeu.isFinished(robots))) {
            logger.info("---------------------- Début du tour numéro : " + nombreTour++ + " ----------------------");
            try {
                robotBleu.choixAction(gestionTrajets, moteurDeJeu);
                robotJaune.chooseaction(moteurDeJeu, map, gestionTrajets);
                robotRouge.choixAction(gestionTrajets, moteurDeJeu);
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
    public  Robot getRobotNewStrategy(){return robotRouge;}

    public Robot getWinner(){
        return winner;
    }

    public static void DLogsOn(){
        logger.setLevel(Level.INFO);
    }

    public static void DLogsOff(){
        logger.setLevel(Level.OFF);
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public int getNombreTour(){
        return nombreTour;
    }

    public Ranking getRanking(){
        return ranking;
    }

    public GestionEtatJeu getGestionEtatJeu(){
        return gestionEtatJeu;
    }

}