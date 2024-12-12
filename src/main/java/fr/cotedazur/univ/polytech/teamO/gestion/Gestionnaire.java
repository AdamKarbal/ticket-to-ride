package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.Carte;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteWagon;
import fr.cotedazur.univ.polytech.teamO.pioche.Pioche;
import fr.cotedazur.univ.polytech.teamO.pioche.PiocheSurTable;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;

import java.util.HashMap;

public class Gestionnaire {
    // Ajouter ici tous les trucs relatifs au jeu et qu'on peut modifier pendant la partie
    public static Pioche pioche=new Pioche();
    public static PiocheSurTable piocheSurTable=new PiocheSurTable();
    // Méthodes du jeu
    public static Carte piocher(Robot robot){
        if(robot.getPiocheCount()==0){
            robot.setPiocheCount(robot.getPiocheCount()+1);
            robot.setTurnOver(false);
        }
        else{
            robot.setPiocheCount(robot.getPiocheCount()-1);
            robot.setTurnOver(true);
        }
        Carte carte=pioche.piocher();
        robot.getWagonByCouleur().put(carte.getCouleur(),robot.getWagonByCouleur().get(carte.getCouleur())+1);
        return carte;
    }
    public static Carte piocherSurTable(Robot robot,Carte carte){
        Carte cartePiochee=piocheSurTable.piocher(carte);
        piocheSurTable.addCarte(pioche.piocher());
        if(robot.getPiocheCount()==0){
            robot.getMain().addCarte(cartePiochee);
            if(cartePiochee.getCouleur()==Couleur.Rainbow) {
                robot.setTurnOver(true);
            }
            else{
                robot.setTurnOver(false);
                robot.setPiocheCount(robot.getPiocheCount()+1);
            }
        }
        else{
            if(cartePiochee.getCouleur()!=Couleur.Rainbow) {
                robot.setTurnOver(true);
                robot.setPiocheCount(robot.getPiocheCount()-1);
                robot.getMain().addCarte(cartePiochee);
            }
            else{
                return piocher(robot);
            }
        }
        return cartePiochee;
    }
    public static HashMap<Couleur,Integer> initWagonByCouleur(Robot robot){
        HashMap<Couleur,Integer> carteWagonByCouleur=new HashMap<>();
        for(Couleur couleur:Couleur.values()){
            carteWagonByCouleur.put(couleur,0);
        }
        return carteWagonByCouleur;
    }
    public static void poseTrain(Robot robot,Route route){
        Couleur couleurRoute=route.getCouleur();
        if(route.isClaimable(robot.getWagonByCouleur().get(couleurRoute),new Wagon(couleurRoute))){
            route.setAvailable(false);
            robot.getWagonByCouleur().put(couleurRoute,robot.getWagonByCouleur().get(couleurRoute)-route.getNbCases());
            robot.getScore().addNbPoints(route.getNbCases());
            for(int i=0;i<route.getNbCases();i++){
                robot.getMain().removeCarte(new CarteWagon((couleurRoute)));
            }
            robot.removeNbWagons(route.getNbCases());
        }
    }

    public boolean isFinished(Robot robot){ // Méthode pour verifier le nombre de wagon, si c'est inferieur a deux alors fin de partie (dans un while qu'on fera pendant l'automatisation)
        int total = 0;
        for (int value : robot.getWagonByCouleur().values()) {
            total += value;
        }
        if(total<=2){
            return false;
        }
        return true;
    }

}
