package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarteDestinationTest {

    Ville villeAugsburg = Ville.Augsburg;
    Ville ville2= Ville.Berlin;
    CarteDestination carteAB;
    CarteDestination carteAB2;
    CarteDestination carteAB3;

        @BeforeEach
        void setUp() {
             carteAB = new CarteDestination(Couleur.Marron, villeAugsburg, ville2, 15);
             carteAB2 =new CarteDestination(Couleur.Bleu, villeAugsburg, ville2, 5);
             carteAB3 =new CarteDestination(Couleur.Marron, villeAugsburg, ville2, 20);
        }

        @Test
        public void testConstructeurValide_Longue() {


            assertEquals(Couleur.Marron, carteAB.getCouleur());
            assertTrue(carteAB.isLongue());
            assertEquals(villeAugsburg, carteAB.getVille1());
            assertEquals(ville2, carteAB.getVille2());
            assertEquals(15, carteAB.getValue());
        }

        @Test
        void testConstructeurValide_Courte() {

            assertEquals(Couleur.Bleu, carteAB2.getCouleur());
            assertFalse(carteAB2.isLongue());
            assertEquals(villeAugsburg, carteAB2.getVille1());
            assertEquals(ville2, carteAB2.getVille2());
            assertEquals(5, carteAB2.getValue());
        }

        @Test
        void testConstructeurInvalide_CouleurLongue() {
            assertThrows(IllegalArgumentException.class, () -> {
                new CarteDestination(Couleur.Bleu, villeAugsburg, ville2, 15);
            }, "Couleur Invalide");
        }

        @Test
        void testConstructeurInvalide_CouleurCourte() {
            assertThrows(IllegalArgumentException.class, () -> {
                new CarteDestination(Couleur.Marron, villeAugsburg, ville2, 5);
            }, "Couleur Invalide");
        }

        @Test
        void testConstructeurInvalide_ValeurLongue() {
            assertThrows(IllegalArgumentException.class, () -> {
                new CarteDestination(Couleur.Marron, villeAugsburg, ville2, 10);
            }, "Valeur invalide");
        }

        @Test
        void testConstructeurInvalide_ValeurCourte() {
            assertThrows(IllegalArgumentException.class, () -> {
                new CarteDestination(Couleur.Bleu, villeAugsburg, ville2, 12);
            }, "Valeur invalide");
        }

        @Test
        void testGetNom_Longue() {
            String expected = "Carte destination longue: de Augsburg à Berlin";

            assertEquals(expected, carteAB.getNom());
        }

        @Test
        void testGetNom_Courte() {
            String expected = "Carte destination courte: de Augsburg à Berlin";

            assertEquals(expected, carteAB2.getNom());
        }

        @Test
        void testAccesseurs() {
            assertEquals(Couleur.Marron, carteAB3.getCouleur());
            assertTrue(carteAB3.isLongue());
            assertEquals(villeAugsburg, carteAB3.getVille1());
            assertEquals(ville2, carteAB3.getVille2());
            assertEquals(20, carteAB3.getValue());
        }
        @Test

        void testIsCompleted(){
            assertFalse(carteAB.isCompleted());
            carteAB.setCompleted(true);
            assertTrue(carteAB.isCompleted());
        }

}