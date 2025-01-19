package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

import java.util.*;

public class GestionEtatJeu {
    private GestionTrajets gestionTrajets;
    public GestionEtatJeu(GestionTrajets gestionTrajets) {
        this.gestionTrajets = gestionTrajets;
    }
    public void updateScoreCarteDestination(Robot robot) {
        for (CarteDestination carteDestination : robot.getDestinations().getMainDeCartes()) {
            if(!carteDestination.isCompleted()){
                robot.getScore().removeNbPoints(carteDestination.getValue());
            }
            else robot.getScore().addNbPoints(carteDestination.getValue());
        }
    }

    public void isDestinationsCardCompleted(List<Robot> robots) {
        for (Robot robot : robots) {
            for (CarteDestination card : robot.getDestinations().getMainDeCartes()) {
                if(gestionTrajets.isConnected(card.getVille1(), card.getVille2(), robot.getTrajet()) || gestionTrajets.isConnected(card.getVille2(), card.getVille1(), robot.getTrajet())){
                    card.setCompleted(true);
                }
            }
        }
    }


}
