package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableauDesScoresTest {
    Route routeBleu6 = new Route(6, Couleur.Bleu);
    Route routeBleu7 = new Route(7, Couleur.Bleu);

    @Test
    void testTableauDesScores() {
        assertEquals(15,TableauDesScores.getValue(routeBleu6.getNbCases()));
        assertEquals(18,TableauDesScores.getValue(routeBleu7.getNbCases()));


    }

}