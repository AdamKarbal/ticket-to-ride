package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    Pioche pioche;
    Robot robot;
    @BeforeEach
    void setUp() {
        pioche = new Pioche();
        robot=new Robot(2, Couleur.Bleu);
    }
    @Test
    void testPioche() {
        System.out.println(pioche.getPioche().size());
        robot.jPioche(pioche);
        System.out.println(robot.getMain());
        System.out.println(pioche.getPioche().size());
    }

}