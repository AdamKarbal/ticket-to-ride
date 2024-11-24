package  fr.cotedazur.univ.polytech.teamO.pioche;

import java.util.ArrayList;

public class MainDeCartesWagon { // peut être renommée pour faire des mains tout simplement
    // Instances
    private ArrayList<CarteWagon> mainDeCartesWagon;

    // Constructeurs

    public MainDeCartesWagon(ArrayList<CarteWagon> mainDeCartesWagon){
        this.mainDeCartesWagon=mainDeCartesWagon;
    }

    // Accesseurs

    public ArrayList<CarteWagon> getMainDeCartesWagon(){
        return mainDeCartesWagon;
    }

    // Méthode

    public void addCarteWagon(CarteWagon CarteWagon){ // ajouter une carte wagon
        mainDeCartesWagon.add(CarteWagon);
    }

    public boolean containCarteWagon(CarteWagon CarteWagon){
        if(mainDeCartesWagon.contains(CarteWagon)) return true;
        return false;
    }

    public CarteWagon carteWagonAtPosI(int i){
        return mainDeCartesWagon.get(i);
    }

    public void removeCarteWagon(CarteWagon carte) {
        mainDeCartesWagon.remove(carte);
    }

}
