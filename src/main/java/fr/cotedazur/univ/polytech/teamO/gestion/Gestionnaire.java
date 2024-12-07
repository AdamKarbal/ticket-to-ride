package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.pioche.Carte;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;

public class Gestionnaire {
    // Ajouter ici tous les trucs relatifs au jeu et qu'on peut modifier pendant la partie
    private Pioche pioche;
    private PiocheSurTable piocheSurTable;
    public Gestionnaire(Pioche piocheCourante, PiocheSurTable piocheSurCourant) {
        this.pioche = piocheCourante;
        this.piocheSurTable = piocheSurCourant;
    }
    public Carte piocher(){
        return pioche.piocher();
    }
    public Carte piocherSurTable(Carte carte){
        Carte cartePiochee=piocheSurTable.piocher(carte);
        piocheSurTable.addCarte(pioche.piocher());
        return cartePiochee;
    }

}
