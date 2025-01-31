package fr.cotedazur.univ.polytech.teamO.classement;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Ranking {
    private TreeSet<Robot> classement;

    public Ranking(List<Robot> robots) throws NullPointerException, EmptyListException{
        this.classement = new TreeSet<>(Comparator.reverseOrder());
        if(robots.isEmpty()) throw new EmptyListException("La liste que vous avez passé ne contient pas de joueur! Ajoutez en...");
        try{
            classement.addAll(robots);
        }
        catch(NullPointerException e){
            System.out.println("Passez une liste de joueurs en paramètre pour créer un classement!");
        }
    }

    public Robot first(){
        return classement.first();
    }
    /*public TreeSet<Robot> getClassement(){
        return classement;
    }*/
    @Override 
    public String toString(){
        int classeur = 0;
        String display = "\nClassement : \n\n";
        for(Robot robot : classement){
            display += ++classeur + " : " + robot.toString() + " avec un score de " + robot.getPlayerScore() + "\n";
        }
        return display;
    }

}
