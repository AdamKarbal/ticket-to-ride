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
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Random;


public class Jeu {
     RobotAleatoire robotBleu;
     RobotAleatoire robotRouge;
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
    public  String hello() {
        return "Hello World!";
    }
    ;

    public  void setup() throws NotEnoughWagonCard {
        robotBleu=new RobotAleatoire(Couleur.Bleu,new Random());
        robotRouge=new RobotAleatoire(Couleur.Rouge,new Random());
        robotJaune=new RobotAleatoire(Couleur.Jaune,new Random());
        robots=new ArrayList<>();
        robots.add(robotBleu);
        robots.add(robotRouge);
        robots.add(robotJaune);

        pioche = new Pioche();

        map = new GameMap();
        gestionTrajets=new GestionTrajets(map);
        piocheSurTable = new PiocheSurTable();
        piocheDestinationCourte=new PiocheDestinationCourte();
        piocheDestinationLongue=new PiocheDestinationLongue();

        moteurDeJeu =new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        gestionEtatJeu=new GestionEtatJeu(gestionTrajets);

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
        System.out.println("---------------------- Le jeu est prêt à démarrer ! ----------------------");
    }

    public  void endofGame(){
        gestionEtatJeu.isDestinationsCardCompleted(robots);
        for(Robot robot:robots){
            gestionEtatJeu.updateScoreCarteDestination(robot);
        }
    }

    public  void play () throws NotEnoughWagonCard, EmptyListException {
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
        ranking=new Ranking(robots);
        System.out.println(ranking);
    }

}
