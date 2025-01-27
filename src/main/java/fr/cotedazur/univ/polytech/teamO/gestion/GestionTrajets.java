package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.bot.RobotRoute;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.GameMap;
import fr.cotedazur.univ.polytech.teamO.plateau.Route;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

import java.util.*;

public class GestionTrajets {
    private GameMap gameMap;
    private HashMap<Ville,HashMap<Ville,Integer>> trajetsDisponibles;
    public GestionTrajets(GameMap map){
        this.gameMap = map;
        this.trajetsDisponibles = new HashMap<>(map.getMap());
    }
    public boolean isConnected(Ville From, Ville To, List<Trajet> trajets){
        HashMap<Ville, HashMap<Ville, Integer>> subgraph = buildSubgraph(trajets);
        return isConnectedByGraph(From,To,subgraph);
    }
    public void updateTrajetsDisponibles(Trajet trajet) {
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

        if(isConnectedByGraph(ville1,ville2,trajetsDisponibles)) return findShortestTrip(ville1, ville2, trajetsDisponibles);

        return null;
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
                Route route = new Route(graph.get(from).get(to),Couleur.Default); // Color is optional here
                trajet = new Trajet(to, from, route);
            }
            from = to;
        }
        updateTrajetsDisponibles(trajet);
        return trajet;
    }
    // Trouver le trajet optimal à partir de la ville actuelle
    public Trajet findOptimalTrajet(RobotRoute robotRoute, Ville currentVille, Ville destination) {
        Trajet trajetOpti = findNextStep(currentVille, destination, trajetsDisponibles);
        robotRoute.setVilleActuelle(trajetOpti.getTo());
        updateTrajetsDisponibles(trajetOpti);
        return trajetOpti;
    }

    // Algorithme de Dijkstra pour trouver le prochain segment optimal
    private Trajet findNextStep(Ville start, Ville end, HashMap<Ville, HashMap<Ville, Integer>> graph) {
        Map<Ville, Integer> distances = new HashMap<>();
        Map<Ville, Ville> previous = new HashMap<>();
        PriorityQueue<Ville> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Ville ville : graph.keySet()) {
            distances.put(ville, Integer.MAX_VALUE);
            previous.put(ville, null);
        }
        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Ville current = queue.poll();
            if (current.equals(end)) break;

            Map<Ville, Integer> neighbors = graph.get(current);
            if (neighbors != null) {
                for (Map.Entry<Ville, Integer> entry : neighbors.entrySet()) {
                    Ville neighbor = entry.getKey();
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

        // Identifier le premier segment du chemin optimal
        Ville nextStep = end;
        while (previous.get(nextStep) != null && !previous.get(nextStep).equals(start)) {
            nextStep = previous.get(nextStep);
        }

        if (previous.get(nextStep) == null) {
            return null; // Aucun chemin trouvé
        }

        return new Trajet(start, nextStep, new Route(graph.get(start).get(nextStep), Couleur.Default));
    }
}
