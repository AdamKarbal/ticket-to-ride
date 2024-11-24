package  fr.cotedazur.univ.polytech.teamO.pioche;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Pioche {
    // Instances
    private Stack<CarteWagon> pioche;

    // Constantes
    private static String[] couleurs = {"Rouge", "Bleu", "Vert", "Jaune", "Noir", "Blanc", "Orange", "Rose"}; //Avant qu'il y a les énumeration de couleur
    private static int cartesParCouleur = 12;

    // Constructeur
    public Pioche() {
        this.pioche = new Stack<>();
    }

    // Méthode pour créer une pioche complète
    public static Pioche creerPiocheComplete() {
        Pioche nouvellePioche = new Pioche();
        for (String couleur : couleurs) { // créer une carte pour tout les couleurs
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

    public CarteWagon piocher(){
        CarteWagon cartePiocher = pioche.pop();
        return cartePiocher;
    }

    public CarteSurTable mettre5cartesurlatable(CarteSurTable carteSurTable){ // méthode à utiliser a chaque fin de tour pour avoir 5 carte sur la table
        while(carteSurTable.getMainDeCartes().size()!=5){
            carteSurTable.getMainDeCartes().add(piocher());
        }
        return carteSurTable;
    }

}
