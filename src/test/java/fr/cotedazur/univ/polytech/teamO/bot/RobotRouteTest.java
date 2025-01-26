package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheDestinationCourte;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheDestinationLongue;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
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
        for(int i=0;i<120;i++) robotRouteBleu.piocher(moteurDeJeu);
    }

    @Test
    void testShortestPath() throws NotEnoughWagonCard {
        robotRouteBleu.chooseDestinations(moteurDeJeu);
        robotRouteBleu.poseTrainOpti(gestionTrajets,moteurDeJeu);
        assertTrue(robotRouteBleu.getTrajet().size()==1);
        assertTrue(robotRouteBleu.getTrajet().get(0).isDone());
    }

}