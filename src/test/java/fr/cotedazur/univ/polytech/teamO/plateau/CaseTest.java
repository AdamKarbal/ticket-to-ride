package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    Case c1 = new Case(Couleur.Default);
    @Test
    void testConstructeurCase() {
        assertEquals(Couleur.Default, c1.getCouleur());
        assertTrue(c1.isAvailable());
        c1.setAvailable(false);
        assertFalse(c1.isAvailable());
    }


}