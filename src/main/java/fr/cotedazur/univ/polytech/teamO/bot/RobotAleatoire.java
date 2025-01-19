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
        choix=rand.nextInt(4)+1;
        if (choix==1){
            piocher(moteurDeJeu);
        }
        if (choix==2){
            try{
                Trajet trajetRandom=map.getTrajets().get(rand.nextInt(map.getTrajets().size()));
                poseTrain(trajetRandom, moteurDeJeu);}
            finally{
                chooseaction(moteurDeJeu, map);
            }
        }
        if(choix==3){
            choix=rand.nextInt(moteurDeJeu.getPiocheSurTable().size())+1;
            piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(choix),moteurDeJeu);
        }
        if(choix==4){
            chooseDestinations(moteurDeJeu);
        }
    }

    @Override
    public String toString(){
        return "Le robot aleatoire " + getCouleur();
    }
}
