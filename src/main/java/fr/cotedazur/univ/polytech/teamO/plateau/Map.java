package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.gestion.Ville;

import java.util.HashSet;

public class Map {

    //Instance
    private HashMap<Ville,Set<Trajet>> map;

    //Constructeur
    public Map(){
        init();
    }

    //Initialisation
    private void init(){
        HashSet<Trajet> trajetBerlin = new HashSet<>();
        HashSet<Trajet> trajetBremen = new HashSet<>();
        HashSet<Trajet> trajetBremerhaven = new HashSet<>();
        HashSet<Trajet> trajetChemnitz = new HashSet<>();
        HashSet<Trajet> trajetDresden = new HashSet<>();
        HashSet<Trajet> trajetDüsseldorf = new HashSet<>();
        HashSet<Trajet> trajetErfurt = new HashSet<>();
        HashSet<Trajet> trajetFreiburg = new HashSet<>();
        HashSet<Trajet> trajetFrankfurt = new HashSet<>();
        HashSet<Trajet> trajetHamburg = new HashSet<>();
        HashSet<Trajet> trajetHannover = new HashSet<>();
        HashSet<Trajet> trajetKarlsruhe = new HashSet<>();
        HashSet<Trajet> trajetKassel = new HashSet<>();
        HashSet<Trajet> trajetKiel = new HashSet<>();
        HashSet<Trajet> trajetKoblenz = new HashSet<>();
        HashSet<Trajet> trajetKonstanz= new HashSet<>();
        HashSet<Trajet> trajetKöln = new HashSet<>();
        HashSet<Trajet> trajetLeipzig = new HashSet<>();
        HashSet<Trajet> trajetLindau = new HashSet<>();
        HashSet<Trajet> trajetMagdeburg = new HashSet<>();
        HashSet<Trajet> trajetMainz = new HashSet<>();
        HashSet<Trajet> trajetMannheim = new HashSet<>();
        HashSet<Trajet> trajetMünchen = new HashSet<>();
        HashSet<Trajet> trajetMünster = new HashSet<>();
        HashSet<Trajet> trajetNürnberg= new HashSet<>();
        HashSet<Trajet> trajetRegensburg= new HashSet<>();
        HashSet<Trajet> trajetRostock = new HashSet<>();
        HashSet<Trajet> trajetSaarbrücken= new HashSet<>();
        HashSet<Trajet> trajetSchwerin = new HashSet<>();
        HashSet<Trajet> trajetStuttgart = new HashSet<>();
        HashSet<Trajet> trajetUlm = new HashSet<>();
        HashSet<Trajet> trajetWürzburg = new HashSet<>();
        HashSet<Trajet> trajetAusburg = new HashSet<>();
        HashSet<Trajet> trajetEmden = new HashSet<>();
        HashSet<Trajet> trajetFlensburg = new HashSet<>();
        HashSet<Trajet> trajetOsnabrück = new HashSet<>();
        map.put(Ville.Berlin, trajetBerlin);
        map.put(Ville.Bremen, trajetBremen);
        map.put(Ville.Bremerhaven, trajetBremerhaven);
        map.put(Ville.Chemnitz, trajetChemnitz);
        map.put(Ville.Dresden, trajetDresden);
        map.put(Ville.Düsseldorf, trajetDüsseldorf);
        map.put(Ville.Erfurt, trajetErfurt);
        map.put(Ville.Freiburg, trajetFreiburg);
        map.put(Ville.Frankfurt, trajetFrankfurt);
        map.put(Ville.Hamburg, trajetHamburg);
        map.put(Ville.Hannover, trajetHannover);
        map.put(Ville.Karlsruhe, trajetKarlsruhe);
        map.put(Ville.Kassel, trajetKassel);
        map.put(Ville.Kiel, trajetKiel);
        map.put(Ville.Koblenz, trajetKoblenz);
        map.put(Ville.Konstanz, trajetKonstanz);
        map.put(Ville.Köln, trajetKöln);
        map.put(Ville.Leipzig, trajetLeipzig);
        map.put(Ville.Lindau, trajetLindau);
        map.put(Ville.Magdeburg, trajetMagdeburg);
        map.put(Ville.Mainz, trajetMainz);
        map.put(Ville.Mannheim, trajetMannheim);
        map.put(Ville.München, trajetMünchen);
        map.put(Ville.Münster, trajetMünster);
        map.put(Ville.Nürnberg, trajetNürnberg);
        map.put(Ville.Regensburg, trajetRegensburg);
        map.put(Ville.Rostock, trajetRostock);
        map.put(Ville.Saarbrücken, trajetSaarbrücken);
        map.put(Ville.Schwerin, trajetSchwerin);
        map.put(Ville.Stuttgart, trajetStuttgart);
        map.put(Ville.Ulm, trajetUlm);
        map.put(Ville.Würzburg, trajetWürzburg);
        map.put(Ville.Augsburg, trajetAusburg);
        map.put(Ville.Emden, trajetEmden);
        map.put(Ville.Flensburg, trajetFlensburg);
        map.put(Ville.Osnabrück, trajetOsnabrück);
    }
    // Accesseurs

    public HashMap<Ville, Set<Trajet>> getMap() {
        return map;
    }

    // Méthode

    public void ajouterTrajet(HashMap<Ville, Set<Trajet>> map, Set<Trajet> trajer,Ville ville){
        map.put(ville,trajet);
    }

    public String toString(){
        System.out.println("Voici la map, toutes les villes avec les trajets associés \n");
        //sans trajet jpp continuer
    }
    // j'ai besoin de trajet pour tester les méthodes des que c bon dites moi merci l'équipe
}
