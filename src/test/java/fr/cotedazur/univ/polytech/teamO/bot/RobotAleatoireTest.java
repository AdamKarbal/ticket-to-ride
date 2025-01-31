package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.EmptyStackException;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class RobotAleatoireTest {
    RobotAleatoire robot;
    CarteWagon carteBleue;
    CarteWagon carteJaune;
    CarteWagon carteRouge;
    int taillePiocheInit;
    Route routeBleue;
    Robot robotRouge;
    MoteurDeJeu moteurDeJeu;
    PiocheSurTable piocheSurTable;
    Pioche pioche;
    PiocheDestinationLongue piocheDestinationLongue;
    PiocheDestinationCourte piocheDestinationCourte;
    Trajet trajet;
    GameMap map;
    @Mock Random random = mock(Random.class);

    @BeforeEach
    void setUp() {
        robot=new RobotAleatoire(Couleur.Bleu, random);
        pioche= new Pioche();
        pioche.creerPiocheComplete();
        piocheSurTable=new PiocheSurTable();
        robotRouge=new Robot(Couleur.Rouge);
        carteBleue = new CarteWagon(Couleur.Bleu);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        piocheDestinationCourte=new PiocheDestinationCourte();
        piocheDestinationLongue=new PiocheDestinationLongue();
        moteurDeJeu = new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        taillePiocheInit= moteurDeJeu.getPioche().size();
        routeBleue= new Route(1,Couleur.Bleu);
        trajet= new Trajet(Ville.Leipzig,Ville.Chemnitz,routeBleue);
        map = new GameMap();
    }

    @Test
    void testChooseAction() throws NotEnoughWagonCard {
        robot=new RobotAleatoire(Couleur.Bleu, random);
        int taille = pioche.size();
        for(int i=0;i<taille;i++) robot.piocher(moteurDeJeu);
        when(random.nextInt(anyInt())).thenReturn(21);
        robot.chooseaction(moteurDeJeu,map);
    }

    @Test
    void testChooseActionPosePioche() throws NotEnoughWagonCard, EmptyStackException {
        RobotAleatoire robotSpy = spy(robot);
        when(random.nextInt(100)).thenReturn(10); // 10 → doit piocher
        robotSpy.chooseaction(moteurDeJeu, map);
        verify(robotSpy, times(2)).piocher(moteurDeJeu);
    }

    @Test
    void testChooseActionPoste() throws NotEnoughWagonCard, EmptyStackException{
        RobotAleatoire robotSpy= spy(robot);
        int taille = pioche.size();
        for(int i=0; i<taille; i++){
            robotSpy.piocher(moteurDeJeu);
        }
        when(random.nextInt(100)).thenReturn(40);
        robotSpy.chooseaction(moteurDeJeu,map);
        verify(robotSpy, times(1)).poseTrainAleatoire(moteurDeJeu,map);
    }

    @Test
    void testChooseActionPiocheTable() throws NotEnoughWagonCard, EmptyStackException{
        /*RobotAleatoire robotSpy = spy(robot);
        when(random.nextInt(100)).thenReturn(88);
        when(random.nextInt(moteurDeJeu.getPiocheSurTable().size())).thenReturn(0);
        CarteWagon carteMock = mock(CarteWagon.class);
        when(moteurDeJeu.getPiocheSurTable().carteAtPosI(0)).thenReturn(carteMock);
        robotSpy.chooseaction(moteurDeJeu,map);
        verify(robotSpy, times(1)).piocherSurTable(carteMock,moteurDeJeu);*/
    }

    @Test
    void testChooseDestination(){
        RobotAleatoire robotSpy = spy(robot);
        MoteurDeJeu moteurDeJeuSpy = spy(moteurDeJeu);
        when(random.nextInt(2)).thenReturn(0);
        robotSpy.chooseDestination(moteurDeJeu);
       // verify(moteurDeJeuSpy, atLeastOnce()).giveDestinationsCard(robotSpy,1);
    }

    @Test
    void testRefuseDestination(){
        MoteurDeJeu moteurDeJeuSpy = spy(moteurDeJeu);
        when(random.nextInt(2)).thenReturn(0);
        robot.init(moteurDeJeuSpy);
        verify(moteurDeJeuSpy, times(4)).removeDestinationsCard(robot,0);
    }



}
