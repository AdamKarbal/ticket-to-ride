package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;


import java.util.Random;

public class RobotAleatoire extends Robot {
    private Random rand;

    public RobotAleatoire(Couleur couleur, Random random) {
        super(couleur);
        this.rand = random;
    }

    @Override
    public void chooseDestination(MoteurDeJeu moteurDeJeu){ // Le robot aléatoire choisie aléatoirement c'est carte entre une destination longue ou courte
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

    public void chooseaction(MoteurDeJeu moteurDeJeu, GameMap map, GestionTrajets gestionTrajets) throws NotEnoughWagonCard {
        int choix;
        choix=rand.nextInt(100)+1;
        if (1<=choix && choix<=20){ // Le robot a 20% de chance de piocher une carte, si c'est son premier tour il repioche.
            piocher(moteurDeJeu);
            if(!(isTurnOver())){
                chooseactionnotenought(moteurDeJeu);
            }
        }
        if (20< choix && choix <= 80) { // Le robot a 60% de chance de poser un train
            poseTrainAleatoire(moteurDeJeu,map,gestionTrajets);
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

    public void poseTrainAleatoire(MoteurDeJeu moteurDeJeu, GameMap map,GestionTrajets gestionTrajets){
        int choice;
        int nbpassengers;
        Trajet trajetRandom = null;
        try { // Ici on essaye de poser un train si c'est impossible (pas assez de carte wagon) on retourne a la méthode ou on peut que piocher
            trajetRandom = map.getTrajets().get(rand.nextInt(map.getTrajets().size()));
            poseTrain(trajetRandom, moteurDeJeu,gestionTrajets);
            choice = rand.nextInt(2)+1;
            if(choice==1) { // on choisit un meeples dans la ville from
                takeMeeplesAleatory(trajetRandom.getFrom(),moteurDeJeu);
            }
            if(choice==2){
               takeMeeplesAleatory(trajetRandom.getTo(),moteurDeJeu);
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
