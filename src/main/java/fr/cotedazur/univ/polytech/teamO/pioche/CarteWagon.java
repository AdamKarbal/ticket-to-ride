package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

import java.util.Arrays;

public class CarteWagon extends Carte {
    //Instance
    private String nomCarte;

    // Constructeurs
    public CarteWagon(Couleur couleur) {
        super(couleur);
    }


    // Accesseurs

    @Override
    public String getNom() {
        if(isValid()){
            return nom;
        }
        return "Carte invalide";
    }

    @Override
    public Couleur getCouleur() {
        return couleur;
    }

    // Méthode

    public boolean isValid() {
        return Arrays.asList(Couleur.values()).contains(couleur);
    }


}

