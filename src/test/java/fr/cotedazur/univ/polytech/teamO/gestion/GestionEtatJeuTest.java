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
    List<Robot>robots;
    Robot robot;
    List<CarteDestination>listeDestinations;
    Trajet trajetNiederlandeBerlin;

    @BeforeEach
    void setUp() {
         map=new Map();
        gestionEtatJeu=new GestionEtatJeu(map);
        robots=new ArrayList<>();
        robot = new Robot(Couleur.Bleu);
        robots.add(robot);
        listeDestinations =List.of(new CarteDestination(Couleur.Bleu,Ville.Niederlande,Ville.Berlin,5));
        trajetNiederlandeBerlin=new Trajet(Ville.Berlin,Ville.Niederlande,new Route(5,Couleur.Bleu));
        robot.chooseDestination(listeDestinations.getFirst());
        robot.getTrajet().add(trajetNiederlandeBerlin);
    }

    @Test
    void testIsDestinationsCardCompleted(){
        gestionEtatJeu.isDestinationsCardCompleted(robots);
        assertFalse(robots.isEmpty());
        assertFalse(listeDestinations.getFirst().isCompleted());
        assertFalse(trajetNiederlandeBerlin.isDone());
        assertTrue(trajetNiederlandeBerlin.getRoute().isAvailable());
        trajetNiederlandeBerlin.getRoute().setAvailable(false);
        assertFalse(trajetNiederlandeBerlin.getRoute().isAvailable());
        assertTrue(trajetNiederlandeBerlin.isDone());
        gestionEtatJeu.isDestinationsCardCompleted(robots);
        assertTrue(robot.getDestinations().getMainDeCartes().getFirst().isCompleted());
    }


}