package fr.cotedazur.univ.polytech.teamO.affichageTest;

import fr.cotedazur.univ.polytech.teamO.affichage.Affichage;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.Carte;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AffichageTest {
    private Affichage affichage;
    private Robot robot;
    private CarteWagon carteWagonBleu;
    private CarteDestination carteDestinationAugsburgBerlin;
    private Ville villeAugsburg = Ville.Augsburg;
    private Ville villeBerlin = Ville.Berlin;

    @BeforeEach
    void setUp() {
        affichage = new Affichage();
        robot = new Robot(Couleur.Marron);
        carteDestinationAugsburgBerlin = new CarteDestination(Couleur.Noir, villeAugsburg, villeBerlin, 15);
    }

    @Test
    void testAffichePiocher() {
        String expected = "Le  à piocher la carte :  dans la pioche invisible.";
        String actual = affichage.affichePiocher(robot, carteWagonBleu);
        assertEquals(expected, actual);
    }

    @Test
    void testAffichePiocherSurTable() {
        String expected = "Le  à piocher la carte :  dans la pioche visible.";
        String actual = affichage.affichePiocherSurTable(robot, carteWagonBleu);
        assertEquals(expected, actual);
    }

    @Test
    void testAfficheCheckCarteDestinationOK() {
        String expected = "Le  à réussie la carte destination : Marron entre Augsburg et Berlin et ajoute 15 au score.";
        String actual = affichage.afficheCheckCarteDestinationOK(robot, carteDestinationAugsburgBerlin);
        assertEquals(expected, actual);
    }
}
