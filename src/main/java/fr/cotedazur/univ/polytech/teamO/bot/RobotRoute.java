package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.gestion.NotEnoughWagonCard;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;
import fr.cotedazur.univ.polytech.teamO.plateau.Ville;

public class RobotRoute extends Robot{
    // Ce robot va en priorité au plus vite terminer ses cartes de destination dans l'ordre où elles sont présentes dans sa main
    private Ville villeActuelle;
    public RobotRoute(Couleur couleur) {
        super(couleur);
    }
    public void setVilleActuelle(Ville villeActuelle) {
        this.villeActuelle = villeActuelle;
    }
    @Override
    public void chooseDestination(CarteDestination destination) {
        super.chooseDestination(destination);
        this.villeActuelle=getDestinations().getMainDeCartes().get(0).getVille1();
    }

    public void poseTrainOpti(GestionTrajets gestionTrajets, MoteurDeJeu moteurDeJeu) throws NotEnoughWagonCard {
        //Trajet trajetOpti=gestionTrajets.trajetOpti(this,this.getDestinations().getMainDeCartes().get(0));
        Trajet trajetOpti = gestionTrajets.findOptimalTrajet(this,villeActuelle,this.getDestinations().getMainDeCartes().get(0).getVille2());
        super.poseTrain(trajetOpti,moteurDeJeu);
    }
}
