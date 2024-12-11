package fr.cotedazur.univ.polytech.teamO.bot;


import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.gestion.Gestionnaire;
import java.util.ArrayList;
import java.util.Objects;

public class Robot implements Comparable<Robot> {
    private Score score;
    private Couleur couleur;
    //private ArrayList<DestinationCard> destinations;
    private MainJoueur main;
    private boolean turnOver;
    private int piocheCount;
    private Gestionnaire gestionnaire;
    public Robot(Score score, Couleur couleur, Gestionnaire gestionnaire) {
        this.score = score;
        this.couleur = couleur;
        //this.destinations = new ArrayList<>();
        this.main=new MainJoueur();
        this.turnOver=false;
        this.piocheCount=0;
        this.gestionnaire=gestionnaire;
    }
    public MainJoueur getMain() {
        return main;
    }

    /* public void chooseDestination(DestinationCard destination){
        destinations.add(destination);
    } */
    // Actions du joueur
    public void piocher(){
        Carte carte=gestionnaire.piocher();
        if(piocheCount==0){
            piocheCount++;
            turnOver=false;
        }
        else{
            piocheCount--;
            turnOver=true;
        }
        getMain().addCarte(carte);
    }

    public void piocherSurTable(Carte carteAPiocher){
        Carte carte=gestionnaire.piocherSurTable(carteAPiocher); // à changer
        if(piocheCount==0){
            getMain().addCarte(carte);
            if(carte.getCouleur()==Couleur.Rainbow) {
                turnOver=true;
            }
            else{
                turnOver=false;
                piocheCount++;
            }
        }
        else{
            if(carte.getCouleur()!=Couleur.Rainbow) {
                turnOver=true;
                piocheCount--;
                getMain().addCarte(carte);
            }
            else{
                piocher();
            }
        }
    }

    public int getPlayerScore(){
        return score.getNbPoints();
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

    @Override
    public int compareTo(Robot o) {
        if(score.getNbPoints() > o.score.getNbPoints()) return -1;
        else if (score.getNbPoints() == o.score.getNbPoints()) return 0;
        return 1;
    }
}