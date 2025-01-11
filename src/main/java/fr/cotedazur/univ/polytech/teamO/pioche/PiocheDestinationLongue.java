package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Ville;

import java.util.Stack;

public class PiocheDestinationLongue {
    
    private static final int nbCartesDestinations=34;
    private Stack<CarteDestination> cartesDestinationLongues=new Stack<>();

    public void init() {
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Dortmund, Ville.München, 13));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Niederlande, Ville.Berlin, 13));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Leipzig, Ville.Ulm, 12));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Bremerhaven, Ville.Frankreich, 12));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Schwerin, Ville.Koblenz, 12));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Köln, Ville.Leipzig, 12));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Hamburg, Ville.Dresden, 12));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Dresden, Ville.Augsburg, 12));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Kiel, Ville.Nürnberg, 15));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Leipzig, Ville.Stuttgart, 14));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Münster, Ville.München, 14));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Schwerin, Ville.Frankfurt, 13));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Berlin, Ville.Düsseldorf, 13));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Frankreich, Ville.Leipzig, 15));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Berlin, Ville.Köln, 14));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Berlin, Ville.Frankfurt, 14));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Hamburg, Ville.Karlsruhe, 14));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Dresden, Ville.Freiburg, 15));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Berlin, Ville.München, 15));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Münster, Ville.Österreich, 15));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Berlin, Ville.Mainz, 15));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Hamburg, Ville.Stuttgart, 15));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Emden, Ville.Freiburg, 15));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Emden, Ville.Österreich, 19));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Berlin, Ville.Stuttgart, 18));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Frankreich, Ville.Danemark, 17));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Kiel, Ville.Stuttgart, 17));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Hamburg, Ville.München, 18));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Saarbrücken, Ville.Dresden, 16));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Bremerhaven, Ville.Regensburg, 16));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Berlin, Ville.Schweiz, 20));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Rostock, Ville.Österreich, 22));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Danemark, Ville.Lindau, 22));
        cartesDestinationLongues.push(new CarteDestination(Couleur.Marron, Ville.Kiel, Ville.Schweiz, 20));
    }
    public PiocheDestinationLongue(){
        init();
    }

    public Stack<CarteDestination> getCartesDestinationLongues() {
        return cartesDestinationLongues;
    }

    public CarteDestination popCartesDestinationLongue() {
        if(!cartesDestinationLongues.isEmpty())return cartesDestinationLongues.pop();
        else throw new IllegalArgumentException("Pioche vide");
    }
    public boolean valide(){
        return cartesDestinationLongues.size()==nbCartesDestinations;
    }

    public CarteDestination peekCartesDestinationLongue() {
        if(!cartesDestinationLongues.isEmpty())return cartesDestinationLongues.peek();
        else throw new IllegalArgumentException("Pioche vide");
    }

}
