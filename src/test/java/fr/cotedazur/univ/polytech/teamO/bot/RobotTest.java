package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughtWagonCard;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

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
    @BeforeEach
    void setUp() {
        robot=new Robot(Couleur.Bleu);
        pioche= new Pioche();
        piocheSurTable=new PiocheSurTable();
        robotRouge=new Robot(Couleur.Rouge);
        carteBleue = new CarteWagon(Couleur.Bleu);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        //moteurDeJeu = new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
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
        // Mock dependencies
        PiocheSurTable piocheTableMock = mock(PiocheSurTable.class);
        MoteurDeJeu moteurDeJeuMock = mock(MoteurDeJeu.class);
        Pioche piocheMock = mock(Pioche.class);
        CarteLocomotive carteLocomotive = new CarteLocomotive();

        when(moteurDeJeuMock.getPiocheSurTable()).thenReturn(piocheTableMock);
        when(moteurDeJeuMock.getPioche()).thenReturn(piocheMock);
        when(moteurDeJeuMock.piocherSurTable(robot,carteBleue)).thenReturn(carteLocomotive);

        // Initial assertions
        assertFalse(robot.isTurnOver(), "Robot should not have ended its turn before piocherSurTable.");

        // Act: Call the method under test
        robot.piocherSurTable(carteBleue, moteurDeJeuMock);

        // Assertions
        assertTrue(robot.isTurnOver(), "Robot should have ended its turn after piocherSurTable.");

        // Verify interactions
        verify(moteurDeJeuMock).piocherSurTable(robot, ArgumentMatchers.eq(new CarteWagon(Couleur.Bleu)));
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
    void poseTrain() throws NotEnoughtWagonCard {
        for(int i=0;i<120;i++){
            robot.piocher(moteurDeJeu);
        }
        System.out.println(robot.getWagonByCouleur());
        robot.poseTrain(trajet, moteurDeJeu);
        assertEquals(robot.getTrajet().size(),1);
        assertEquals(robot.getTrajet(),new ArrayList<>(Arrays.asList(trajet)));
        assertEquals(routeBleue.isAvailable(),false);
    }


}