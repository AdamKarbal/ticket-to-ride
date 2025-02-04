package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RobotRouteTest {
    RobotRoute robotRoute;
    MoteurDeJeu moteurDeJeu;
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    PiocheDestinationCourte piocheDestinationCourte;
    PiocheDestinationLongue piocheDestinationLongue;
    GestionEtatJeu gestionEtatJeu;
    GameMap map;
    GestionTrajets gestionTrajets;
    CarteDestination berlinKiel;
    int taille;
    @BeforeEach
    void setUp() {
        robotRoute =new RobotRoute(Couleur.Bleu);
        pioche=new Pioche();
        pioche.creerPiocheComplete();
        piocheSurTable=new PiocheSurTable();
        piocheDestinationCourte=new PiocheDestinationCourte();
        piocheDestinationLongue=new PiocheDestinationLongue();
        map=new GameMap();
        moteurDeJeu= new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        piocheSurTable.initPiocheSurTable(moteurDeJeu);
        gestionTrajets=new GestionTrajets(map);
        gestionEtatJeu=new GestionEtatJeu(gestionTrajets);
        berlinKiel= new CarteDestination(Couleur.Marron, Ville.Kiel,Ville.Berlin,22);
        taille=pioche.size();
    }

    @Test
    void testShortestPath() throws NotEnoughWagonCard {
        for(int i=0;i<taille;i++) robotRoute.piocher(moteurDeJeu);
        robotRoute.chooseDestination(berlinKiel);
        robotRoute.choixAction(gestionTrajets,moteurDeJeu);
        assertTrue(robotRoute.getVilleActuelle()==Ville.Schwerin);
        robotRoute.choixAction(gestionTrajets,moteurDeJeu);
        assertThrows(NoAvailableTrip.class, ()-> robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu));
        assertTrue(robotRoute.getTrajet().size()==2);
        assertTrue(robotRoute.getTrajet().get(0).isDone());
    }

    @Test
    void shortestTrip() throws NotEnoughWagonCard {
        for(int i=0;i<taille;i++) robotRoute.piocher(moteurDeJeu);
        robotRoute.chooseDestination(berlinKiel);
        assertEquals(gestionTrajets.findOptimalTrajet(robotRoute, robotRoute.getVilleActuelle(), robotRoute.getCurrentDestination().getVille2()),new Trajet(Ville.Kiel,Ville.Schwerin,new Route(2,Couleur.Default)));
        robotRoute.choixAction(gestionTrajets,moteurDeJeu);
        assertEquals(gestionTrajets.findOptimalTrajet(robotRoute, robotRoute.getVilleActuelle(), robotRoute.getCurrentDestination().getVille2()),new Trajet(Ville.Schwerin,Ville.Berlin,new Route(2,Couleur.Default)));
        robotRoute.choixAction(gestionTrajets,moteurDeJeu);
    }
    @Test
    void noCitiesStep() throws NotEnoughWagonCard {
        for(int i=0;i<taille;i++) {
            robotRoute.piocher(moteurDeJeu);
        }
        robotRoute.chooseDestination(new CarteDestination(Couleur.Marron,Ville.Emden,Ville.Düsseldorf,21));
        robotRoute.chooseDestination(new CarteDestination(Couleur.Marron,Ville.Frankfurt,Ville.Hamburg,21));
        robotRoute.choixAction(gestionTrajets,moteurDeJeu);
        robotRoute.choixAction(gestionTrajets,moteurDeJeu);
        robotRoute.choixAction(gestionTrajets,moteurDeJeu);
        assertThrows(NoAvailableTrip.class, ()-> robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu));
        robotRoute.choixAction(gestionTrajets,moteurDeJeu);
        assertEquals(robotRoute.getCurrentDestination(),new CarteDestination(Couleur.Marron,Ville.Frankfurt,Ville.Hamburg,21));
    }

    @Test
    void testChoixNotEnoughWagonCard() throws NotEnoughWagonCard {
        robotRoute.chooseDestination(berlinKiel);
        assertDoesNotThrow(() ->
                        gestionTrajets.findOptimalTrajet(robotRoute, robotRoute.getVilleActuelle(), robotRoute.getCurrentDestination().getVille2()),
                "Le trajet optimal ne devrait pas lever d'exception (NoAvailableTrip)."
        );
        assertThrows(NotEnoughWagonCard.class, ()-> robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu));}

    @Test
    void testChooseDestination() {
        robotRoute.chooseDestinations(moteurDeJeu);
        assertEquals(robotRoute.getDestinations().getMainDeCartes().size(),4);
    }

    @Test
    void testRefuseDestinaions(){
        robotRoute.chooseDestinations(moteurDeJeu);
        List<CarteDestination> destinations=robotRoute.getDestinations().getMainDeCartes();
        robotRoute.refuseDestinations(moteurDeJeu);
        destinations.sort(Comparator.comparing(CarteDestination::getValue));
        assertEquals(robotRoute.getDestinations().getMainDeCartes().get(0),destinations.get(0));
    }

    @Test
    void choosePioche(){
        RobotRoute robotSpy= spy(robotRoute);
        robotSpy.chooseDestination(berlinKiel);
        assertThrows(NotEnoughWagonCard.class, ()-> robotSpy.poseTrainOpti(gestionTrajets,moteurDeJeu));
        Trajet trajetOpti= gestionTrajets.findOptimalTrajet(robotSpy,robotSpy.getVilleActuelle(),robotSpy.getCurrentDestination().getVille2());
        robotSpy.choixAction(gestionTrajets,moteurDeJeu);
        verify(robotSpy,atLeastOnce()).choosePioche(trajetOpti.getRoute().getCouleur(), moteurDeJeu);
    }

}