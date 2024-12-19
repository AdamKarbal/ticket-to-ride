package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Ville;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarteDestinationTest {

    Ville ville1= Ville.Augsburg;
    Ville ville2= Ville.Berlin;



        @Test
        public void testConstructeurValide_Longue() {
            CarteDestination carte = new CarteDestination(Couleur.Marron, true, ville1, ville2, 15);

            assertEquals(Couleur.Marron, carte.getCouleur());
            assertTrue(carte.isLongue());
            assertEquals(ville1, carte.getVille1());
            assertEquals(ville2, carte.getVille2());
            assertEquals(15, carte.getValue());
        }

        @Test
        public void testConstructeurValide_Courte() {
            CarteDestination carte = new CarteDestination(Couleur.Bleu, false, ville1, ville2, 5);

            assertEquals(Couleur.Bleu, carte.getCouleur());
            assertFalse(carte.isLongue());
            assertEquals(ville1, carte.getVille1());
            assertEquals(ville2, carte.getVille2());
            assertEquals(5, carte.getValue());
        }

        @Test
        public void testConstructeurInvalide_CouleurLongue() {
            assertThrows(IllegalArgumentException.class, () -> {
                new CarteDestination(Couleur.Bleu, true, ville1, ville2, 15);
            }, "Couleur Invalide");
        }

        @Test
        public void testConstructeurInvalide_CouleurCourte() {
            assertThrows(IllegalArgumentException.class, () -> {
                new CarteDestination(Couleur.Marron, false, ville1, ville2, 5);
            }, "Couleur Invalide");
        }

        @Test
        public void testConstructeurInvalide_ValeurLongue() {
            assertThrows(IllegalArgumentException.class, () -> {
                new CarteDestination(Couleur.Marron, true, ville1, ville2, 10);
            }, "Valeur invalide");
        }

        @Test
        public void testConstructeurInvalide_ValeurCourte() {
            assertThrows(IllegalArgumentException.class, () -> {
                new CarteDestination(Couleur.Bleu, false, ville1, ville2, 12);
            }, "Valeur invalide");
        }

        @Test
        public void testGetNom_Longue() {

            CarteDestination carte = new CarteDestination(Couleur.Marron, true, ville1, ville2, 15);
            String expected = "Carte destination longue: de Augsburg à Berlin";

            assertEquals(expected, carte.getNom());
        }

        @Test
        public void testGetNom_Courte() {
            CarteDestination carte = new CarteDestination(Couleur.Bleu, false, ville1, ville2, 7);
            String expected = "Carte destination courte: de Augsburg à Berlin";

            assertEquals(expected, carte.getNom());
        }

        @Test
        public void testAccesseurs() {
            CarteDestination carte = new CarteDestination(Couleur.Marron, true, ville1, ville2, 20);

            assertEquals(Couleur.Marron, carte.getCouleur());
            assertTrue(carte.isLongue());
            assertEquals(ville1, carte.getVille1());
            assertEquals(ville2, carte.getVille2());
            assertEquals(20, carte.getValue());
        }
}