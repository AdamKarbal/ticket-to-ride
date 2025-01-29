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
        pioche.creerPiocheComplete();
        piocheSurTable=new PiocheSurTable();
        robots = new ArrayList<>();


        Robot Bleu = new Robot(Couleur.Bleu);
        Robot Rouge = new Robot(Couleur.Rouge);
        Robot Jaune = new Robot(Couleur.Jaune);
        Robot Noir = new Robot(Couleur.Noir);

        Bleu.getScore().addNbPoints(0);
        Rouge.getScore().addNbPoints(0);
        Jaune. getScore().addNbPoints(43);
        Noir.getScore().addNbPoints(17);

        robots.add(Bleu);
        robots.add(Rouge);
        robots.add(Jaune);
        robots.add(Noir);
        classement = new Ranking(robots);
    }

    @Test
    void toStringTest() {
        System.out.println(classement);
    }

}
