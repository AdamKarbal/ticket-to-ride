package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.plateau.NoAvailableTrip;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;

import java.util.*;

public class RobotMeeples extends Robot {

    public RobotMeeples(Couleur couleur) {
        super(couleur);
    }


    public Stack<MCouleur> StackTakenMeeples(){
        int maxMeeples = 0;
        Stack<MCouleur> StackMeeplesColors = new Stack<MCouleur>();
        Map<MCouleur, Integer> takenMeeples = this.getTakenMeeples();
        for(MCouleur color : takenMeeples.keySet()){
            if(takenMeeples.get(color) >= maxMeeples) maxMeeples = takenMeeples.get(color);
            StackMeeplesColors.push(color);
        }
        return StackMeeplesColors;
    }

    public MCouleur colorToPUp(Stack <MCouleur> stackTakenMeeples, Ville ville){
        while(!stackTakenMeeples.isEmpty()){
            if(ville.getPassagersColors().contains(stackTakenMeeples.peek())){
                return stackTakenMeeples.pop();
            }
            stackTakenMeeples.pop();
        }
        return null;
    }

    public Trajet chooseNext(MoteurDeJeu moteur, GameMap map, GestionTrajets gestionTrajets) throws NoAvailableTrip {
        Ville villeDeDepart = map.getVilleMaxMeeples();
        ArrayList<Trajet> corresp = map.getCorresp(villeDeDepart);
        Trajet trajetPro = map.bestNext(corresp);
        return trajetPro;
    }

    public MCouleur couleurDepart(Trajet trajetPro){
        return colorToPUp(StackTakenMeeples(), trajetPro.getFrom());
    }

    public MCouleur couleurArrivee(Trajet trajetPro){
        return colorToPUp(StackTakenMeeples(), trajetPro.getTo());
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


    public void choixAction(MoteurDeJeu moteur, GameMap map, GestionTrajets gestionTrajets) throws NotEnoughWagonCard {
        try {
            Trajet trajetPro = chooseNext(moteur, map, gestionTrajets);
            MCouleur couleurDepart = couleurDepart(trajetPro);
            MCouleur couleurArrivee = couleurArrivee(trajetPro);
            moteur.poseTrainPUp(this, trajetPro, couleurDepart == null, couleurArrivee == null, couleurDepart, couleurArrivee, gestionTrajets);
        }
        catch(NotEnoughWagonCard e) {
            Couleur color = chooseNext(moteur, map, gestionTrajets).getRoute().getCouleur();
            choosePioche(color, moteur);
        }
    }

    @Override
    public void chooseDestination(MoteurDeJeu moteurDeJeu){ // Le robot aléatoire choisie aléatoirement c'est carte entre une destination longue ou courte
        int choice;
        Random rand = new Random();
        for(int i=0; i<4;i++){
            choice = rand.nextInt(2)+1; // choisie entre 1 et 2 pour savoir si c'est long ou courte
            CarteDestination carteDestinationPioche=moteurDeJeu.giveDestinationsCard(this,choice);
            getDestinations().addCarte(carteDestinationPioche);
        }
    }

    @Override
    public void refuseDestinations(MoteurDeJeu moteurDeJeu){ // pour chaque carte destination le robot a 1/2 de la refuser.
        int choice;
        int temp=0;
        Random rand = new Random();
        for(int i=0;i<4;i++){
            choice = rand.nextInt(2)+1;
            if(choice==1){
                moteurDeJeu.removeDestinationsCard(this,i-temp++);
            }
        }
    }

    public void init(MoteurDeJeu moteurDeJeu){
        chooseDestination(moteurDeJeu);
        refuseDestinations(moteurDeJeu);
    }


    @Override
    public String toString(){
        return "Le robot qui adopte une stratégie axée sur les Meeples. Sa couleur est : " + getCouleur();
    }
}


