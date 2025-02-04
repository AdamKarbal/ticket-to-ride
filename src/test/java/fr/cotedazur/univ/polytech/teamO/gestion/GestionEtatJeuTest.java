package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionEtatJeuTest {
    GameMap gameMap;
    GestionEtatJeu gestionEtatJeu;
    Trajet berlinRostock;
    Trajet rostockKiel;
    Route routeViolet;
    Route routeOrange;
    Robot robotBleu;
    Robot robotRouge;
    Robot robotJaune;
    CarteDestination berlinKielDestination;
    MoteurDeJeu moteurDeJeu;
    GestionTrajets gestionTrajets;
    ArrayList<Robot> robots;
    PiocheDestinationCourte piocheDestinationCourte;
    @BeforeEach
    void setUp() {
        moteurDeJeu=new MoteurDeJeu(new Pioche<>(),new PiocheSurTable<>(),new PiocheDestinationLongue(),new PiocheDestinationCourte());
        gameMap =new GameMap();
        gestionTrajets=new GestionTrajets(gameMap);
        gestionEtatJeu=new GestionEtatJeu(gestionTrajets);
        routeOrange=new Route(4,Couleur.Orange);
        routeViolet=new Route(6,Couleur.Violet);
        robotRouge= new Robot(Couleur.Rouge);
        robotBleu = new Robot(Couleur.Bleu);
        robotJaune = new Robot(Couleur.Jaune);
        berlinRostock=new Trajet(Ville.Berlin,Ville.Rostock,routeViolet);
        rostockKiel=new Trajet(Ville.Rostock,Ville.Kiel,routeViolet);
        berlinKielDestination= new CarteDestination(Couleur.Marron,Ville.Berlin,Ville.Rostock,22);
        robots = new ArrayList<>();
        robots.add(robotJaune);
        robots.add(robotRouge);
        robots.add(robotBleu);
        piocheDestinationCourte = new PiocheDestinationCourte();
        piocheDestinationCourte.init();
    }


    @Test
    void completeDestinationCard(){
        robotBleu.addTrajetDone(rostockKiel);
        robotBleu.addTrajetDone(berlinRostock);
        robotBleu.chooseDestination(berlinKielDestination);
        assertTrue(gestionTrajets.isConnected(berlinKielDestination.getVille1(),berlinKielDestination.getVille2(),robotBleu.getTrajet()));
        gestionEtatJeu.isDestinationsCardCompleted(new ArrayList<>(Arrays.asList(robotBleu)));
        gestionEtatJeu.updateScoreCarteDestination(robotBleu);
        assertEquals(robotBleu.getScore().getNbPoints(),22);
    }

    @Test
    void testWhoHaveMostDestinationCards(){
        robotBleu.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotBleu,1));
        robotBleu.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotBleu,1));
        robotBleu.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotBleu,1));
        robotJaune.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotJaune,1));
        robotJaune.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotJaune,1));
        robotRouge.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotRouge,1));
        for(Robot robot : robots){
            for(int i=0;i<robot.getDestinations().getMainDeCartes().size();i++){
                robot.getDestinations().getMainDeCartes().get(i).setCompleted(true);
            }
        }
        gestionEtatJeu.whoHaveMostDestinationCards(robots);
    }

    @Test
    void testWhoHaveMostMeeples(){
        robotBleu.getTakenMeeples().put(MCouleur.Bleu,6);
        robotBleu.getTakenMeeples().put(MCouleur.Bleu, 3);
        robotRouge.getTakenMeeples().put(MCouleur.Bleu, 4);
        robotJaune.getTakenMeeples().put(MCouleur.Bleu, 3);
        robotBleu.getTakenMeeples().put(MCouleur.Blanc, 2);
        robotRouge.getTakenMeeples().put(MCouleur.Blanc, 5);
        robotJaune.getTakenMeeples().put(MCouleur.Blanc, 3);
        robotBleu.getTakenMeeples().put(MCouleur.Noir, 5);
        robotRouge.getTakenMeeples().put(MCouleur.Noir, 3);
        robotJaune.getTakenMeeples().put(MCouleur.Noir, 2);
        robotBleu.getTakenMeeples().put(MCouleur.Vert, 3);
        robotRouge.getTakenMeeples().put(MCouleur.Vert, 1);
        robotJaune.getTakenMeeples().put(MCouleur.Vert, 6);
        robotBleu.getTakenMeeples().put(MCouleur.Rouge, 5);
        robotRouge.getTakenMeeples().put(MCouleur.Rouge, 2);
        robotJaune.getTakenMeeples().put(MCouleur.Rouge, 3);
        robotBleu.getTakenMeeples().put(MCouleur.Jaune, 2);
        robotRouge.getTakenMeeples().put(MCouleur.Jaune, 5);
        robotJaune.getTakenMeeples().put(MCouleur.Jaune, 3);
        gestionEtatJeu.whoHaveMostMeeples(robots);
    }

    @Test
    void testNombreCarteDestination(){
        robotBleu.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotBleu,1));
        robotBleu.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotBleu,1));
        robotBleu.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotBleu,1));
        robotJaune.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotJaune,1));
        robotJaune.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotJaune,1));
        robotRouge.getDestinations().addCarte(moteurDeJeu.giveDestinationsCard(robotRouge,1));
        for(Robot robot : robots){
            for(int i=0;i<robot.getDestinations().getMainDeCartes().size();i++){
                robot.getDestinations().getMainDeCartes().get(i).setCompleted(true);
            }
        }
        assertEquals(1,gestionEtatJeu.nombreCarteDestination(robotRouge));
        assertEquals(2,gestionEtatJeu.nombreCarteDestination(robotJaune));
        assertEquals(3,gestionEtatJeu.nombreCarteDestination(robotBleu));
    }






}