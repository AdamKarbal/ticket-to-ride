package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class CarteLocomotive extends CarteWagon {
    //Instance
    private Couleur couleur;
    private String nomCarte;
    public CarteLocomotive(){
        super(Couleur.Rainbow);
    }
    public String getNom(){
        return nomCarte;
    }
    public Couleur getCouleur(){
        return Couleur.Rainbow;
    }
}
