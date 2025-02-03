package fr.cotedazur.univ.polytech.teamO;

import fr.cotedazur.univ.polytech.teamO.automatisation.Jeu;
import fr.cotedazur.univ.polytech.teamO.automatisation.csv;
import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;


public class Main {
    public static String  hello(){
        return "Hello world !";
    }
   public static void main(String[] args) throws Exception {
       Jeu jeu=new Jeu();
       csv csv = new csv();
       //jeu.play();
       int i=1;
       for(;i<100;i++){
           jeu.play();
           csv.updateStat(jeu,i);
       }
       csv.getCsvWriter().close();
       System.out.println(i);
   }
}