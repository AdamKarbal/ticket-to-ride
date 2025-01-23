package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.affichage.Affichage;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MoteurDeJeu {
    // Ajouter ici tous les trucs relatifs aux actions du joueur
    private Affichage affichage;
    private Pioche pioche;
    private PiocheSurTable piocheSurTable;
    private PiocheDestinationLongue piocheDestinationLongue;
    private PiocheDestinationCourte piocheDesinationCourte;
    public MoteurDeJeu(Pioche pioche, PiocheSurTable piocheSurTable, PiocheDestinationLongue piocheDestinationLongue, PiocheDestinationCourte piocheDesinationCourte) {
        this.pioche = pioche;
        this.piocheSurTable = piocheSurTable;
        this.piocheDestinationLongue = new PiocheDestinationLongue();
        this.piocheDesinationCourte = new PiocheDestinationCourte();
        this.affichage = new Affichage();
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
        //affichage.affichePiocher(robot,carte);
        robot.getWagonByCouleur().put(carte.getCouleur(),robot.getWagonByCouleur().get(carte.getCouleur())+1);
        return carte;
    }
    public Carte piocherSurTable(Robot robot,Carte carte){
        Carte cartePiochee=getPiocheSurTable().piocher(carte);
        affichage.affichePiocherSurTable(robot,cartePiochee);
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
    public CarteDestination giveDestinationsCard(Robot robot, int choice){
        CarteDestination carteDestinationL=piocheDestinationLongue.popCartesDestinationLongue();
        CarteDestination carteDestinationC=piocheDesinationCourte.popCartesDestinationCourtes(); // devrait être pop ?
        if(choice==1)return carteDestinationC;
        if(choice==2)return carteDestinationL;
        else return null;
    }
    public void removeDestinationsCard(Robot robot,int choice){
        List<CarteDestination> cartesEnlevees= new ArrayList<>();
        for(int i=0;i<choice;i++){
            cartesEnlevees.add(robot.getDestinations().getMainDeCartes().remove(i));
        //Enlève le nb de cartes non voulues
        }
        for(CarteDestination carteDestination:cartesEnlevees){
            if(carteDestination.isLongue())piocheDestinationLongue.pushCartesDestinationLongues(carteDestination);
            else piocheDesinationCourte.pushCartesDestinationCourtes(carteDestination);
        }//Remet dans la pioche correspondante
        Collections.shuffle(piocheDesinationCourte.getCartesDestinationCourtes()); // shuffle dans la pioche correspondante directement ?
        Collections.shuffle(piocheDestinationLongue.getCartesDestinationLongues());


    }
    public static HashMap<Couleur,Integer> initWagonByCouleur(Robot robot){
        HashMap<Couleur,Integer> carteWagonByCouleur=new HashMap<>();
        for(Couleur couleur:Couleur.values()){
            carteWagonByCouleur.put(couleur,0);
        }
        return carteWagonByCouleur;
    }

    public void poseTrain(Robot robot, Trajet trajet){
        Route route=trajet.getRoute();
        Couleur couleurRoute=route.getCouleur();
        // x réprésentera le nombre de wagons posés dans le tour
        if(route.isClaimable(robot.getWagonByCouleur().get(couleurRoute),new Wagon(couleurRoute))){
            route.setAvailable(false);
            robot.getWagonByCouleur().put(couleurRoute,robot.getWagonByCouleur().get(couleurRoute)-route.getNbCases()); //enlève x nombre de wagons de la couleur correspondante
            robot.getScore().addNbPoints(TableauDesScores.getValue(route.getNbCases())); // ajoute le score correspondant à x
            for(int i=0;i<route.getNbCases();i++){
                robot.getMain().removeCarte(new CarteWagon((couleurRoute))); //va enlever x carte carteWagon de la main du joueur
            }
            robot.removeNbWagons(route.getNbCases()); // décremente de x le nombre de wagons total du joueur
            robot.addTrajetDone(trajet); // ajoute le trajet aux trajets déjà faits du joueur
            affichage.affichePoseTrain(robot,trajet);
        }
    }

    public boolean isFinished(Robot robot){ // Méthode pour verifier le nombre de wagon, si c'est inferieur a deux alors fin de partie (dans un while qu'on fera pendant l'automatisation)
        return robot.getNbWagons()<2; // refactor pour prendre une liste de robots ?
    }

    public void checkCarteDestination(Robot robot) {
        isCarteDestinationClaim(robot);
        for (CarteDestination carteDestination : robot.getDestinations().getMainDeCartes())
        {
                if(!(carteDestination.isCompleted())){
                    robot.getScore().removeNbPoints(carteDestination.getValue());
                    affichage.afficheCheckCarteDestinationPASOK(robot,carteDestination);
                }
                else {
                    robot.getScore().addNbPoints(carteDestination.getValue());
                    affichage.afficheCheckCarteDestinationOK(robot,carteDestination);
                }
        }
    }

    public void isCarteDestinationClaim(Robot robot){
        // la il me faut la maindecartedestination comme ça je parcours tout les cartes de destination et je regarde si les trajet sont fait
        // et après j'attribue les points avec l'autre fonction.
        for(CarteDestination carteDestination : robot.getDestinations().getMainDeCartes()){

        }
    }
}
