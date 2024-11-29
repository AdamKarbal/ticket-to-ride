package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Route<T extends Case> {

    private List<T> cases= new ArrayList<>();
    private final int nbCases;
    private Couleur couleur;

    public Route(int nbCases, Couleur couleur) throws IllegalArgumentException {
        if (nbCases < 0 )this.nbCases = 0;
        else this.nbCases = nbCases;
        if (!validColor()) throw new IllegalArgumentException("La couleur de la route est différente que celle d'une de ses cases.");
        else this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }
    public int getNbCases() {
        return nbCases;
    }
    public List<T> getCases() {
        return cases;
    }

    public boolean validColor(){
        for (T c : cases) {
            if(!c.getCouleur().equals(couleur))return false;
        }
        return true;
    }

    public boolean addCase(T c) {
        if (!(c.getCouleur().equals(getCouleur()) && getNbCases()==getCases().size())) return false;
        else {cases.add(c);return true;}
    }
    public void addAllCases(List<T> newCases) {
        cases.addAll(newCases);
    }

    public boolean isAvailable() {
        for (Case c : cases) {
            if (!c.isAvailable()) return false;
        }
        return true;
    }


}
