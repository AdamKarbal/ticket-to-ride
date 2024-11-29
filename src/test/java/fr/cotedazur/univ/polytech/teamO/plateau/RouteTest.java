package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RouteTest {

    Route route = new Route(3, Couleur.Default);
    Route route2 = new Route(6, Couleur.Default);

    @BeforeEach
            void setUp() {
        route.addAllCases(Arrays.asList(new Case(Couleur.Default),new Case(Couleur.Default),new Case(Couleur.Default)));
        route2.addAllCases(Arrays.asList(new Case(Couleur.Blanc),new Case(Couleur.Default),new Case(Couleur.Default)));
    }

    @Test
    void testConstructorRoute(){
        System.out.println(route.getCases());
        assertEquals(3, route.getCases().size());
        assertEquals(Couleur.Default, route.getCouleur());
        assertTrue(route.validColor());
        assertEquals(3, route2.getCases().size());
        assertEquals(Couleur.Default, route2.getCouleur());
        assertFalse(route2.validColor());
        assertEquals(3,route.getNbCases());
        assertEquals(6,route2.getNbCases());


    }


}