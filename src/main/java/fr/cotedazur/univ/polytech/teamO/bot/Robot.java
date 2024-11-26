package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.Main;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.pioche.MainJoueur;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;


import java.util.ArrayList;
import java.util.Objects;

public class Robot {
    //private Score score;
    private Couleur couleur;
    //private ArrayList<DestinationCard> destinations;
    private MainJoueur main;
    public Robot(int score, Couleur couleur) {
        //this.score = score;
        this.couleur = couleur;
        //this.destinations = new ArrayList<>();
        this.main=new MainJoueur();
    }
    public MainJoueur getMain() {
        return main;
    }
    /*public void chooseDestination(DestinationCard destination){
        destinations.add(destination);
    }*/
    public void jPioche(Pioche pioche) {
        CarteWagon cartePiocher = (CarteWagon) pioche.getPioche().pop();
        main.addCarte(cartePiocher);
    }
    @Override
    public String toString() {
        return couleur.name();
    }
    @Override
    public boolean equals(Object obj) {
        if (equals(obj)) return true;
        if (obj==null || !(obj instanceof Robot)) return false;
        Robot other = (Robot) obj;
        return couleur.equals(other.couleur);
    }
    @Override
    public int hashCode() {
        return Objects.hash(couleur);
    }
}
