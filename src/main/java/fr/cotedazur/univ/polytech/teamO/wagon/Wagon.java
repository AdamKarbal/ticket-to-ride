package fr.cotedazur.univ.polytech.teamO.wagon;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class Wagon extends Vehicule{
    private Couleur couleur;

    public Wagon(Couleur couleur) {
        if(couleur == null) this.couleur = Couleur.Default;
        else this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    @Override
    public String toString(){
        return "Un wagon de couleur " + getCouleur().name() + " à placer sur une route.";
    }

}
