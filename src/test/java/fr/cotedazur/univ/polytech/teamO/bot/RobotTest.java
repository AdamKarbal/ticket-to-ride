package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RobotTest {
    Robot robot;
    CarteWagon carteBleue;
    CarteWagon carteJaune;
    CarteWagon carteRouge;
    int taillePiocheInit;
    Route routeBleue;
    Robot robotRouge;
    MoteurDeJeu moteurDeJeu;
    PiocheSurTable piocheSurTable;
    Pioche pioche;
    PiocheDestinationLongue piocheDestinationLongue;
    PiocheDestinationCourte piocheDestinationCourte;
    Trajet trajet;
    @Mock PiocheSurTable piocheSurTableMock = mock(PiocheSurTable.class);
    @Mock Pioche piocheMock = mock(Pioche.class);
    @BeforeEach
    void setUp() {
        robot=new Robot(Couleur.Bleu);
        pioche= new Pioche();
        pioche.creerPiocheComplete();
        piocheSurTable=new PiocheSurTable();
        robotRouge=new Robot(Couleur.Rouge);
        carteBleue = new CarteWagon(Couleur.Bleu);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        moteurDeJeu = new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        taillePiocheInit= moteurDeJeu.getPioche().size();
        routeBleue= new Route(1,Couleur.Bleu);
        trajet= new Trajet(Ville.Leipzig,Ville.Chemnitz,routeBleue);
    }
    @Test
    void equal(){
        assertEquals(robot,robot);
        assertNotEquals(robot,robotRouge);
    }
    @Test
    void testPioche() {
        robot.piocher(moteurDeJeu);
        assertEquals(moteurDeJeu.getPioche().size(), taillePiocheInit -1);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
    }
    @Test
    void testPiocheSurTable(){
        assertFalse(robot.isTurnOver());
        robot.piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(0),moteurDeJeu);
        robot.piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(0),moteurDeJeu);
        assertEquals(moteurDeJeu.getPioche().size(),taillePiocheInit -2);
        assertEquals(moteurDeJeu.getPiocheSurTable().getMainDeCartes().size(),5);
        assertTrue(robot.isTurnOver());
    }
    @Test
    void testPiocheRainbow() {

        moteurDeJeu = new MoteurDeJeu(piocheMock,piocheSurTableMock,piocheDestinationLongue,piocheDestinationCourte);

        when(moteurDeJeu.getPiocheSurTable().piocher(carteBleue)).thenReturn(new CarteWagon(Couleur.Rainbow));

        assertFalse(robot.isTurnOver(), "Robot should not have ended its turn before piocherSurTable.");

        robot.piocherSurTable(carteBleue, moteurDeJeu);

        assertTrue(robot.isTurnOver(), "Robot should have ended its turn after piocherSurTable.");

    }

    @Test
    void wagonByCouleur() {
        for(int i=0;i<10;i++){
            robot.piocher(moteurDeJeu);
        }
        assertEquals(moteurDeJeu.getPioche().size(),taillePiocheInit-10);
        System.out.println(robot.getWagonByCouleur());
    }
    @Test
    void poseTrain() throws NotEnoughWagonCard {
        for(int i=0;i<pioche.size();i++){
            robot.piocher(moteurDeJeu);
        }
        int nbWagonByCouleurOriginel = robot.getWagonByCouleur().get(trajet.getRoute().getCouleur());
        robot.poseTrain(trajet, moteurDeJeu);
        assertTrue(trajet.isDone());
        assertEquals(robot.getWagonByCouleur().get(trajet.getRoute().getCouleur()),nbWagonByCouleurOriginel-trajet.getRoute().getNbCases());
        assertEquals(robot.getTrajet().size(),1);
        assertEquals(robot.getTrajet(),new ArrayList<>(Arrays.asList(trajet)));
        assertEquals(routeBleue.isAvailable(),false);
    }


}