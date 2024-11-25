package  fr.cotedazur.univ.polytech.teamO.pioche;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class Pioche<T extends Carte> {
    // Instances
    public Stack<T> pioche;

    // Constantes
    private static int cartesParCouleur = 12;

    // Constructeur
    public Pioche() {
        this.pioche = new Stack<>();
    }
    // Getter
    public Stack getPioche() {
        return pioche;
    }
    // Méthode pour créer une pioche complète
    public static Pioche creerPiocheComplete() { // bien handle le cas des locomotives
        Pioche nouvellePioche = new Pioche();
        for (Couleur couleur : Couleur.values()) { // créer une carte pour tout les couleurs
            for (int i = 0; i < cartesParCouleur; i++) {
                nouvellePioche.pioche.push(new CarteWagon(couleur));
            }
        }
        // Mélanger la pioche
        Collections.shuffle(nouvellePioche.pioche);
        Collections.shuffle(nouvellePioche.pioche);
        Collections.shuffle(nouvellePioche.pioche);
        return nouvellePioche;
    }

    // Méthode

    public T piocher(){
        return pioche.pop();
    }

    public CarteSurTable mettre5cartesurlatable(CarteSurTable carteSurTable){ // méthode à utiliser a chaque fin de tour pour avoir 5 carte sur la table
        while(carteSurTable.getMainDeCartes().size()!=5){
            carteSurTable.getMainDeCartes().add(piocher());
        }
        return carteSurTable;
    }


}
