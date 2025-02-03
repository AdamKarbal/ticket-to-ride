package fr.cotedazur.univ.polytech.teamO.pioche;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class PiocheDestinationLongueTest {
     PiocheDestinationLongue piocheDestinationLongue;

    @BeforeEach
    void setUp() {
        // Initialise une nouvelle pioche avant chaque test
        piocheDestinationLongue = new PiocheDestinationLongue();
    }

    @Test
    void testInitPioche() {
        // Test si la pioche est correctement initialisée
        Stack<CarteDestination> cartes = piocheDestinationLongue.getCartesDestinationLongues();
        assertNotNull(cartes, "La pioche ne devrait pas être null après l'initialisation");
        assertEquals(34, cartes.size(), "La pioche devrait contenir exactement 34 cartes après l'initialisation");
    }

    @Test
    void testPopCarteDestinationLongue() {
        // Test la méthode pop pour retirer une carte
        int tailleInitiale = piocheDestinationLongue.getCartesDestinationLongues().size();
        CarteDestination carte = piocheDestinationLongue.popCartesDestinationLongue();
        assertNotNull(carte, "La carte retirée ne devrait pas être null");
        assertEquals(tailleInitiale - 1, piocheDestinationLongue.getCartesDestinationLongues().size(),
                "La taille de la pioche devrait diminuer de 1 après un pop");
    }

    @Test
    void testPopCarteDestinationLonguePiocheVide() {
        // Vider complètement la pioche et tester l'exception
        while (!piocheDestinationLongue.getCartesDestinationLongues().isEmpty()) {
            piocheDestinationLongue.popCartesDestinationLongue();
        }
        assertThrows(IllegalArgumentException.class,
                () -> piocheDestinationLongue.popCartesDestinationLongue(),
                "Une exception doit être levée lorsque l'on pop une carte d'une pioche vide");
    }


    @Test
    void testValidePioche() {
        // Test de la méthode valide pour une pioche complète
        assertTrue(piocheDestinationLongue.valide(),
                "La pioche devrait être valide juste après son initialisation");

        // Retirer une carte et tester à nouveau
        piocheDestinationLongue.popCartesDestinationLongue();
        assertFalse(piocheDestinationLongue.valide(),
                "La pioche ne devrait plus être valide après avoir retiré une carte");
    }
}