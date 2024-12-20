package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.plateau.Map;

public class GestionEtatJeu {
    private Map map;
    public GestionEtatJeu(Map map){
        this.map=map;
    }
    public boolean isVoisin(Ville v1,Ville v2){
        return map.getMap().get(v1).contains(v2);
    }
}
