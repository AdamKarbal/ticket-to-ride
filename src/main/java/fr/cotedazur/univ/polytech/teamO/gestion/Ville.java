package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public enum Ville {
    Berlin(5),
    Bremen(1),
    Bremerhaven(1),
    Chemnitz(1),
    Dresden(1),
    Dortmund(1),
    Düsseldorf(1),
    Erfurt(1),
    Freiburg(1),
    Frankfurt(4),
    Hamburg(4),
    Hannover(1),
    Karlsruhe(1),
    Kassel(1),
    Kiel(1),
    Koblenz(1),
    Konstanz(1),
    Köln(4),
    Leipzig(3),
    Lindau(1),
    Magdeburg(1),
    Mainz(1),
    Mannheim(1),
    München(4),
    Münster(1),
    Nürnberg(1),
    Regensburg(1),
    Rostock(1),
    Saarbrücken(1),
    Schwerin(1),
    Stuttgart(3),
    Ulm(1),
    Würzburg(1),
    Augsburg(1),
    Emden(1),
    //Pays
    Niederlande(1),
    Frankreich(1),
    Danemark(1),
    Österreich(1),
    Schweiz(1);

    private final int NbMeeple;
    private ArrayList<Meeples> passagers;

    Ville(int NbMeeple) {
        this.NbMeeple = NbMeeple;
        this.passagers = new ArrayList<>();
    }

    public int getNbMeeple() {
        return NbMeeple;
    }

    public static boolean isValid(Ville ville) {
        return Arrays.asList(Ville.values()).contains(ville);
    }

    public ArrayList<Meeples> getPassagers() {
        return this.passagers;
    }

    public String PDisplay() {
        String output = "";
        ArrayList<Meeples> p = getPassagers();
        for(Meeples passager : p){
            output += passager.toString() + "\n";
        }
        return output;
    }

}

