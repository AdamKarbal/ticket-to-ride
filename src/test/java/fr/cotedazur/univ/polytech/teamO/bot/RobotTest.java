package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Gestionnaire;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {
    Robot robot;
    CarteWagon carteBleue;
    CarteWagon carteJaune;
    CarteWagon carteRouge;
    int taillePiocheInit;
    Route routeBleue;
    @BeforeEach
    void setUp() {
        robot=new Robot(Couleur.Bleu);
        carteBleue = new CarteWagon(Couleur.Bleu);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        taillePiocheInit=Gestionnaire.pioche.getPioche().size();
        routeBleue= new Route(2,Couleur.Bleu);
    }
    @Test
    void testPioche() {
        robot.piocher();
        assertEquals(Gestionnaire.pioche.getPioche().size(), taillePiocheInit -1);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
    }

    @Test
    void testPiocheSurTable() {
        assertEquals(Gestionnaire.piocheSurTable.getMainDeCartes().size(),5);
        robot.piocherSurTable(Gestionnaire.piocheSurTable.carteAtPosI(0));
        assertEquals(Gestionnaire.piocheSurTable.getMainDeCartes().size(),5);
        assertEquals(robot.getMain().getMainDeCartes().size(),1);
        assertEquals(Gestionnaire.pioche.getPioche().size(),taillePiocheInit-1);
    }
    @Test
    void wagonByCouleur() {
        for(int i=0;i<10;i++){
            robot.piocher();
        }
        assertEquals(Gestionnaire.pioche.getPioche().size(),taillePiocheInit-10);
        System.out.println(robot.getWagonByCouleur());
    }
    @Test
    void poseTrain(){
        for(int i=0;i<10;i++){
            robot.piocher();
        }
        System.out.println(robot.getWagonByCouleur());
        robot.poseTrain(routeBleue); // je sais pas trop comment manip le test car ça dépend ce que je pioche
        assertEquals(routeBleue.isAvailable(),false);
    }


}