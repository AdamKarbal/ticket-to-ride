package fr.cotedazur.univ.polytech.teamO.affichage;
import fr.cotedazur.univ.polytech.teamO.LogFormatter;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.Carte;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

public class Affichage {

    private static final Logger logger = Logger.getLogger(Affichage.class.getName());

    static{
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
    }

    public Affichage() {
    }

    public void affichePiocher(Robot robot, Carte carte) {
        logger.info("Le " + robot + " a pioché la " + carte + " dans la pioche invisible.");
    }

    public void affichePiocherSurTable(Robot robot, Carte carte) {
        logger.info("Le " + robot + " a pioché la " + carte + " dans la pioche visible.");
    }

    public void affichePoseTrain(Robot robot, Trajet trajet) {
        logger.info("Le " + robot + " a posé un train de " + trajet.getRoute().getNbCases() + " wagons entre " + trajet.getFrom() + " et " + trajet.getTo() + ".");
    }

    public void afficheIsFinished(Robot robot) {
        logger.info("Il reste deux wagons ou moins à " + robot + ". Le jeu est donc arrivé à son terme.");
    }

    public void afficheCheckCarteDestinationOK(Robot robot, CarteDestination carteDestination) {
        logger.info("Le " + robot + " a réussi la " + carteDestination + " et ajoute " + carteDestination.getValue() + " à son score.");
    }

    public void afficheCheckCarteDestinationPASOK(Robot robot, CarteDestination carteDestination) {
        logger.info("Le " + robot + " n'a pas réussi la carte destination : " + carteDestination + " et perd " + carteDestination.getValue() + " au score.");
    }

    public void afficheCarteDestinationPiocher(Robot robot, CarteDestination carteDestination) {
        logger.info("Le " + robot + " a pioché la carte destination : " + carteDestination + ".");
    }

    public void afficheCarteDestinationRefuser(Robot robot, CarteDestination carteDestination) {
        logger.info("Le " + robot + " a refusé la carte destination : " + carteDestination + ".");
    }

    public void afficheTakeMeeples(Robot robot, int nbMeeples, MCouleur couleur) {
        logger.info("Le " + robot + " prend " + nbMeeples + " passagers de couleur " + couleur + ".");
    }

    public void afficheLastScore(Ranking classement) {
        logger.info(classement.toString());
    }

    public void affichePointMeeples(MCouleur couleur, Robot first, Robot second) {
        logger.info("Le " + first + " a ramassé le plus de passagers de couleur " + couleur + " et gagne 20 points !");
        logger.info("Le " + second + " a ramassé le plus de passagers de couleur " + couleur + " et gagne 10 points !");
    }

    public void affichePointMeeplespasSecond(MCouleur couleur, Robot first) {
        logger.info("Le " + first + " a ramassé le plus de passagers de couleur " + couleur + " et gagne 20 points !");
        logger.info("Il n'y a pas de robot en deuxième position pour les passagers de couleur " + couleur + ".");
    }

    public void afficheFirstCarteDestination(Robot first) {
        logger.info("Le " + first + " a complété le plus de cartes destination et gagne 15 points ! De plus, il obtient le titre de Globetrotter.");
    }
}
