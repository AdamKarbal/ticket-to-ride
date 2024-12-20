package fr.cotedazur.univ.polytech.teamO.trajet;

import fr.cotedazur.univ.polytech.teamO.gestion.Ville;

public class Trajet {
    private Ville from;
    private Ville to;
    private int nbWagonsTr;
    public Trajet(Ville from, Ville to, int nbWagonsTr) {
        this.from = from;
        this.to = to;
        this.nbWagonsTr = nbWagonsTr;
    }

    public int getNbWagonsTr() {
        return nbWagonsTr;
    }

    public Ville getFrom() {
        return from;
    }

    public Ville getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Trajet : " + " De " + from + " à " + to + ".";
    }

    public void createTrajet(){

    }
}
