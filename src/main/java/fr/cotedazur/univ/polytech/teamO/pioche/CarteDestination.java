package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.gestion.Ville;

import java.util.Objects;

public class CarteDestination extends Carte {

    private Couleur couleur;
    private Ville ville1;
    private Ville ville2;
    private int value;
    private boolean completed=false;

    public CarteDestination(Couleur couleur,Ville ville1, Ville ville2, int value) throws IllegalArgumentException {
        super(couleur);
        if(!couleur.equals(Couleur.Marron) &&!couleur.equals(Couleur.Bleu))throw new IllegalArgumentException("Couleur Invalide");
        else this.couleur = couleur;
        this.ville1 = ville1;
        this.ville2 = ville2;
        if((couleur.equals(Couleur.Marron)&&(value<12 || value>22))||(couleur.equals(Couleur.Bleu)&&(value<2 || value>11)))throw new IllegalArgumentException("Valeur invalide");
        else this.value=value;
    }

    public boolean isLongue() {
        return couleur.equals(Couleur.Marron);
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass()!=getClass()) return false;
        CarteDestination carte = (CarteDestination) obj;
        return getValue()==carte.getValue()&&getVille1().equals(carte.ville1)&&getVille2().equals(carte.ville2);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value,ville1,ville2);
    }


    // POUR LE TIRAGE FAIRE UNE CLASSE AVEC UNE MAIN DE CARTE DESTINATION ? OU GARDER DANS LA CLASSE ROBOT ? PERSO PLUS POUR LE PREMIER CHOIX
}
