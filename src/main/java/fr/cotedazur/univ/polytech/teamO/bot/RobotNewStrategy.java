package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.NoAvailableTrip;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

import java.util.*;
import java.util.stream.Collectors;

public class RobotNewStrategy extends RobotRoute {


    public RobotNewStrategy(Couleur couleur) {
        super(couleur);
    }
    @Override
    public void chooseDestination(CarteDestination carteDestination) {
        super.chooseDestination(carteDestination);
    }

    public void chooseShortDestination(MoteurDeJeu moteurDeJeu) {
        for(int i =0; i<4; i++){
            CarteDestination carteDestination=moteurDeJeu.giveDestinationsCard(this,1);
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
    @Override

    public void init(MoteurDeJeu moteurDeJeu) {
        chooseDestinations(moteurDeJeu);
    }

    @Override
    public void refuseDestinations(MoteurDeJeu moteurDeJeu){//Parmi sa main le joueur choisi les cartes qu'il ne veut pas
        if(isVillesCommunes()){chooseDestinationsWithCommonCity(moteurDeJeu);}
        else chooseDestinationsWIthBetterPoints(moteurDeJeu);
    }

    public void chooseAnotherDestination(MoteurDeJeu moteurDeJeu,GestionTrajets gestionTrajets){
        boolean cartesDone=true;
        for(CarteDestination carteDestination: getDestinations().getMainDeCartes()){
            if (!carteDestination.isCompleted()) {
                cartesDone = false;
                break;
            }
        }
        if(cartesDone){
            chooseShortDestination(moteurDeJeu);
            findingShortestPathForDestinations(moteurDeJeu,gestionTrajets);
            takeOnlyShortPathDestinations(moteurDeJeu);
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

    public boolean isVillesCommunes(){// True si une ville apparait plus d'une fois dans la main du joueur
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
        if(isVillesCommunes()){
            moteurDeJeu.removeDestinationsCard(this,0);
            moteurDeJeu.removeDestinationsCard(this,0);
        }
        else for(int i=0; i<3;i++)moteurDeJeu.removeDestinationsCard(this,0);
    }

    @Override
    public void choixAction(GestionTrajets gestionTrajets,MoteurDeJeu moteurDeJeu){
        try {
            chooseAnotherDestination(moteurDeJeu,gestionTrajets);
            Trajet trajetOpti = gestionTrajets.findOptimalTrajet(this, getCurrentDestination().getVille1(), getCurrentDestination().getVille2());
            if(trajetOpti==null){
                updateCarteDestination(moteurDeJeu);
            }
            poseTrainOpti(gestionTrajets, moteurDeJeu);
            Random random=new Random();
            int choice=random.nextInt(2)+1;
            if(choice==1){
                takeMeeplesAleatory(trajetOpti.getFrom(),moteurDeJeu);
            }
            if(choice==2){
                takeMeeplesAleatory(trajetOpti.getTo(),moteurDeJeu);
            }
            gestionTrajets.updateAfterTrainPlacement(this,trajetOpti);
        } catch (NotEnoughWagonCard e) {
            // Handle insufficient cards by attempting to draw
            handleNotEnoughWagonCard(gestionTrajets, moteurDeJeu);
        } catch (NoAvailableTrip noAvailableTrip) { // Cas où tous les trajets sont déjà
            updateCarteDestination(moteurDeJeu);
        }
    }

    @Override

    public void updateCarteDestination(MoteurDeJeu moteurDeJeu) {
        int currentIndex = getDestinations().getMainDeCartes().indexOf(getCurrentDestination());
        if (currentIndex + 1 < getDestinations().getMainDeCartes().size()) {
            setCurrentDestination(getDestinations().getMainDeCartes().get(currentIndex + 1));
            setVilleActuelle(getCurrentDestination().getVille1());
        } else {
            if(!moteurDeJeu.getPiocheDesinationCourte().getCartesDestinationCourtes().empty())moteurDeJeu.giveDestinationsCard(this, 1);
            else if (!moteurDeJeu.getPiocheDestinationLongue().getCartesDestinationLongues().isEmpty())moteurDeJeu.giveDestinationsCard(this, 2);
        }
    }

    @Override
    public String toString(){
        return "RobotNewStrategy "+getCouleur();
    }


}
