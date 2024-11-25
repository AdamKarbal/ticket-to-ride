package  fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.Main;

import java.util.ArrayList;

public class MainJoueur implements MainDeCarte { // peut être renommée pour faire des mains tout simplement
    // Instances
    private ArrayList<CarteWagon> mainJoueur;

    // Constructeurs
    public MainJoueur() { this.mainJoueur = new ArrayList(); }
    public MainJoueur(ArrayList<CarteWagon> mainDeCartesWagon){
        this.mainJoueur=mainDeCartesWagon;
    }

    // Accesseurs
    @Override
    public ArrayList<CarteWagon> getMainDeCartes(){
        return mainJoueur;
    }

    // Méthode
    @Override
    public void addCarte(CarteWagon CarteWagon){ // ajouter une carte wagon
        mainJoueur.add(CarteWagon);
    }
    @Override
    public boolean containCarte(CarteWagon CarteWagon){
        if(mainJoueur.contains(CarteWagon)) return true;
        return false;
    }
    @Override
    public CarteWagon carteAtPosI(int i){
        return mainJoueur.get(i);
    }
    @Override
    public void removeCarte(CarteWagon carte) {
        mainJoueur.remove(carte);
    }
    // ton contains et ton remove ne vont pas marcher tu dois le faire manuellement
    // vérifie si la carte est présente avant de la remove
    // Rajoute une méthode qui retourne une carte aléatoire
}
