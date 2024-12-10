package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;

public class Route {

    private final int nbCases;
    private final Couleur couleur;
    private boolean available;

    public Route(int nbCases, Couleur couleur)  {
        if (nbCases < 1 )throw new IllegalArgumentException("nbCases doit être positif.");
        else this.nbCases = nbCases;
        if (couleur == null)throw new NullPointerException("La couleur ne peut pas être nulle");
        else this.couleur = couleur;
        this.available = true;
    }

    public Couleur getCouleur() {
        return couleur;
    }
    public int getNbCases() {
        return nbCases;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isClaimable(int nbWagons, Wagon wagon) {
        if(nbWagons<getNbCases()||!wagon.getCouleur().equals(couleur)||!isAvailable()) return false;
        else return true;
    }


}
