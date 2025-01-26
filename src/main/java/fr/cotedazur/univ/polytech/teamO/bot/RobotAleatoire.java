package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

import java.util.Random;

public class RobotAleatoire extends Robot {

    public RobotAleatoire(Couleur couleur) {
        super(couleur);
    }

    public void chooseaction(MoteurDeJeu moteurDeJeu,GameMap map) throws NotEnoughWagonCard {
        int choix;
        Random rand = new Random();
        choix=rand.nextInt(100)+1;
        if (1<=choix && choix<=20){
            piocher(moteurDeJeu);
            if(!(isTurnOver())){
                chooseactionnotenought(moteurDeJeu);
            }
        }
        if (20< choix && choix <= 80) {
            try {
                Trajet trajetRandom = map.getTrajets().get(rand.nextInt(map.getTrajets().size()));
                poseTrain(trajetRandom, moteurDeJeu);
            } catch (NotEnoughWagonCard e) {
                chooseactionnotenought(moteurDeJeu);
            }
        }
        if(80<choix && choix<=100){
            int pos;
            pos=rand.nextInt(moteurDeJeu.getPiocheSurTable().size());
            piocherSurTable(moteurDeJeu.getPiocheSurTable().carteAtPosI(pos),moteurDeJeu);
            if(!(isTurnOver())){
                chooseactionnotenought(moteurDeJeu);
            }
        }
    }

    public void chooseactionnotenought(MoteurDeJeu moteurDeJeu){
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

    @Override
    public String toString(){
        return "robot aleatoire " + getCouleur();
    }
}
