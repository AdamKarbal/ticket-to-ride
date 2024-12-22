package fr.cotedazur.univ.polytech.teamO.plateau;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.Ville;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class MapTest {

    Map map;
    GestionEtatJeu gestionEtatJeu;
    //Initialisation

    @BeforeEach
    public void testInit(){
        map = new Map();
        gestionEtatJeu = new GestionEtatJeu(map);
    }

    @Test
    void testVoisin(){
        assertTrue(gestionEtatJeu.isVoisin(Ville.Berlin,Ville.Hamburg));
    }

    @Test
    void villeDispos(){
        assertEquals(map.getVillesFrom(Ville.Ulm).size(),3);
        assertEquals(map.getVillesFrom(Ville.Ulm),new ArrayList<>(Arrays.asList(Ville.Stuttgart,Ville.Augsburg,Ville.Lindau)));
    }

    @Test
    void trajetsExistants(){
        System.out.println(map);
    }
}
