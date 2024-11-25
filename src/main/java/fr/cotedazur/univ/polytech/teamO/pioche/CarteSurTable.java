package fr.cotedazur.univ.polytech.teamO.pioche;

import java.util.ArrayList;

public class CarteSurTable<T extends Carte> implements  MainDeCarte<T>{
    // Instances
    private ArrayList<T> carteSurTable;

    // Constructeurs

    public CarteSurTable(ArrayList<T> carteSurTable){
        this.carteSurTable =carteSurTable;
    }

    // Accesseurs
    @Override
    public ArrayList<T> getMainDeCartes(){
        return carteSurTable;
    }

    // Méthode

    public void addCarte(T CarteWagon){ // ajouter une carte wagon
        carteSurTable.add(CarteWagon);
    }
    public boolean containCarte(T CarteWagon){
        if(carteSurTable.contains(CarteWagon)) return true;
        return false;
    }
    public T carteAtPosI(int i){
        return carteSurTable.get(i);
    }
    public void removeCarte(T carte) {
        carteSurTable.remove(carte);
    }

}
