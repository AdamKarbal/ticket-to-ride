package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.plateau.Map;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

import java.util.Random;

public class RobotAleatoire extends Robot {

    public RobotAleatoire(Couleur couleur) {
        super(couleur);
    }

    public void chooseaction(MoteurDeJeu moteurDeJeu,Map map) throws NotEnoughWagonCard {
        int choix;
        Random rand = new Random();
        choix=rand.nextInt(100)+1;
        if (1<=choix && choix<=30){
            piocher(moteurDeJeu);
        }
        if (30<choix && choix<=60){
            try{
                Trajet trajetRandom=map.getTrajets().get(rand.nextInt(map.getTrajets().size()));
                poseTrain(trajetRandom, moteurDeJeu);}
            finally{
                chooseactionnotenought(moteurDeJeu);
            }
        }
        if(60<choix && choix<=90){
            int pos;
            pos=rand.nextInt(moteurDeJeu.getPiocheSurTable().size());
            piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(pos),moteurDeJeu);
        }
        if(90<choix && choix<=100){
            chooseDestinations(moteurDeJeu);
        }
    }

    public void chooseactionnotenought(MoteurDeJeu moteurDeJeu){
        int choix;
        Random rand = new Random();
        choix=rand.nextInt(100)+1;
        if (1<=choix && choix<=50){
            piocher(moteurDeJeu);
        }
        if(choix<50 && choix<=100){
            int pos;
            pos=rand.nextInt(moteurDeJeu.getPiocheSurTable().size());
            piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(pos),moteurDeJeu);
        }
    }

    @Override
    public String toString(){
        return "Le robot aleatoire " + getCouleur();
    }
}
