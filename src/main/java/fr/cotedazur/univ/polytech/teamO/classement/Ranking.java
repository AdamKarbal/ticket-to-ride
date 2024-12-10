package fr.cotedazur.univ.polytech.teamO.classement;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;

import java.util.ArrayList;
import java.util.TreeSet;

public class Ranking {
    private TreeSet<Robot> ranking;

    public Ranking(ArrayList<Robot> robots) throws NullPointerException, EmptyListException{
        this.ranking = new TreeSet<>();
        if(robots.isEmpty()) throw new EmptyListException("La liste que vous avez passé ne contient pas de joueur! Ajoutez en...");
        try{
            ranking.addAll(robots);
        }
        catch(NullPointerException e){
            System.out.println("Passez une liste de joueurs en paramètre pour créer un classement!");
        }
    }
    
    @Override 
    public String toString(){
        int classeur = 0;
        String display = "Classement actuel : \n";
        for(Robot robot : ranking){
            display += ++classeur + " : " + robot.toString() + " avec un score de " + robot.getPlayerScore() + "\n";
        }
        return display;
    }

}
