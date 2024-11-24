package fr.cotedazur.univ.polytech.teamO.wagon;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class Locomotive extends Vehicule{
    private Couleur couleur;

    public Locomotive() {
        this.couleur = Couleur.LOCOMOTIVE;
    }

    @Override
    public String toString(){
        return "Locomotive (joker)";
    }

    public Couleur getCouleur() {
        return couleur;
    }
}
