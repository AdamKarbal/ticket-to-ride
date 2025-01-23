package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
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
    Robot robot;
    CarteDestination berlinKielDestination;
    MoteurDeJeu moteurDeJeu;
    GestionTrajets gestionTrajets;
    @BeforeEach
    void setUp() {
        moteurDeJeu=new MoteurDeJeu(new Pioche<>(),new PiocheSurTable<>(),new PiocheDestinationLongue(),new PiocheDestinationCourte());
        gameMap =new GameMap();
        gestionTrajets=new GestionTrajets(gameMap);
        gestionEtatJeu=new GestionEtatJeu(gestionTrajets);
        routeOrange=new Route(4,Couleur.Orange);
        routeViolet=new Route(6,Couleur.Violet);
        robot= new Robot(Couleur.Rouge);
        berlinRostock=new Trajet(Ville.Berlin,Ville.Rostock,routeViolet);
        rostockKiel=new Trajet(Ville.Rostock,Ville.Kiel,routeViolet);
        berlinKielDestination= new CarteDestination(Couleur.Marron,Ville.Berlin,Ville.Rostock,22);
    }


    @Test
    void completeDestinationCard(){
        robot.addTrajetDone(rostockKiel);
        robot.addTrajetDone(berlinRostock);
        robot.chooseDestination(berlinKielDestination);
        assertTrue(gestionTrajets.isConnected(berlinKielDestination.getVille1(),berlinKielDestination.getVille2(),robot.getTrajet()));
        gestionEtatJeu.isDestinationsCardCompleted(new ArrayList<>(Arrays.asList(robot)));
        gestionEtatJeu.updateScoreCarteDestination(robot);
        assertEquals(robot.getScore().getNbPoints(),22);
    }





}