package fr.cotedazur.univ.polytech.teamO.plateau;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class GameMapTest {

    GameMap gameMap;
    GestionEtatJeu gestionEtatJeu;
    //Initialisation

    @BeforeEach
    void testInit(){
        gameMap = new GameMap();
    }

    @Test
    void villeDispos(){
        System.out.print(Ville.values());
        System.out.println(gameMap.getTrajets().get(21));
        assertEquals(gameMap.getVillesFrom(Ville.Ulm).size(),3);
        Map<Ville, Integer> expectedMap = new HashMap<>();
        expectedMap.put(Ville.Stuttgart, 2);
        expectedMap.put(Ville.Augsburg, 1);
        expectedMap.put(Ville.Lindau, 2);

        assertEquals(expectedMap, gameMap.getVillesFrom(Ville.Ulm));
    }


    @Test
    void ajouterTrajetTest() {
        System.out.print(Ville.values());
            gameMap.ajouterTrajet(Ville.Ulm, Ville.Stuttgart, 2, Couleur.Default );
            gameMap.ajouterTrajet(Ville.Ulm, Ville.Augsburg, 2, Couleur.Rouge );
        assertEquals(224, gameMap.getTrajets().size());
    }

    @Test
    void MeeplesTest(){
        int Mcompteur = 0;
        for(Ville ville : Ville.values()){
            Mcompteur += ville.getNbMeeple();
        }
        assertEquals(60, Mcompteur); //Teste que les meeples au total sont bien 60 sur la map
        for(MCouleur couleur : MCouleur.values()){
            assertEquals(0, couleur.getQuant());
        } // Teste que tous les meeples de chaque couleurs sont bien utilisés après leur pose sur le plateau
        assertEquals(4, Ville.Frankfurt.getNbMeeple());
        Ville.Frankfurt.PDisplay();
        assertEquals(3, Ville.Leipzig.getNbMeeple());
        Ville.Leipzig.PDisplay();
    }

    @Test
    void getCorrespTest(){
       /* ArrayList<Trajet> corresp = new ArrayList<Trajet>(gameMap.getCorresp(Ville.Magdeburg));
        //assertTrue(corresp.contains(Ville.Hannover));
        assertTrue(corresp.contains(Ville.Leipzig));
        assertTrue(corresp.contains(Ville.Berlin));*/
    }

}
