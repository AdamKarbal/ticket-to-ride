package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.bot.RobotRoute;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.*;

import java.util.*;

public class GestionTrajets {
    private GameMap gameMap;
    private HashMap<Ville,HashMap<Ville,Integer>> trajetsDisponibles;
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
    private HashMap<Ville,HashMap<Ville,Integer>> buildSubgraph(List<Trajet> trajets){
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

    private boolean isConnectedByGraph(Ville from,Ville to,HashMap<Ville,HashMap<Ville,Integer>> subgraph){
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

    // Algorithme de Dijkstra pour trouver le prochain segment optimal
    private Trajet findNextStep(RobotRoute robotRoute,Ville start, Ville end, HashMap<Ville, HashMap<Ville, Integer>> graph) {
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
        Couleur couleur=findMatchingCouleur(robotRoute,start,nextStep);
        return new Trajet(start, nextStep, new Route(graph.get(start).get(nextStep), couleur));
    }

    private Couleur findMatchingCouleur(RobotRoute robotRoute, Ville from,Ville to){
        List<Couleur> couleurs=new ArrayList<>();
        Trajet trajetVoulu=new Trajet(from,to,new Route(1,Couleur.Default));
        for(Trajet trajet : gameMap.getTrajets()){
            if(trajetVoulu.equals(trajet)){
                couleurs.add(trajet.getRoute().getCouleur());
            }
        }
        if(couleurs.size()>1){
            return findMaxCouleurBetween(robotRoute,couleurs);
        }
        return couleurs.get(0);
    }

    private Couleur findMaxCouleurBetween(RobotRoute robotRoute,List<Couleur> couleurs){
        Couleur couleurMax=Couleur.Default;
        int max = 0;
        for(Couleur couleur : couleurs ){
            if(max<robotRoute.getWagonByCouleur().get(couleur)){
                max=robotRoute.getWagonByCouleur().get(couleur);
                couleurMax = couleur;
            }
        }
        return couleurMax;
    }

    // Méthodes principales
    public boolean isConnected(Ville From, Ville To, List<Trajet> trajets){
        HashMap<Ville, HashMap<Ville, Integer>> subgraph = buildSubgraph(trajets);
        return isConnectedByGraph(From,To,subgraph);
    }



    // Trouver le trajet optimal à partir de la ville actuelle
    public Trajet findOptimalTrajet(RobotRoute robotRoute, Ville currentVille, Ville destination) throws NoAvailableTrip {
        Trajet trajetOpti = findNextStep(robotRoute,currentVille, destination, trajetsDisponibles);
        System.out.println(trajetOpti);
        if(trajetOpti != null) {
            robotRoute.setVilleActuelle(trajetOpti.getTo());
            updateTrajetsDisponibles(trajetOpti);
            return trajetOpti;
        }
        else throw new NoAvailableTrip("Il n'y a pas de trajet disponible entre ces deux villes");
    }


}
