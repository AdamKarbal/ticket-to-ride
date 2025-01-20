package  fr.cotedazur.univ.polytech.teamO.pioche;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class Pioche<T extends Carte> {
    // Instances
    public Stack<T> pioche;

    public Pioche(){
        creerPiocheComplete();
    }
    // Constantes
    private static int cartesParCouleur = 12;

    // Getter
    public Stack getPioche() {
        return pioche;
    }
    // Méthode pour créer une pioche complète
    public void creerPiocheComplete() { // bien handle le cas des locomotives
        Stack<T> nouvellePioche = new Stack<T>();
        for (Couleur couleur : Couleur.values()) { // créer une carte pour tout les couleurs
            if(!couleur.equals(Couleur.Default)) {
                for (int i = 0; i < cartesParCouleur; i++) {
                    nouvellePioche.push((T) new CarteWagon(couleur));
                }
            }
        }
        // Mélanger la pioche
        Collections.shuffle(nouvellePioche);
        Collections.shuffle(nouvellePioche);
        Collections.shuffle(nouvellePioche);
        this.pioche=nouvellePioche;
    }

    // Méthode

    public T piocher(){
        if(pioche.empty()){throw new EmptyStackException();}
        else return pioche.pop();
    }

    public void viderPioche(){
        if(!pioche.empty())pioche.clear();
    }

    public int size(){
        return pioche.size();
    }
    @Override
    public String toString() {
        String str = "";
        for(T carte : pioche){
            str += carte.toString() + "\n";
        }
        return str;
    }

}
