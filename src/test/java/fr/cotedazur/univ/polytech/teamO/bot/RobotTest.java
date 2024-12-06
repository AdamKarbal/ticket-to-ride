package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    Pioche pioche;
    Robot robot;
    PiocheSurTable piocheSurTable;
    @BeforeEach
    void setUp() {
        pioche = new Pioche();
        piocheSurTable=new PiocheSurTable();
        pioche.mettre5cartesurlatable(piocheSurTable);
        robot=new Robot(2, Couleur.Bleu,pioche,piocheSurTable);
    }
    @Test
    void testPioche() {
        int tailleinit=pioche.getPioche().size();
        robot.piocher();
        assertEquals(pioche.getPioche().size(),tailleinit-1);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
    }

    @Test
    void testPiocheSurTable() {
        assertEquals(piocheSurTable.getMainDeCartes().size(),5);
        //robot.piocherSurTable();
        
    }

}