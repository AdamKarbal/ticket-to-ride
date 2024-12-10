package fr.cotedazur.univ.polytech.teamO.rankingTest;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Gestionnaire;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RankingTest {
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    Gestionnaire gestionnaire;
    ArrayList<Robot> robots;
    Score score1;
    Score score2;
    Score score3;
    Score score4;
    Ranking classement;


    @BeforeEach
    void SetUp() throws EmptyListException {
        pioche = new Pioche();
        piocheSurTable=new PiocheSurTable();
        pioche.mettre5cartesurlatable(piocheSurTable);
        gestionnaire= new Gestionnaire(pioche,piocheSurTable);
        robots = new ArrayList<>();
        score1 = new Score();
        score2 = new Score();
        score3 = new Score();
        score4 = new Score();
        score1.addNbPoints(1000);
        score2.addNbPoints(1200);
        score3.addNbPoints(1500);
        score4.addNbPoints(1800);

        Robot Un = new Robot(score1, Couleur.Bleu, gestionnaire);
        Robot Deux = new Robot(score2, Couleur.Rouge, gestionnaire);
        Robot Trois = new Robot(score3, Couleur.Jaune, gestionnaire);
        Robot Quatre = new Robot(score4, Couleur.Noir, gestionnaire);
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
