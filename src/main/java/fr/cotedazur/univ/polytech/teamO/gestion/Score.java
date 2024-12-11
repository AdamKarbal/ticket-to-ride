package fr.cotedazur.univ.polytech.teamO.gestion;


import org.jetbrains.annotations.NotNull;

public class Score implements Comparable<Score> {
    private int nbPoints;

    public Score() {
        nbPoints = 0;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void addNbPoints(int nbPoints) {
        this.nbPoints += nbPoints;
    }


    @Override
    public int compareTo(@NotNull Score o) {
        if(this.getNbPoints()==o.getNbPoints()) return 0;
        else if(this.getNbPoints()>o.getNbPoints()) return 1;
        else return -1;
    }
}
