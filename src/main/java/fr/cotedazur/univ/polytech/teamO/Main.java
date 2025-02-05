package fr.cotedazur.univ.polytech.teamO;

import com.beust.jcommander.JCommander;
import fr.cotedazur.univ.polytech.teamO.automatisation.Jeu;
import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    private final static Logger logger = Logger.getLogger(Main.class.getName());
    static{
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
        logger.setLevel(Level.OFF);
    }

    private static Jeu jeu = new Jeu();
    private static int routeWins = 0;
    private static int randomWins = 0;

    public static void main(String[] args) throws Exception {
        Commands commands = new Commands();

       JCommander JCommander = new JCommander(commands);

       JCommander.parse(args);

        for(int i = 0 ; i < 10000; i++){
            jeu.play(false);
            if(jeu.getWinner() == jeu.getRobotAleatoire()) randomWins++;
            else if(jeu.getWinner() == jeu.getRobotRoute()) routeWins++;
        }

        System.out.println("Aléatoire : " + randomWins + "\n" + "Routes : " + routeWins + "\n");

       /*if(commands.isTwoThousands()){
           //for(int i = 0 ; i < 1000; i++){

           //}
       }
       else if(commands.isDemoCommand()){

       }
       else{

       }*/
   }

}
