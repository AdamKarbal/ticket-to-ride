package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
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
    Route routeRouge;
    Robot robotRouge;
    MoteurDeJeu moteurDeJeu;
    PiocheSurTable piocheSurTable;
    Pioche pioche;
    PiocheDestinationLongue piocheDestinationLongue;
    PiocheDestinationCourte piocheDestinationCourte;
    Trajet trajet;
    GestionTrajets gestionTrajets;
    GameMap map;
    Carte carteRainbow;
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
        carteRainbow = new CarteWagon(Couleur.Rainbow);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        moteurDeJeu = new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        piocheSurTable.initPiocheSurTable(moteurDeJeu);
        taillePiocheInit= moteurDeJeu.getPioche().size();
        routeBleue= new Route(1,Couleur.Bleu);
        routeRouge= new Route(5,Couleur.Rouge);
        trajet= new Trajet(Ville.Leipzig,Ville.Chemnitz,routeBleue);
        map=new GameMap();
        gestionTrajets= new GestionTrajets(map);
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
    void testPiocheSurTable(){ // TEST A MONTRER
        taillePiocheInit=moteurDeJeu.getPioche().size();
        assertFalse(robot.isTurnOver());
        moteurDeJeu = new MoteurDeJeu(pioche,piocheSurTableMock,piocheDestinationLongue,piocheDestinationCourte);
        when(moteurDeJeu.getPiocheSurTable().piocher(ArgumentMatchers.any())).thenReturn(carteBleue);
        taillePiocheInit= moteurDeJeu.getPioche().size();
        robot.piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(0),moteurDeJeu);
        robot.piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(0),moteurDeJeu);
        assertEquals(moteurDeJeu.getPioche().size(),taillePiocheInit -2);
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
        taillePiocheInit=moteurDeJeu.getPioche().size();
        for(int i=0;i<taillePiocheInit;i++){
            robot.piocher(moteurDeJeu);
        }
        robot.poseTrain(trajet, moteurDeJeu,gestionTrajets);
        assertTrue(trajet.isDone());
        assertEquals(robot.getTrajet().size(),1);
        assertEquals(robot.getTrajet(),new ArrayList<>(Arrays.asList(trajet)));
        assertEquals(routeBleue.isAvailable(),false);
    }
    @Test
    void testRainbowCase_Success() throws NotEnoughWagonCard {
        Trajet trajetRouge = new Trajet(Ville.Berlin,Ville.Niederlande,routeRouge);

        Couleur couleurRoute = Couleur.Rouge;
        robot.getWagonByCouleur().put(
                couleurRoute, 3
        );
        robot.getWagonByCouleur().put(
                Couleur.Rainbow, 3
        );

        assertTrue(routeRouge.isClaimable(5, new Wagon(couleurRoute)));

        boolean result = moteurDeJeu.rainbowCase(robot, trajetRouge);
        assertTrue(result);
        System.out.println(robot.getWagonByCouleur());
        moteurDeJeu.poseTrain(robot,trajetRouge,gestionTrajets);
        System.out.println(robot.getWagonByCouleur());
        assertEquals(0,robot.getWagonByCouleur().get(couleurRoute));
        assertEquals(1,robot.getWagonByCouleur().get(Couleur.Rainbow));
    }


}