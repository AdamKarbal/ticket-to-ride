package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiocheSurTableTest {
    MoteurDeJeu moteurDeJeu;
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    PiocheDestinationCourte piocheDestinationCourte;
    PiocheDestinationLongue piocheDestinationLongue;
    int taille;
    @BeforeEach
    void setUp() {
        pioche = new Pioche();
        pioche.creerPiocheComplete();
        piocheSurTable = new PiocheSurTable();
        piocheDestinationCourte = new PiocheDestinationCourte();
        piocheDestinationLongue = new PiocheDestinationLongue();
        moteurDeJeu=new MoteurDeJeu(pioche,piocheSurTable,piocheDestinationLongue,piocheDestinationCourte);
        taille=pioche.size();
    }

    @Test
    void init(){
        piocheSurTable.initPiocheSurTable(moteurDeJeu);
        assertEquals(moteurDeJeu.getPioche().size(),taille-5);
        assertEquals(moteurDeJeu.getPiocheSurTable().size(),5);
    }

    @Test
    void contains(){
        piocheSurTable.addCarte(new CarteWagon(Couleur.Bleu));
        assertTrue(piocheSurTable.containsCarte(new CarteWagon(Couleur.Bleu)));
    }

    @Test
    void piocher(){
        assertThrows(NullPointerException.class,()->piocheSurTable.piocher(new CarteWagon(Couleur.Bleu)));
        System.out.println(piocheSurTable.toString());
    }

}