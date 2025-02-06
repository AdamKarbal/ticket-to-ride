package fr.cotedazur.univ.polytech.teamO.gestion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    Score score66 = new Score();
    Score scorem66 = new Score();

    @Test
    void testConstructeur(){
        assertEquals(0,score66.getNbPoints());
        assertEquals(0, scorem66.getNbPoints());
        score66.addNbPoints(66);
        scorem66.addNbPoints(-66);
        assertEquals(66, score66.getNbPoints());
        assertEquals(-66, scorem66.getNbPoints());
    }
    
    @Test
    void testRemovePoint(){
        score66.addNbPoints(66);
        score66.removeNbPoints(66);
        assertEquals(0,score66.getNbPoints());
    }


}