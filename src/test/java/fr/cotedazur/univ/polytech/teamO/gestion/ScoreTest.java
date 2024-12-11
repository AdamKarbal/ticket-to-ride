package fr.cotedazur.univ.polytech.teamO.gestion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    Score score1 = new Score();
    Score score2 = new Score();

    @Test
    void testConstructeur(){
        assertEquals(0,score1.getNbPoints());
        assertEquals(0, score2.getNbPoints());
        score1.addNbPoints(66);
        score2.addNbPoints(-66);
        assertEquals(66, score1.getNbPoints());
        assertEquals(-66, score2.getNbPoints());
    }
    @Test
    void testCompareTo(){
        assertEquals(0,score1.compareTo(score2));
        score1.addNbPoints(66);
        score2.addNbPoints(-66);
        assertEquals(1,score1.compareTo(score2));
    }


}