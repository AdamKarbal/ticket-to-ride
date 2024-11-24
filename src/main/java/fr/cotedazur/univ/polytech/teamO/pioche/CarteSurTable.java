package fr.cotedazur.univ.polytech.teamO.pioche;

import java.util.ArrayList;

public class CarteSurTable implements  MainDeCarte{
    // Instances
    private ArrayList<CarteWagon> carteSurTable;

    // Constructeurs

    public CarteSurTable(ArrayList<CarteWagon> carteSurTable){
        this.carteSurTable =carteSurTable;
    }

    // Accesseurs
    @Override
    public ArrayList<CarteWagon> getMainDeCartes(){
        return carteSurTable;
    }

    // Méthode
    @Override
    public void addCarte(CarteWagon CarteWagon){ // ajouter une carte wagon
        carteSurTable.add(CarteWagon);
    }
    @Override
    public boolean containCarte(CarteWagon CarteWagon){
        if(carteSurTable.contains(CarteWagon)) return true;
        return false;
    }
    @Override
    public CarteWagon carteAtPosI(int i){
        return carteSurTable.get(i);
    }
    @Override
    public void removeCarte(CarteWagon carte) {
        carteSurTable.remove(carte);
    }

}
