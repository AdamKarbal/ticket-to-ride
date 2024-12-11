package fr.cotedazur.univ.polytech.teamO.gestion;


public class Score {
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


}
