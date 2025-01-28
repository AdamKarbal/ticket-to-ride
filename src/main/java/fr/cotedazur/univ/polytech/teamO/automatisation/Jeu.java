package fr.cotedazur.univ.polytech.teamO.automatisation;
import fr.cotedazur.univ.polytech.teamO.bot.*;
import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.*;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Map;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Random;


public class Jeu {
    static RobotAleatoire robotBleu;
    static RobotAleatoire robotRouge;
    static RobotAleatoire robotJaune;
    static ArrayList<Robot> robots;
    static Ranking ranking;
    static MoteurDeJeu moteurDeJeu;
    static Pioche pioche;
    static PiocheSurTable piocheSurTable;
    static PiocheDestinationCourte piocheDestinationCourte;
    static PiocheDestinationLongue piocheDestinationLongue;
    static Map map;
    static GestionEtatJeu gestionEtatJeu;
    public static String hello() {
        return "Hello World!";
    }
    ;

    public static void setup() throws NotEnoughWagonCard {
        robotBleu=new RobotAleatoire(Couleur.Bleu);
        robotRouge=new RobotAleatoire(Couleur.Rouge);
        robotJaune=new RobotAleatoire(Couleur.Jaune);
        robots=new ArrayList<>();
        robots.add(robotBleu);
        robots.add(robotRouge);
        robots.add(robotJaune);

        pioche = new Pioche();

        map = new Map();
        piocheSurTable = new PiocheSurTable();
        piocheDestinationCourte=new PiocheDestinationCourte();
        piocheDestinationLongue=new PiocheDestinationLongue();

        moteurDeJeu =new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        gestionEtatJeu=new GestionEtatJeu(map);

        for(int i=0;i<4;i++){
            robotBleu.piocher(moteurDeJeu);
            robotRouge.piocher(moteurDeJeu);
            robotJaune.piocher(moteurDeJeu);
        }
        robotJaune.init(moteurDeJeu);
        robotBleu.init(moteurDeJeu);
        robotRouge.init(moteurDeJeu);

        System.out.println("---------------------- Le jeu est prêt à démarrer ! ----------------------");
    }

    public static void endofGame() throws EmptyListException {
        System.out.print("\n---------------------- Le jeu est finit ! ----------------------\n");
        System.out.print("Voici la mise à jour par rapport au carte destination !\n");
        gestionEtatJeu.isDestinationsCardCompleted(robots);
        for(Robot robot:robots){
            gestionEtatJeu.updateScoreCarteDestination(robot);
        }
        gestionEtatJeu.whoHaveMostMeeples(robots);
        ranking=new Ranking(robots);
        System.out.println(ranking);
    }

    public static void play () throws NotEnoughWagonCard, EmptyListException {
        int tour = 1;
        setup();
        robots=new ArrayList<>();
        robots.add(robotJaune);
        robots.add(robotBleu);
        robots.add(robotRouge);
        while (!(moteurDeJeu.isFinished(robots))){
            System.out.println("---------------------- Début du tour numéro : " + tour++ + "---------------------- \n");
            try{
            robotBleu.chooseaction(moteurDeJeu,map);
            robotRouge.chooseaction(moteurDeJeu,map);
            robotJaune.chooseaction(moteurDeJeu,map);}
            catch(EmptyStackException e){
                break;
            }
            System.out.print("\n");
        }
        endofGame();
    }

}
