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
    private Scanner scanner = new Scanner(System.in);

    public MoteurDeJeu(Pioche pioche, PiocheSurTable piocheSurTable, PiocheDestinationLongue piocheDestinationLongue, PiocheDestinationCourte piocheDesinationCourte) {
        this.pioche = pioche;
        this.piocheSurTable = piocheSurTable;
        this.piocheDestinationLongue = new PiocheDestinationLongue();
        this.piocheDesinationCourte = new PiocheDestinationCourte();
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

    // Méthodes du jeu
    public Carte piocher(Robot robot) {
        try {
            if (robot.getPiocheCount() == 0) {
                robot.setPiocheCount(robot.getPiocheCount() + 1);
                robot.setTurnOver(false);
            } else {
                robot.setPiocheCount(robot.getPiocheCount() - 1);
                robot.setTurnOver(true);
            }
            return piocherCarte(robot);
        } catch (EmptyStackException e) {
            replacePiocheByDefausse();
            return piocherCarte(robot);
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

    public Carte piocherSurTable(Robot robot,Carte carte){
        Carte cartePiochee=getPiocheSurTable().piocher(carte);
        affichage.affichePiocherSurTable(robot,cartePiochee);
        try {
            getPiocheSurTable().addCarte(pioche.piocher());
        } catch(EmptyStackException e) {
            replacePiocheByDefausse();
        }
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

    public void replacePiocheByDefausse(){
        pioche=defaussePioche;
    }

    public CarteDestination giveDestinationsCard(Robot robot, int choice){
        CarteDestination carteDestinationL=piocheDestinationLongue.peekCartesDestinationLongue();
        CarteDestination carteDestinationC=piocheDesinationCourte.peekCartesDestinationCourtes();
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
    public void poseTrain(Robot robot, Trajet trajet) throws NotEnoughWagonCard {
        Route route=trajet.getRoute();
        Couleur couleurRoute=route.getCouleur();
        if(couleurRoute.equals(Couleur.Default)){
            couleurRoute=findMaxWagon(robot);
        }
        if(route.isClaimable(robot.getWagonByCouleur().get(couleurRoute),new Wagon(couleurRoute))){
            route.setAvailable(false);
            robot.getWagonByCouleur().put(couleurRoute,robot.getWagonByCouleur().get(couleurRoute)-route.getNbCases());
            robot.getScore().addNbPoints(TableauDesScores.getValue(route.getNbCases()));
            for(int i=0;i<route.getNbCases();i++){
                CarteWagon carteWagon=new CarteWagon(couleurRoute);
                robot.getMain().removeCarte(carteWagon);
                defaussePioche.getPioche().push(carteWagon);

            }
            affichage.affichePoseTrain(robot,trajet);
            robot.removeNbWagons(route.getNbCases());
        }
        else{
            throw new NotEnoughWagonCard(robot + "n'a pas assez de carte wagon. \n");
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

    public void TakeMeeples(Robot robot, Trajet trajet, Ville villeChoisie, MCouleur meepleChoisi){
        if(villeChoisie == trajet.getFrom() || villeChoisie == trajet.getTo()){
            robot.addMeeple(meepleChoisi);
            villeChoisie.getPassagers().remove(meepleChoisi);
        }
    }
}
