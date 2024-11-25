package fr.cotedazur.univ.polytech.teamO.piocheTest;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import fr.cotedazur.univ.polytech.teamO.pioche.MainJoueur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;

import java.util.ArrayList;

public class MainJoueurTest {
     CarteWagon CarteWagonNoir;
     CarteWagon CarteWagonJaune;
     CarteWagon CarteWagonRouge;
     CarteWagon CarteWagonViolet;
     CarteWagon CarteWagonBlanc;
     MainJoueur MainJoueur1;
     ArrayList<CarteWagon> main = new ArrayList<>();

     @BeforeEach
    void startup(){
         CarteWagonNoir = new CarteWagon(Couleur.NOIR);
         CarteWagonJaune = new CarteWagon(Couleur.JAUNE);
         CarteWagonRouge = new CarteWagon(Couleur.ROUGE);
         CarteWagonViolet = new CarteWagon(Couleur.VIOLET);
         CarteWagonBlanc = new CarteWagon(Couleur.BLANC);
         MainJoueur1 = new MainJoueur(main);
     }

     @Test
    void testAddCarte(){
         MainJoueur1.addCarte(CarteWagonNoir);
         MainJoueur1.addCarte(CarteWagonRouge);
         MainJoueur1.addCarte(CarteWagonJaune);
         MainJoueur1.addCarte(CarteWagonViolet);
         MainJoueur1.addCarte(CarteWagonBlanc);
         System.out.println(MainJoueur1.getMainDeCartes());
     }

     @Test
     void testContainCarte(){
         MainJoueur1.addCarte(CarteWagonNoir);
         MainJoueur1.addCarte(CarteWagonRouge);
         MainJoueur1.addCarte(CarteWagonJaune);
         MainJoueur1.addCarte(CarteWagonViolet);
         MainJoueur1.addCarte(CarteWagonBlanc);
         assertTrue(MainJoueur1.containCarte(CarteWagonNoir));
     }

     @Test
    void testCarteAtPosI(){
         MainJoueur1.addCarte(CarteWagonNoir);
         MainJoueur1.addCarte(CarteWagonRouge);
         MainJoueur1.addCarte(CarteWagonJaune);
         MainJoueur1.addCarte(CarteWagonViolet);
         MainJoueur1.addCarte(CarteWagonBlanc);
         System.out.println(MainJoueur1.carteAtPosI(2));
     }

     @Test
    void testRemoveCarte(){
         MainJoueur1.addCarte(CarteWagonNoir);
         MainJoueur1.addCarte(CarteWagonRouge);
         MainJoueur1.addCarte(CarteWagonJaune);
         MainJoueur1.addCarte(CarteWagonViolet);
         MainJoueur1.addCarte(CarteWagonBlanc);
         MainJoueur1.removeCarte(CarteWagonNoir);
         System.out.println(MainJoueur1.getMainDeCartes());
     }
}
