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


import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;


public class Jeu {
     RobotRoute robotBleu;
     //RobotMeeples robotRouge;
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
    public  String hello() {
        return "Hello World!";
    }


    public  void setup() throws NotEnoughWagonCard {
        Random random = new Random();
        robotBleu=new RobotRoute(Couleur.Bleu);
        robotRouge=new RobotNewStrategy(Couleur.Rouge);
        //robotRouge=new RobotMeeples(Couleur.Rouge);
        robotJaune=new RobotAleatoire(Couleur.Jaune, random);
        robots=new ArrayList<>();
        robots.add(robotBleu);
        //robots.add(robotRouge);
        robots.add(robotJaune);
        robots.add(robotRouge);

        pioche = new Pioche();
        pioche.creerPiocheComplete();

        map = new GameMap();

        piocheSurTable = new PiocheSurTable();
        piocheDestinationCourte=new PiocheDestinationCourte();
        piocheDestinationLongue=new PiocheDestinationLongue();
        gestionTrajets= new GestionTrajets(map);
        moteurDeJeu =new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        piocheSurTable.initPiocheSurTable(moteurDeJeu);
        gestionEtatJeu=new GestionEtatJeu(gestionTrajets);

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

    public  void endofGame() throws EmptyListException {
        System.out.print("\n---------------------- Le jeu est fini ! ----------------------\n");
        System.out.print("Voici la mise à jour par rapport aux cartes destinations !\n");
        gestionEtatJeu.isDestinationsCardCompleted(robots);
        for(Robot robot:robots){
            gestionEtatJeu.updateScoreCarteDestination(robot);
        }
        gestionEtatJeu.endOfGameUpdate(robots);
        ranking=new Ranking(robots);
        System.out.println(ranking);
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
                robotBleu.choixAction(gestionTrajets,moteurDeJeu);
                robotRouge.choixAction(gestionTrajets,moteurDeJeu);
                robotJaune.chooseaction(moteurDeJeu,map,gestionTrajets);}
            catch(EmptyStackException e){
                break;
            }
            System.out.print("\n");
        }
        endofGame();
    }

}
