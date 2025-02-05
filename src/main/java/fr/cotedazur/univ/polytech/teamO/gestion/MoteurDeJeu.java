package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.affichage.Affichage;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.bot.RobotRoute;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;
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

    /**
     * Cette méthode permet de definir si le joueur peut repiocher ou pas après.
     * @param robot
     */
    private void updateRobotPioche(Robot robot) {
        if (robot.getPiocheCount() == 0) {
            robot.setPiocheCount(robot.getPiocheCount() + 1);
            robot.setTurnOver(false);
        } else {
            robot.setPiocheCount(robot.getPiocheCount() - 1);
            robot.setTurnOver(true);
        }
    }

    /**
     * Cette méthode permet de piocher une carte de le donner à un joueur, et d'afficher ce qui ce passe
     * @param robot
     * @return
     */
    private Carte piocherCarte(Robot robot) {
        Carte carte = getPioche().piocher();
        affichage.affichePiocher(robot, carte);
        robot.getWagonByCouleur().put(
                carte.getCouleur(),
                robot.getWagonByCouleur().get(carte.getCouleur()) + 1
        );
        return carte;
    }

    /**
     * Cette méthode permet de definir ce que le robot à le droit de faire en fonction de ce qu'il pioche sur la table.
     * @param robot
     * @param cartePiochee
     */
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

    /**
     * Cette méthode permet de trouver la couleur de carte wagon que le robot possède en plus grande quantité.
     * @param robot
     * @return
     */
    public Couleur findMaxWagon(Robot robot) {
        if (robot == null || robot.getWagonByCouleur() == null || robot.getWagonByCouleur().isEmpty()) {
            return Couleur.Default; // Valeur par défaut si aucune donnée disponible
        }
        Couleur couleurMax = Couleur.Default;
        int max = 0;
        for (Map.Entry<Couleur, Integer> entry : robot.getWagonByCouleur().entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                couleurMax = entry.getKey();
            }
        }
        return couleurMax;
    }

    public Couleur findMinWagon(Robot robot) {
        if (robot == null || robot.getWagonByCouleur() == null || robot.getWagonByCouleur().isEmpty()) {
            return Couleur.Default; // Valeur par défaut si aucune donnée disponible
        }
        Couleur couleurMin = Couleur.Default;
        int min = 0;
        for (Map.Entry<Couleur, Integer> entry : robot.getWagonByCouleur().entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                couleurMin = entry.getKey();
            }
        }
        return couleurMin;
    }

    /**
     * Cette méthode permet de donner la couleur qu'on doit utiliser pour la route : Si la route est grise alors on prend la couleur avec le plus de carte wagon
     * sinon la couleur de la route
     * @param robot
     * @param route
     * @return
     */
    private Couleur determineRouteColor(Robot robot, Route route) {
        return route.getCouleur().equals(Couleur.Default) ? findMaxWagon(robot) : route.getCouleur();
    }

    /**
     * Cette méthode permet de claim la route, d'ajouter a la defausse les cartes et d'afficher le message suite à l'action.
     * @param robot
     * @param trajet
     * @param route
     * @param couleurRoute
     */
    private void processRouteClaim(Robot robot, Trajet trajet, Route route, Couleur couleurRoute) {
        route.setAvailable(false);
        for (int i = 0; i < route.getNbCases(); i++) {
            defaussePioche.getPioche().push(new CarteWagon(couleurRoute));
        }
        affichage.affichePoseTrain(robot, trajet);
    }

    /**
     * Cette méthode permet de mettre à jour le nombre de wagon, de dire que le trajet et fait, d'ajouter les points en fonction de la route prise.
     * @param robot
     * @param route
     * @param trajet
     * @param couleurRoute
     */
    private void updateRobotWagonData(Robot robot, Route route, Trajet trajet, Couleur couleurRoute) {
        int nbCases = route.getNbCases();
        int nbRainbow=robot.getWagonByCouleur().get(Couleur.Rainbow);
        int nbWagontoDiscard = nbCases-robot.getWagonByCouleur().get(couleurRoute);
        if(rainbowCase(robot,trajet)){
            robot.getWagonByCouleur().put(Couleur.Rainbow, nbRainbow-nbWagontoDiscard);
            robot.getWagonByCouleur().put(couleurRoute, 0);
        }
        else {
            robot.getWagonByCouleur().put(couleurRoute, robot.getWagonByCouleur().get(couleurRoute) - nbCases);
        }
        robot.getScore().addNbPoints(TableauDesScores.getValue(nbCases));
        robot.removeNbWagons(nbCases);
        robot.addTrajetDone(trajet);
    }

    // Méthodes du jeu

    /**
     * Cette méthode permet de piocher et de donner la carte au robot, elle handle le cas ou la pioche est vide et la remplace par la defausse
     * @param robot
     * @return
     */
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

    /**
     * cette méthode permet de piocher sur la table, et faire l'affichage en consequence, il permet aussi de reremplir la pioche visible
     * si on pioche, tout en faisant attention a si la pioche devient vide.
     * @param robot
     * @param carte
     * @return
     * @throws NotEnoughWagonCard si le robot a pas assez de wagon pour poser un train il throw l'exception
     */
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

    /**
     * Cette méthode remplace la pioche par la defausse
     */
    public void replacePiocheByDefausse(){
        pioche=defaussePioche;
        Collections.shuffle(pioche.getPioche());
    }

    /**
     * Cette methode permet de donner une carte destination, et de faire l'affichage
     * @param robot
     * @param choice si le choix est égale a 1 alors on pioche une destination courte, sinon une longue
     * @return
     */
    public CarteDestination giveDestinationsCard(Robot robot, int choice){
        CarteDestination carteDestination = null;
        if(choice==1) carteDestination= piocheDesinationCourte.popCartesDestinationCourtes();
        if(choice==2) carteDestination = piocheDestinationLongue.popCartesDestinationLongue();
        affichage.afficheCarteDestinationPiocher(robot,carteDestination);
        return carteDestination;
    }

    /**
     * Cette méthode permet d'enlever une carte destination au robot, et de la rajouter a la pioche correspondante
     * @param robot
     * @param choice
     */
    public void removeDestinationsCard(Robot robot,int choice){//enleve la carte destination a la place choice de son deck
        CarteDestination carteDestination = robot.getDestinations().getMainDeCartes().remove(choice);
        affichage.afficheCarteDestinationRefuser(robot,carteDestination);
        if(carteDestination.isLongue())piocheDestinationLongue.pushCartesDestinationLongues(carteDestination);
        else piocheDesinationCourte.pushCartesDestinationCourtes(carteDestination);
        //Remet dans la pioche correspondante
        Collections.shuffle(piocheDesinationCourte.getCartesDestinationCourtes());
        Collections.shuffle(piocheDestinationLongue.getCartesDestinationLongues());
    }

    /**
     * Cette méthode permet d'initialiser les wagons par couleur du robot
     * @param robot
     * @return
     */
    public static Map<Couleur,Integer> initWagonByCouleur(Robot robot){
        Map<Couleur,Integer> carteWagonByCouleur=new HashMap<>();
        for(Couleur couleur:Couleur.values()){
            if(!couleur.equals(Couleur.Default) && !couleur.equals(Couleur.Marron)) carteWagonByCouleur.put(couleur,0);
        }
        return carteWagonByCouleur;
    }

    /**
     *Cette méthode permet de poser des wagons sur une route, il met a jour tout ce qui est en rapport avec l'action
     * On updateRobotWagonData, on procede au claimage de la route et on update les trajets disponible.
     * @param robot
     * @param trajet
     * @param gestionTrajets
     * @throws NotEnoughWagonCard si le robot a pas assez de wagon pour poser un train il throw l'exception
     */
    public void poseTrain(Robot robot, Trajet trajet,GestionTrajets gestionTrajets) throws NotEnoughWagonCard { // prendre en compte le cas où on a assez de locomotives pour terminer un trajet
        Route route = trajet.getRoute();
        Couleur couleurRoute = determineRouteColor(robot, route);
        int nombreWagons = robot.getWagonByCouleur().getOrDefault(couleurRoute, 0);
        if (route.isClaimable(nombreWagons, new Wagon(couleurRoute)) || rainbowCase(robot, trajet)) {
            updateRobotWagonData(robot, route, trajet, couleurRoute);
            processRouteClaim(robot, trajet, route, couleurRoute);
            gestionTrajets.updateTrajetsDisponibles(trajet);
            robot.addNbRoute();
        } else {
            throw new NotEnoughWagonCard(robot + " n'a pas assez de carte wagon.");
        }
    }

    /**
     * Cette méthode permet de verifier si la partie est finie ou non (moins de 2 wagons)
     * @param robots
     * @return
     */
    public boolean isFinished(List<Robot> robots){ // Méthode pour verifier le nombre de wagon, si c'est inferieur a deux alors fin de partie (dans un while qu'on fera pendant l'automatisation)
        for(Robot robot:robots){
            if(robot.getNbWagons()<2 || (getPioche().getPioche().empty() && defaussePioche.getPioche().empty())){
                affichage.afficheIsFinished(robot);
                return true;
            }
        }
        return false;
    }

    /**
     * Cette méthode permet de prendre un meeple dans la ville choisie par le robot et de le donner au robot
     * @param robot
     * @param ville
     * @param meepleChoisi
     */
    public void takeMeeples(Robot robot, Ville ville, MCouleur meepleChoisi){
        Meeples meepleToPick = new Meeples(meepleChoisi);
        ville.removePassenger(meepleToPick);
        robot.addMeeple(meepleChoisi);
    }

    /**
     * Cette méthode permet de voir si le robot a assez de wagon de la même couleur de la route et de couleur rainbow
     * pour claim une route
     * @param robot
     * @param trajet
     * @return
     */
    public boolean rainbowCase(Robot robot, Trajet trajet) {
        Route route = trajet.getRoute();
        Couleur couleurRoute = determineRouteColor(robot, route);
        int nbCouleurWagon = robot.getWagonByCouleur().getOrDefault(couleurRoute,0);
        int nbRainbowCard = robot.getWagonByCouleur().getOrDefault(Couleur.Rainbow,0);
        if (nbRainbowCard == 0) return false;
        else return route.isClaimable(nbRainbowCard + nbCouleurWagon, new Wagon(couleurRoute));

    }

    /**
     * Cette méthode permet de de poser un train et de prendre les meeples en fonction de la ville choisie du trajet
     * entre ville from et ville to
     * @param robot
     * @param trajet
     * @param From
     * @param To
     * @param meepleChoisiFrom
     * @param meepleChoisiTo
     * @param gestionTrajets
     * @throws NotEnoughWagonCard si le robot a pas assez de wagon pour poser un train il throw l'exception
     */
    public void poseTrainPUp(Robot robot, Trajet trajet, boolean From, boolean To, MCouleur meepleChoisiFrom, MCouleur meepleChoisiTo, GestionTrajets gestionTrajets) throws NotEnoughWagonCard {
        poseTrain(robot,trajet,gestionTrajets);
        if(From) takeMeeples(robot, trajet.getFrom(), meepleChoisiFrom);
        if(To) takeMeeples(robot, trajet.getTo(), meepleChoisiTo);
    }

    public void sortDestinationsCardByPathNb(RobotRoute robotRoute,Map<CarteDestination,Integer> nbCheminsParCarte){
        List<CarteDestination> mainJoueur=robotRoute.getDestinations().getMainDeCartes();
        List<Map.Entry<CarteDestination, Integer>> destinationsSorted = new ArrayList<>(nbCheminsParCarte.entrySet());
        destinationsSorted.sort(Map.Entry.comparingByValue());
        mainJoueur.clear();
        for(Map.Entry<CarteDestination,Integer> entry: destinationsSorted){
            mainJoueur.add(entry.getKey());
        }
    }

}


