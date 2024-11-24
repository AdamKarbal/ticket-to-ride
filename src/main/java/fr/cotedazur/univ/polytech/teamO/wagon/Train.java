package fr.cotedazur.univ.polytech.teamO.wagon;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Train<E extends Vehicule>{
    private List<E> suiteDeWagons;
    private Couleur couleur;
    //private Route route;

    public Train(Couleur couleur, List<E> suiteDeWagons) {
        if(suiteDeWagons.get(0).getCouleur() == null) this.couleur = Couleur.DEFAULT;
        else this.couleur = couleur;
        if(suiteDeWagons == null) this.suiteDeWagons = new ArrayList<E>();
        else this.suiteDeWagons = suiteDeWagons;
    }

    @Override
    public String toString(){
        return "Un train de " + suiteDeWagons.size() + " wagons de couleur "+ couleur+".";
    }

    public int getNbWagons(){
        return suiteDeWagons.size();
    }

    public Couleur getCouleurTrain(){
        return couleur;
    }

}
