package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.affichage.Affichage;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;

import java.util.*;

public class MoteurDeJeu {

    // Ajouter ici tous les trucs relatifs aux actions du joueur
    private Affichage affichage;
    private Pioche pioche;
    private Pioche defaussePioche;
    private PiocheSurTable piocheSurTable;
    private PiocheDestinationLongue piocheDestinationLongue;
    private PiocheDestinationCourte piocheDesinationCourte;

    public MoteurDeJeu(Pioche pioche, PiocheSurTable piocheSurTable, PiocheDestinationLongue piocheDestinationLongue, PiocheDestinationCourte piocheDesinationCourte) {
        this.pioche = pioche;
        this.piocheSurTable = piocheSurTable;
        this.piocheDestinationLongue = piocheDestinationLongue;
        this.piocheDesinationCourte = piocheDesinationCourte;
        this.affichage = new Affichage();
        this.defaussePioche = new Pioche();
        this.defaussePioche.viderPioche();
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
    // Méthodes intermédiaires
    private void updateRobotPioche(Robot robot) {
        if (robot.getPiocheCount() == 0) {
            robot.setPiocheCount(robot.getPiocheCount() + 1);
            robot.setTurnOver(false);
        } else {
            robot.setPiocheCount(robot.getPiocheCount() - 1);
            robot.setTurnOver(true);
        }
    }
    private Carte piocherCarte(Robot robot) {
        Carte carte = getPioche().piocher();
        affichage.affichePiocher(robot, carte);
        robot.getWagonByCouleur().put(
                carte.getCouleur(),
                robot.getWagonByCouleur().get(carte.getCouleur()) + 1
        );
        return carte;
    }
    private void updateRobotPiocheSurTable(Robot robot, Carte cartePiochee) {
        Couleur couleurCarte= cartePiochee.getCouleur();
        boolean isRainbow = couleurCarte== Couleur.Rainbow;

        if (robot.getPiocheCount() == 0) {
            robot.setTurnOver(isRainbow);
            robot.getWagonByCouleur().put(couleurCarte, robot.getWagonByCouleur().get(couleurCarte) + 1);
            if (!isRainbow) robot.setPiocheCount(1);
        }
        else {
            robot.setTurnOver(true);
            robot.setPiocheCount(robot.getPiocheCount() - 1);
            if (isRainbow) {
                robot.getWagonByCouleur().put(pioche.piocher().getCouleur(), robot.getWagonByCouleur().get(pioche.piocher().getCouleur()) + 1);
            }
            else{
                robot.getWagonByCouleur().put(couleurCarte, robot.getWagonByCouleur().get(couleurCarte) + 1);
            }
        }
    }
    private Couleur findMaxWagon(Robot robot){
        Couleur couleurMax = Couleur.Default;
        int max=0;
        for(Couleur couleur: robot.getWagonByCouleur().keySet()){
            if(max<robot.getWagonByCouleur().get(couleur)){
                max=robot.getWagonByCouleur().get(couleur);
                couleurMax = couleur;
            }
        }
        return couleurMax;
    }
    private Couleur determineRouteColor(Robot robot, Route route) {
        return route.getCouleur().equals(Couleur.Default) ? findMaxWagon(robot) : route.getCouleur();
    }

    private void processRouteClaim(Robot robot, Trajet trajet, Route route, Couleur couleurRoute) {
        route.setAvailable(false);
        for (int i = 0; i < route.getNbCases(); i++) {
            defaussePioche.getPioche().push(new CarteWagon(couleurRoute));
        }
        affichage.affichePoseTrain(robot, trajet);
    }

    private void updateRobotWagonData(Robot robot, Route route, Trajet trajet, Couleur couleurRoute) {
        int nbCases = route.getNbCases();
        robot.getWagonByCouleur().put(couleurRoute, robot.getWagonByCouleur().get(couleurRoute) - nbCases);
        robot.getScore().addNbPoints(TableauDesScores.getValue(nbCases));
        robot.removeNbWagons(nbCases);
        robot.addTrajetDone(trajet);
    }

    // Méthodes du jeu
    public Carte piocher(Robot robot) {
        try {
            updateRobotPioche(robot);
            return piocherCarte(robot);
        } catch (EmptyStackException e) {
            replacePiocheByDefausse();
            updateRobotPioche(robot);
            return piocherCarte(robot);
        }
    }


    public Carte piocherSurTable(Robot robot,Carte carte) throws NullPointerException{
        try{
            Carte cartePiochee=getPiocheSurTable().piocher(carte);
            affichage.affichePiocherSurTable(robot,cartePiochee);
            try {
                getPiocheSurTable().addCarte(pioche.piocher());
            }
            catch(EmptyStackException e) {
                replacePiocheByDefausse();
                getPiocheSurTable().addCarte(pioche.piocher());
            }
        updateRobotPiocheSurTable(robot,cartePiochee);
        return cartePiochee;}
        catch(NullPointerException e){
            throw e;
        }
    }

    public void replacePiocheByDefausse(){
        pioche=defaussePioche;
    }

    public CarteDestination giveDestinationsCard(Robot robot, int choice){
        CarteDestination carteDestination = null;
        if(choice==1) carteDestination= piocheDesinationCourte.popCartesDestinationCourtes();
        if(choice==2) carteDestination = piocheDestinationLongue.popCartesDestinationLongue();
        affichage.afficheCarteDestinationPiocher(robot,carteDestination);
        return carteDestination;
    }
    public void removeDestinationsCard(Robot robot,int choice){//enleve la carte destination a la place choice de son deck
        CarteDestination carteDestination = robot.getDestinations().getMainDeCartes().remove(choice);
        affichage.afficheCarteDestinationRefuser(robot,carteDestination);
        if(carteDestination.isLongue())piocheDestinationLongue.pushCartesDestinationLongues(carteDestination);
        else piocheDesinationCourte.pushCartesDestinationCourtes(carteDestination);
        //Remet dans la pioche correspondante
        Collections.shuffle(piocheDesinationCourte.getCartesDestinationCourtes());
        Collections.shuffle(piocheDestinationLongue.getCartesDestinationLongues());
    }

    public static HashMap<Couleur,Integer> initWagonByCouleur(Robot robot){
        HashMap<Couleur,Integer> carteWagonByCouleur=new HashMap<>();
        for(Couleur couleur:Couleur.values()){
            if(!couleur.equals(Couleur.Default) && !couleur.equals(Couleur.Marron)) carteWagonByCouleur.put(couleur,0);
        }
        return carteWagonByCouleur;
    }

    public void poseTrain(Robot robot, Trajet trajet) throws NotEnoughWagonCard { // prendre en compte le cas où on a assez de locomotives pour terminer un trajet
        Route route = trajet.getRoute();
        Couleur couleurRoute = determineRouteColor(robot, route);

        if (route.isClaimable(robot.getWagonByCouleur().get(couleurRoute), new Wagon(couleurRoute))) {
            processRouteClaim(robot, trajet, route, couleurRoute);
            updateRobotWagonData(robot, route, trajet, couleurRoute);
        } else {
            throw new NotEnoughWagonCard(robot + " n'a pas assez de carte wagon.");
        }
    }


    public void poseTrainPUp(Robot robot, Trajet trajet, Ville villeChoisie, MCouleur meepleChoisi) throws NotEnoughWagonCard {
        poseTrain(robot,trajet);
        robot.setTaking(true);
        TakeMeeples(robot, trajet, villeChoisie, meepleChoisi);
    }

    public boolean isFinished(List<Robot> robots){ // Méthode pour verifier le nombre de wagon, si c'est inferieur a deux alors fin de partie (dans un while qu'on fera pendant l'automatisation)
        for(Robot robot:robots){
            if(robot.getNbWagons()<2 || (getPioche().getPioche().empty() && defaussePioche.getPioche().empty())){
                affichage.afficheIsFinished(robot);
                return true;
            }
        }
        return false;
    }

    public void TakeMeeples(Robot robot, Trajet trajet, Ville villeChoisie, MCouleur meepleChoisi){
        if(villeChoisie == trajet.getFrom() || villeChoisie == trajet.getTo()){
            robot.addMeeple(meepleChoisi);
            villeChoisie.getPassagers().remove(meepleChoisi);
            affichage.afficheTakeMeeples(robot,1,meepleChoisi);
        }
    }

}
