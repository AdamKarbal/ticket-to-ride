package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableauDesScoresTest {
    Route route = new Route(6, Couleur.Bleu);
    Route route2 = new Route(7, Couleur.Bleu);

    @Test
    void testTableauDesScores() {
        assertEquals(15,TableauDesScores.getValue(route.getNbCases()));
        assertEquals(18,TableauDesScores.getValue(route2.getNbCases()));


    }

}