package fr.cotedazur.univ.polytech.teamO.affichage;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.pioche.Carte;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;

public class Affichage {

    //Constructeur

    public Affichage(){
    }
    //Adam
    public void affichePiocher(Robot robot, Carte carte){
            String disp = "Le " + robot + " à piocher la carte : " + carte + " dans la pioche invisible. \n";
            System.out.print(disp);
    }
    //Adam
    public void affichePiocherSurTable(Robot robot, Carte carte){
        String disp = "Le " + robot + " à piocher la carte : " + carte + " dans la pioche visible. \n";
        System.out.println(disp);
    }
    //Younes
    public void affichePoseTrain(Robot robot, Route route){
        String disp = "" + robot + "a posé une route sur ";

    }
    //Younes
    public void afficheIsFinished(Robot robot){

    }
    //Adam
    public void afficheCheckCarteDestinationOK(Robot robot, CarteDestination carteDestination){
        String disp = "Le " + robot + " à réussi la carte destination : " + carteDestination + " et ajoute " + carteDestination.getValue() +" au score. \n";
        System.out.println(disp);
    }

    public void afficheCheckCarteDestinationPASOK(Robot robot, CarteDestination carteDestination){
        String disp = "Le " + robot + " n'a pas réussi la carte destination : " + carteDestination + " et perd " + carteDestination.getValue() +" au score. \n";
        System.out.println(disp);
    }

    //Younes
    public void afficheTakeMeeples(){

    }
    //Younes
    public void afficheLastScore(){

    }

}
