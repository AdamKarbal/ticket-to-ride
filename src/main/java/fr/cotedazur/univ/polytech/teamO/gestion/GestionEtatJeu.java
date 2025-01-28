package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.affichage.Affichage;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

import java.util.*;

public class GestionEtatJeu {
    private GestionTrajets gestionTrajets;
    private Affichage affichage;
    public GestionEtatJeu(GestionTrajets gestionTrajets) {
        this.gestionTrajets = gestionTrajets;
        this.affichage = new Affichage();
    }
    public void updateScoreCarteDestination(Robot robot) {
        for (CarteDestination carteDestination : robot.getDestinations().getMainDeCartes()) {
            if(!carteDestination.isCompleted()){
                robot.getScore().removeNbPoints(carteDestination.getValue());
                affichage.afficheCheckCarteDestinationPASOK(robot, carteDestination);
            }
            else{
                robot.getScore().addNbPoints(carteDestination.getValue());
                affichage.afficheCheckCarteDestinationOK(robot,carteDestination);
            }
        }
    }

    public void isDestinationsCardCompleted(List<Robot> robots) { // refactor cette méthode en combinant avec celle d'en haut ?
        for (Robot robot : robots) {
            for (CarteDestination card : robot.getDestinations().getMainDeCartes()) {
                if(gestionTrajets.isConnected(card.getVille1(), card.getVille2(), robot.getTrajet()) || gestionTrajets.isConnected(card.getVille2(), card.getVille1(), robot.getTrajet())){
                    card.setCompleted(true);
                }
            }
        }
    }

    public void whoHaveMostMeeples(List<Robot> robots) {
        Robot first = robots.get(0);
        Robot second = robots.get(1);
        int max = 0;
        int max2 = 0;
        int temp = 0;
        for (MCouleur couleur : MCouleur.values()) {
            for (Robot robot : robots) {
                temp = robot.getTakenMeeplesByCouleur(couleur);
                if (temp > max) {
                    second = first;
                    max2 = max;
                    first = robot;
                    max = temp;
                }
                if (temp > max2 && temp < max) {
                    second = robot;
                    max2 = temp;
                }
            }
            first.getScore().addNbPoints(20);
            if(!(second.equals(first))) {
                second.getScore().addNbPoints(10);
                affichage.affichePointMeeples(couleur, first, second);
            }
            else{
                affichage.affichePointMeeplespasSecond(couleur, first);
            }
        }
    }

}
