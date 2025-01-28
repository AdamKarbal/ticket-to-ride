package fr.cotedazur.univ.polytech.teamO;

import fr.cotedazur.univ.polytech.teamO.automatisation.Jeu;
import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;


public class Main {
    public static String  hello(){
        return "Hello world !";
    }
   public static void main(String[] args) throws Exception {
       Jeu jeu=new Jeu();
       jeu.play();
   }

}
