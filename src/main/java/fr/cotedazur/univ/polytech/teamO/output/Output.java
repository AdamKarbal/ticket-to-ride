package fr.cotedazur.univ.polytech.teamO.output;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.classement.Ranking;


public class Output {
    private Ranking ranking;
    private Robot gagnant;

    public Output(Ranking ranking) {
        this.ranking = ranking;
        this.gagnant = this.ranking.first();
    }

    @Override
    public String toString(){
        String output = "La partie est terminée ! Le gagnant est... \n\n"
                +"     " + gagnant.toString() + " avec un score de " + gagnant.getPlayerScore() + " !";
        output += "\n" + ranking.toString();
        return output;
    }

    public void display(){
        System.out.println(this);
    }



}
