package fr.cotedazur.univ.polytech.teamO.piocheTest;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import fr.cotedazur.univ.polytech.teamO.pioche.MainJoueur;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteSurTable

import java.util.ArrayList;

public class PiocheTest {
    Pioche pioche;
    CarteSurTable carteSurTable;
    ArrayList<CarteWagon> carteSurTablee;

    @BeforeEach
    void startup(){
        pioche = new Pioche();
        carteSurTable = new CarteSurTable(carteSurTablee);
    }

    @Test
    void testPioche(){
        System.out.print(pioche.getPioche());
    }

    @Test
    void testCarteSurTable(){
        System.out.println(pioche.mettre5cartesurlatable());
    }
}
