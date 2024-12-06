package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RouteTest {

    Route<Case> route = new Route(3, Couleur.Default);
    Route<Case> route2 = new Route(6, Couleur.Blanc);
    Route<Case> route3 = new Route(3, Couleur.Default);

    @BeforeEach
            void setUp() {
        route.addCases();
        route2.addCases();
        route3.addCases();

    }
    @Test
    void testCheckLengthRouteThrowsException() {
        assertEquals(3,route.getCases().size());
        System.out.println("Elements de la liste : "+route.getCases());

    }
    @Test
    void testConstructorRoute(){
        System.out.println(route.getCases());
        assertEquals(3, route.getCases().size());
        assertEquals(Couleur.Default, route.getCouleur());
        assertEquals(6, route2.getCases().size());
        assertEquals(Couleur.Blanc, route2.getCouleur());
        assertEquals(3,route.getNbCases());
        assertEquals(6,route2.getNbCases());


    }
    @Test
    void testAvailable(){
        assertTrue(route.isAvailable());
        assertTrue(route2.isAvailable());
        assertTrue(route3.isAvailable());
    }


}