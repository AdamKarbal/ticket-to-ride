package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {
    Pioche pioche;
    Robot robot;
    PiocheSurTable piocheSurTable;
    CarteWagon carteBleue;
    CarteWagon carteJaune;
    CarteWagon carteRouge;
    int taillePiocheInit;
    @BeforeEach
    void setUp() {
        pioche = new Pioche();
        piocheSurTable=new PiocheSurTable();
        pioche.mettre5cartesurlatable(piocheSurTable);
        robot=new Robot(2, Couleur.Bleu,pioche,piocheSurTable);
        carteBleue = new CarteWagon(Couleur.Bleu);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        taillePiocheInit=pioche.getPioche().size();
    }
    @Test
    void testPioche() {
        robot.piocher();
        assertEquals(pioche.getPioche().size(), taillePiocheInit -1);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
    }

    @Test
    void testPiocheSurTable() {
        assertEquals(piocheSurTable.getMainDeCartes().size(),5);
        robot.piocherSurTable(piocheSurTable.carteAtPosI(0));
        assertEquals(piocheSurTable.getMainDeCartes().size(),5);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
        assertEquals(pioche.getPioche().size(),taillePiocheInit-1);
    }

}