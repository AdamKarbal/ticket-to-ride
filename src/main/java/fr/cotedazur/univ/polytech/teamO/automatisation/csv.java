package fr.cotedazur.univ.polytech.teamO.automatisation;

import com.opencsv.CSVWriter;
import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class csv {
    private CSVWriter csvWriter;

    private String fichierCSV = "stats/gamestats.csv";

    public csv() throws IOException {
        csvWriter = new CSVWriter(new FileWriter(fichierCSV, false));
        String[] entete = {"Numero de la partie", "Nombre de tours","Nom robot", "Positionnement","Nombre de point","Nombre de carte destination completer","Nombre de route","Nombre de meeples NOIR","Nombre de meeples JAUNE","Nombre de meeples BLANC", "Nombre de meeples VERT", "Nombre de meeples ROUGE","Nombre de meeples BLEU",};
        csvWriter.writeNext(entete);
    }

    public CSVWriter getCsvWriter(){
        return csvWriter;
    }

    public void updateStat(Jeu jeu,int compteur) throws IOException {
        ArrayList<Robot> robots = jeu.getRobots();
        for(Robot robot : robots){
            String[] ligne = {String.valueOf(compteur), String.valueOf(jeu.getNombreTour()),robot.toString(), String.valueOf(jeu.getRanking().position(robot)),String.valueOf(robot.getScore().getNbPoints()),String.valueOf(jeu.getGestionEtatJeu().nombreCarteDestination(robot)),String.valueOf(robot.getNbroute()),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Noir)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Jaune)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Blanc)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Vert)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Rouge)),String.valueOf(robot.getTakenMeeplesByCouleur(MCouleur.Bleu)) };
            csvWriter.writeNext(ligne);

        }
        System.out.println("Les statistiques ont été mises à jour dans " + fichierCSV);
    }
}
