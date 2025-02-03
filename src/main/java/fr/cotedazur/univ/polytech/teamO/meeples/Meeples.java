package fr.cotedazur.univ.polytech.teamO.meeples;

public class Meeples {

    private MCouleur couleur;
    public boolean Free;


    public Meeples(MCouleur couleur) {
        this.couleur = couleur;
        Free = true;
    }

    public MCouleur getCouleur() {
        return couleur;
    }

    public boolean isFree() {
        return Free;
    }

    @Override
    public String toString() {
        return "Un Meeple de couleur " + getCouleur()+ ".";
    }

}
