package fr.cotedazur.univ.polytech.teamO.wagonTest;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.wagon.Train;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TrainTest{

    private Train train1;
    private Wagon wagon1;
    private Wagon wagon2;
    private Wagon wagon3;
    private ArrayList<Wagon> WagonsList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        wagon1 = new Wagon(Couleur.Bleu);
        wagon2 = new Wagon(Couleur.Bleu);
        wagon3 = new Wagon(Couleur.Bleu);
        WagonsList.add(wagon1);
        WagonsList.add(wagon2);
        WagonsList.add(wagon3);
        train1 = new Train(Couleur.Bleu, WagonsList);
    }

    @Test
    public void toStringTest(){
        System.out.println(train1);
        assertEquals("Un train de 3 wagons de couleur Bleu.", train1.toString());
    }

    @Test
    public void getNbWagonsTest(){
        assertEquals(3, train1.getNbWagons());
    }

    @Test
    public void getCouleurTrainTest(){
        assertEquals(Couleur.Bleu, train1.getCouleurTrain());
    }

}