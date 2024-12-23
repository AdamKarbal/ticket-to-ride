package fr.cotedazur.univ.polytech.teamO.rankingTest;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class RankingTest {
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    ArrayList<Robot> robots;
    Ranking classement;


    @BeforeEach
    void SetUp() throws EmptyListException {
        pioche = new Pioche();
        piocheSurTable=new PiocheSurTable();
        robots = new ArrayList<>();


        Robot Un = new Robot(Couleur.Bleu);
        Robot Deux = new Robot(Couleur.Rouge);
        Robot Trois = new Robot(Couleur.Jaune);
        Robot Quatre = new Robot(Couleur.Noir);

        Un.getScore().addNbPoints(0);
        Deux.getScore().addNbPoints(0);
        Trois. getScore().addNbPoints(43);
        Quatre.getScore().addNbPoints(17);

        robots.add(Un);
        robots.add(Deux);
        robots.add(Trois);
        robots.add(Quatre);
        classement = new Ranking(robots);
    }

    @Test
    void toStringTest() {
        System.out.println(classement);
    }

}
