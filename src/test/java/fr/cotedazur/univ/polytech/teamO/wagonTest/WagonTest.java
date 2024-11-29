package fr.cotedazur.univ.polytech.teamO.wagonTest;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WagonTest {

    private Wagon wagon1;
    private Wagon wagon2;
    private Wagon wagon3;

    @BeforeEach
    void setup() {
        wagon1 = new Wagon(Couleur.Bleu);
        wagon2 = new Wagon(Couleur.Jaune);
        wagon3 = new Wagon(Couleur.Rouge);
    }

    @Test
    void toStringTest() {
        assertEquals("Un wagon de couleur Bleu à placer sur une route.", wagon1.toString());
        assertEquals("Un wagon de couleur Jaune à placer sur une route.", wagon2.toString());
        assertEquals("Un wagon de couleur Rouge à placer sur une route.", wagon3.toString());
    }

    @Test
    void getCouleurTest(){
        assertEquals(Couleur.Bleu, wagon1.getCouleur());
        assertEquals(Couleur.Jaune, wagon2.getCouleur());
        assertEquals(Couleur.Rouge, wagon3.getCouleur());
    }
}
