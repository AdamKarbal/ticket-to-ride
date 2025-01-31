package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.meeples.MCouleur;
import fr.cotedazur.univ.polytech.teamO.meeples.Meeples;

import java.util.*;

public class GameMap {

    //Instance
    private Map<Ville, Map<Ville,Integer>> map;
    private List<Trajet> trajets;

    //Constructeur
    public GameMap(){
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
    private void init() {
        Random random = new Random();
        for (Ville ville : Ville.values()) {
            /*int nbMeeples = ville.getNbMeeple();
            Set<MCouleur> couleursUtilisées = new HashSet<>();
            for (int i = 0; i < nbMeeples; i++) {
                MCouleur couleur;
                int attempts = 0;
                do {
                    couleur = getRandomMeepleColor(MCouleur.class);
                    attempts++;
                    // Si trop d'essais, c'est qu'on n'a plus de couleurs valides
                    if (attempts > 100) {
                        throw new IllegalStateException("Trop d'essais pour générer des meeples avec des couleurs disponibles.");
                    }
                } while (couleursUtilisées.contains(couleur) || couleur.getQuant() == 0);
                couleursUtilisées.add(couleur);
                Meeples meeple = new Meeples(couleur);
                couleur.DecremQT(); // Décrémente le nombre de meeples restants pour cette couleur
                ville.getPassagers().add(meeple);
            }*/
            map.put(ville, new HashMap<>());
        }




        // Création des trajets uns à uns entre les villes.
        ajouterTrajet(Ville.Kassel, Ville.Hannover, 3, Couleur.Default );
        ajouterTrajet(Ville.Kassel, Ville.Hannover, 3, Couleur.Default );
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


    // Accesseurs
    public List<Trajet> getTrajets() {
        return trajets;
    }

    public Map<Ville, Map<Ville,Integer>> getMap() {
        return map;
    }

    // Méthode
    void ajouterTrajet(Ville ville, Ville voisin, int nbCases, Couleur couleur){
        Trajet trajet1 = new Trajet(ville,voisin,new Route(nbCases,couleur));
        Trajet trajet2 = new Trajet(voisin,ville,new Route(nbCases,couleur));
        map.get(ville).put(voisin,nbCases);
        map.get(voisin).put(ville,nbCases);
        trajets.add(trajet1);
        trajets.add(trajet2);
    }

    public Map<Ville,Integer> getVillesFrom(Ville ville) {
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
}
