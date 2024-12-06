package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    Robot robot1;
    Robot robot2;
    Score score1 = new Score(robot1);
    Score score2 = new Score(robot2);

    @Test
    void testConstructeur(){
        assertEquals(0,score1.getNbPoints());
        assertEquals(0, score2.getNbPoints());
        score1.addNbPoints(66);
        score2.addNbPoints(-66);
        assertEquals(66, score1.getNbPoints());
        assertEquals(0, score2.getNbPoints());
    }


}