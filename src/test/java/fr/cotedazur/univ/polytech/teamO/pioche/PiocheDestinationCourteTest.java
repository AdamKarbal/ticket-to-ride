package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class PiocheDestinationCourteTest {
    PiocheDestinationCourte piocheDestinationCourte;

    @BeforeEach
    void setUp() {
        piocheDestinationCourte = new PiocheDestinationCourte();
    }

    @Test
    void testInitialisationPioche() {
        // Vérifie si la pile est correctement initialisée
        Stack<CarteDestination> cartes = piocheDestinationCourte.getCartesDestinationCourtes();
        assertEquals(55, cartes.size(), "La pioche devrait contenir 55 cartes au départ");
    }

    @Test
    void testPeekCartesDestinationCourtes() {
        // Vérifie si la méthode peek retourne correctement la carte du haut
        CarteDestination topCard = piocheDestinationCourte.peekCartesDestinationCourtes();
        assertNotNull(topCard, "La carte du dessus ne devrait pas être null");
        assertEquals(Couleur.Bleu, topCard.getCouleur(), "La couleur de la carte devrait être Bleue");
    }

    @Test
    void testPopCartesDestinationCourtes() {
        // Teste si pop enlève correctement la carte et la retourne
        int tailleAvant = piocheDestinationCourte.getCartesDestinationCourtes().size();
        CarteDestination poppedCard = piocheDestinationCourte.popCartesDestinationCourtes();

        assertNotNull(poppedCard, "La carte retirée ne devrait pas être null");
        assertEquals(tailleAvant - 1, piocheDestinationCourte.getCartesDestinationCourtes().size(),
                "La taille de la pioche devrait diminuer de 1 après un pop");
    }

    @Test
    void testPopCartesDestinationCourtes_Vide() {
        // Teste l'exception lorsque la pile est vide
        while (!piocheDestinationCourte.getCartesDestinationCourtes().isEmpty()) {
            piocheDestinationCourte.popCartesDestinationCourtes();
        }

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            piocheDestinationCourte.popCartesDestinationCourtes();
        });

        assertEquals("Pile vide", exception.getMessage());
    }

    @Test
    void testValide() {
        // Teste la méthode valide avec une pioche remplie
        assertTrue(piocheDestinationCourte.valide(), "La pioche devrait être valide au début");
        piocheDestinationCourte.popCartesDestinationCourtes();
        assertFalse(piocheDestinationCourte.valide(), "La pioche ne devrait plus être valide après un pop");
    }

    @Test
    void testPushCartesDestinationCourtes() {
        // Teste l'ajout d'une carte
        CarteDestination nouvelleCarte = new CarteDestination(Couleur.Bleu, Ville.Berlin, Ville.Hamburg, 5);
        piocheDestinationCourte.pushCartesDestinationCourtes(nouvelleCarte);

        assertEquals(56, piocheDestinationCourte.getCartesDestinationCourtes().size(),
                "La taille de la pioche devrait augmenter de 1 après un push");
        assertEquals(nouvelleCarte, piocheDestinationCourte.peekCartesDestinationCourtes(),
                "La carte ajoutée devrait être en haut de la pile");
    }

}