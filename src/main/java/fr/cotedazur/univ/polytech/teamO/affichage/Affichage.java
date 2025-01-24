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
        // c'est bon :)
    }
    //Adam
    public String affichePiocher(Robot robot, Carte carte){
            String disp = "Le " + robot + " a pioché la " + carte + " dans la pioche invisible.\n";
            System.out.print(disp);
            return disp;
    }
    //Adam
    public String affichePiocherSurTable(Robot robot, Carte carte){
        String disp = "Le " + robot + " a pioché la " + carte + " dans la pioche visible.\n";
        System.out.print(disp);
        return disp;
    }
    //Younes
    public String affichePoseTrain(Robot robot, Trajet trajet){
        String disp = "Le " + robot + " a posé un train de "+ trajet.getRoute().getNbCases() +" wagons entre " + trajet.getFrom() +" et " + trajet.getTo() + ".\n";
        System.out.print(disp);
        return disp;
    }
    //Younes
    public String afficheIsFinished(Robot robot){
        String disp = "Il reste deux wagons ou moins à " + robot + ". Le jeu est donc arrivé à son terme. \n";
        System.out.println(disp);
        return disp;
    }
    //Adam
    public String afficheCheckCarteDestinationOK(Robot robot, CarteDestination carteDestination){
        String disp = "Le " + robot + " a réussi la " + carteDestination + " et ajoute " + carteDestination.getValue() +" à son score.\n";
        System.out.println(disp);
        return disp;
    }

    public String afficheCheckCarteDestinationPASOK(Robot robot, CarteDestination carteDestination){
        String disp = "Le " + robot + " n'as pas réussie la carte destination : " + carteDestination + " et perd " + carteDestination.getValue() +" au score. \n";
        System.out.println(disp);
        return disp;
    }

    //Younes
    public String afficheTakeMeeples(Robot robot, int nbMeeples, MCouleur couleur){
        String disp = "Le " + robot + " prends " + nbMeeples + " passagers de couleur "+ couleur + ".\n";
        System.out.println(disp);
        return disp;
    }
    //Younes
    public void afficheLastScore(Ranking classement){
        System.out.println(classement);
    }

}
