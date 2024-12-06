package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Route<T extends Case> {

    private List<T> cases= new ArrayList<>();
    private final int nbCases;
    private Couleur couleur;

    public Route(int nbCases, Couleur couleur)  {
        if (nbCases < 0 )this.nbCases = 0;
        else this.nbCases = nbCases;
        this.couleur = couleur;
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
    public boolean checkLengthRoute() throws IllegalArgumentException {
        if(getNbCases()!=getCases().size()){cases.clear();throw new IllegalArgumentException("La taille de la liste de cases doit être la même que celle indiqué par nbCases.");}
        else return true;
    }

    public boolean addCase(T c) {
        if (!(c.getCouleur().equals(getCouleur()) && getNbCases()>=getCases().size())) return false;
        else {cases.add(c);return true;}
    }
    public boolean addAllCases(List<T> newCases) {
        for (T c : newCases) addCase(c);
        return false;
    }

    public boolean isAvailable() {
        for (Case c : cases) {
            if (!c.isAvailable()) return false;
        }
        return true;
    }
    public boolean finalCheck(){
        if(checkLengthRoute()) return true;
        else {cases.clear();return false;}
    }


}
