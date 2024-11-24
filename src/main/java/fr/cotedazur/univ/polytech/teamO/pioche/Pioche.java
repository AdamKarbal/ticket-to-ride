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
    public static Stack<CarteWagon> creerPiocheComplete() {
        Stack<CarteWagon> pioche = new Stack<>();
        for (String couleur : couleurs) {
            for (int i = 0; i < cartesParCouleur; i++) {
                pioche.push(new CarteWagon(couleur));
            }
        }
        // Mélanger la pioche
        Collections.shuffle(pioche);
        Collections.shuffle(pioche);
        Collections.shuffle(pioche);
        return pioche;
    }

    // Méthode

    public CarteWagon piocher(){
        CarteWagon cartePiocher = pioche.pop();
        return cartePiocher;
    }

    public MainDeCartesWagon mettre5cartesurlatable(){
        ArrayList<CarteWagon> cartesPiocher= new ArrayList<>();
        for(int i=0; i<=5;i++){
            cartesPiocher.add(piocher());
        }
        MainDeCartesWagon carteSurLaTable = new MainDeCartesWagon(cartesPiocher);
        return carteSurLaTable;
    }

}
