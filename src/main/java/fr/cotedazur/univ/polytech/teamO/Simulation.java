package fr.cotedazur.univ.polytech.teamO;

import java.util.logging.*;

public class Simulation {

    // Déclaration du Logger
    private final static Logger LOGGER = Logger.getLogger(Simulation.class.getName());

    // Bloc statique pour configurer le niveau de log
    static {
        LOGGER.setLevel(Level.INFO);
        for (Handler handler : LOGGER.getParent().getHandlers()) {
            //handler.setFormatter(new CustomFormatter());
        }
    }

    public static void main(String[] args) {
        LOGGER.info("Début de la simulation...");

        int partiesGagnees = 1000;
        int partiesPerdues = 1000;
        int partiesNulles = 0;
        int totalParties = partiesGagnees + partiesPerdues + partiesNulles;

        // Affichage des résultats avec des paramètres. La
        LOGGER.info(() -> String.format("Nombre total de parties simulées : %d", totalParties));
        //Remplace les placeholders par les éléments du tableau d'objets
        LOGGER.log(Level.INFO, "Résultats : {} gagnées, {} perdues, {} nulles",
                new Object[]{partiesGagnees, partiesPerdues, partiesNulles});

        double tauxVictoire = (partiesGagnees / (double) totalParties) * 100;
        LOGGER.info(() -> String.format("Taux de victoire : %.2f%%", tauxVictoire));

        LOGGER.info("Fin de la simulation.");
    }

    static class CustomFormatter extends Formatter {
        //Surcharge du formatteur pour des logs plus épurés
        @Override
        public String format(LogRecord record) {
            return record.getLevel() + ": " + record.getMessage() + "\n";
        }
    }

}
