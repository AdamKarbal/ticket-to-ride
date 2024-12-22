package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Ville;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TrajetTest {

    Route route = new Route(3, Couleur.Noir);
    Route route2 = new Route(6, Couleur.Blanc);
    Route route3 = new Route(3, Couleur.Blanc);
    Trajet trajet1 = new Trajet(Ville.Frankfurt, Ville.Köln, route);
    Trajet trajet2 = new Trajet(Ville.Erfurt, Ville.Kassel, route2);


    @Test
    void testGetFrom(){
        assertEquals(Ville.Frankfurt, trajet1.getFrom());
        assertEquals(Ville.Erfurt, trajet2.getFrom());
    }

    @Test
    void testGetTo(){
        assertEquals(Ville.Köln, trajet1.getTo());
        assertEquals(Ville.Kassel, trajet2.getTo());
    }

    @Test
    void testGetRoute(){
        assertEquals(route, trajet1.getRoute());
        assertEquals(route2, trajet2.getRoute());
    }

    @Test
    void testToString(){
        assertEquals("Trajet : De Frankfurt à Köln de 3 cases et de couleur Noir.", trajet1.toString());
        assertEquals("Trajet : De Erfurt à Kassel de 6 cases et de couleur Blanc.", trajet2.toString());
    }

}
