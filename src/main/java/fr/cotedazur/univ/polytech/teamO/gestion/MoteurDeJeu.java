package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;

import java.util.HashMap;

public class MoteurDeJeu {
    // Ajouter ici tous les trucs relatifs aux actions du joueur
    private Pioche pioche;
    private PiocheSurTable piocheSurTable;
    private PiocheDestinationLongue piocheDestinationLongue;
    private PiocheDestinationCourte piocheDesinationCourte;
    public MoteurDeJeu(Pioche pioche, PiocheSurTable piocheSurTable, PiocheDestinationLongue piocheDestinationLongue, PiocheDestinationCourte piocheDesinationCourte) {
        this.pioche = pioche;
        this.piocheSurTable = piocheSurTable;
        this.piocheDestinationLongue = piocheDestinationLongue;
        this.piocheDesinationCourte = piocheDesinationCourte;
    }
    // Accesseurs et setters

    public Pioche getPioche() { return pioche; }

    public PiocheSurTable getPiocheSurTable() { return piocheSurTable; }

    public PiocheDestinationLongue getPiocheDestinationLongue() {
        return piocheDestinationLongue;
    }

    public PiocheDestinationCourte getPiocheDesinationCourte() {
        return piocheDesinationCourte;
    }

    // Méthodes du jeu
    public Carte piocher(Robot robot){
        if(robot.getPiocheCount()==0){
            robot.setPiocheCount(robot.getPiocheCount()+1);
            robot.setTurnOver(false);
        }
        else{
            robot.setPiocheCount(robot.getPiocheCount()-1);
            robot.setTurnOver(true);
        }
        Carte carte=getPioche().piocher();
        robot.getWagonByCouleur().put(carte.getCouleur(),robot.getWagonByCouleur().get(carte.getCouleur())+1);
        return carte;
    }
    public Carte piocherSurTable(Robot robot,Carte carte){
        Carte cartePiochee=getPiocheSurTable().piocher(carte);
        getPiocheSurTable().addCarte(pioche.piocher());
        if(robot.getPiocheCount()==0){
            if(cartePiochee.getCouleur()==Couleur.Rainbow) {
                robot.setTurnOver(true);
            }
            else{
                robot.setTurnOver(false);
                robot.setPiocheCount(robot.getPiocheCount()+1);
            }
        }
        else{
            robot.setTurnOver(true);
            robot.setPiocheCount(robot.getPiocheCount()-1);
            if(cartePiochee.getCouleur()==Couleur.Rainbow) {
                cartePiochee = getPioche().piocher();
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
    //JE VAIS CHANGER TOUTES LES "ROUTES" PAR TRAJETS PCQ CA MARCHE PAS ENFT LES REUF C OBSOLETE CETTE IMPLEMENTATION
    public void poseTrain(Robot robot, Trajet trajet){
        Route route=trajet.getRoute();
        Couleur couleurRoute=route.getCouleur();
        if(route.isClaimable(robot.getWagonByCouleur().get(couleurRoute),new Wagon(couleurRoute))){
            route.setAvailable(false);
            robot.getWagonByCouleur().put(couleurRoute,robot.getWagonByCouleur().get(couleurRoute)-route.getNbCases());
            robot.getScore().addNbPoints(TableauDesScores.getValue(route.getNbCases()));
            for(int i=0;i<route.getNbCases();i++){
                robot.getMain().removeCarte(new CarteWagon((couleurRoute)));
            }
            robot.removeNbWagons(route.getNbCases());
            robot.addTrajetDone(trajet);
        }
    }

    public boolean isFinished(Robot robot){ // Méthode pour verifier le nombre de wagon, si c'est inferieur a deux alors fin de partie (dans un while qu'on fera pendant l'automatisation)
        return robot.getNbWagons()<2;
    }

}
