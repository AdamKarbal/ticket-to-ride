package fr.cotedazur.univ.polytech.teamO;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    static Robot robotBleu;
    static Robot robotRouge;
    static Robot robotJaune;
    static Route routeBleue;
    static ArrayList<Robot> robots;
    static Ranking ranking;
    static MoteurDeJeu moteurDeJeu;
    static Pioche pioche;
    static PiocheSurTable piocheSurTable;
    public static String hello() {
        return "Hello World!";
    }

    public static void setup() throws Exception {
        robotBleu=new Robot(Couleur.Bleu);
        robotRouge=new Robot(Couleur.Rouge);
        robotJaune=new Robot(Couleur.Jaune);
        routeBleue=new Route(4,Couleur.Bleu);
        moteurDeJeu =new MoteurDeJeu();
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
                robot.poseTrain(routeBleue, moteurDeJeu);
                System.out.println("Le robot "+robot.getCouleur().name()+" a posé une route");
                System.out.println(robot.getNbWagons()+" "+robot.getCouleur().name());}
        }
    }
    public static void main (String... args) throws Exception{
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
