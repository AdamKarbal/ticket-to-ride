package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.NoAvailableTrip;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotRouteTest {
    RobotRoute robotRouteBleu;
    MoteurDeJeu moteurDeJeu;
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    PiocheDestinationCourte piocheDestinationCourte;
    PiocheDestinationLongue piocheDestinationLongue;
    GestionEtatJeu gestionEtatJeu;
    GameMap map;
    GestionTrajets gestionTrajets;
    CarteDestination berlinKiel;
    @BeforeEach
    void setUp() {
        robotRouteBleu=new RobotRoute(Couleur.Bleu);
        pioche=new Pioche();
        piocheSurTable=new PiocheSurTable();
        piocheDestinationCourte=new PiocheDestinationCourte();
        piocheDestinationLongue=new PiocheDestinationLongue();
        map=new GameMap();
        moteurDeJeu= new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        gestionTrajets=new GestionTrajets(map);
        gestionEtatJeu=new GestionEtatJeu(gestionTrajets);
        berlinKiel= new CarteDestination(Couleur.Marron, Ville.Kiel,Ville.Berlin,22);
        for(int i=0;i<pioche.size();i++) robotRouteBleu.piocher(moteurDeJeu);
    }

    @Test
    void testShortestPath() throws NotEnoughWagonCard {
        robotRouteBleu.chooseDestination(berlinKiel);
        robotRouteBleu.poseTrainOpti(gestionTrajets,moteurDeJeu);
        robotRouteBleu.poseTrainOpti(gestionTrajets,moteurDeJeu);
        assertThrows(NoAvailableTrip.class, ()-> robotRouteBleu.poseTrainOpti(gestionTrajets,moteurDeJeu));
        assertTrue(robotRouteBleu.getTrajet().size()==2);
        assertTrue(robotRouteBleu.getTrajet().get(0).isDone());
    }

}