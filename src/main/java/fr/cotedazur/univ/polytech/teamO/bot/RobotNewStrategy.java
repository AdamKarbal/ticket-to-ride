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

    public void chooseShortDestination(MoteurDeJeu moteurDeJeu) {
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

    public void init(MoteurDeJeu moteurDeJeu,GestionTrajets gestionTrajets) {
        chooseDestinations(moteurDeJeu);
        findingShortestPathForDestinations(moteurDeJeu,gestionTrajets);
    }

    @Override
    public void refuseDestinations(MoteurDeJeu moteurDeJeu){//Parmi sa main le joueur choisi les cartes qu'il ne veut pas
        if(hasVillesCommunes()){chooseDestinationsWithCommonCity(moteurDeJeu);}
        else chooseDestinationsWIthBetterPoints(moteurDeJeu);
    }

    public void chooseAnotherDestination(MoteurDeJeu moteurDeJeu){
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

    private Map<Ville,Long> nbOccurencesVilles(){//Compte le nb d'occurences de chaque ville de la main du joueur
        List<CarteDestination> destinations = getDestinations().getMainDeCartes();
        List<Ville> villesCommunes=new ArrayList<>();
        for(CarteDestination carteDestination:destinations){
            villesCommunes.add(carteDestination.getVille1());
            villesCommunes.add(carteDestination.getVille2());
        }
        return villesCommunes.stream()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));
    }

    public boolean hasVillesCommunes(){// True si une ville apparait plus d'une fois dans la main du joueur
        return nbOccurencesVilles().values().stream().anyMatch(count->count>1);
    }

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

    public void chooseDestinationsWIthBetterPoints(MoteurDeJeu moteurDeJeu){//Choisis les cartes en fct de leur valeur
        List<CarteDestination> destinations = getDestinations().getMainDeCartes();
        destinations.sort(Comparator.comparingInt(CarteDestination::getValue));
        while(destinations.size()!=2){
            moteurDeJeu.removeDestinationsCard(this,0);
        }
    }

    public void findingShortestPathForDestinations(MoteurDeJeu moteurDeJeu, GestionTrajets gestionTrajets){
        List<CarteDestination> destinations=getDestinations().getMainDeCartes();
        Map<CarteDestination,Integer> destinationsCount=new HashMap<>();

        for(CarteDestination carteDestination: destinations){
            destinationsCount.put(carteDestination,gestionTrajets.getNbChemin(this,carteDestination));
        }
        moteurDeJeu.sortDestinationsCardByPathNb(this,destinationsCount);
    }

    public void takeOnlyShortPathDestinations(MoteurDeJeu moteurDeJeu){
        if(hasVillesCommunes()){
            moteurDeJeu.removeDestinationsCard(this,0);
            moteurDeJeu.removeDestinationsCard(this,0);
        }
        else for(int i=0; i<3;i++)moteurDeJeu.removeDestinationsCard(this,0);
    }

    @Override
    public void choixAction(GestionTrajets gestionTrajets,MoteurDeJeu moteurDeJeu){
        findingShortestPathForDestinations(moteurDeJeu,gestionTrajets);
        chooseAnotherDestination(moteurDeJeu);
        super.choixAction(gestionTrajets,moteurDeJeu);
    }

    @Override
    public String toString(){
        return "RobotNewStrategy "+getCouleur();
    }


}
