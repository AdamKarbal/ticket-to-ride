package fr.cotedazur.univ.polytech.teamO.bot;


import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.pioche.*;


import java.util.ArrayList;
import java.util.Objects;

public class Robot {
    private Score score;
    private Couleur couleur;
    //private ArrayList<DestinationCard> destinations;
    private Pioche piocheCourante;
    private MainJoueur main;
    private boolean turnOver;
    private PiocheSurTable piocheSurTable;
    private int piocheCount;
    public Robot(int score, Couleur couleur, Pioche piocheCourante, PiocheSurTable piocheSurTable) {
        //this.score = score;
        this.couleur = couleur;
        //this.destinations = new ArrayList<>();
        this.main=new MainJoueur();
        this.piocheCourante=piocheCourante;
        this.piocheSurTable=piocheSurTable;
        this.turnOver=false;
        this.piocheCount=0;
    }
    public MainJoueur getMain() {
        return main;
    }

    /* public void chooseDestination(DestinationCard destination){
        destinations.add(destination);
    } */
    // Actions du joueur
    public void piocher(){
        Carte carte=piocheCourante.piocher();
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
        Carte carte=piocheSurTable.piocher(carteAPiocher); // à changer
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
        piocheSurTable.addCarte(piocheCourante.piocher());
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