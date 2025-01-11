package fr.cotedazur.univ.polytech.teamO.automatisation;
import fr.cotedazur.univ.polytech.teamO.bot.*;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.*;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Map;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;

import java.util.ArrayList;
import java.util.Random;


public class Jeu {
    static Robot robotBleu;
    static Robot robotRouge;
    static Robot robotJaune;
    static Route routeBleue;
    static ArrayList<Robot> robots;
    static Ranking ranking;
    static MoteurDeJeu moteurDeJeu;
    static Pioche pioche;
    static PiocheSurTable piocheSurTable;
    static PiocheDestinationCourte piocheDestinationCourte;
    static PiocheDestinationLongue piocheDestinationLongue;
    static Map map;
    public static String hello() {
        return "Hello World!";
    }

    public static void setup() throws Exception {
        robotBleu=new Robot(Couleur.Bleu);
        pioche = new Pioche();
        map = new Map();
        piocheSurTable = new PiocheSurTable();
        robotRouge=new Robot(Couleur.Rouge);
        robotJaune=new Robot(Couleur.Jaune);
        routeBleue=new Route(4,Couleur.Bleu);
        piocheDestinationCourte=new PiocheDestinationCourte();
        piocheDestinationLongue=new PiocheDestinationLongue();
        moteurDeJeu =new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        for(int i=0;i<4;i++){
            robotBleu.piocher(moteurDeJeu);
            robotRouge.piocher(moteurDeJeu);
            robotJaune.piocher(moteurDeJeu);
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
            if(robot.getWagonByCouleur().get(Couleur.Bleu)==routeBleue.getNbCases()){
                int trajetRandom=rand.nextInt(map.getTrajets().size());
                robot.poseTrain(map.getTrajets().get(trajetRandom), moteurDeJeu);
                System.out.println("Le robot "+robot.getCouleur().name()+" a posé une route");
                System.out.println(robot.getNbWagons()+" "+robot.getCouleur().name());}
        }
    }
    public static void play () throws Exception{
        setup();
        //while(!(gestionnaire.isFinished(robotBleu) || gestionnaire.isFinished(robotRouge) || gestionnaire.isFinished(robotJaune))){
        while(routeBleue.isAvailable()){
            randomAction(robotBleu);
            randomAction(robotRouge);
            randomAction(robotJaune);
        }
        robots=new ArrayList<>();
        robots.add(robotJaune);
        robots.add(robotBleu);
        robots.add(robotRouge);
        ranking=new Ranking(robots);
        System.out.println(ranking);

    }

}
