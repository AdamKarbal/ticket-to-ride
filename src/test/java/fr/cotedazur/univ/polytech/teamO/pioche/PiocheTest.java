package fr.cotedazur.univ.polytech.teamO.pioche;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PiocheTest {
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    ArrayList<CarteWagon> carteSurTablee;

    @BeforeEach
    void startup(){
        pioche = new Pioche();
        piocheSurTable = new PiocheSurTable(carteSurTablee);
    }

    @Test
    void testPioche(){
        System.out.print(pioche.getPioche());
    }


    /*void testCarteSurTable(){
        System.out.println(pioche.mettre5cartesurlatable(carteSurTable));
    }*/
}
