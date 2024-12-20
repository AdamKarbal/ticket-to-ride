package fr.cotedazur.univ.polytech.teamO.gestion;

import java.util.Arrays;

public enum Ville {
    Berlin,
    Bremen,
    Bremerhaven,
    Chemnitz,
    Dresden,
    Dortmund,
    Düsseldorf,
    Erfurt,
    Freiburg,
    Frankfurt,
    Hamburg,
    Hannover,
    Karlsruhe,
    Kassel,
    Kiel,
    Koblenz,
    Konstanz,
    Köln,
    Leipzig,
    Lindau,
    Magdeburg,
    Mainz,
    Mannheim,
    München,
    Münster,
    Nürnberg,
    Regensburg,
    Rostock,
    Saarbrücken,
    Schwerin,
    Stuttgart,
    Ulm,
    Würzburg,
    Augsburg,
    Emden;
    public static boolean isValid(Ville ville){
        return Arrays.asList(Ville.values()).contains(ville);
    }
}
