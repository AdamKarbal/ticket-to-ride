package fr.cotedazur.univ.polytech.teamO;

import com.beust.jcommander.JCommander;
import fr.cotedazur.univ.polytech.teamO.automatisation.Jeu;
import fr.cotedazur.univ.polytech.teamO.automatisation.Csv;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    /**
     * Déclaration du Logger de la classe qui servira à afficher tous les messages destinés à la sortie standard.
     */
    private final static Logger logger = Logger.getLogger(Main.class.getName());
    static{
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
        logger.setLevel(Level.OFF);
    }

    /**
     * Déclarations des variables statiques qui servent de compteurs
     * aux victoires des différents bots.
     */
    private static Jeu jeu = new Jeu();
    private static int route1Wins = 0;
    private static int route2Wins = 0;
    private static int randomWins = 0;
    private static int newWins = 0;
    private static double TauxVictR1 = 0;
    private static double TauxVictR2 = 0;
    private static double TauxVictRandom = 0;
    private static double TauxVictNew = 0;


    /**
     * Si la commande entrée a pour option "--2thousands", ces deux boucles
     * sont lancées. Meilleur bot contre un autre, puis meilleur bot contre lui même.
     * Le booléen en paramètre de jeu.play() permet de déterminer si les Logs sont affchés.
     * L'entier en 2ème paramètre dicte la version de la boucle de jeu utilisée.
     *
     * En revanche, si la commande entrée a pour option "--demo", seule une partie
     * sera lancée en affichant tous les logs d'inforamtions. Tous les bots
     * existants (et pertinents) sont inclus dans la partie.
     */

    public static void main(String[] args) throws Exception {
        Commands commands = new Commands();
       JCommander JCommander = new JCommander(commands);

       JCommander.parse(args);
        Csv csv = new Csv();

        if(commands.isTwoThousands()){
            for(int i = 0 ; i < 1000; i++){
                jeu.play(false,1); //Version 3 Pour tester avec d'autres bots
                if(commands.isCsvCommand()){
                    csv.updateStat(jeu);
                }
                if(jeu.getWinner() == jeu.getRobotAleatoire()) randomWins++;
                else if(jeu.getWinner() == jeu.getRobotRoute1()) route1Wins++;
                else if(jeu.getWinner() == jeu.getRobotNewStrategy()) newWins++;
           }
            logger.setLevel(Level.INFO);
            TauxVictRandom =((double) randomWins / 10);
            TauxVictR1 = ((double) route1Wins / 10);
            TauxVictNew = ((double) newWins / 10);

            logger.info("\nPremières 1000 parties :\n" + "Robot Aleatoire a gagné "+ randomWins + " parties, soit "+ TauxVictRandom +"% et perdu "+ (1000-randomWins) +" parties, soit " + (100-TauxVictRandom) +"%\n" +
                    "Robot Route a gagné "+ route1Wins + " parties, soit "+ TauxVictR1 +"% et perdu "+ (1000-route1Wins) +" parties, soit " + (100-TauxVictR1) +"%\n"
                           +"Robot Nico a gagné "+ newWins + " parties, soit "+ TauxVictNew +"% et perdu "+ (1000-newWins) +" parties, soit " + (100-TauxVictNew) +"%\n");
            logger.setLevel(Level.OFF);

           route1Wins = 0;
           TauxVictR1 = 0;
           for(int i = 0 ; i < 1000; i++){
               jeu.play(true,2);
               if(commands.isCsvCommand()){
                   csv.updateStat(jeu);
               }
               if(jeu.getWinner() == jeu.getRobotRoute1()) route1Wins++;
               else if(jeu.getWinner() == jeu.getRobotRoute2()) route2Wins++;
               else if(jeu.getWinner() == jeu.getRobotNewStrategy()) newWins++;
           }
            logger.setLevel(Level.INFO);
            TauxVictR1 =((double) route1Wins / 10);
            TauxVictR2 = ((double) route2Wins / 10);
            TauxVictNew = ((double) newWins / 10);

            logger.info("\nDeuxièmes 1000 parties :\n" + "Robot Route 1 a gagné "+ route1Wins + " parties, soit "+ TauxVictR1 +"% et perdu "+ (1000-route1Wins) +" parties, soit " + (100-TauxVictR1) +"%\n" +
                    "Robot Route 2 a gagné "+ route2Wins + " parties, soit "+ TauxVictR2 +"% et perdu "+ (1000-route2Wins) +" parties, soit " + (100-TauxVictR2) +"%\n");
            logger.setLevel(Level.OFF);
       }
       else if(commands.isDemoCommand()){
           jeu.play(true, 3);
            if(commands.isCsvCommand()){
                csv.updateStat(jeu);
            }
       }
       else{
           throw new NotValidCommand("Entrez --2thousands ou --demo");
       }
    }
}
