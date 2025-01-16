package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TrajetTest {

    Route routeNoir3 = new Route(3, Couleur.Noir);
    Route routeBlanc6 = new Route(6, Couleur.Blanc);
    Route routeBlanc3 = new Route(3, Couleur.Blanc);
    Trajet trajetFrankKoln = new Trajet(Ville.Frankfurt, Ville.Köln, routeNoir3);
    Trajet trajetErfurtKassel = new Trajet(Ville.Erfurt, Ville.Kassel, routeBlanc6);


    @Test
    void testGetFrom(){
        assertEquals(Ville.Frankfurt, trajetFrankKoln.getFrom());
        assertEquals(Ville.Erfurt, trajetErfurtKassel.getFrom());
    }

    @Test
    void testGetTo(){
        assertEquals(Ville.Köln, trajetFrankKoln.getTo());
        assertEquals(Ville.Kassel, trajetErfurtKassel.getTo());
    }

    @Test
    void testGetRoute(){
        assertEquals(routeNoir3, trajetFrankKoln.getRoute());
        assertEquals(routeBlanc6, trajetErfurtKassel.getRoute());
    }

    @Test
    void testToString(){
        assertEquals("Trajet : De Frankfurt à Köln de 3 cases et de couleur Noir.", trajetFrankKoln.toString());
        assertEquals("Trajet : De Erfurt à Kassel de 6 cases et de couleur Blanc.", trajetErfurtKassel.toString());
    }

}
