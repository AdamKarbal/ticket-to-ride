package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;

public class Route {

    private final int nbCases;
    private final Couleur couleur;
    private boolean available;
    public Route(int nbCases, Couleur couleur)  {
        if (nbCases < 1 || nbCases>7)throw new IllegalArgumentException("nbCases doit être strictement positif et inférieur ou égal à 7.");
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
        if(couleur==Couleur.Default){
            return nbWagons >= getNbCases() && isAvailable();
        }
        else{
            return nbWagons >= getNbCases() && isAvailable() && wagon.getCouleur().equals(couleur);
        }

    }

    @Override
    public String toString() {
        return "Route de couleur "+"\""+getCouleur()+"\""+" de "+getNbCases()+" case(s)";
    }


}
