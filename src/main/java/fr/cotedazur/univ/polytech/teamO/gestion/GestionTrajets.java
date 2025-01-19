package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

import java.util.*;

public class GestionTrajets {
    private GameMap gameMap;
    public GestionTrajets(GameMap map){
        this.gameMap = map;
    }
    public boolean isConnected(Ville From, Ville To, List<Trajet> trajets){
        HashMap<Ville, HashMap<Ville, Integer>> subgraph = buildSubgraph(trajets);
        return isConnectedByGraph(From,To,subgraph);
    }

    public HashMap<Ville,HashMap<Ville,Integer>> buildSubgraph(List<Trajet> trajets){
        HashMap<Ville,HashMap<Ville,Integer>> subgraph = new HashMap<>();
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

    public boolean isConnectedByGraph(Ville from,Ville to,HashMap<Ville,HashMap<Ville,Integer>> subgraph){
        Set<Ville> visited = new HashSet<>();
        Queue<Ville> queue = new LinkedList<>();
        queue.add(from);
        while(!queue.isEmpty()){
            Ville current = queue.poll();
            visited.add(current);

            HashMap<Ville, Integer> neighbors = subgraph.get(current);
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

    public Trajet trajetOpti(Robot robot, CarteDestination carteDestination) {
        Ville ville1 = carteDestination.getVille1();
        Ville ville2 = carteDestination.getVille2();

        // Subgraph based on the robot's current trips
        HashMap<Ville, HashMap<Ville, Integer>> subgraph = buildSubgraph(robot.getTrajet());

        // Check if the cities are already connected
        if (isConnectedByGraph(ville1, ville2, subgraph)) {
            // Return the shortest trip between them from the robot's current trips
            return findShortestTrip(ville1, ville2, subgraph);
        }

        // If not connected, find the shortest path in the global map
        return findShortestTrip(ville1, ville2, gameMap.getMap());
    }

    // Helper method to find the shortest trip
    private Trajet findShortestTrip(Ville start, Ville end, HashMap<Ville, HashMap<Ville, Integer>> graph) {
        // Dijkstra's algorithm for shortest path
        Map<Ville, Integer> distances = new HashMap<>();
        Map<Ville, Ville> previous = new HashMap<>();
        PriorityQueue<Ville> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Ville ville : graph.keySet()) {
            distances.put(ville, Integer.MAX_VALUE);
            previous.put(ville, null);
        }
        distances.put(start, 0);
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Ville current = priorityQueue.poll();

            if (current.equals(end)) {
                break; // Reached the destination
            }

            HashMap<Ville, Integer> neighbors = graph.get(current);
            if (neighbors != null) {
                for (Map.Entry<Ville, Integer> neighborEntry : neighbors.entrySet()) {
                    Ville neighbor = neighborEntry.getKey();
                    int weight = neighborEntry.getValue();
                    int newDist = distances.get(current) + weight;

                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        previous.put(neighbor, current);
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }

        // Backtrack to build the Trajet
        LinkedList<Ville> path = new LinkedList<>();
        Ville step = end;
        while (step != null && !step.equals(start)) {
            path.addFirst(step);
            step = previous.get(step);
        }
        if (step == null) {
            return null; // No path found
        }
        path.addFirst(start);

        // Create a Trajet from the path
        Ville from = null;
        Trajet trajet = null;
        for (Ville to : path) {
            if (from != null) {
                // Create a new Route and add to Trajet
                Route route = new Route(graph.get(from).get(to), Couleur.Bleu); // Color is optional here
                trajet = new Trajet(from, to, route);
            }
            from = to;
        }
        return trajet;
    }
}
