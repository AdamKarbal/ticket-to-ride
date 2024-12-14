package fr.cotedazur.univ.polytech.teamO;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Gestionnaire;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static Robot robotBleu;
    static Robot robotRouge;
    static Robot robotJaune;
    static Route routeBleue;
    static ArrayList<Robot> robots;
    static Ranking ranking;
    static Gestionnaire gestionnaire;
    public static String hello() {
        return "Hello World!";
    }

    public static void setup() throws Exception {
        robotBleu=new Robot(Couleur.Bleu);
        robotRouge=new Robot(Couleur.Rouge);
        robotJaune=new Robot(Couleur.Jaune);
        routeBleue=new Route(4,Couleur.Bleu);
        gestionnaire=new Gestionnaire();
        for(int i=0;i<4;i++){
            robotBleu.piocher(gestionnaire);
            robotRouge.piocher(gestionnaire);
            robotJaune.piocher(gestionnaire);
        }
    }
    public static void randomAction(Robot robot){
        int choix;
        Random rand = new Random();
        choix=rand.nextInt(2)+1;
        if (choix==1){
            robot.piocher(gestionnaire);
            System.out.println("Le robot "+robot.getCouleur().name()+" a pioché "+robot.getMain().getMainDeCartes().getLast());
        }
        if (choix==2){
            if(robot.getWagonByCouleur().get(Couleur.Bleu)==routeBleue.getNbCases()){
                robot.poseTrain(routeBleue,gestionnaire);
                System.out.println("Le robot "+robot.getCouleur().name()+" a posé une route");
                System.out.println(robot.getNbWagons()+" "+robot.getCouleur().name());}
        }
    }
    public static void main (String... args) throws Exception{
        setup();
        //while(!(Gestionnaire.isFinished(robotBleu) || Gestionnaire.isFinished(robotRouge) || Gestionnaire.isFinished(robotJaune))){
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
