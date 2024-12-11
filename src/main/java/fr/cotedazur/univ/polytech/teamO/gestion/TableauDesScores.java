package fr.cotedazur.univ.polytech.teamO.gestion;
import java.util.HashMap;

public class TableauDesScores {
    private static final HashMap<Integer,Integer> tableauScores = new HashMap<>();

    static {
        tableauScores.put(1, 1);
        tableauScores.put(2, 2);
        tableauScores.put(3, 4);
        tableauScores.put(4, 7);
        tableauScores.put(5, 10);
        tableauScores.put(6, 15);
        tableauScores.put(7, 18);
    }
    public static int getValue(int tailleRoute) {
        return tableauScores.get(tailleRoute);
    }

}
