package fr.cotedazur.univ.polytech.teamO.bot;


import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Robot implements Comparable<Robot> {

    private Score score;
    private Couleur couleur;
    private MainJoueur destinations;
    private MainJoueur main;
    private boolean turnOver;
    private int piocheCount;
    private HashMap<Couleur,Integer> wagonByCouleur;
    private int nbWagons;
    //Constructeur
    public Robot(Couleur couleur) {
        this.score = new Score();
        this.couleur = couleur;
        this.destinations = new MainJoueur();
        this.main=new MainJoueur();
        this.turnOver=false;
        this.wagonByCouleur= MoteurDeJeu.initWagonByCouleur(this);
        this.piocheCount=0;
        this.nbWagons=45;
    }

    // Getters & setters
    public MainJoueur getMain() {
        return main;
    }
    public MainJoueur getDestinations() { return destinations; }
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
    public void removeNbWagons(int nbWagons) { this.nbWagons-=nbWagons; }
    public Couleur getCouleur() {
        return couleur;
    }
    public void chooseDestination(CarteDestination destination){
        destinations.addCarte(destination);
    }
    public int getPlayerScore(){
        return score.getNbPoints();
    }
    // Actions du joueur
    public void piocher(MoteurDeJeu g){
        Carte carte=g.piocher(this);
        getMain().addCarte(carte);
    }

    public void piocherSurTable(Carte carteAPiocher, MoteurDeJeu g){
        Carte carte=g.piocherSurTable(this,carteAPiocher);
        getMain().addCarte(carte);
    }

    public void poseTrain(Route route, MoteurDeJeu g){
        g.poseTrain(this,route);
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
    public int compareTo(@NotNull Robot o) {
        if(this.getScore().getNbPoints()==o.getScore().getNbPoints()){
            return this.getCouleur().name().compareTo(o.getCouleur().name());
        }
        else if(this.getScore().getNbPoints()>o.getScore().getNbPoints()) return 1;
        else return -1;
    }

}