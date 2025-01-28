package fr.cotedazur.univ.polytech.teamO.affichage;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.Carte;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

public class Affichage {

    //Constructeur

    public Affichage(){
    }

    // Ici chaque méthode renvoie dans la console l'état actuel du jeu par rapport à l'action, les méthodes suivantes sont retrouvé dans
    // MoteurDeJeu, GestionEtatJeu ou Jeu.

    public void affichePiocher(Robot robot, Carte carte){
            String disp = "Le " + robot + " a pioché la " + carte + " dans la pioche invisible.\n";
            System.out.print(disp);
    }

    public void affichePiocherSurTable(Robot robot, Carte carte){
        String disp = "Le " + robot + " a pioché la " + carte + " dans la pioche visible.\n";
        System.out.print(disp);
    }

    public void affichePoseTrain(Robot robot, Trajet trajet){
        String disp = "Le " + robot + " a posé un train de "+ trajet.getRoute().getNbCases() +" wagons entre " + trajet.getFrom() +" et " + trajet.getTo() + ".\n";
        System.out.print(disp);
    }

    public void afficheIsFinished(Robot robot){
        String disp = "Il reste deux wagons ou moins à " + robot + ". Le jeu est donc arrivé à son terme. \n";
        System.out.println(disp);
    }

    public void afficheCheckCarteDestinationOK(Robot robot, CarteDestination carteDestination){
        String disp = "Le " + robot + " a réussi la " + carteDestination + " et ajoute " + carteDestination.getValue() +" à son score.\n";
        System.out.print(disp);
    }

    public void afficheCheckCarteDestinationPASOK(Robot robot, CarteDestination carteDestination){
        String disp = "Le " + robot + " n'as pas réussie la carte destination : " + carteDestination + " et perd " + carteDestination.getValue() +" au score. \n";
        System.out.print(disp);
    }

    public void afficheCarteDestinationPiocher(Robot robot, CarteDestination carteDestination){
        String disp = "Le " + robot + " à piocher la carte destination : " + carteDestination +". \n";
        System.out.print(disp);
    }

    public void afficheCarteDestinationRefuser(Robot robot, CarteDestination carteDestination){
        String disp = "Le " + robot + " à refuser la carte destination : " + carteDestination + ". \n";
        System.out.print(disp);
    }

    public void afficheTakeMeeples(Robot robot, int nbMeeples, MCouleur couleur){
        String disp = "Le " + robot + " prends " + nbMeeples + " passagers de couleur "+ couleur + ".\n";
        System.out.print(disp);

    }

    public void afficheLastScore(Ranking classement){
        System.out.println(classement);
    }

    public void affichePointMeeples(MCouleur couleur, Robot first, Robot second){
        String disp1 = "Le " + first + " a ramasser le plus de passagers de couleur " + couleur + " et gagne 20 points ! \n";
        String disp2 =  "Le " + second + " a ramasser le plus de passagers de couleur " + couleur + " et gagne 10 points ! \n";
        System.out.print(disp1);
        System.out.print(disp2);
    }

    public void affichePointMeeplespasSecond(MCouleur couleur,Robot first){
        String disp1 = "Le " + first + " a ramasser le plus de passagers de couleur " + couleur + " et gagne 20 points ! \n";
        String disp2 = "Il n'y a pas de robot en deuxieme position pour les passagers de couleur " + couleur + "\n";
        System.out.print(disp1);
        System.out.print(disp2);
    }

}
