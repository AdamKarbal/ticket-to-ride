package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.Map;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

import java.util.List;

public class GestionEtatJeu {
    private Map map;
    public GestionEtatJeu(Map map){
        this.map=map;
    }
    public void checkCarteDestination(Robot robot) {
        for (CarteDestination carteDestination : robot.getDestinations().getMainDeCartes()) {
            if(!carteDestination.isCompleted()){
                robot.getScore().removeNbPoints(carteDestination.getValue());
            }
            else robot.getScore().addNbPoints(carteDestination.getValue());
        }
    }
    public boolean isVoisin(Ville v1,Ville v2){
        return map.getMap().get(v1).contains(v2);
    }
    public void isDestinationsCardCompleted(List<Robot> robots) {
        for (Robot robot : robots) {
            for (CarteDestination card : robot.getDestinations().getMainDeCartes()) {
                for (Trajet trajet : map.getTrajets()) {
                    if((trajet.getFrom().equals(card.getVille1())||trajet.getFrom().equals(card.getVille2()))&&(trajet.getTo().equals(card.getVille1())||trajet.getTo().equals(card.getVille2()))){
                        if (trajet.isDone()) card.setCompleted(true);
                    }

                }
            }
        }
    }

}
