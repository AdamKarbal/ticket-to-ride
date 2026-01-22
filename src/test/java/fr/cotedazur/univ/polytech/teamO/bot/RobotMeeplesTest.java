package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheDestinationCourte;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheDestinationLongue;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotMeeplesTest {
    RobotMeeples bot = new RobotMeeples(Couleur.Bleu);

    @BeforeEach
    void Setup() {
        GameMap map = new GameMap();
        PiocheDestinationCourte PDC = new PiocheDestinationCourte();
        PiocheDestinationLongue PDL = new PiocheDestinationLongue();
        Pioche pioche = new Pioche();
        PiocheSurTable PST = new PiocheSurTable();
        MoteurDeJeu moteur = new MoteurDeJeu(pioche, PST, PDL, PDC);
    }

    @Test
    void StackTakenMeeplesTest(){
        StringBuilder sb = new StringBuilder();
        //bot.StackTakenMeeples();
        sb.append("Pile ordonnée selon le nombre de Meeple par couleur : \n");

    }
}
