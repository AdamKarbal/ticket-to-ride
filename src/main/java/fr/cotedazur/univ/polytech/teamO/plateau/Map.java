package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.gestion.Ville;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    //Instance
    private HashMap<Ville, ArrayList<Ville>> map;
    private ArrayList<Trajet> trajets;

    //Constructeur
    public Map(){
        this.map= new HashMap<>();
        this.trajets = new ArrayList<>();
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

        /*ajouterTrajet(Ville.Kiel, Ville.Hamburg, 1);
        ajouterTrajet(Ville.Kiel, Ville.Schwerin, 1);

        ajouterTrajet(Ville.Schwerin, Ville.Rostock, 1);
        ajouterTrajet(Ville.Schwerin, Ville.Berlin, 2);

        ajouterTrajet(Ville.Hamburg, Ville.Bremen, 1);
        ajouterTrajet(Ville.Hamburg, Ville.Hannover, 2);
        ajouterTrajet(Ville.Hamburg, Ville.Berlin, 2);

        ajouterTrajet(Ville.Bremen, Ville.Emden, 1);
        ajouterTrajet(Ville.Bremen, Ville.Münster, 1);
        ajouterTrajet(Ville.Bremen, Ville.Hannover, 1);

        ajouterTrajet(Ville.Hannover, Ville.Kassel, 2);
        ajouterTrajet(Ville.Hannover, Ville.Erfurt, 1);
        ajouterTrajet(Ville.Hannover, Ville.Berlin, 1);
        ajouterTrajet(Ville.Hannover, Ville.Magdeburg, 1);

        ajouterTrajet(Ville.Berlin, Ville.Magdeburg, 1);
        ajouterTrajet(Ville.Berlin, Ville.Leipzig, 1);
        ajouterTrajet(Ville.Berlin, Ville.Dresden, 1);

        ajouterTrajet(Ville.Magdeburg, Ville.Leipzig, 1);
        ajouterTrajet(Ville.Magdeburg, Ville.Erfurt, 1);

        ajouterTrajet(Ville.Erfurt, Ville.Leipzig, 1);
        ajouterTrajet(Ville.Erfurt, Ville.Chemnitz, 1);
        ajouterTrajet(Ville.Erfurt, Ville.Würzburg, 1);

        ajouterTrajet(Ville.Leipzig, Ville.Chemnitz, 1);
        ajouterTrajet(Ville.Chemnitz, Ville.Dresden, 1);
        ajouterTrajet(Ville.Dresden, Ville.Regensburg, 2);

        ajouterTrajet(Ville.Kassel, Ville.Erfurt, 1);
        ajouterTrajet(Ville.Kassel, Ville.Frankfurt, 2);
        ajouterTrajet(Ville.Frankfurt, Ville.Mannheim, 2);
        ajouterTrajet(Ville.Frankfurt, Ville.Würzburg, 1);
        ajouterTrajet(Ville.Würzburg, Ville.Nürnberg, 1);
        ajouterTrajet(Ville.Nürnberg, Ville.Ulm, 1);
        ajouterTrajet(Ville.Nürnberg, Ville.Regensburg, 1);
        ajouterTrajet(Ville.Regensburg, Ville.München, 1);
        ajouterTrajet(Ville.Ulm, Ville.München, 1);
        ajouterTrajet(Ville.Ulm, Ville.Stuttgart, 1);
        ajouterTrajet(Ville.Stuttgart, Ville.Karlsruhe, 1);
        ajouterTrajet(Ville.Karlsruhe, Ville.Saarbrücken, 1);
        ajouterTrajet(Ville.Karlsruhe, Ville.Mannheim, 1);
        ajouterTrajet(Ville.Mannheim, Ville.Koblenz, 2);
        ajouterTrajet(Ville.Koblenz, Ville.Köln, 1);
        ajouterTrajet(Ville.Koblenz, Ville.Düsseldorf, 1);
        ajouterTrajet(Ville.Köln, Ville.Düsseldorf, 1);
        ajouterTrajet(Ville.Düsseldorf, Ville.Münster, 1);
        ajouterTrajet(Ville.Münster, Ville.Dortmund, 1);
        ajouterTrajet(Ville.Dortmund, Ville.Düsseldorf, 1);

        ajouterTrajet(Ville.Freiburg, Ville.Karlsruhe, 1);
        ajouterTrajet(Ville.Freiburg, Ville.Stuttgart, 1);
        ajouterTrajet(Ville.Freiburg, Ville.Konstanz, 1);
        ajouterTrajet(Ville.Konstanz, Ville.Stuttgart, 1);
        ajouterTrajet(Ville.Konstanz, Ville.Lindau, 1);

        ajouterTrajet(Ville.Lindau, Ville.München, 1);
        ajouterTrajet(Ville.Saarbrücken, Ville.Mannheim, 1);
        ajouterTrajet(Ville.Saarbrücken, Ville.Karlsruhe, 1);*/

    }

    /*private void ajouterTrajet(Ville ville1, Ville ville2, int nombreTrajets) {
        for (int i = 0; i < nombreTrajets; i++) {
            trajets.add(new Trajet(ville1, ville2));
        }
    }*/

    public ArrayList<Trajet> getTrajets() {
        return trajets;
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Carte des trajets :\n");
        for (Trajet trajet : trajets) {
            builder.append(trajet.toString()).append("\n");
        }
        return builder.toString();
    }

    // j'ai besoin de trajet pour tester les méthodes des que c bon dites moi merci l'équipe
}
