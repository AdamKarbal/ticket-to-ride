package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RobotAleatoireTest {
    Robot robot;
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

    @BeforeEach
    void setUp() {
        robot=new Robot(Couleur.Bleu);
        pioche= new Pioche();
        piocheSurTable=new PiocheSurTable();
        robotRouge=new Robot(Couleur.Rouge);
        carteBleue = new CarteWagon(Couleur.Bleu);
        carteJaune = new CarteWagon(Couleur.Jaune);
        carteRouge = new CarteWagon(Couleur.Rouge);
        //moteurDeJeu = new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        taillePiocheInit= moteurDeJeu.getPioche().size();
        routeBleue= new Route(1,Couleur.Bleu);
        trajet= new Trajet(Ville.Leipzig,Ville.Chemnitz,routeBleue);
    }

    @Test
    void testChooseAction(){}
}
