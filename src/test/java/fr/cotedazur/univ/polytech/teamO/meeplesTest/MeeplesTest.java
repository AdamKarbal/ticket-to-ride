package fr.cotedazur.univ.polytech.teamO.meeplesTest;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
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
        meeple1 = new Meeples(Couleur.Noir);
        meeple2 = new Meeples(Couleur.Rouge);
        meeple3 = new Meeples(Couleur.Bleu);
    }

    @Test
    void getCouleurTest() {
        assertEquals(Couleur.Noir, meeple1.getCouleur());
        assertEquals(Couleur.Rouge, meeple2.getCouleur());
        assertEquals(Couleur.Bleu, meeple3.getCouleur());
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
