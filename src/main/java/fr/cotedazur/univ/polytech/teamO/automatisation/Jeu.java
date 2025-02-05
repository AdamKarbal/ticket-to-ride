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
    RobotRoute robotRoute2;
    RobotRoute robotRoute1;
    RobotAleatoire robotAleatoire;
    RobotNewStrategy robotNewStrategy;
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

    public int getNombreTour(){
        return this.nombreTour;
    }

    public Ranking getRanking(){
        return ranking;
    }

    public GestionEtatJeu getGestionEtatJeu(){
        return gestionEtatJeu;
    }

    static{
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
    }

    public void setup(boolean Logs, int Version) throws NotEnoughWagonCard {
        Random random = new Random();
        robotRoute1 = new RobotRoute(Couleur.Bleu);
        robotAleatoire = new RobotAleatoire(Couleur.Jaune, random);
        robotRoute2 = new RobotRoute(Couleur.Vert);
        robotNewStrategy = new RobotNewStrategy(Couleur.Rouge);
        robots = new ArrayList<>();
        this.nombreTour = 0;

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

        if(Version == 1) {//Faudra rajouter un if pour la V3 avec tous les bots)
            robots.add(robotRoute1);
            robots.add(robotAleatoire);
            for (int i = 0; i < 4; i++) {
                robotRoute1.piocher(moteurDeJeu);
                robotAleatoire.piocher(moteurDeJeu);
            }
            robotAleatoire.init(moteurDeJeu);
            robotRoute1.init(moteurDeJeu);
        }
        else if(Version == 2) {
            robots.add(robotRoute1);
            robots.add(robotRoute2);
            for (int i = 0; i < 4; i++) {
                robotRoute1.piocher(moteurDeJeu);
                robotRoute2.piocher(moteurDeJeu);
            }
            robotRoute2.init(moteurDeJeu);
            robotRoute1.init(moteurDeJeu);
        }
        else if(Version == 3) {
            robots.add(robotRoute1);
            robots.add(robotNewStrategy);
            robots.add(robotAleatoire);
            for (int i = 0; i < 4; i++) {
                robotRoute1.piocher(moteurDeJeu);
                robotNewStrategy.piocher(moteurDeJeu);
                robotAleatoire.piocher(moteurDeJeu);
            }
            robotRoute1.init(moteurDeJeu);
            robotNewStrategy.init(moteurDeJeu);
            robotAleatoire.init(moteurDeJeu);
        }

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

    public void play(boolean Logs, int Version) throws NotEnoughWagonCard, EmptyListException {
        if(!Logs) DLogsOff();
        nombreTour = 1;
        setup(Logs, Version);
        robots = new ArrayList<>();
        if(Version == 1) {
            robots.add(robotRoute1);
            robots.add(robotAleatoire);
            loopV1();
        }
        else if(Version == 2) {
            robots.add(robotRoute1);
            robots.add(robotRoute2);
            loopV2();
        }
        else if(Version == 3) {
            robots.add(robotRoute1);
            robots.add(robotNewStrategy);
            robots.add(robotAleatoire);
            loopV3();
        }
        endofGame();
    }

    public Robot getRobotAleatoire(){
        return robotAleatoire;
    }

    public Robot getRobotRoute1(){
        return robotRoute1;
    }

    public Robot getRobotNewStrategy(){
        return robotNewStrategy;
    }

    public Robot getRobotRoute2(){
        return robotRoute2;
    }

    public Robot getWinner(){
        return winner;
    }

    public ArrayList<Robot> getRobots(){
        return robots;
    }

    public static void DLogsOn(){
        logger.setLevel(Level.INFO);
    }

    public static void DLogsOff(){
        logger.setLevel(Level.OFF);
    }

    public void loopV1() throws NotEnoughWagonCard{
        while (!(moteurDeJeu.isFinished(robots))) {
            logger.info("---------------------- Début du tour numéro : " + nombreTour++ + " ----------------------");
            try {
                robotRoute1.choixAction(gestionTrajets, moteurDeJeu);
                robotAleatoire.chooseaction(moteurDeJeu, map, gestionTrajets);
            }
            catch (EmptyStackException e) {
                logger.warning("Fin du jeu prématurée : Pioche vide.");
                break;
            }
            logger.info("\n");
        }
    }

    public void loopV2(){
        while (!(moteurDeJeu.isFinished(robots))) {
            logger.info("---------------------- Début du tour numéro : " + nombreTour++ + " ----------------------");
            try {
                robotRoute1.choixAction(gestionTrajets, moteurDeJeu);
                robotRoute2.choixAction(gestionTrajets, moteurDeJeu);
            }
            catch (EmptyStackException e) {
                logger.warning("Fin du jeu prématurée : Pioche vide.");
                break;
            }
            logger.info("\n");
        }
    }

    public void loopV3() throws NotEnoughWagonCard{
        while (!(moteurDeJeu.isFinished(robots))) {
            logger.info("---------------------- Début du tour numéro : " + nombreTour++ + " ----------------------");
            try {
                robotRoute1.choixAction(gestionTrajets, moteurDeJeu);
                robotAleatoire.chooseaction(moteurDeJeu, map, gestionTrajets);
                robotNewStrategy.choixAction(gestionTrajets, moteurDeJeu);
            }
            catch (EmptyStackException e) {
                logger.warning("Fin du jeu prématurée : Pioche vide.");
                break;
            }
            logger.info("\n");
        }
    }
}