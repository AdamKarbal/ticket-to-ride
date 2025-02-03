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
        pioche.creerPiocheComplete();
        piocheSurTable = new PiocheSurTable();
    }

    @Test
    void testPioche(){
        System.out.print(pioche.getPioche());
    }

}
