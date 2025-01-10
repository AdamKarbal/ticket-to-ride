package fr.cotedazur.univ.polytech.teamO.plateau;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionEtatJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.Ville;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
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

    @Test
    void ajouterTrajetTest() {
            map.ajouterTrajet(Ville.Ulm, Ville.Stuttgart, 2, Couleur.Default );
            map.ajouterTrajet(Ville.Ulm, Ville.Augsburg, 2, Couleur.Rouge );
        assertEquals(111, map.getTrajets().size());
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




}
