package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

import java.util.ArrayList;
import java.util.Random;

public class PiocheSurTable<T extends Carte> extends Pioche<T> {
    // Instances
    private ArrayList<T> cartesSurTable;

    // Constructeurs
    public PiocheSurTable() {
        this.cartesSurTable=new ArrayList<>();
        Random random=new Random();
        Couleur[] couleurs=Couleur.values();
        for(int i=0;i<5;i++){
            this.cartesSurTable.add((T) new CarteWagon(couleurs[random.nextInt(couleurs.length)]));
        }
    }

    public PiocheSurTable(ArrayList<T> carteSurTable){
        this.cartesSurTable = carteSurTable;
    }

    // Accesseurs
    /*@Override*/
    public ArrayList<T> getMainDeCartes(){
        return cartesSurTable;
    }

    // Méthode

    public void addCarte(T CarteWagon){ // ajouter une carte wagon
        cartesSurTable.add(CarteWagon);
    }
    public boolean containsCarte(T CarteWagon){
        return cartesSurTable.contains(CarteWagon);
    }
    public T carteAtPosI(int i){
        return cartesSurTable.get(i);
    }
    public T get(T carte){
        if(cartesSurTable.contains(carte)) return carteAtPosI(cartesSurTable.indexOf(carte));
        return null;
    }
    public void removeCarte(T carte) {
        cartesSurTable.remove(carte);
    }

    public int size(){
        return cartesSurTable.size();
    }
    public T piocher(T carte) throws NullPointerException{
        if(containsCarte(carte)){
            removeCarte(carte);
            return carte;
        }
        throw new NullPointerException("La carte n'est pas présente dans la pioche visible");
    }
    @Override
    public String toString() {
        String str = "";
        for(T carte : cartesSurTable){
            str += carte.toString() + "\n";
        }
        return str;
    }
}
