package  fr.cotedazur.univ.polytech.teamO.pioche;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

import fr.cotedazur.univ.polytech.teamO.classement.EmptyListException;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class Pioche<T extends Carte> {
    // Instances
    private Stack<T> pioche;

    public Pioche(){
        pioche=new Stack<>();
    }
    // Constantes
    private static int cartesParCouleur = 12;
    private static int cartesLocomotives = 14;

    // Getter
    public Stack getPioche() {
        return pioche;
    }
    // Méthode pour créer une pioche complète
    public void creerPiocheComplete() {
        Stack<T> nouvellePioche = new Stack<T>();
        for (Couleur couleur : Couleur.values()) { // créer une carte pour tout les couleurs
            if(isCouleurStandard(couleur)) {
                for (int i = 0; i < cartesParCouleur; i++) {
                    nouvellePioche.push((T) new CarteWagon(couleur));
                }
            }
            else if (couleur.equals(Couleur.Rainbow)) {
                for (int i = 0; i < cartesLocomotives; i++) {
                    nouvellePioche.push((T) new CarteWagon(Couleur.Rainbow));
                }
            }
        }
        // Mélanger la pioche
        Collections.shuffle(nouvellePioche);
        this.pioche=nouvellePioche;
    }

    private boolean isCouleurStandard(Couleur couleur) {
        return !couleur.equals(Couleur.Default) && !couleur.equals(Couleur.Marron) && !couleur.equals(Couleur.Rainbow);
    }

    // Méthode

    public T piocher(){
        if(pioche.empty()) throw new EmptyStackException();
        return pioche.pop();
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
