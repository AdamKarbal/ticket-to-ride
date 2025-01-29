package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;


import java.util.Random;

public class RobotAleatoire extends Robot {

    public RobotAleatoire(Couleur couleur) {
        super(couleur);
    }

    @Override
    public void chooseDestination(MoteurDeJeu moteurDeJeu){ // Le robot aléatoire choisie aléatoirement c'est carte entre une destination longue ou courte
        Random rand = new Random();
        int choice;
        for(int i=0; i<4;i++){
            choice = rand.nextInt(2)+1; // choisie entre 1 et 2 pour savoir si c'est long ou courte
            CarteDestination carteDestinationPioche=moteurDeJeu.giveDestinationsCard(this,choice);
            getDestinations().addCarte(carteDestinationPioche);
        }
    }

    @Override
    public void refuseDestinations(MoteurDeJeu moteurDeJeu){ // pour chaque carte destination le robot a 1/2 de la refuser.
        int choice;
        Random rand = new Random();
        int temp=0;
        for(int i=0;i<4;i++){
            choice = rand.nextInt(2)+1;
            if(choice==1){
                moteurDeJeu.removeDestinationsCard(this,i-temp++);
            }
        }
    }

    public void init(MoteurDeJeu moteurDeJeu){
        chooseDestination(moteurDeJeu);
        refuseDestinations(moteurDeJeu);
    }

    public void chooseaction(MoteurDeJeu moteurDeJeu,GameMap map) throws NotEnoughWagonCard {
        int choix;
        Random rand = new Random();
        choix=rand.nextInt(100)+1;
        if (1<=choix && choix<=20){ // Le robot a 20% de chance de piocher une carte, si c'est son premier tour il repioche.
            piocher(moteurDeJeu);
            if(!(isTurnOver())){
                chooseactionnotenought(moteurDeJeu);
            }
        }
        if (20< choix && choix <= 80) { // Le robot a 60% de chance de poser un train
            poseTrainAleatoire(moteurDeJeu,map);
        }
        if(80<choix && choix<=100){ // Le robot a 20% de chance de piocher dans la pioche visible
            int pos;
            pos=rand.nextInt(moteurDeJeu.getPiocheSurTable().size());
            piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(pos),moteurDeJeu);
            if(!(isTurnOver())){
                chooseactionnotenought(moteurDeJeu);
            }
        }
    }

    public void chooseactionnotenought(MoteurDeJeu moteurDeJeu){ // Même méthode mais pour le second tour s'il a piocher OU s'il a essayer de poser un train mais qu'il ne pouvait pas
        int choix;
        Random rand = new Random();
        choix=rand.nextInt(100)+1;
        if (1<=choix && choix<=50){
            piocher(moteurDeJeu);
            if(!isTurnOver()){
                chooseactionnotenought(moteurDeJeu);
            }
        }
        if(50<choix && choix<=100){
            int pos;
            pos=rand.nextInt(moteurDeJeu.getPiocheSurTable().size());
            piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(pos),moteurDeJeu);
            if(!(isTurnOver())){
                chooseactionnotenought(moteurDeJeu);
            }
        }
    }

    public void poseTrainAleatoire(MoteurDeJeu moteurDeJeu, GameMap map){
        int choice;
        int nbpassengers;
        Random rand = new Random();
        Trajet trajetRandom = null;
        try { // Ici on essaye de poser un train si c'est impossible (pas assez de carte wagon) on retourne a la méthode ou on peut que piocher
            trajetRandom = map.getTrajets().get(rand.nextInt(map.getTrajets().size()));
            poseTrain(trajetRandom, moteurDeJeu);
            choice = rand.nextInt(2)+1;
            if(choice==1){ // on choisit un meeples dans la ville from
                nbpassengers = trajetRandom.getFrom().getPassagers().size(); // on regarde le nombre de meeples dans la ville from
                if (nbpassengers == 0) { // s'il n'y a pas de meeples on quitte la méthode
                    return;
                }
                else if (nbpassengers==1) {
                    choice=0;
                }
                else {
                    choice = rand.nextInt(nbpassengers);
                }
                moteurDeJeu.TakeMeeples(this,trajetRandom,trajetRandom.getFrom(),trajetRandom.getFrom().getPassagers().get(choice).getCouleur());
            }
            if(choice==2){
                nbpassengers = trajetRandom.getTo().getPassagers().size();
                if (nbpassengers == 0) {
                    return;
                }
                else if (nbpassengers==1) {
                    choice=0;
                }
                else {
                    choice = rand.nextInt(nbpassengers);
                }
                moteurDeJeu.TakeMeeples(this,trajetRandom,trajetRandom.getTo(),trajetRandom.getTo().getPassagers().get(choice).getCouleur());
            }
        } catch (NotEnoughWagonCard e) {
            chooseactionnotenought(moteurDeJeu);
        }
    }

    @Override
    public String toString(){
        return "robot aleatoire " + getCouleur();
    }
}
