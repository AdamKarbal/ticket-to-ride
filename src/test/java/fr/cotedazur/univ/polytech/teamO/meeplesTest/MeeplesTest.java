package fr.cotedazur.univ.polytech.teamO.meeplesTest;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MeeplesTest {

    Meeples meepleNoir;
    Meeples meepleRouge;
    Meeples meepleBleu;

    @BeforeEach
    void setUp() {
        meepleNoir = new Meeples(Couleur.Noir);
        meepleRouge = new Meeples(Couleur.Rouge);
        meepleBleu = new Meeples(Couleur.Bleu);
    }

    @Test
    void getCouleurTest() {
        assertEquals(Couleur.Noir, meepleNoir.getCouleur());
        assertEquals(Couleur.Rouge, meepleRouge.getCouleur());
        assertEquals(Couleur.Bleu, meepleBleu.getCouleur());
    }

    @Test
    void setFreeTest() {
        meepleNoir.setFree(false);
        assertFalse(meepleNoir.isFree());
    }

    @Test
    void isFreeTest(){
        assertTrue(meepleNoir.isFree());
        assertTrue(meepleRouge.isFree());
        assertTrue(meepleBleu.isFree());
    }


}
