package fr.cotedazur.univ.polytech.teamO.pioche;
import java.util.List;

public interface MainDeCarte<T extends Carte> {

    List<T> getMainDeCartes();
    // Methode

    T carteAtPosI(int i);

    boolean containCarte(T carteWagon);

    void addCarte(T carteWagon);
}
