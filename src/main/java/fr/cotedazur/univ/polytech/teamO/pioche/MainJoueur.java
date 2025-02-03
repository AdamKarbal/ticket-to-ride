package  fr.cotedazur.univ.polytech.teamO.pioche;



import java.util.ArrayList;
import java.util.List;

public class MainJoueur<T extends Carte> implements MainDeCarte<T>{ // peut être renommée pour faire des mains tout simplement
    // Instances
    private List<T> mainJoueur;

    // Constructeurs
    public MainJoueur() { this.mainJoueur = new ArrayList(); }
    public MainJoueur(List<T> mainDeCartesWagon){
        this.mainJoueur=mainDeCartesWagon;
    }

    // Accesseurs

    public List<T> getMainDeCartes(){
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

    @Override
    public String toString() {
        String str = "";
        for(T carte : mainJoueur){
            str += carte.toString() + "\n";
        }
        return str;
    }
}
