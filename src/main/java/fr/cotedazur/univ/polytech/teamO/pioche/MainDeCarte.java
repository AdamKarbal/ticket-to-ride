package fr.cotedazur.univ.polytech.teamO.pioche;

import java.util.ArrayList;

public interface MainDeCarte<T extends Carte> {

    ArrayList<T> getMainDeCartes();
    // Methode

    T carteAtPosI(int i);

    boolean containCarte(T carteWagon);

    void addCarte(T carteWagon);
}
