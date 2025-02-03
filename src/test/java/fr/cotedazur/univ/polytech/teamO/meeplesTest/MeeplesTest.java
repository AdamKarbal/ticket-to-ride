package fr.cotedazur.univ.polytech.teamO.meeplesTest;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
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
        meepleNoir = new Meeples(MCouleur.Noir);
        meepleRouge = new Meeples(MCouleur.Rouge);
        meepleBleu = new Meeples(MCouleur.Bleu);
    }

    @Test
    void getCouleurTest() {
        assertEquals(MCouleur.Noir, meepleNoir.getCouleur());
        assertEquals(MCouleur.Rouge, meepleRouge.getCouleur());
        assertEquals(MCouleur.Bleu, meepleBleu.getCouleur());
    }


    @Test
    void isFreeTest(){
        assertTrue(meepleNoir.isFree());
        assertTrue(meepleRouge.isFree());
        assertTrue(meepleBleu.isFree());
    }


}
