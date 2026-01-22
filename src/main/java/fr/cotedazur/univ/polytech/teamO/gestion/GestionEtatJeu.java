package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.LogFormatter;
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
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionEtatJeu {

    private static final Logger logger = Logger.getLogger(GestionEtatJeu.class.getName());
    private GestionTrajets gestionTrajets;
    private Affichage affichage;

    /**
     * Paramétrage du Logger de la classe qui servira à afficher tous les messages destinés à la sortie standard.
     */
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

    /**
     * Cette méthode est appelée à la fin de la partie pour vérifier si les cartes destinations sont complétées.
     * Si elles le sont, les points nécessaires sont ajoutés ; sinon, ils sont retirés.
     * @param robot
     */

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

    /**
     * Cette méthode permet de definir si une carte destination est completer ou non
     * @param robots
     */
    public void isDestinationsCardCompleted(List<Robot> robots) { // refactor cette méthode en combinant avec celle d'en haut ?
        for (Robot robot : robots) {
            for (CarteDestination card : robot.getDestinations().getMainDeCartes()) {
                if (gestionTrajets.isConnected(card.getVille1(), card.getVille2(), robot.getTrajet()) ||
                        gestionTrajets.isConnected(card.getVille2(), card.getVille1(), robot.getTrajet())) {
                    card.setCompleted(true);
                }
            }
        }
    }

    /**
     * Cette méthode est appeller en fin de partie pour désigner qui a le plus de meeples par couleur, il ajoute les points en conséquences.
     * @param robots
     */
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

    /**
     * Cette méthode est appeller en fin de partie pour dire qui a le plus de carte destination, les points sont ajouter en consequence.
     * @param robots
     */
    public void whoHaveMostDestinationCards(List<Robot> robots) {
        List<Robot> winners = new ArrayList<>();
        int max = 0;

        for (Robot robot : robots) {
            int nbclaim = nombreCarteDestination(robot);
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

    /**
     * Cette méthode est utiliser pour les statistiques du jeu, il dit combien de carte destination le robot a completer
     * @param robot
     * @return
     */
    public int nombreCarteDestination(Robot robot) {
        int nbclaim = 0;
        for (int i = 0; i < robot.getDestinations().getMainDeCartes().size(); i++) {
            if (robot.getDestinations().getMainDeCartes().get(i).isCompleted()) {
                nbclaim++;
            }
        }
        return nbclaim;
    }

    /**
     * Cette méthode est appeler lors de la fin de partie pour mettre à jour les informations necessaires.
     * @param robots
     */
    public void endOfGameUpdate(List<Robot> robots){
        whoHaveMostDestinationCards(robots);
        whoHaveMostMeeples(robots);
    }

    public void DLogsOn(){
        logger.setLevel(Level.INFO);
        affichage.DLogsOn();
    }

    public void DLogsOff(){
        logger.setLevel(Level.OFF);
        affichage.DLogsOff();
    }
}