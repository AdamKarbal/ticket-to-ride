package fr.cotedazur.univ.polytech.teamO.bot;


import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.gestion.Gestionnaire;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;

import java.util.*;

public class Robot implements Comparable<Robot> {
    private Score score;
    private Couleur couleur;
    //private ArrayList<DestinationCard> destinations;
    private MainJoueur main;
    private boolean turnOver;
    private int piocheCount;
    private HashMap<Couleur,Integer> wagonByCouleur;
    private int nbWagons;
    //Constructeur
    public Robot(Score score, Couleur couleur) {
        this.score = score;
        this.couleur = couleur;
        //this.destinations = new ArrayList<>();
        this.main=new MainJoueur();
        this.turnOver=false;
        this.wagonByCouleur=Gestionnaire.initWagonByCouleur(this);
        this.piocheCount=0;
        this.nbWagons=45;
    }

    // Getters & setters
    public MainJoueur getMain() {
        return main;
    }
    public Score getScore(){
        return score;
    }
    public boolean isTurnOver() {
        return turnOver;
    }
    public void setTurnOver(boolean turnOver) {
        this.turnOver = turnOver;
    }
    public int getPiocheCount() {
        return piocheCount;
    }
    public void setPiocheCount(int piocheCount) {
        this.piocheCount = piocheCount;
    }
    public HashMap<Couleur,Integer> getWagonByCouleur() {
        return wagonByCouleur;
    }
    public int getNbWagons() { return nbWagons; }
    public void removeNbWagons(int nbWagons) { nbWagons-=nbWagons; }
    /* public void chooseDestination(CarteDestination destination){
        destinations.add(destination);
    } */
    public int getPlayerScore(){
        return score.getNbPoints();
    }
    // Actions du joueur
    public void piocher(){
        Carte carte=Gestionnaire.piocher(this);
        getMain().addCarte(carte);
    }

    public void piocherSurTable(Carte carteAPiocher){
        Carte carte=Gestionnaire.piocherSurTable(this,carteAPiocher);
    }

    public void poseTrain(Route route){
        Gestionnaire.poseTrain(this,route);
    }

    // Méthodes générales
    @Override
    public String toString() {
        return couleur.name();
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj==null || !(obj instanceof Robot)) return false;
        Robot other = (Robot) obj;
        return couleur.equals(other.couleur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couleur);
    }

    @Override
    public int compareTo(Robot o) {
        return score.compareTo(o.getScore());
    }
}