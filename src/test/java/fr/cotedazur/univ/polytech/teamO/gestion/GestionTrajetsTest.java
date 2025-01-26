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

class GestionTrajetsTest {
    GameMap gameMap;
    Trajet berlinRostock;
    Trajet rostockKiel;
    Trajet berlinSchwerin;
    Trajet kielSchwerin;
    List<Trajet> trajetList;
    Robot robot;
    CarteDestination berlinKielDestination;
    MoteurDeJeu moteurDeJeu;
    GestionTrajets gestionTrajets;
    GestionEtatJeu gestionEtatJeu;
    @BeforeEach
    void setUp() {
        moteurDeJeu=new MoteurDeJeu(new Pioche<>(),new PiocheSurTable<>(),new PiocheDestinationLongue(),new PiocheDestinationCourte());
        gameMap =new GameMap();
        gestionTrajets=new GestionTrajets(gameMap);
        gestionEtatJeu= new GestionEtatJeu(gestionTrajets);
        robot= new Robot(Couleur.Rouge);
        berlinRostock=new Trajet(Ville.Berlin,Ville.Rostock,new Route(4,Couleur.Default));
        rostockKiel=new Trajet(Ville.Rostock,Ville.Kiel,new Route(6,Couleur.Default));
        berlinSchwerin=new Trajet(Ville.Berlin,Ville.Schwerin,new Route(5, Couleur.Default));
        kielSchwerin= new Trajet(Ville.Kiel,Ville.Schwerin, new Route(3,Couleur.Default));
        trajetList=new ArrayList<>();
        trajetList.add(berlinRostock);
        trajetList.add(rostockKiel);
        berlinKielDestination = new CarteDestination(Couleur.Marron,Ville.Berlin,Ville.Kiel,22);
    }

    @Test
    void areCitiesConnected(){
        assertTrue(gestionTrajets.isConnected(Ville.Berlin,Ville.Rostock,trajetList));
        assertFalse(gestionTrajets.isConnected(Ville.Berlin,Ville.Hamburg,trajetList));
    }

    @Test
    void shortestTrip(){
        robot.chooseDestination(berlinKielDestination);
        robot.addTrajetDone(rostockKiel);
        robot.addTrajetDone(berlinRostock);
        //assertEquals(gestionTrajets.trajetOpti(robot,robot.getDestinations().getMainDeCartes().get(0)),kielSchwerin);
        System.out.println(gestionTrajets.trajetOpti(robot,robot.getDestinations().getMainDeCartes().get(0)));
    }


}