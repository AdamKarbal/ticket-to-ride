package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.gestion.Score;

public class CarteDestination{
    //private Ville villeFrom; à enlever le commentaire quand on aura fait l'enum ville
    //private Ville villeTo;
    private boolean isDone;
    private Score value;
    private int id;

    public CarteDestination(ville villeFrom, ville villeTo, Score value){
        if(ville.villeFrom.isValid()){
            this.villeFrom=villeFrom;
        }
        if(ville.villeTo.isValid()){
            this.villeTo=villeTo;
        }
        this.isDone=false;
        this.value=value;
    }

    public void isCompleted(){
        if(id==Route.getId()){
            this.isDone=true;
            Robot.addScore(value);
        }
    }

    public void notCompleted(){
        if(isDone==False){
            Robot.removeScore(value);
        }
    }

    // POUR LE TIRAGE FAIRE UNE CLASSE AVEC UNE MAIN DE CARTE DESTINATION ? OU GARDER DANS LA CLASSE ROBOT ? PERSO PLUS POUR LE PREMIER CHOIX
}
