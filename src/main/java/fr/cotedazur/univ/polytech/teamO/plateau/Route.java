package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Route<T extends Case> {

    private List<T> cases= new ArrayList<>();
    private final int nbCases;
    private Couleur couleur;
    private boolean available;

    public Route(int nbCases, Couleur couleur)  {
        if (nbCases < 0 )this.nbCases = 0;
        else this.nbCases = nbCases;
        this.couleur = couleur;
        this.available = true;
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

    public void addCases(){
        if(cases.size()==0){
            for(int i=0;i<getNbCases();i++){cases.add((T) new Case(couleur));}
        }
    }


    public boolean isAvailable() {
        for (Case c : cases) {
            if (!c.isAvailable()) setAvailable(false);
        }
        return true;
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }
}
