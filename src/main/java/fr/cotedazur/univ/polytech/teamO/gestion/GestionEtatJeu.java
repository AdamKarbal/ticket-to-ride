package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.Map;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

import java.util.*;

public class GestionEtatJeu {
    private Map map;
    public GestionEtatJeu(Map map){
        this.map=map;
    }
    public void updateScoreCarteDestination(Robot robot) {
        for (CarteDestination carteDestination : robot.getDestinations().getMainDeCartes()) {
            if(!carteDestination.isCompleted()){
                robot.getScore().removeNbPoints(carteDestination.getValue());
            }
            else robot.getScore().addNbPoints(carteDestination.getValue());
        }
    }
    public boolean isVoisin(Map map, Ville v1,Ville v2){
        return map.getMap().get(v1).containsKey(v2);
    }

    public void isDestinationsCardCompleted(List<Robot> robots) {
        for (Robot robot : robots) {
            for (CarteDestination card : robot.getDestinations().getMainDeCartes()) {
                for (Trajet trajet : robot.getTrajet()) {
                    if((trajet.getFrom().equals(card.getVille1())||trajet.getFrom().equals(card.getVille2()))&&(trajet.getTo().equals(card.getVille1())||trajet.getTo().equals(card.getVille2()))){
                        if (trajet.isDone()) card.setCompleted(true);
                    }

                }
            }
        }
    }
    public boolean isConnected(Ville From,Ville To,List<Trajet> trajets){
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

            for(Ville voisin : subgraph.keySet()){
                if(voisin.equals(to)){ return true;}
                if(!visited.contains(voisin)){ queue.add(voisin);}
            }
        }
        return false;
    }



}
