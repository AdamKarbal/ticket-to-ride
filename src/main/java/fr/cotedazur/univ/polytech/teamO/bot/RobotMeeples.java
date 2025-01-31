package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class RobotMeeples extends Robot {

    RobotMeeples(Couleur couleur) {
        super(couleur);
    }

    private Stack<MCouleur> StackTakenMeeples(){
        int maxMeeples = 0;
        Stack<MCouleur> StackMeeplesColors = new Stack<MCouleur>();
        HashMap<MCouleur, Integer> takenMeeples = this.getTakenMeeples();
        for(MCouleur color : takenMeeples.keySet()){
            if(takenMeeples.get(color) >= maxMeeples) maxMeeples = takenMeeples.get(color);
            StackMeeplesColors.push(color);
        }
        return StackMeeplesColors;
    }

    private MCouleur colorToPUp(Stack <MCouleur> StackTakenMeeples, Ville ville){
        while(!StackTakenMeeples.isEmpty()){
            if(ville.getPassagersColors().contains(StackTakenMeeples.peek())){
                return StackTakenMeeples.pop();
            }
            StackTakenMeeples.pop();
        }
        return null;
    }

    public void chooseNext(MoteurDeJeu moteur, GameMap map, GestionTrajets gestionTrajets) throws NotEnoughWagonCard {
        boolean From = true;
        boolean To = true;
        Ville villeDeDepart = map.getVilleMaxMeeples();
        ArrayList<Trajet> corresp = map.getCorresp(villeDeDepart);
        Trajet trajetPro = map.bestNext(corresp);
        MCouleur couleurDepart = colorToPUp(StackTakenMeeples(), villeDeDepart);
        MCouleur couleurArrivee = colorToPUp(StackTakenMeeples(), trajetPro.getTo());
        if(couleurDepart == null){
            From = false;
        }
        if(couleurArrivee == null){
            To = false;
        }
        moteur.poseTrainPUp(this, trajetPro, From, To, couleurDepart, couleurArrivee,gestionTrajets);
    }

    @Override
    public String toString(){
        return "Le robot qui adopte une stratégie axée sur les Meeples. Sa couleur est : " + getCouleur();
    }
}


