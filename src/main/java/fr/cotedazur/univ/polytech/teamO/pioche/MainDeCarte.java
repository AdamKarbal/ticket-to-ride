package fr.cotedazur.univ.polytech.teamO.pioche;

import java.util.ArrayList;

public interface MainDeCarte {

    ArrayList<CarteWagon> getMainDeCartes();
    // Methode
    void removeCarte(CarteWagon carte);
    CarteWagon carteAtPosI(int i);
    boolean containCarte(CarteWagon carteWagon);
    void addCarte(CarteWagon carteWagon);
}
