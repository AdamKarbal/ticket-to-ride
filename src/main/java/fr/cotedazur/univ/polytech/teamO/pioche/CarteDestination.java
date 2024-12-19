package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.gestion.Ville;

public class CarteDestination extends Carte {
    private Couleur couleur;
    private boolean longue;
    private Ville ville1;
    private Ville ville2;
    private int value;

    public CarteDestination(Couleur couleur,boolean longue,Ville ville1, Ville ville2, int value){
        super(couleur);
        if((longue && !couleur.equals(Couleur.Marron))||(!longue && !couleur.equals(Couleur.Bleu)))throw new IllegalArgumentException("Couleur Invalide");
        else this.couleur = couleur;
        this.longue = longue;
        this.ville1 = ville1;
        this.ville2 = ville2;
        if((longue && (value<12 || value>22))||(!longue &&(value<3 || value>11)))throw new IllegalArgumentException("Valeur invalide");
        else this.value=value;
    }

    public boolean isLongue() {
        return longue;
    }

    public Ville getVille1() {
        return ville1;
    }

    public Ville getVille2() {
        return ville2;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String getNom() {
        String str ="";
        if(isLongue())str+="longue";
        else str+="courte";
        return "Carte destination "+str+": de "+getVille1()+" à "+getVille2();
    }

    @Override
    public Couleur getCouleur() {
        return couleur;
    }


    // POUR LE TIRAGE FAIRE UNE CLASSE AVEC UNE MAIN DE CARTE DESTINATION ? OU GARDER DANS LA CLASSE ROBOT ? PERSO PLUS POUR LE PREMIER CHOIX
}
