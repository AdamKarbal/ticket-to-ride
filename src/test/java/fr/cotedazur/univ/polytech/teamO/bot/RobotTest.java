package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    Robot robot;
    CarteWagon carteBleue;
    CarteWagon carteJaune;
    CarteWagon carteRouge;
    int taillePiocheInit;
    Route routeBleue;
    Robot robotRouge;
    MoteurDeJeu moteurDeJeu;
    @BeforeEach
    void setUp() {
        robot=new Robot(Couleur.Bleu);
        robotRouge=new Robot(Couleur.Rouge);
        carteBleue = new CarteWagon(Couleur.Bleu);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        moteurDeJeu = new MoteurDeJeu();
        taillePiocheInit= moteurDeJeu.getPioche().size();
        routeBleue= new Route(1,Couleur.Bleu);
    }
    @Test
    void equal(){
        assertEquals(robot,robot);
        assertNotEquals(robot,robotRouge);
    }
    @Test
    void testPioche() {
        robot.piocher(moteurDeJeu);
        assertEquals(moteurDeJeu.getPioche().size(), taillePiocheInit -1);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
    }

    @Test
    void testPiocheSurTable() {
        assertEquals(moteurDeJeu.getPiocheSurTable().getMainDeCartes().size(),5);
        robot.piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(0), moteurDeJeu);
        assertEquals(moteurDeJeu.getPiocheSurTable().getMainDeCartes().size(),5);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
        assertEquals(moteurDeJeu.getPioche().size(),taillePiocheInit-1);
    }
    @Test
    void wagonByCouleur() {
        for(int i=0;i<10;i++){
            robot.piocher(moteurDeJeu);
        }
        assertEquals(moteurDeJeu.getPioche().size(),taillePiocheInit-10);
        System.out.println(robot.getWagonByCouleur());
    }
    @Test
    void poseTrain(){
        for(int i=0;i<10;i++){
            robot.piocher(moteurDeJeu);
        }
        System.out.println(robot.getWagonByCouleur());
        robot.poseTrain(routeBleue, moteurDeJeu); // je sais pas trop comment manip le test car ça dépend ce que je pioche
        assertEquals(routeBleue.isAvailable(),false);
    }


}