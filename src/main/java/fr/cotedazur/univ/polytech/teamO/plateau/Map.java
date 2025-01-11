package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.Ville;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;

import java.util.*;

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

    //Méthode pour récupérer une couleur au hasard parmi les couleurs des Meeples

    public <T extends Enum<MCouleur>> T getRandomMeepleColor(Class<T> enumClass) {
        T[] Couleurs = enumClass.getEnumConstants(); // Récupérer les valeurs de l'énum sous forme d'un tableau
        int randomIndex = new Random().nextInt(Couleurs.length); // Générer un index aléatoire
        return Couleurs[randomIndex];
    }

    //Initialisation
    private void init(){
        Random random = new Random();
        for(Ville ville : Ville.values()){
            int nbMeeples = ville.getNbMeeple();
            Set<MCouleur> couleursUtilisées = new HashSet<>();

            for (int i = 0; i < nbMeeples; i++) {
                // Génére une couleur unique pour chaque meeple dans une ville
                MCouleur couleur;
                do {
                    couleur = getRandomMeepleColor(MCouleur.class);
                } while (couleursUtilisées.contains(couleur) || couleur.getQuant() == 0);

                couleursUtilisées.add(couleur);

                //Crée le meeple et l'ajoute à la ville
                Meeples meeple = new Meeples(couleur);
                couleur.DecremQT(); //Décrémente l'attribut nombre de pions meeple restants pour chaque couleur de meeple
                ville.getPassagers().add(meeple);
            }

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
        ajouterVoisin(Ville.Kiel,Ville.Danemark);
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
        ajouterVoisin(Ville.Düsseldorf, Ville.Niederlande);

        ajouterVoisin(Ville.Emden, Ville.Niederlande);

        ajouterVoisin(Ville.Erfurt, Ville.Leipzig);
        ajouterVoisin(Ville.Erfurt,Ville.Nürnberg);
        ajouterVoisin(Ville.Erfurt,Ville.Hannover);
        ajouterVoisin(Ville.Erfurt, Ville.Chemnitz);
        ajouterVoisin(Ville.Erfurt, Ville.Kassel);
        ajouterVoisin(Ville.Erfurt,Ville.Regensburg);

        ajouterVoisin(Ville.Freiburg, Ville.Karlsruhe);
        ajouterVoisin(Ville.Freiburg, Ville.Stuttgart);
        ajouterVoisin(Ville.Freiburg, Ville.Konstanz);
        ajouterVoisin(Ville.Freiburg, Ville.Frankreich);
        ajouterVoisin(Ville.Freiburg, Ville.Schweiz);

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
        ajouterVoisin(Ville.Karlsruhe, Ville.Frankreich);

        ajouterVoisin(Ville.Kassel, Ville.Frankfurt);
        ajouterVoisin(Ville.Kassel, Ville.Erfurt);
        ajouterVoisin(Ville.Kassel,Ville.Dortmund);
        ajouterVoisin(Ville.Kassel, Ville.Hannover);


        ajouterVoisin(Ville.Kiel, Ville.Hamburg);
        ajouterVoisin(Ville.Kiel,Ville.Rostock);
        ajouterVoisin(Ville.Kiel,Ville.Bremerhaven);
        ajouterVoisin(Ville.Bremerhaven, Ville.Danemark);
        ajouterVoisin(Ville.Kiel,Ville.Schwerin);

        ajouterVoisin(Ville.Koblenz, Ville.Saarbrücken);
        ajouterVoisin(Ville.Koblenz, Ville.Mainz);
        ajouterVoisin(Ville.Koblenz, Ville.Köln);

        ajouterVoisin(Ville.Konstanz, Ville.Lindau);
        ajouterVoisin(Ville.Konstanz, Ville.Stuttgart);
        ajouterVoisin(Ville.Konstanz,Ville.Freiburg);
        ajouterVoisin(Ville.Konstanz,Ville.Schweiz);

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
        ajouterVoisin(Ville.Lindau, Ville.Schweiz);
        ajouterVoisin(Ville.Lindau,Ville.Österreich);

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
        ajouterVoisin(Ville.München,Ville.Österreich);
        ajouterVoisin(Ville.München, Ville.Konstanz);

        ajouterVoisin(Ville.Münster, Ville.Bremen);
        ajouterVoisin(Ville.Münster, Ville.Dortmund);
        ajouterVoisin(Ville.Münster, Ville.Hannover);
        ajouterVoisin(Ville.Münster, Ville.Emden);
        ajouterVoisin(Ville.Münster, Ville.Niederlande);

        ajouterVoisin(Ville.Niederlande, Ville.Emden);
        ajouterVoisin(Ville.Niederlande, Ville.Münster);
        ajouterVoisin(Ville.Niederlande, Ville.Düsseldorf);

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
        ajouterVoisin(Ville.Regensburg, Ville.Österreich);

        ajouterVoisin(Ville.Rostock, Ville.Schwerin);
        ajouterVoisin(Ville.Rostock, Ville.Kiel);
        ajouterVoisin(Ville.Rostock, Ville.Berlin);

        ajouterVoisin(Ville.Saarbrücken, Ville.Mainz);
        ajouterVoisin(Ville.Saarbrücken, Ville.Koblenz);
        ajouterVoisin(Ville.Saarbrücken, Ville.Mannheim);
        ajouterVoisin(Ville.Saarbrücken, Ville.Karlsruhe);
        ajouterVoisin(Ville.Saarbrücken, Ville.Frankreich);

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


        // Création des trajets uns à uns entre les villes.

        ajouterTrajet(Ville.Kiel, Ville.Hamburg, 2, Couleur.Violet);
        ajouterTrajet(Ville.Kiel, Ville.Hamburg, 2, Couleur.Noir);

        ajouterTrajet(Ville.Kiel, Ville.Schwerin, 3, Couleur.Jaune );

        ajouterTrajet(Ville.Kiel, Ville.Rostock, 4, Couleur.Orange );

        ajouterTrajet(Ville.Kiel, Ville.Bremerhaven, 3, Couleur.Default );
        ajouterTrajet(Ville.Kiel, Ville.Danemark, 2, Couleur.Default );

        ajouterTrajet(Ville.Bremerhaven, Ville.Hamburg, 3, Couleur.Default );

        ajouterTrajet(Ville.Bremerhaven, Ville.Danemark, 5, Couleur.Vert);
        ajouterTrajet(Ville.Hamburg, Ville.Schwerin, 2, Couleur.Vert );

        ajouterTrajet(Ville.Schwerin, Ville.Rostock, 2, Couleur.Rouge );
        ajouterTrajet(Ville.Rostock, Ville.Berlin, 6, Couleur.Violet );

        ajouterTrajet(Ville.Schwerin, Ville.Berlin, 5, Couleur.Blanc );

        ajouterTrajet(Ville.Hamburg, Ville.Berlin, 7, Couleur.Bleu );
        ajouterTrajet(Ville.Hamburg, Ville.Berlin, 7, Couleur.Jaune );

        ajouterTrajet(Ville.Hamburg, Ville.Hannover, 4, Couleur.Rouge );
        ajouterTrajet(Ville.Hamburg, Ville.Hannover, 4, Couleur.Blanc );

        ajouterTrajet(Ville.Hamburg, Ville.Bremen, 3, Couleur.Orange );
        ajouterTrajet(Ville.Bremerhaven, Ville.Emden, 3, Couleur.Default );
        ajouterTrajet(Ville.Bremerhaven, Ville.Bremen, 1, Couleur.Blanc );

        ajouterTrajet(Ville.Emden, Ville.Bremen, 3, Couleur.Bleu );
        ajouterTrajet(Ville.Bremen, Ville.Münster, 3, Couleur.Noir );
        ajouterTrajet(Ville.Bremen, Ville.Hannover, 3, Couleur.Jaune );

        ajouterTrajet(Ville.Hannover, Ville.Berlin, 7, Couleur.Noir );

        ajouterTrajet(Ville.Berlin, Ville.Magdeburg, 3, Couleur.Rouge );
        ajouterTrajet(Ville.Berlin, Ville.Leipzig, 4, Couleur.Orange );
        ajouterTrajet(Ville.Berlin, Ville.Dresden, 5, Couleur.Vert );
        ajouterTrajet(Ville.Magdeburg, Ville.Hannover, 4, Couleur.Bleu );

        ajouterTrajet(Ville.Magdeburg, Ville.Leipzig, 2, Couleur.Jaune );
        ajouterTrajet(Ville.Leipzig, Ville.Erfurt, 3, Couleur.Rouge );
        ajouterTrajet(Ville.Leipzig, Ville.Dresden, 3, Couleur.Noir );
        ajouterTrajet(Ville.Leipzig, Ville.Chemnitz, 2, Couleur.Bleu );

        ajouterTrajet(Ville.Erfurt, Ville.Chemnitz, 4, Couleur.Noir );
        ajouterTrajet(Ville.Hannover, Ville.Erfurt, 5, Couleur.Vert );
        ajouterTrajet(Ville.Erfurt, Ville.Nürnberg, 4, Couleur.Violet );
        ajouterTrajet(Ville.Erfurt, Ville.Nürnberg, 4, Couleur.Jaune );

        ajouterTrajet(Ville.Erfurt, Ville.Regensburg, 7, Couleur.Blanc );
        ajouterTrajet(Ville.Chemnitz, Ville.Regensburg, 6, Couleur.Violet );

        ajouterTrajet(Ville.Dresden, Ville.Regensburg, 7, Couleur.Rouge );
        ajouterTrajet(Ville.Dresden, Ville.Berlin, 5, Couleur.Vert );
        ajouterTrajet(Ville.Erfurt, Ville.Kassel, 3, Couleur.Default );

        ajouterTrajet(Ville.Kassel, Ville.Hannover, 3, Couleur.Default );
        ajouterTrajet(Ville.Kassel, Ville.Hannover, 3, Couleur.Default );

        ajouterTrajet(Ville.Münster, Ville.Hannover, 4, Couleur.Violet );

        ajouterTrajet(Ville.Münster, Ville.Emden, 4, Couleur.Rouge );
        ajouterTrajet(Ville.Emden, Ville.Niederlande, 2, Couleur.Blanc );

        ajouterTrajet(Ville.Münster, Ville.Niederlande, 2, Couleur.Orange );
        ajouterTrajet(Ville.Düsseldorf, Ville.Niederlande, 3, Couleur.Violet );

        ajouterTrajet(Ville.Düsseldorf, Ville.Dortmund, 1, Couleur.Default );
        ajouterTrajet(Ville.Düsseldorf, Ville.Dortmund, 1, Couleur.Default );
        ajouterTrajet(Ville.Düsseldorf, Ville.Dortmund, 1, Couleur.Default );

        ajouterTrajet(Ville.Dortmund, Ville.Münster, 1, Couleur.Default );
        ajouterTrajet(Ville.Dortmund, Ville.Münster, 1, Couleur.Default );

        ajouterTrajet(Ville.Düsseldorf, Ville.Köln, 1, Couleur.Default );
        ajouterTrajet(Ville.Düsseldorf, Ville.Köln, 1, Couleur.Default );
        ajouterTrajet(Ville.Düsseldorf, Ville.Köln, 1, Couleur.Default );

        ajouterTrajet(Ville.Köln, Ville.Frankfurt, 4, Couleur.Default );
        ajouterTrajet(Ville.Köln, Ville.Frankfurt, 4, Couleur.Default );

        ajouterTrajet(Ville.Köln, Ville.Koblenz, 1, Couleur.Default );
        ajouterTrajet(Ville.Köln, Ville.Koblenz, 1, Couleur.Default );

        ajouterTrajet(Ville.Koblenz, Ville.Mainz, 2, Couleur.Default );
        ajouterTrajet(Ville.Koblenz, Ville.Mainz, 2, Couleur.Default );
        ajouterTrajet(Ville.Koblenz, Ville.Saarbrücken, 3, Couleur.Default );

        ajouterTrajet(Ville.Mainz, Ville.Frankfurt, 1, Couleur.Default );
        ajouterTrajet(Ville.Mainz, Ville.Frankfurt, 1, Couleur.Default );

        ajouterTrajet(Ville.Mainz, Ville.Mannheim, 1, Couleur.Default );
        ajouterTrajet(Ville.Mainz, Ville.Mannheim, 1, Couleur.Default );

        ajouterTrajet(Ville.Mainz, Ville.Saarbrücken, 3, Couleur.Default );
        ajouterTrajet(Ville.Mannheim, Ville.Saarbrücken, 3, Couleur.Default );
        ajouterTrajet(Ville.Saarbrücken, Ville.Karlsruhe, 3, Couleur.Default );

        ajouterTrajet(Ville.Frankfurt, Ville.Mannheim, 2, Couleur.Default );
        ajouterTrajet(Ville.Frankfurt, Ville.Mannheim, 2, Couleur.Default );

        ajouterTrajet(Ville.Dortmund, Ville.Kassel, 4, Couleur.Vert );
        ajouterTrajet(Ville.Frankfurt, Ville.Kassel, 4, Couleur.Bleu );
        ajouterTrajet(Ville.Frankfurt, Ville.Kassel, 4, Couleur.Blanc);

        ajouterTrajet(Ville.Frankfurt, Ville.Würzburg, 2, Couleur.Default );
        ajouterTrajet(Ville.Frankfurt, Ville.Würzburg, 2, Couleur.Default );

        ajouterTrajet(Ville.Würzburg, Ville.Nürnberg, 2, Couleur.Default );
        ajouterTrajet(Ville.Würzburg, Ville.Nürnberg, 2, Couleur.Default );

        ajouterTrajet(Ville.Nürnberg, Ville.Augsburg, 4, Couleur.Orange );
        ajouterTrajet(Ville.Nürnberg, Ville.München, 5, Couleur.Bleu );
        ajouterTrajet(Ville.Nürnberg, Ville.München, 5, Couleur.Noir );

        ajouterTrajet(Ville.Nürnberg, Ville.Regensburg, 3, Couleur.Vert );

        ajouterTrajet(Ville.Regensburg, Ville.München, 3, Couleur.Orange );
        ajouterTrajet(Ville.Regensburg, Ville.Österreich, 4, Couleur.Jaune );

        ajouterTrajet(Ville.München, Ville.Österreich, 3, Couleur.Rouge );
        ajouterTrajet(Ville.München, Ville.Augsburg, 2, Couleur.Default );
        ajouterTrajet(Ville.München, Ville.Augsburg, 2, Couleur.Default );
        ajouterTrajet(Ville.Augsburg, Ville.Ulm, 1, Couleur.Default );
        ajouterTrajet(Ville.Augsburg, Ville.Ulm, 1, Couleur.Default );

        ajouterTrajet(Ville.Ulm, Ville.Lindau, 2, Couleur.Rouge );
        ajouterTrajet(Ville.Ulm, Ville.Stuttgart, 2, Couleur.Default );
        ajouterTrajet(Ville.Ulm, Ville.Stuttgart, 2, Couleur.Default );

        ajouterTrajet(Ville.Mannheim, Ville.Stuttgart, 2, Couleur.Default );
        ajouterTrajet(Ville.Mannheim, Ville.Stuttgart, 2, Couleur.Default );

        ajouterTrajet(Ville.Mannheim, Ville.Karlsruhe, 1, Couleur.Bleu );
        ajouterTrajet(Ville.Frankreich, Ville.Karlsruhe, 2, Couleur.Noir );
        ajouterTrajet(Ville.Frankreich, Ville.Saarbrücken, 1, Couleur.Vert );
        ajouterTrajet(Ville.Karlsruhe, Ville.Stuttgart, 1, Couleur.Violet );

        ajouterTrajet(Ville.Karlsruhe, Ville.Freiburg, 3, Couleur.Blanc );
        ajouterTrajet(Ville.Freiburg, Ville.Stuttgart, 3, Couleur.Default );
        ajouterTrajet(Ville.Konstanz, Ville.Stuttgart, 3, Couleur.Vert );
        ajouterTrajet(Ville.Frankreich, Ville.Freiburg, 2, Couleur.Jaune );
        ajouterTrajet(Ville.Freiburg, Ville.Schweiz, 2, Couleur.Orange );
        ajouterTrajet(Ville.Freiburg, Ville.Konstanz, 2, Couleur.Noir );
        ajouterTrajet(Ville.Konstanz, Ville.Schweiz, 1, Couleur.Blanc );
        ajouterTrajet(Ville.Konstanz, Ville.Lindau, 1, Couleur.Jaune );
        ajouterTrajet(Ville.Schweiz, Ville.Lindau, 2, Couleur.Bleu );
        ajouterTrajet(Ville.Österreich, Ville.Lindau, 2, Couleur.Violet );
        ajouterTrajet(Ville.Konstanz, Ville.München, 5, Couleur.Default );
    }

    //La méthode marche nickel, mais l'exeption peut pas être implémentée correctement pcq il manque des voisins.
    void ajouterTrajet(Ville ville1, Ville ville2, int nbCases, Couleur couleur){
       if(!map.get(ville1).contains(ville2) && !map.get(ville2).contains(ville1)){
            throw new NotNearbyCitiesException("Vous ne pouvez pas créer de trajet entre "+ville1.name()+" et "+ville2.name());
       }
        Route routeTr = new Route(nbCases, couleur);
        trajets.add(new Trajet(ville1, ville2, routeTr));
    }

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
