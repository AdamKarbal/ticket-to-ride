package fr.cotedazur.univ.polytech.teamO.affichageTest;

import fr.cotedazur.univ.polytech.teamO.affichage.Affichage;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AffichageTest {
    private Affichage affichage;
    private Robot robot;
    private CarteWagon carteWagonBleu;
    private CarteDestination CDBA;
    private Route route = new Route(3, Couleur.Rouge);
    private Trajet trajet = new Trajet(Ville.Berlin, Ville.Magdeburg, route);

    @BeforeEach
    void setUp() {
        affichage = new Affichage();
        robot = new Robot(Couleur.Marron);
        CDBA = new CarteDestination(Couleur.Marron, Ville.Augsburg, Ville.Berlin, 15);
        carteWagonBleu = new CarteWagon(Couleur.Bleu);
    }

    @Test
    void testAffichePiocher() {
        String expected = "Le Marron a pioché la CarteWagon de couleur Bleu dans la pioche invisible.\n";
        String actual = affichage.affichePiocher(robot, carteWagonBleu);
        assertEquals(expected, actual);
    }

    @Test
    void testAffichePiocherSurTable() {
        String expected = "Le Marron a pioché la CarteWagon de couleur Bleu dans la pioche visible.\n";
        String actual = affichage.affichePiocherSurTable(robot, carteWagonBleu);
        assertEquals(expected, actual);
    }

    @Test
    void testAfficheCheckCarteDestinationOK() {
        String expected = "Le Marron a réussi la Carte destination longue entre Augsburg et Berlin et ajoute 15 à son score.\n";
        String actual = affichage.afficheCheckCarteDestinationOK(robot, CDBA);
        assertEquals(expected, actual);
    }

    @Test
    void afficheIsFinishedTest(){
        String expected = "Il reste deux wagons ou moins à Marron. Le jeu est donc arrivé à son terme. \n";
        String actual = affichage.afficheIsFinished(robot);
        assertEquals(expected, actual);
    }

    @Test
    void afficheTakeMeeplesTest(){
        String expected = "Marron prends 3 passagers de couleur Rouge.\n";
        String actual = affichage.afficheTakeMeeples(robot, 3, MCouleur.Rouge);
        assertEquals(expected, actual);
    }

    @Test
    void affichePoseTrainTest(){
        String expected = "Marron a posé un train de 3 wagons entre Berlin et Magdeburg.\n";
        String actual = affichage.affichePoseTrain(robot, trajet);
        assertEquals(expected, actual);
    }

}
