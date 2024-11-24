package fr.cotedazur.univ.polytech.teamO.piocheTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;


public class CarteWagonTest {

    // Variables
    private CarteWagon CarteWagonNoirValide;
    private CarteWagon CarteWagonBleuValide;
    private CarteWagon CarteWagonRougeValide;
    private CarteWagon CarteWagonChocolatInvalide;
    private CarteWagon CarteWagonMauveInvalide;


    @BeforeEach
    void startup(){
        CarteWagonNoirValide = new CarteWagon("Noir");
         CarteWagonBleuValide = new CarteWagon("Bleu");
         CarteWagonRougeValide = new CarteWagon("Rouge");
         CarteWagonChocolatInvalide = new CarteWagon("Chocolat");
         CarteWagonMauveInvalide = new CarteWagon("Mauve");
    }

    @Test
    void testIsValid(){
        assertTrue(CarteWagonNoirValide.isValid());
        assertTrue(CarteWagonBleuValide.isValid());
        assertTrue(CarteWagonRougeValide.isValid());
        assertFalse(CarteWagonMauveInvalide.isValid());
        assertFalse(CarteWagonChocolatInvalide.isValid());
    }

    @Test
    void testAffichageNom(){
        assertEquals("Carte Wagon de Couleur : Noir",CarteWagonNoirValide.getNom());
        assertEquals("Carte Wagon de Couleur : Rouge",CarteWagonRougeValide.getNom());
        assertEquals("Carte Wagon de Couleur : Bleu",CarteWagonBleuValide.getNom());
        assertEquals("Carte invalide",CarteWagonMauveInvalide.getNom());
        assertEquals("Carte invalide",CarteWagonChocolatInvalide.getNom());
    }
}
