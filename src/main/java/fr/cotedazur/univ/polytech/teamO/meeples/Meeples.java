package fr.cotedazur.univ.polytech.teamO.meeples;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class Meeples {

    private Couleur couleur;
    public boolean Free;


    public Meeples(Couleur couleur) {
        this.couleur = couleur;
        Free = true;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setFree(boolean free) {
        this.Free = free;
    }

    public boolean isFree() {
        return Free;
    }


}
