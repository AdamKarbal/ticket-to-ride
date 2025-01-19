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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GestionTrajetsTest {
    GameMap gameMap;
    Trajet berlinRostock;
    Trajet rostockKiel;
    Route routeViolet;
    Route routeOrange;
    List<Trajet> trajetList;
    Robot robot;
    CarteDestination berlinRostockDestination;
    MoteurDeJeu moteurDeJeu;
    GestionTrajets gestionTrajets;
    @BeforeEach
    void setUp() {
        moteurDeJeu=new MoteurDeJeu(new Pioche<>(),new PiocheSurTable<>(),new PiocheDestinationLongue(),new PiocheDestinationCourte());
        gameMap =new GameMap();
        gestionTrajets=new GestionTrajets(gameMap);
        routeOrange=new Route(4, Couleur.Orange);
        routeViolet=new Route(6,Couleur.Violet);
        robot= new Robot(Couleur.Rouge);
        berlinRostock=new Trajet(Ville.Berlin,Ville.Rostock,routeViolet);
        rostockKiel=new Trajet(Ville.Rostock,Ville.Kiel,routeViolet);
        trajetList=new ArrayList<>();
        trajetList.add(berlinRostock);
        trajetList.add(rostockKiel);
        berlinRostockDestination= new CarteDestination(Couleur.Marron,Ville.Berlin,Ville.Rostock,22);
    }

    @Test
    void areCitiesConnected(){
        assertTrue(gestionTrajets.isConnected(Ville.Berlin,Ville.Rostock,trajetList));
        assertFalse(gestionTrajets.isConnected(Ville.Berlin,Ville.Hamburg,trajetList));
    }

}