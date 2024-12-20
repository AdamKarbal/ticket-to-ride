package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.gestion.Ville;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Map {

    //Instance
    private HashMap<Ville, ArrayList<Ville>> map;

    //Constructeur
    public Map(){
        this.map= new HashMap<>();
        init();
    }

    //Initialisation
    private void init(){
        for(Ville ville : Ville.values()){
            map.put(ville, new ArrayList<>());
        }

        ajouterVoisin(Ville.Augsburg, Ville.Ulm);
        ajouterVoisin(Ville.Augsburg, Ville.Nürnberg);
        ajouterVoisin(Ville.Augsburg, Ville.München);

        ajouterVoisin(Ville.Berlin, Ville.Magdeburg);
        ajouterVoisin(Ville.Berlin, Ville.Leipzig);
        ajouterVoisin(Ville.Berlin,Ville.Dresden);
        ajouterVoisin(Ville.Berlin, Ville.Schwerin);
        ajouterVoisin(Ville.Berlin,Ville.Hannover);
        ajouterVoisin(Ville.Berlin,Ville.Hamburg);
        ajouterVoisin(Ville.Berlin,Ville.Rostock);

        ajouterVoisin(Ville.Bremen, Ville.Hamburg);
        ajouterVoisin(Ville.Bremen, Ville.Hannover);
        ajouterVoisin(Ville.Bremen, Ville.Münster);
        ajouterVoisin(Ville.Bremen,Ville.Emden);
        ajouterVoisin(Ville.Bremen, Ville.Bremerhaven);

        ajouterVoisin(Ville.Bremerhaven, Ville.Emden);
        ajouterVoisin(Ville.Bremerhaven, Ville.Bremen);
        ajouterVoisin(Ville.Bremerhaven,Ville.Hamburg);
        ajouterVoisin(Ville.Bremerhaven,Ville.Kiel);

        ajouterVoisin(Ville.Chemnitz, Ville.Leipzig);
        ajouterVoisin(Ville.Chemnitz, Ville.Dresden);
        ajouterVoisin(Ville.Chemnitz, Ville.Erfurt);
        ajouterVoisin(Ville.Chemnitz,Ville.Regensburg);

        ajouterVoisin(Ville.Dresden, Ville.Leipzig);
        ajouterVoisin(Ville.Dresden, Ville.Chemnitz);
        ajouterVoisin(Ville.Dresden,Ville.Berlin);
        ajouterVoisin(Ville.Dresden,Ville.Regensburg);

        ajouterVoisin(Ville.Düsseldorf, Ville.Köln);
        ajouterVoisin(Ville.Düsseldorf, Ville.Dortmund);

        ajouterVoisin(Ville.Erfurt, Ville.Leipzig);
        ajouterVoisin(Ville.Erfurt,Ville.Nürnberg);
        ajouterVoisin(Ville.Erfurt,Ville.Hannover);
        ajouterVoisin(Ville.Erfurt, Ville.Chemnitz);
        ajouterVoisin(Ville.Erfurt, Ville.Kassel);
        ajouterVoisin(Ville.Erfurt,Ville.Regensburg);

        ajouterVoisin(Ville.Freiburg, Ville.Karlsruhe);
        ajouterVoisin(Ville.Freiburg, Ville.Stuttgart);
        ajouterVoisin(Ville.Freiburg, Ville.Konstanz);

        ajouterVoisin(Ville.Frankfurt, Ville.Mainz);
        ajouterVoisin(Ville.Frankfurt, Ville.Kassel);
        ajouterVoisin(Ville.Frankfurt, Ville.Würzburg);
        ajouterVoisin(Ville.Frankfurt,Ville.Köln);
        ajouterVoisin(Ville.Frankfurt, Ville.Mannheim);

        ajouterVoisin(Ville.Hamburg, Ville.Bremen);
        ajouterVoisin(Ville.Hamburg, Ville.Hannover);
        ajouterVoisin(Ville.Hamburg,Ville.Bremerhaven);
        ajouterVoisin(Ville.Hamburg,Ville.Berlin);
        ajouterVoisin(Ville.Hamburg, Ville.Kiel);
        ajouterVoisin(Ville.Hamburg, Ville.Schwerin);

        ajouterVoisin(Ville.Hannover, Ville.Bremen);
        ajouterVoisin(Ville.Hannover,Ville.Hamburg);
        ajouterVoisin(Ville.Hannover,Ville.Berlin);
        ajouterVoisin(Ville.Hannover,Ville.Erfurt);
        ajouterVoisin(Ville.Hannover, Ville.Magdeburg);
        ajouterVoisin(Ville.Hannover, Ville.Kassel);
        ajouterVoisin(Ville.Hannover, Ville.Münster);

        ajouterVoisin(Ville.Karlsruhe, Ville.Freiburg);
        ajouterVoisin(Ville.Karlsruhe, Ville.Stuttgart);
        ajouterVoisin(Ville.Karlsruhe, Ville.Saarbrücken);
        ajouterVoisin(Ville.Karlsruhe, Ville.Mannheim);

        ajouterVoisin(Ville.Kassel, Ville.Frankfurt);
        ajouterVoisin(Ville.Kassel, Ville.Erfurt);
        ajouterVoisin(Ville.Kassel,Ville.Dortmund);
        ajouterVoisin(Ville.Kassel, Ville.Hannover);


        ajouterVoisin(Ville.Kiel, Ville.Hamburg);
        ajouterVoisin(Ville.Kiel,Ville.Rostock);
        ajouterVoisin(Ville.Kiel,Ville.Bremerhaven);
        ajouterVoisin(Ville.Kiel,Ville.Schwerin);

        ajouterVoisin(Ville.Koblenz, Ville.Saarbrücken);
        ajouterVoisin(Ville.Koblenz, Ville.Mainz);
        ajouterVoisin(Ville.Koblenz, Ville.Köln);

        ajouterVoisin(Ville.Konstanz, Ville.Lindau);
        ajouterVoisin(Ville.Konstanz, Ville.Stuttgart);
        ajouterVoisin(Ville.Konstanz,Ville.Freiburg);

        ajouterVoisin(Ville.Köln, Ville.Düsseldorf);
        ajouterVoisin(Ville.Köln, Ville.Koblenz);
        ajouterVoisin(Ville.Köln,Ville.Frankfurt);

        ajouterVoisin(Ville.Leipzig, Ville.Berlin);
        ajouterVoisin(Ville.Leipzig, Ville.Chemnitz);
        ajouterVoisin(Ville.Leipzig, Ville.Erfurt);
        ajouterVoisin(Ville.Leipzig,Ville.Dresden);
        ajouterVoisin(Ville.Leipzig,Ville.Magdeburg);

        ajouterVoisin(Ville.Lindau, Ville.Konstanz);
        ajouterVoisin(Ville.Lindau, Ville.Ulm);
        ajouterVoisin(Ville.Lindau,Ville.München);

        ajouterVoisin(Ville.Magdeburg, Ville.Berlin);
        ajouterVoisin(Ville.Magdeburg, Ville.Hannover);
        ajouterVoisin(Ville.Magdeburg, Ville.Leipzig);

        ajouterVoisin(Ville.Mainz, Ville.Frankfurt);
        ajouterVoisin(Ville.Mainz, Ville.Koblenz);
        ajouterVoisin(Ville.Mainz, Ville.Mannheim);
        ajouterVoisin(Ville.Mainz,Ville.Saarbrücken);

        ajouterVoisin(Ville.Mannheim, Ville.Mainz);
        ajouterVoisin(Ville.Mannheim, Ville.Karlsruhe);
        ajouterVoisin(Ville.Mannheim, Ville.Frankfurt);
        ajouterVoisin(Ville.Mannheim, Ville.Saarbrücken);
        ajouterVoisin(Ville.Mannheim, Ville.Stuttgart);

        ajouterVoisin(Ville.München, Ville.Lindau);
        ajouterVoisin(Ville.München, Ville.Augsburg);
        ajouterVoisin(Ville.München, Ville.Regensburg);
        ajouterVoisin(Ville.München, Ville.Nürnberg);

        ajouterVoisin(Ville.Münster, Ville.Bremen);
        ajouterVoisin(Ville.Münster, Ville.Dortmund);
        ajouterVoisin(Ville.Münster, Ville.Hannover);
        ajouterVoisin(Ville.Münster, Ville.Emden);

        ajouterVoisin(Ville.Nürnberg, Ville.Würzburg);
        ajouterVoisin(Ville.Nürnberg, Ville.Regensburg);
        ajouterVoisin(Ville.Nürnberg, Ville.Augsburg);
        ajouterVoisin(Ville.Nürnberg, Ville.Erfurt);
        ajouterVoisin(Ville.Nürnberg, Ville.München);

        ajouterVoisin(Ville.Regensburg, Ville.München);
        ajouterVoisin(Ville.Regensburg, Ville.Nürnberg);
        ajouterVoisin(Ville.Regensburg, Ville.Dresden);
        ajouterVoisin(Ville.Regensburg, Ville.Chemnitz);
        ajouterVoisin(Ville.Regensburg, Ville.Erfurt);

        ajouterVoisin(Ville.Rostock, Ville.Schwerin);
        ajouterVoisin(Ville.Rostock, Ville.Kiel);
        ajouterVoisin(Ville.Rostock, Ville.Berlin);

        ajouterVoisin(Ville.Saarbrücken, Ville.Mainz);
        ajouterVoisin(Ville.Saarbrücken, Ville.Koblenz);
        ajouterVoisin(Ville.Saarbrücken, Ville.Mannheim);
        ajouterVoisin(Ville.Saarbrücken, Ville.Karlsruhe);

        ajouterVoisin(Ville.Schwerin, Ville.Hamburg);
        ajouterVoisin(Ville.Schwerin, Ville.Rostock);
        ajouterVoisin(Ville.Schwerin, Ville.Kiel);
        ajouterVoisin(Ville.Schwerin, Ville.Berlin);

        ajouterVoisin(Ville.Stuttgart, Ville.Karlsruhe);
        ajouterVoisin(Ville.Stuttgart, Ville.Ulm);
        ajouterVoisin(Ville.Stuttgart, Ville.Mannheim);
        ajouterVoisin(Ville.Stuttgart, Ville.Konstanz);

        ajouterVoisin(Ville.Ulm, Ville.Stuttgart);
        ajouterVoisin(Ville.Ulm, Ville.Augsburg);
        ajouterVoisin(Ville.Ulm, Ville.Lindau);

        ajouterVoisin(Ville.Würzburg, Ville.Frankfurt);
        ajouterVoisin(Ville.Würzburg, Ville.Nürnberg);
    }


    // Accesseurs

    public HashMap<Ville, ArrayList<Ville>> getMap() {
        return map;
    }

    // Méthode

    private void ajouterVoisin(Ville ville,Ville voisin){
        map.get(ville).add(voisin);
    }

    public ArrayList<Ville> getVillesFrom(Ville ville) {
        return map.get(ville);
    }

    public String toString(){
        System.out.println("Voici la map, toutes les villes avec les trajets associés \n");
        //sans trajet jpp continuer
        return "";
    }

    // j'ai besoin de trajet pour tester les méthodes des que c bon dites moi merci l'équipe
}
