package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CarteWagonTest {

    // Variables
    private CarteWagon CarteWagonNoirValide;
    private CarteWagon CarteWagonBleuValide;
    private CarteWagon CarteWagonRougeValide;


    @BeforeEach
    void startup(){
        CarteWagonNoirValide = new CarteWagon(Couleur.Noir);
        CarteWagonBleuValide = new CarteWagon(Couleur.Bleu);
        CarteWagonRougeValide = new CarteWagon(Couleur.Rouge);
    }

    @Test
    void testIsValid(){
        assertNotNull(CarteWagonNoirValide);
        assertNotNull(CarteWagonNoirValide.getCouleur());
        assertTrue(CarteWagonNoirValide.isValid());
        assertTrue(CarteWagonBleuValide.isValid());
        assertTrue(CarteWagonRougeValide.isValid());
    }

    @Test
    void testAffichageNom(){

        assertEquals("CarteWagon de couleur  Noir",CarteWagonNoirValide.getNom());
        assertEquals("CarteWagon de couleur  Rouge",CarteWagonRougeValide.getNom());
        assertEquals("CarteWagon de couleur  Bleu",CarteWagonBleuValide.getNom());

    }
}
