package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;

import java.util.*;

public enum Ville {
    Berlin(5, true),
    Bremen(1, true),
    Bremerhaven(1, true),
    Chemnitz(1, true),
    Dresden(1, true),
    Dortmund(1, true),
    Düsseldorf(1, true),
    Erfurt(1, true),
    Freiburg(1, true),
    Frankfurt(4, true),
    Hamburg(4, true),
    Hannover(1, true),
    Karlsruhe(1, true),
    Kassel(1, true),
    Kiel(1, true),
    Koblenz(1, true),
    Konstanz(1, true),
    Köln(4, true),
    Leipzig(3, true),
    Lindau(1, true),
    Magdeburg(1, true),
    Mainz(1, true),
    Mannheim(1, true),
    München(4, true),
    Münster(1, true),
    Nürnberg(1, true),
    Regensburg(1, true),
    Rostock(1, true),
    Saarbrücken(1, true),
    Schwerin(1, true),
    Stuttgart(3, true),
    Ulm(1, true),
    Würzburg(1, true),
    Augsburg(1, true),
    Emden(1, true),
    //Pays
    Niederlande(1, false),
    Frankreich(1, false),
    Danemark(1, false),
    Österreich(1, false),
    Schweiz(1, false);


    private int NbMeeple;
    private final boolean isVille;
    private List<Meeples> passagers;

    Ville(int NbMeeple, boolean isVille) {
        this.NbMeeple = NbMeeple;
        this.isVille=isVille;
        this.passagers = new ArrayList<>();
    }

    public int getBaseNbMeeple() {
        return NbMeeple;
    }

    public int getCurrentNbMeeples(){
        return passagers.size();
    }

    public void setNbMeeple(int nbMeeple){
        this.NbMeeple= nbMeeple;
    }

    public boolean getIsVille(){
        return isVille;
    }

    public static boolean isValid(Ville ville) {
        return Arrays.asList(Ville.values()).contains(ville);
    }

    public List<Meeples> getPassagers() {
        return this.passagers;
    }

    public void removePassenger(Meeples meeple) {
        this.passagers.remove(meeple);
    }

    public Set<MCouleur> getPassagersColors(){
        Set<MCouleur> colors = new HashSet<>();
        for(Meeples meeple : passagers) {
            colors.add(meeple.getCouleur());
        }
        return colors;
    }


    public String PDisplay() {
        String output = "";
        List<Meeples> p = getPassagers();
        for(Meeples passager : p){
            output += passager.toString() + "\n";
        }
        return output;
    }

}


