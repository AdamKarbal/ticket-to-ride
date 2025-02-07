package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.NoAvailableTrip;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RobotNewStrategyTest {
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    RobotNewStrategy robotNewStrategy;
    MoteurDeJeu moteurDeJeu;
    GestionTrajets gestionTrajets;
    GameMap gameMap;
    CarteDestination berlinLeipzigDestination;

    @BeforeEach
    void setUp() {
        piocheSurTable = new PiocheSurTable();
        pioche = new Pioche();
        pioche.creerPiocheComplete();
        gameMap = new GameMap();
        robotNewStrategy=new RobotNewStrategy(Couleur.Bleu);
        moteurDeJeu=new MoteurDeJeu(pioche,piocheSurTable,new PiocheDestinationLongue(),new PiocheDestinationCourte());
        piocheSurTable.initPiocheSurTable(moteurDeJeu);
        gestionTrajets=new GestionTrajets(gameMap);
        berlinLeipzigDestination= new CarteDestination(Couleur.Bleu,Ville.Berlin,Ville.Leipzig,10);

    }

    @Test
    void testPiocherCarteDestination(){
        robotNewStrategy.getDestinations().getMainDeCartes().addAll(List.of(new CarteDestination(Couleur.Bleu, Ville.Niederlande,Ville.Berlin,10),new CarteDestination(Couleur.Marron, Ville.Berlin,Ville.Bremerhaven,12),new CarteDestination(Couleur.Bleu, Ville.Leipzig,Ville.Hannover,8),new CarteDestination(Couleur.Marron, Ville.Augsburg,Ville.Dortmund,16)));
        assertEquals(4,robotNewStrategy.getDestinations().getMainDeCartes().size());
        assertTrue(robotNewStrategy.hasVillesCommunes());
        robotNewStrategy.refuseDestinations(moteurDeJeu);
        assertEquals(2,robotNewStrategy.getDestinations().getMainDeCartes().size());
        assertEquals(new ArrayList<>(Arrays.asList(new CarteDestination(Couleur.Bleu, Ville.Niederlande,Ville.Berlin,10),new CarteDestination(Couleur.Marron, Ville.Berlin,Ville.Bremerhaven,12))),robotNewStrategy.getDestinations().getMainDeCartes());
    }

    @Test
    void testChooseDestination() {
        // Vérifie que le robot peut choisir une destination
        robotNewStrategy.chooseDestination(new CarteDestination(Couleur.Bleu,Ville.Leipzig,Ville.Berlin,11));
        assertTrue(robotNewStrategy.getDestinations().getMainDeCartes().contains(new CarteDestination(Couleur.Bleu,Ville.Leipzig,Ville.Berlin,11)));
    }

    @Test
    void testIsVillesCommunes() {
        robotNewStrategy.chooseDestination(new CarteDestination(Couleur.Bleu,Ville.Berlin,Ville.Hamburg,10));
        robotNewStrategy.chooseDestination(new CarteDestination(Couleur.Bleu,Ville.Leipzig,Ville.Berlin,11));

        // Vérifie que le robot détecte une ville commune (villeB)
        assertTrue(robotNewStrategy.hasVillesCommunes());
    }

    @Test
    void testChooseBetterDestination() {
        robotNewStrategy.getDestinations().getMainDeCartes().addAll(List.of(new CarteDestination(Couleur.Bleu, Ville.Niederlande,Ville.Düsseldorf,10),new CarteDestination(Couleur.Marron, Ville.Berlin,Ville.Bremerhaven,12),new CarteDestination(Couleur.Bleu, Ville.Leipzig,Ville.Hannover,8),new CarteDestination(Couleur.Marron, Ville.Augsburg,Ville.Dortmund,16)));
        assertFalse(robotNewStrategy.hasVillesCommunes());
        robotNewStrategy.refuseDestinations(moteurDeJeu);
        assertEquals(new ArrayList<>(List.of(new CarteDestination(Couleur.Marron, Ville.Berlin,Ville.Bremerhaven,12),new CarteDestination(Couleur.Marron, Ville.Augsburg,Ville.Dortmund,16))),robotNewStrategy.getDestinations().getMainDeCartes());

    }

    @Test
    void testSortWithFindingPath(){
        robotNewStrategy.getDestinations().getMainDeCartes().addAll(List.of(new CarteDestination(Couleur.Bleu, Ville.Niederlande,Ville.Düsseldorf,10),new CarteDestination(Couleur.Marron, Ville.Berlin,Ville.Bremerhaven,12),new CarteDestination(Couleur.Bleu, Ville.Leipzig,Ville.Hannover,8),new CarteDestination(Couleur.Marron, Ville.Augsburg,Ville.Dortmund,16)));
        robotNewStrategy.refuseDestinations(moteurDeJeu);
        robotNewStrategy.findingShortestPathForDestinations(moteurDeJeu,gestionTrajets);
        assertEquals(new ArrayList<>(List.of(new CarteDestination(Couleur.Marron, Ville.Berlin,Ville.Bremerhaven,12),new CarteDestination(Couleur.Marron, Ville.Augsburg,Ville.Dortmund,16))),robotNewStrategy.getDestinations().getMainDeCartes());
    }

    @Test
    void choixAction(){
        RobotNewStrategy robotSpy= spy(robotNewStrategy);
        robotSpy.chooseDestination(berlinLeipzigDestination);
        robotSpy.setVilleActuelle(Ville.Leipzig);
        assertThrows(NoAvailableTrip.class,()-> robotSpy.poseTrainOpti(gestionTrajets,moteurDeJeu));
        robotSpy.choixAction(gestionTrajets,moteurDeJeu);
        verify(robotSpy,atLeastOnce()).choosePioche(moteurDeJeu.findMinWagon(robotSpy),moteurDeJeu);

    }
    @Test
    void testChooseAnotherDestination(){
        robotNewStrategy.chooseDestination(berlinLeipzigDestination);
        robotNewStrategy.getCurrentDestination().setCompleted(true);
        robotNewStrategy.chooseAnotherDestination(moteurDeJeu);
        System.out.println(robotNewStrategy.getDestinations().getMainDeCartes());
        assertEquals(2,robotNewStrategy.getDestinations().getMainDeCartes().size());

    }

    @Test
    void testInit(){
        robotNewStrategy.chooseDestinations(moteurDeJeu);
        assertEquals(2,robotNewStrategy.getDestinations().getMainDeCartes().size());
        robotNewStrategy.getDestinations().getMainDeCartes().clear();
        robotNewStrategy.init(moteurDeJeu,gestionTrajets);
        assertEquals(2,robotNewStrategy.getDestinations().getMainDeCartes().size());
    }


}