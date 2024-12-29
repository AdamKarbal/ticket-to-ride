package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RouteTest {

    Route routeNoir3 = new Route(3, Couleur.Noir);
    Route routeBlanc6 = new Route(6, Couleur.Blanc);
    Route routeBlanc3 = new Route(3, Couleur.Blanc);

    Wagon wagon=new Wagon(Couleur.Noir);
    Wagon wagon2=new Wagon(Couleur.Blanc);

    @Test
    void testConstructorRoute(){
        assertThrows(IllegalArgumentException.class,() -> new Route(-6,Couleur.Noir));
        assertThrows(NullPointerException.class,() -> new Route(3,null));
        assertEquals(Couleur.Noir, routeNoir3.getCouleur());
        assertTrue(routeNoir3.isClaimable(4,wagon));
        assertFalse(routeBlanc6.isClaimable(3,wagon));
        assertTrue(routeBlanc3.isClaimable(6,wagon2));
        assertEquals(Couleur.Blanc, routeBlanc6.getCouleur());
        assertEquals(3,routeNoir3.getNbCases());
        assertEquals(6,routeBlanc6.getNbCases());


    }


}