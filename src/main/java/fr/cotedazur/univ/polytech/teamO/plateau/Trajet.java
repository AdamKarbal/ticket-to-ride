package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

public class Trajet {
    private final Route route;
    private Ville from;
    private Ville to;


    public Trajet(Ville from, Ville to, Route route) {
        this.from = from;
        this.to = to;
        this.route = route;
    }

    public Ville getFrom() {
        return from;
    }

    public Ville getTo() {
        return to;
    }

    public Route getRoute() {
        return route;
    }

    public boolean isDone() {
        return !route.isAvailable();
    }

    @Override
    public String toString() {
        return "Trajet : " + "De " + getFrom() + " à " + getTo() + " de " + route.getNbCases() +" cases et de couleur " + route.getCouleur() + "." ;
    }


}
