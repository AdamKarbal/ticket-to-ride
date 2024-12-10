package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RouteTest {

    Route route = new Route(3, Couleur.Noir);
    Route route2 = new Route(6, Couleur.Blanc);
    Route route3 = new Route(3, Couleur.Blanc);

    Wagon wagon=new Wagon(Couleur.Noir);
    Wagon wagon2=new Wagon(Couleur.Blanc);

    @Test
    void testConstructorRoute(){
        assertThrows(IllegalArgumentException.class,() -> new Route(-6,Couleur.Noir));
        assertThrows(NullPointerException.class,() -> new Route(3,null));
        assertEquals(Couleur.Noir, route.getCouleur());
        assertTrue(route.isClaimable(4,wagon));
        assertFalse(route2.isClaimable(3,wagon));
        assertTrue(route3.isClaimable(6,wagon2));
        assertEquals(Couleur.Blanc, route2.getCouleur());
        assertEquals(3,route.getNbCases());
        assertEquals(6,route2.getNbCases());


    }


}