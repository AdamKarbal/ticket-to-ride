package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

import java.util.Collections;
import java.util.Stack;

public class PiocheDestinationCourte{

    private Stack<CarteDestination> cartesDestinationCourtes=new Stack<>();
    private static final int nbCartesDestinationsCourtes=55;

    public void init(){
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Mannheim, Ville.Stuttgart, 2));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Mainz, Ville.Stuttgart, 3));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Köln, Ville.Saarbrücken, 4));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Berlin, Ville.Leipzig, 4));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Frankfurt, Ville.Stuttgart, 4));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Karlsruhe, Ville.Augsburg, 4));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hamburg, Ville.Rostock, 4));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Köln, Ville.Frankfurt, 4));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Mannheim, Ville.Würzburg, 4));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Bremen, Ville.Düsseldorf, 5));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.München, Ville.Stuttgart, 5));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Emden, Ville.Hamburg, 6));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.München, Ville.Konstanz, 6));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Erfurt, Ville.Würzburg, 6));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Bremen, Ville.Kassel, 6));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hannover, Ville.Leipzig, 6));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Berlin, Ville.Chemnitz, 6));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Dortmund, Ville.Mannheim, 6));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Nürnberg, Ville.Stuttgart, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Dortmund, Ville.Erfurt, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hamburg, Ville.Kassel, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Bremerhaven, Ville.Köln, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hannover, Ville.Frankfurt, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Berlin, Ville.Erfurt, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Koblenz, Ville.Ulm, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Leipzig, Ville.Nürnberg, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hamburg, Ville.Berlin, 7));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Köln, Ville.Nürnberg, 8));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Niederlande, Ville.Frankfurt, 8));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Frankreich, Ville.München, 8));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.München, Ville.Freiburg, 8));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Frankfurt, Ville.Lindau, 8));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Frankfurt, Ville.München, 9));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hamburg, Ville.Köln, 9));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Karlsruhe, Ville.Regensburg, 9));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Niederlande, Ville.Karlsruhe, 9));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Münster, Ville.Stuttgart, 9));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Dortmund, Ville.Magdeburg, 9));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hamburg, Ville.Leipzig, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Köln, Ville.Schweiz, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Leipzig, Ville.Frankfurt, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Danemark, Ville.Berlin, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hamburg, Ville.Koblenz, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Frankfurt, Ville.Österreich, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Düsseldorf, Ville.Konstanz, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Danemark, Ville.Niederlande, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Bremen, Ville.Berlin, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Münster, Ville.Stuttgart, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Kassel, Ville.Freiburg, 10));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hannover, Ville.Saarbrücken, 11));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Leipzig, Ville.München, 11));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Hamburg, Ville.Frankfurt, 11));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Köln, Ville.München, 11));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Magdeburg, Ville.Köln, 11));
        cartesDestinationCourtes.push(new CarteDestination(Couleur.Bleu, Ville.Köln, Ville.Regensburg, 11));


    }
    public PiocheDestinationCourte(){
        init();
        Collections.shuffle(cartesDestinationCourtes);
    }

    public Stack<CarteDestination> getCartesDestinationCourtes() {
        return cartesDestinationCourtes;
    }

    public CarteDestination popCartesDestinationCourtes() throws IllegalArgumentException {
        if(!cartesDestinationCourtes.isEmpty())return cartesDestinationCourtes.pop();
        else throw new IllegalArgumentException("Pile vide");
    }

    public boolean valide(){
        return cartesDestinationCourtes.size()==nbCartesDestinationsCourtes;
    }

    public void pushCartesDestinationCourtes(CarteDestination cartesDestination) {
        cartesDestinationCourtes.push(cartesDestination);
    }
}
