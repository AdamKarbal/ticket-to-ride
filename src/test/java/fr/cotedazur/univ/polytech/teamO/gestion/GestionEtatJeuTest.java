package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.Map;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionEtatJeuTest {
    Map map;
    GestionEtatJeu gestionEtatJeu;
    Trajet berlinRostock;
    Trajet rostockKiel;
    Route routeViolet;
    Route routeOrange;
    List<Trajet> trajetList;
    @BeforeEach
    void setUp() {
        map=new Map();
        gestionEtatJeu=new GestionEtatJeu(map);
        routeOrange=new Route(4,Couleur.Orange);
        routeViolet=new Route(6,Couleur.Violet);
        berlinRostock=new Trajet(Ville.Berlin,Ville.Rostock,routeViolet);
        rostockKiel=new Trajet(Ville.Rostock,Ville.Kiel,routeViolet);
        trajetList=new ArrayList<>();
        trajetList.add(berlinRostock);
        trajetList.add(rostockKiel);
    }
    @Test
    void areCitiesConnected(){
        assertTrue(gestionEtatJeu.isConnected(Ville.Berlin,Ville.Rostock,trajetList));
        assertFalse(gestionEtatJeu.isConnected(Ville.Berlin,Ville.Hamburg,trajetList));
    }


}