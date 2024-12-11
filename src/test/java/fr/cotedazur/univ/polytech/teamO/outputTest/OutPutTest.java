package fr.cotedazur.univ.polytech.teamO.outputTest;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Gestionnaire;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.output.Output;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class OutPutTest {
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    ArrayList<Robot> robots;
    Score score1;
    Score score2;
    Score score3;
    Score score4;
    Ranking classement;
    Output output;

    @BeforeEach
    void SetUp() throws EmptyListException {
        pioche = new Pioche();
        piocheSurTable=new PiocheSurTable();
        pioche.mettre5cartesurlatable(piocheSurTable);
        robots = new ArrayList<>();
        score1 = new Score();
        score2 = new Score();
        score3 = new Score();
        score4 = new Score();
        score1.addNbPoints(20);
        score2.addNbPoints(10);
        score3.addNbPoints(15);
        score4.addNbPoints(60);

        Robot Un = new Robot(score1, Couleur.Bleu);
        Robot Deux = new Robot(score2, Couleur.Rouge);
        Robot Trois = new Robot(score3, Couleur.Jaune);
        Robot Quatre = new Robot(score4, Couleur.Noir);
        robots.add(Un);
        robots.add(Deux);
        robots.add(Trois);
        robots.add(Quatre);
        classement = new Ranking(robots);
        output = new Output(classement);
    }

    @Test
    void displayTest(){
        output.display();
    }
}
