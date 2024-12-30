package fr.cotedazur.univ.polytech.teamO.meeplesTest;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MeeplesTest {

    Meeples meeple1;
    Meeples meeple2;
    Meeples meeple3;

    @BeforeEach
    void setUp() {
        meeple1 = new Meeples(MCouleur.Noir);
        meeple2 = new Meeples(MCouleur.Rouge);
        meeple3 = new Meeples(MCouleur.Bleu);
    }

    @Test
    void getCouleurTest() {
        assertEquals(MCouleur.Noir, meeple1.getCouleur());
        assertEquals(MCouleur.Rouge, meeple2.getCouleur());
        assertEquals(MCouleur.Bleu, meeple3.getCouleur());
    }

    @Test
    void setFreeTest() {
        meeple1.setFree(false);
        assertFalse(meeple1.isFree());
    }

    @Test
    void isFreeTest(){
        assertTrue(meeple1.isFree());
        assertTrue(meeple2.isFree());
        assertTrue(meeple3.isFree());
    }


}
