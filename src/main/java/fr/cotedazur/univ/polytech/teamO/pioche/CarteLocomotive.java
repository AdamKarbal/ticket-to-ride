package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class CarteLocomotive extends Carte{
    //Instance
    private Couleur couleur;
    private String nomCarte;
    public CarteLocomotive(){
        super(Couleur.RAINBOW);
    }
    public String getNom(){
        return nomCarte;
    }
    public Couleur getCouleur(){
        return couleur;
    }
}
