package fr.cotedazur.univ.polytech.teamO.automatisation;
import fr.cotedazur.univ.polytech.teamO.bot.*;
import fr.cotedazur.univ.polytech.teamO.couleur.*;
import fr.cotedazur.univ.polytech.teamO.pioche.*;

public class Jeu {

    private Pioche pioche;
    private Robot robot1;
    private Robot robot2;
    private CarteSurTable carteSurTable;


    //Accesseurs
    public Pioche getPioche(){
        return pioche;
    }

    //Setup de la partie
    public void setupPioche(){
        pioche.creerPiocheComplete();
    }
    public void setupCarteSurTable(){
        carteSurTable=getPioche().mettre5cartesurlatable(carteSurTable);

    }

}
