package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.bot.RobotRoute;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.*;

import java.util.*;
import java.util.stream.Collectors;

public class GestionTrajets {
    private GameMap gameMap;
    private Map<Ville,Map<Ville,Integer>> trajetsDisponibles;
    public GestionTrajets(GameMap map){
        this.gameMap = map;
        this.trajetsDisponibles = new HashMap<>(map.getMap());
    }
    // Méthodes auxiliaires

    private void updateTrajetsDisponibles(Trajet trajet) {
        Ville from = trajet.getFrom();
        Ville to = trajet.getTo();
        if (trajetsDisponibles.containsKey(from)) {
            trajetsDisponibles.get(from).remove(to);
            if (trajetsDisponibles.get(from).isEmpty()) {
                trajetsDisponibles.remove(from);
            }
        }
        if (trajetsDisponibles.containsKey(to)) {
            trajetsDisponibles.get(to).remove(from);
            if (trajetsDisponibles.get(to).isEmpty()) {
                trajetsDisponibles.remove(to);
            }
        }
    }
    private Map<Ville,Map<Ville,Integer>> buildSubgraph(List<Trajet> trajets){
        Map<Ville,Map<Ville,Integer>> subgraph = new HashMap<>();
        for (Trajet trajet : trajets) {
            Ville from=trajet.getFrom();
            Ville to=trajet.getTo();
            subgraph.putIfAbsent(from, new HashMap<>());
            subgraph.putIfAbsent(to, new HashMap<>());
            subgraph.get(from).put(to,1);
            subgraph.get(to).put(from,1);
        }
        return subgraph;
    }

    private boolean isConnectedByGraph(Ville from,Ville to,Map<Ville,Map<Ville,Integer>> subgraph){
        Set<Ville> visited = new HashSet<>();
        Queue<Ville> queue = new LinkedList<>();
        queue.add(from);
        while(!queue.isEmpty()){
            Ville current = queue.poll();
            visited.add(current);

            Map<Ville, Integer> neighbors = subgraph.get(current);
            if(neighbors!=null){
                for(Ville voisin : subgraph.get(current).keySet()){
                    if(voisin.equals(to)){
                        return true;
                    }
                    if(!visited.contains(voisin)){
                        queue.add(voisin);
                    }
                }
            }

        }
        return false;
    }

    // Algorithme de Dijkstra pour trouver le prochain segment optimal
    private Trajet findNextStep(RobotRoute robotRoute, Ville start, Ville end, Map<Ville, Map<Ville, Integer>> graph) {
        Map<Ville, Integer> distances = new HashMap<>();
        Map<Ville, Ville> previous = new HashMap<>();
        PriorityQueue<Ville> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize distances and previous maps
        for (Ville ville : graph.keySet()) {
            distances.put(ville, Integer.MAX_VALUE);
            previous.put(ville, null);
        }
        distances.put(start, 0);
        queue.add(start);

        // Dijkstra's algorithm to find the shortest path
        while (!queue.isEmpty()) {
            Ville current = queue.poll();
            if (current.equals(end)) break;

            Map<Ville, Integer> neighbors = graph.get(current);
            if (neighbors != null) {
                for (Map.Entry<Ville, Integer> entry : neighbors.entrySet()) {
                    Ville neighbor = entry.getKey();

                    // Skip countries as intermediate steps
                    if (!neighbor.getIsVille() && !neighbor.equals(end)) {
                        continue;
                    }

                    int weight = entry.getValue();
                    int newDist = distances.get(current) + weight;

                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        previous.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Identify the first segment of the optimal path
        Ville nextStep = end;
        while (previous.get(nextStep) != null && !previous.get(nextStep).equals(start)) {
            nextStep = previous.get(nextStep);

            // Skip intermediate steps that are countries
            if (!nextStep.getIsVille()) {
                return null; // No valid path if an intermediate step is a country
            }
        }

        if (previous.get(nextStep) == null) {
            return null; // No path found
        }

        // Create the trajectory with the matching color
        Couleur couleur = findMatchingCouleur(robotRoute, start, nextStep);
        return new Trajet(start, nextStep, new Route(graph.get(start).get(nextStep), couleur));
    }


    private Couleur findMatchingCouleur(RobotRoute robotRoute, Ville from, Ville to) {
        // Crée un trajet fictif pour la recherche
        Trajet trajetVoulu = new Trajet(from, to, new Route(1, Couleur.Default));

        // Filtre les trajets correspondants, récupère leurs couleurs et élimine les doublons
        List<Couleur> couleurs = gameMap.getTrajets().stream()
                .filter(trajet -> trajetVoulu.equals(trajet))
                .map(trajet -> trajet.getRoute().getCouleur())
                .distinct() // Élimine les doublons
                .collect(Collectors.toList());

        // Retourne la couleur avec la priorité maximale si plusieurs sont trouvées
        if (couleurs.size() > 1) {
            return findMaxCouleurBetween(robotRoute, couleurs);
        }

        // Retourne la seule couleur trouvée ou null si aucune n'existe
        return couleurs.isEmpty() ? null : couleurs.get(0);
    }



    private Couleur findMaxCouleurBetween(RobotRoute robotRoute,List<Couleur> couleurs){
        Couleur couleurMax=couleurs.get(0);
        int max = 0;
        for(Couleur couleur : couleurs ){
            if(max<robotRoute.getWagonByCouleur().get(couleur)){
                max=robotRoute.getWagonByCouleur().get(couleur);
                couleurMax = couleur;
            }
        }
        return couleurMax;
    }

    public void updateAfterTrainPlacement(RobotRoute robotRoute, Trajet trajet) {
        // Update the robot's current city
        robotRoute.setVilleActuelle(trajet.getTo());

        // Update available trips
        updateTrajetsDisponibles(trajet);
    }

    // Méthodes principales
    public boolean isConnected(Ville From, Ville To, List<Trajet> trajets){
        Map<Ville, Map<Ville, Integer>> subgraph = buildSubgraph(trajets);
        return isConnectedByGraph(From,To,subgraph);
    }



    // Trouver le trajet optimal à partir de la ville actuelle
    public Trajet findOptimalTrajet(RobotRoute robotRoute, Ville currentVille, Ville destination) throws NoAvailableTrip {
        Trajet trajetOpti = findNextStep(robotRoute,currentVille, destination, trajetsDisponibles);
        if(trajetOpti != null) {;
            return trajetOpti;
        }
        else throw new NoAvailableTrip("Il n'y a pas de trajet disponible entre ces deux villes");
    }


}
