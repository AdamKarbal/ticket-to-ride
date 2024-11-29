package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class Case {
    private Couleur couleur;
    private boolean available;
     public Case(Couleur couleur) {
         this.couleur = couleur;
         this.available = true;
     }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }
}
