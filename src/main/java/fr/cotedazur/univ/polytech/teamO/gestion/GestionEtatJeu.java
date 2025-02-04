package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.LogFormatter;
import fr.cotedazur.univ.polytech.teamO.affichage.Affichage;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

public class GestionEtatJeu {

    private static final Logger logger = Logger.getLogger(GestionEtatJeu.class.getName());
    private GestionTrajets gestionTrajets;
    private Affichage affichage;

    static{
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
    }

    public GestionEtatJeu(GestionTrajets gestionTrajets) {
        this.gestionTrajets = gestionTrajets;
        this.affichage = new Affichage();
    }

    public void updateScoreCarteDestination(Robot robot) {
        for (CarteDestination carteDestination : robot.getDestinations().getMainDeCartes()) {
            if (!carteDestination.isCompleted()) {
                robot.getScore().removeNbPoints(carteDestination.getValue());
                affichage.afficheCheckCarteDestinationPASOK(robot, carteDestination);
            } else {
                robot.getScore().addNbPoints(carteDestination.getValue());
                affichage.afficheCheckCarteDestinationOK(robot, carteDestination);
            }
        }
    }

    public void isDestinationsCardCompleted(List<Robot> robots) {
        for (Robot robot : robots) {
            for (CarteDestination card : robot.getDestinations().getMainDeCartes()) {
                if (gestionTrajets.isConnected(card.getVille1(), card.getVille2(), robot.getTrajet()) ||
                        gestionTrajets.isConnected(card.getVille2(), card.getVille1(), robot.getTrajet())) {
                    card.setCompleted(true);
                }
            }
        }
    }

    public void whoHaveMostMeeples(List<Robot> robots) {
        logger.info("Concernant les meeples ...");
        Robot first = robots.get(0);
        Robot second = robots.get(1);
        int max = 0;
        int max2 = 0;
        int temp;

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
            if (!second.equals(first)) {
                second.getScore().addNbPoints(10);
                affichage.affichePointMeeples(couleur, first, second);
            } else {
                affichage.affichePointMeeplespasSecond(couleur, first);
            }
        }
    }

    public void whoHaveMostDestinationCards(List<Robot> robots) {
        List<Robot> winners = new ArrayList<>();
        int max = 0;

        for (Robot robot : robots) {
            int nbclaim = 0;
            for (CarteDestination card : robot.getDestinations().getMainDeCartes()) {
                if (card.isCompleted()) {
                    nbclaim++;
                }
            }
            if (nbclaim > max) {
                max = nbclaim;
                winners.clear();
                winners.add(robot);
            } else if (nbclaim == max) {
                winners.add(robot);
            }
        }

        logger.info("Le titre de GlobeTrotter est pour....");
        for (Robot winner : winners) {
            winner.getScore().addNbPoints(15);
            affichage.afficheFirstCarteDestination(winner);
        }
    }

    public void endOfGameUpdate(List<Robot> robots) {
        whoHaveMostDestinationCards(robots);
        whoHaveMostMeeples(robots);
    }
}