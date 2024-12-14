package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Gestionnaire;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    Robot robot;
    CarteWagon carteBleue;
    CarteWagon carteJaune;
    CarteWagon carteRouge;
    int taillePiocheInit;
    Route routeBleue;
    Robot robotRouge;
    Gestionnaire gestionnaire;
    @BeforeEach
    void setUp() {
        robot=new Robot(Couleur.Bleu);
        robotRouge=new Robot(Couleur.Rouge);
        carteBleue = new CarteWagon(Couleur.Bleu);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        gestionnaire = new Gestionnaire();
        taillePiocheInit=gestionnaire.getPioche().size();
        routeBleue= new Route(1,Couleur.Bleu);
    }
    @Test
    void equal(){
        assertEquals(robot,robot);
        assertNotEquals(robot,robotRouge);
    }
    @Test
    void testPioche() {
        robot.piocher(gestionnaire);
        assertEquals(gestionnaire.getPioche().size(), taillePiocheInit -1);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
    }

    @Test
    void testPiocheSurTable() {
        assertEquals(gestionnaire.getPiocheSurTable().getMainDeCartes().size(),5);
        robot.piocherSurTable(gestionnaire.getPiocheSurTable().carteAtPosI(0),gestionnaire);
        assertEquals(gestionnaire.getPiocheSurTable().getMainDeCartes().size(),5);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
        assertEquals(gestionnaire.getPioche().size(),taillePiocheInit-1);
    }
    @Test
    void wagonByCouleur() {
        for(int i=0;i<10;i++){
            robot.piocher(gestionnaire);
        }
        assertEquals(gestionnaire.getPioche().size(),taillePiocheInit-10);
        System.out.println(robot.getWagonByCouleur());
    }
    @Test
    void poseTrain(){
        for(int i=0;i<10;i++){
            robot.piocher(gestionnaire);
        }
        System.out.println(robot.getWagonByCouleur());
        robot.poseTrain(routeBleue,gestionnaire); // je sais pas trop comment manip le test car ça dépend ce que je pioche
        assertEquals(routeBleue.isAvailable(),false);
    }


}