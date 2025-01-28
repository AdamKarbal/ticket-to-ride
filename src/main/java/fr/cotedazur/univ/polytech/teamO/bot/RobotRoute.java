package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.plateau.NoAvailableTrip;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

public class RobotRoute extends Robot{
    // Ce robot va en priorité au plus vite terminer ses cartes de destination dans l'ordre où elles sont présentes dans sa main
    private Ville villeActuelle;
    private CarteDestination currentDestination;
    public RobotRoute(Couleur couleur) {
        super(couleur);
    }
    public void setVilleActuelle(Ville villeActuelle) {
        this.villeActuelle = villeActuelle;
    }
    @Override
    public void chooseDestinations(MoteurDeJeu moteurDeJeu) {
        for(int i=0;i<4;i++){
            CarteDestination carteDestinationPioche = moteurDeJeu.giveDestinationsCard(this,2);
            chooseDestination(carteDestinationPioche);
        }
    }

    @Override
    public void refuseDestinations(MoteurDeJeu moteurDeJeu) {
        int maxvalue = getDestinations().getMainDeCartes().get(0).getValue();
        CarteDestination temp = getDestinations().getMainDeCartes().get(0);
        for(int i =0 ; i<4 ; i++){
            if(getDestinations().getMainDeCartes().get(i).getValue()>maxvalue){
                maxvalue = getDestinations().getMainDeCartes().get(i).getValue();
                temp = getDestinations().getMainDeCartes().get(i);
            }
        }
        for(int i = 0 ; i<4 ; i++){
            moteurDeJeu.removeDestinationsCard(this,i);
        }
        getDestinations().getMainDeCartes().add(temp);
    }

    public void init(MoteurDeJeu moteurDeJeu){
        chooseDestinations(moteurDeJeu);
        //refuseDestinations(moteurDeJeu);
    }

    @Override
    public void chooseDestination(CarteDestination destination) {
        super.chooseDestination(destination);
        currentDestination = getDestinations().getMainDeCartes().get(0);
        this.villeActuelle=currentDestination.getVille1();
    }

    public void updateCarteDestination(){
        currentDestination=getDestinations().getMainDeCartes().get(getDestinations().getMainDeCartes().indexOf(currentDestination)+1);
        this.villeActuelle=currentDestination.getVille1();
    }

    public Couleur poseTrainOpti(GestionTrajets gestionTrajets, MoteurDeJeu moteurDeJeu) throws NotEnoughWagonCard {
        Trajet trajetOpti = gestionTrajets.findOptimalTrajet(this,villeActuelle,currentDestination.getVille2());
        super.poseTrain(trajetOpti,moteurDeJeu);
        return trajetOpti.getRoute().getCouleur();
    }

    public void choixAction(GestionTrajets gestionTrajets, MoteurDeJeu moteurDeJeu) {
        try{
            poseTrainOpti(gestionTrajets,moteurDeJeu);
        } catch (NotEnoughWagonCard e) { // s'il n'a pas assez de CarteWagon pour compléter son trajet, il va essaie de piocher
            Couleur couleurTrajet;
            try{
                couleurTrajet=gestionTrajets.findOptimalTrajet(this,villeActuelle,currentDestination.getVille2()).getRoute().getCouleur();
                choosePioche(couleurTrajet,moteurDeJeu);
            }
            catch(NoAvailableTrip noAvailableTrip){ // s'il n'y a pas de trajets pour compléter sa carteDestination, on passe à l'autre
                updateCarteDestination();
            }
        }
    }

    public void choosePioche(Couleur couleurTrajet,MoteurDeJeu moteurDeJeu) {
        try {
            piocherSurTable(new CarteWagon(couleurTrajet), moteurDeJeu); // essaie de piocher la carte qu'il lui faut pour compléter son trajet
            if (!(isTurnOver())) {
                choosePioche(couleurTrajet, moteurDeJeu);
            }
        }
        catch(NullPointerException n){ // si elle n'est pas présente , il pioche
            piocher(moteurDeJeu);
            if (!(isTurnOver())) {
                choosePioche(couleurTrajet, moteurDeJeu);
            }
        }
    }

    @Override
    public String toString() {
        return "robotRoute" + getCouleur();
    }
}
