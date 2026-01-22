package fr.cotedazur.univ.polytech.teamO.bot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.gestion.Score;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Robot implements Comparable<Robot> {

    private Score score;
    private final Couleur couleur;
    private MainJoueur<CarteDestination> destinations;
    private MainJoueur main;
    private boolean turnOver;
    private int piocheCount;
    private Map<Couleur,Integer> wagonByCouleur;
    private int nbWagons;
    private List<Trajet> trajets;
    private Map<MCouleur,Integer> takenMeeples;
    private boolean taking;
    private int nbroute;

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
        this.trajets =new ArrayList<>();
        this.takenMeeples=new HashMap<>();
        this.taking=false;
        this.nbroute=0;
    }

    // Getters & setters
    public MainJoueur getMain() {
        return main;
    }
    public void chooseDestination(CarteDestination destination) {
        this.destinations.addCarte(destination);
    }
    public MainJoueur<CarteDestination> getDestinations() {
        return destinations;}
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
    public Map<Couleur,Integer> getWagonByCouleur() {
        return wagonByCouleur;
    }

    public int getNbWagons() {
        return nbWagons;
    }
    public void removeNbWagons(int nbWagons) {
        this.nbWagons-=nbWagons;
    }
    public Couleur getCouleur() {
        return couleur;
    }
    public int getPlayerScore(){
        return score.getNbPoints();
    }
    public List<Trajet> getTrajet() {
        return trajets;
    }
    public void addTrajetDone(Trajet trajet){
        trajets.add(trajet);
    }
    public void addNbRoute(){
        this.nbroute++;
    }
    public int getNbroute(){
        return nbroute;
    }
    public Map<MCouleur,Integer> getTakenMeeples() {
        return takenMeeples;
    }

    public int getTakenMeeplesByCouleur(MCouleur couleur){ // Renvoie le nombre de meeple d'une couleur du joueur
        return takenMeeples.getOrDefault(couleur, 0);
    }


    // Actions du joueur

    /**
     * Le moteur de jeu pioche une carte dans la main du joueur
     * @param moteurDeJeu
     */
    public void piocher(MoteurDeJeu moteurDeJeu){
        Carte carte=moteurDeJeu.piocher(this);
        getMain().addCarte(carte);
    }

    /**
     * Le moteur de jeu pioche une carte dans la pioche visible dans la main du joueur
     * @param carteAPiocher
     * @param moteurDeJeu
     */
    public void piocherSurTable(Carte carteAPiocher, MoteurDeJeu moteurDeJeu){ // De même mais pour une carte dans la pioche visible
        Carte carte=moteurDeJeu.piocherSurTable(this,carteAPiocher);
        getMain().addCarte(carte);
    }

    /**
     *
     * @param trajet
     * @param moteurDeJeu
     * @param gestionTrajets
     * @throws NotEnoughWagonCard dans quel cas ?
     */
    public void poseTrain(Trajet trajet, MoteurDeJeu moteurDeJeu, GestionTrajets gestionTrajets) throws NotEnoughWagonCard { // Le moteurdejeu pose un des wagons sur le trajet voulue
        moteurDeJeu.poseTrain(this,trajet,gestionTrajets);
    }

    public void chooseDestination(MoteurDeJeu moteurDeJeu) {}

    public void chooseDestinations(MoteurDeJeu moteurDeJeu) {}

    public void refuseDestinations(MoteurDeJeu moteurDeJeu){}

    /**
     * Le moteurdejeu rajoute un meeples de la couleur du paramètre au joueur
     * @param couleur
     */
    public void addMeeple(MCouleur couleur){
        int compteur = takenMeeples.getOrDefault(couleur, 0); // Si absent, on initialise à 0
        compteur++;
        takenMeeples.put(couleur,compteur);
    }

    /**
     * Le robot prend aléatoirement un meeple dans la ville de départ ou d'arrivée du dernier trajet
     * @param ville
     * @param moteurDeJeu
     */
    public void takeMeeplesAleatory(Ville ville, MoteurDeJeu moteurDeJeu){
        int nbpassengers;
        int choice;
        Random rand = new Random();
        nbpassengers = ville.getPassagers().size(); // on regarde le nombre de meeples dans la ville from
        if (nbpassengers == 0) { // s'il n'y a pas de meeples on quitte la méthode
            return;
        }
        else if (nbpassengers==1) {
            choice=0;
        }
        else {
            choice = rand.nextInt(nbpassengers);
        }
        moteurDeJeu.takeMeeples(this,ville,ville.getPassagers().get(choice).getCouleur());
    }

    // Méthodes générales

    @Override
    public String toString() {
        return couleur.name();
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (!(obj instanceof Robot other)) return false;
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
