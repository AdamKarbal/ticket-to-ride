package fr.cotedazur.univ.polytech.teamO.trajet;

import fr.cotedazur.univ.polytech.teamO.gestion.Ville;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;

public class Trajet {
    private Ville from;
    private Ville to;
    private Route route;
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

    @Override
    public String toString() {
        return "Trajet : " + " De " + from + " à " + to + ".";
    }

    public void createTrajet(){

    }
}
