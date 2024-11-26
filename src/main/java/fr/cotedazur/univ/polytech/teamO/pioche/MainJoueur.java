package  fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.Main;

import java.util.ArrayList;

public class MainJoueur<T extends Carte> implements MainDeCarte<T>{ // peut être renommée pour faire des mains tout simplement
    // Instances
    private ArrayList<T> mainJoueur;

    // Constructeurs
    public MainJoueur() { this.mainJoueur = new ArrayList(); }
    public MainJoueur(ArrayList<T> mainDeCartesWagon){
        this.mainJoueur=mainDeCartesWagon;
    }

    // Accesseurs

    public ArrayList<T> getMainDeCartes(){
        return mainJoueur;
    }

    // Méthode

    public void addCarte(T carte){ // ajouter une carte wagon
        mainJoueur.add(carte);
    }

    public boolean containCarte(T CarteWagon){
        if(mainJoueur.contains(CarteWagon)) return true;
        return false;
    }

    public T carteAtPosI(int i){
        return mainJoueur.get(i);
    }

    public void removeCarte(T carte) {
        mainJoueur.remove(carte);
    }
}
