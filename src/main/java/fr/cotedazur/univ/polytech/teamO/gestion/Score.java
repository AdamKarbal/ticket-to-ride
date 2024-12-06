package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;

public class Score {
    private Robot robot;
    private int nbPoints;

    public Score(Robot robot) {
        this.robot = robot;
        nbPoints = 0;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void addNbPoints(int nbPoints) {
        if(nbPoints>=0)this.nbPoints += nbPoints;
    }

    public Robot getRobot() {
        return robot;
    }
}
