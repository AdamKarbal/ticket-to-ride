package fr.cotedazur.univ.polytech.teamO.automatisation;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import java.io.FileWriter;
import java.io.IOException;

import java.io.*;
import java.util.ArrayList;

public class csv {
    private CSVWriter csvWriter;
    private int compteur;
    private String fichierCSV = "stats/gamestats.csv";



    public csv() throws IOException, CsvValidationException {
        File file = new File(fichierCSV);
        if (!file.exists()) {
            FileWriter writer = new FileWriter(fichierCSV, true);
            csvWriter = new CSVWriter(writer);
            init();
            this.compteur=1;
        } else {
            csvWriter = new CSVWriter(new FileWriter(fichierCSV, true));
            this.compteur=getDernierNumeroPartie();
        }
    }

    public CSVWriter getCsvWriter(){
        return csvWriter;
    }

    public void init(){
        String[] entete = {"Numero de la partie", "Nombre de tours","Nom robot", "Positionnement","Nombre de point","Nombre de carte destination completer","Nombre de route","Nombre de meeples NOIR","Nombre de meeples JAUNE","Nombre de meeples BLANC", "Nombre de meeples VERT", "Nombre de meeples ROUGE","Nombre de meeples BLEU",};
        csvWriter.writeNext(entete);
    }

    private int getDernierNumeroPartie() throws IOException, CsvValidationException {
        int dernierNumero = 0;
        CSVReader reader = new CSVReader(new FileReader(fichierCSV));
        String[] ligne;
        reader.readNext();
        while ((ligne = reader.readNext()) != null) {
            try {
                dernierNumero = Integer.parseInt(ligne[0].trim());
            } catch (NumberFormatException e) {
            }
        }
        return dernierNumero + 1;
    }


    /**
     * Cette méthode permet de mettre a jour le fichier gamestats, avec différent stat :
     * Le numéro de la partie, le nombre de tour de la partie, le nom du robot, son positionnement, son nombre de point, son nombre de carte destination completer, son nombre de route poser et enfin le nombre de meeples par couleur
     * @param jeu* @throws IOException
     */
    public void updateStat(Jeu jeu) throws IOException, CsvValidationException {
        FileWriter writer = new FileWriter(fichierCSV, true);
        csvWriter = new CSVWriter(writer);
        ArrayList<Robot> robots = jeu.getRobots();
        for(Robot robot : robots){
            String[] ligne = {String.valueOf(compteur), String.valueOf(jeu.getNombreTour()),robot.toString(), String.valueOf(jeu.getRanking().position(robot)),String.valueOf(robot.getScore().getNbPoints()),String.valueOf(jeu.getGestionEtatJeu().nombreCarteDestination(robot)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Noir)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Jaune)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Blanc)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Vert)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Rouge)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Bleu)) };
            csvWriter.writeNext(ligne);
        }
        compteur++;
        csvWriter.close();
    }

}
