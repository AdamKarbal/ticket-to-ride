package fr.cotedazur.univ.polytech.teamO.automatisation;
import fr.cotedazur.univ.polytech.teamO.bot.*;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.*;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Map;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Jeu {
    static Robot robotBleu;
    static Robot robotRouge;
    static Robot robotJaune;
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

    public static void setup() throws Exception {
        robotBleu=new Robot(Couleur.Bleu);
        robotRouge=new Robot(Couleur.Rouge);
        robotJaune=new Robot(Couleur.Jaune);
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
            robotBleu.chooseDestinations(moteurDeJeu);
            robotRouge.chooseDestinations(moteurDeJeu);
            robotJaune.chooseDestinations(moteurDeJeu);
        }
        for(Robot robot:robots){
            robot.refuseDestinations(moteurDeJeu);
        }
    }
    public static void randomAction(Robot robot){
        int choix;
        Random rand = new Random();
        choix=rand.nextInt(2)+1;
        if (choix==1){
                robot.piocher(moteurDeJeu);
                System.out.println("Le robot "+robot.getCouleur().name()+" a pioché "+robot.getMain().getMainDeCartes().getLast());

        }
        if (choix==2){
                    Trajet trajetRandom=map.getTrajets().get(rand.nextInt(map.getTrajets().size()));
                    robot.poseTrain(trajetRandom, moteurDeJeu);
                    System.out.println("Le robot "+robot.getCouleur().name()+" a posé une route sur le trajet "+trajetRandom);
                    System.out.println(robot.getNbWagons()+" "+robot.getCouleur().name());
        }
    }

    public static void endofGame(){
        gestionEtatJeu.isDestinationsCardCompleted(robots);
        for(Robot robot:robots){
            gestionEtatJeu.updateScoreCarteDestination(robot);
        }
    }
    public static void play () throws Exception{
        setup();
        robots=new ArrayList<>();
        robots.add(robotJaune);
        robots.add(robotBleu);
        robots.add(robotRouge);
        while (!(moteurDeJeu.isFinished(robotBleu) || moteurDeJeu.isFinished(robotRouge) || moteurDeJeu.isFinished(robotJaune)||pioche.getPioche().isEmpty())){
            randomAction(robotBleu);
            randomAction(robotRouge);
            randomAction(robotJaune);
        }
        endofGame();
        ranking=new Ranking(robots);
        System.out.println(ranking);

    }

}
