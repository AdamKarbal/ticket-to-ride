package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

import java.util.*;
import java.util.stream.Collectors;

public class RobotNewStrategy extends RobotRoute {


    public RobotNewStrategy(Couleur couleur) {
        super(couleur);
    }

    /**
     * Permet de piocher une carte Destination après avoir complété les premières
     * @param moteurDeJeu
     */
    public void chooseShortDestination(MoteurDeJeu moteurDeJeu) {//test
        Random rand = new Random();
        int choice= rand.nextInt(2)+1;
        if(moteurDeJeu.getPiocheDestinationLongue().getCartesDestinationLongues().isEmpty()) {
            CarteDestination carteDestination=moteurDeJeu.giveDestinationsCard(this,1);
            chooseDestination(carteDestination);
        }
        else if(moteurDeJeu.getPiocheDesinationCourte().getCartesDestinationCourtes().isEmpty()) {
            CarteDestination carteDestination=moteurDeJeu.giveDestinationsCard(this,2);
            chooseDestination(carteDestination);
        }
        else {
            CarteDestination carteDestination=moteurDeJeu.giveDestinationsCard(this,choice);
            chooseDestination(carteDestination);
        }
    }
    /**
     * Pioche les cartes Destinations en début de parties et garde
     * les cartes Destinations qui correspondent à son algorithme de choix
     * @param moteurDeJeu
     */
    @Override
    public void chooseDestinations(MoteurDeJeu moteurDeJeu) {//Pioche CarteDestination
        Random rand = new Random();
        for(int i=0; i<4;i++){
            int n = rand.nextInt(2)+1;
            CarteDestination carteDestination=moteurDeJeu.giveDestinationsCard(this,n);
            chooseDestination(carteDestination);
        }
        refuseDestinations(moteurDeJeu);

    }
    /**
     * Utilisé en début de partie pour initialiser la main de cartes destinations du robot
     * et trie du plus court chemin au plus grand
     * @param moteurDeJeu
     * @param gestionTrajets
     */
    public void init(MoteurDeJeu moteurDeJeu,GestionTrajets gestionTrajets) {
        chooseDestinations(moteurDeJeu);
        findingShortestPathForDestinations(moteurDeJeu,gestionTrajets);
    }

    /**
     * Refuse les cartes Destinations qui ne conviennent pas à la stratégie du robot
     * @param moteurDeJeu
     */
    @Override
    public void refuseDestinations(MoteurDeJeu moteurDeJeu){//Parmi sa main le joueur choisi les cartes qu'il ne veut pas, test
        if(hasVillesCommunes()){chooseDestinationsWithCommonCity(moteurDeJeu);}
        else chooseDestinationsWIthBetterPoints(moteurDeJeu);
    }

    /**
     * Pioche les cartes Destinations en début de parties et garde
     * les cartes Destinations qui correspondent à son algorithme de choix
     * @param moteurDeJeu
     */
    public void chooseAnotherDestination(MoteurDeJeu moteurDeJeu){//test
        boolean cartesDone=true;
        for(CarteDestination carteDestination: getDestinations().getMainDeCartes()){
            if (!carteDestination.isCompleted()) {
                cartesDone = false;
                break;
            }
        }
        if(cartesDone){
            chooseShortDestination(moteurDeJeu);
        }
    }

    /// Compte le nombre d'occurences de villes des cartes Destinations présentes dans la main du robot
    /// @return Map</Ville,Long>
    private Map<Ville,Long> nbOccurencesVilles(){
        List<CarteDestination> destinations = getDestinations().getMainDeCartes();
        List<Ville> villesCommunes=new ArrayList<>();
        for(CarteDestination carteDestination:destinations){
            villesCommunes.add(carteDestination.getVille1());
            villesCommunes.add(carteDestination.getVille2());
        }
        return villesCommunes.stream()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));
    }

    /**
     * True si une ville apparait plus d'une fois dans la main du joueur
     * @return boolean
     */
    public boolean hasVillesCommunes(){// True si une ville apparait plus d'une fois dans la main du joueur
        return nbOccurencesVilles().values().stream().anyMatch(count->count>1);
    }

    /**
     * Choisis les cartesDestinations ayant des villes qui sont récurrentes et
     * si la main de cartes destinations contient plus de 2 cartes, on garde celle
     * avec le plus de points
     * @param moteurDeJeu
     */
    public void chooseDestinationsWithCommonCity(MoteurDeJeu moteurDeJeu){
        Map<Ville, Long> occurrences = nbOccurencesVilles();
        occurrences.entrySet().removeIf(entry -> entry.getValue().equals(1L));//Enlève toutes les villes qui n'apparaissent qu'une fois

        for (int i = getDestinations().getMainDeCartes().size() - 1; i >= 0; i--) {
            CarteDestination carteDestination = getDestinations().getMainDeCartes().get(i);
            if (!occurrences.containsKey(carteDestination.getVille1()) && !occurrences.containsKey(carteDestination.getVille2())) {
                moteurDeJeu.removeDestinationsCard(this, i);
            }
        }
        if (getDestinations().getMainDeCartes().size() > 2) {
            chooseDestinationsWIthBetterPoints(moteurDeJeu);
        }
    }

    /**
     * Choisi uniquement les cartes Destinations dans la main avec le plus de points à remporter
     * jusqu'à qu'il n'en reste que 2
     * @param moteurDeJeu
     */
    public void chooseDestinationsWIthBetterPoints(MoteurDeJeu moteurDeJeu){//Choisis les cartes en fct de leur valeur //test
        List<CarteDestination> destinations = getDestinations().getMainDeCartes();
        destinations.sort(Comparator.comparingInt(CarteDestination::getValue));
        while(destinations.size()!=2){
            moteurDeJeu.removeDestinationsCard(this,0);
        }
    }

    /**
     * Trouve les cartes dans la main qui ont besoin du moins de routes à poser
     * pour être complétées et trie la main en conséquence
     * @param moteurDeJeu
     */
    public void findingShortestPathForDestinations(MoteurDeJeu moteurDeJeu, GestionTrajets gestionTrajets){//
        List<CarteDestination> destinations=getDestinations().getMainDeCartes();
        Map<CarteDestination,Integer> destinationsCount=new HashMap<>();

        for(CarteDestination carteDestination: destinations){
            destinationsCount.put(carteDestination,gestionTrajets.getNbChemin(this,carteDestination));
        }
        moteurDeJeu.sortDestinationsCardByPathNb(this,destinationsCount);
    }

    /**
     * Choisi une action à faire dans le jeu
     * @param gestionTrajets
     * @param moteurDeJeu
     */
    @Override
    public void choixAction(GestionTrajets gestionTrajets,MoteurDeJeu moteurDeJeu){//test
        findingShortestPathForDestinations(moteurDeJeu,gestionTrajets);
        chooseAnotherDestination(moteurDeJeu);
        super.choixAction(gestionTrajets,moteurDeJeu);
    }

    @Override
    public String toString(){
        return "RobotNewStrategy "+getCouleur();
    }


}
