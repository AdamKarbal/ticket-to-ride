package fr.cotedazur.univ.polytech.teamO.bot;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.gestion.GestionTrajets;
import fr.cotedazur.univ.polytech.teamO.gestion.MoteurDeJeu;
import fr.cotedazur.univ.polytech.teamO.pioche.CarteDestination;
import fr.cotedazur.univ.polytech.teamO.plateau.Trajet;

public class RobotRoute extends Robot{
    // Ce robot va en priorité au plus vite terminer ses cartes de destination dans l'ordre où elles sont présentes dans sa main
    public RobotRoute(Couleur couleur) {
        super(couleur);
    }
    public void poseTrainOpti(GestionTrajets gestionTrajets, MoteurDeJeu moteurDeJeu) {
        CarteDestination current= this.getDestinations().getMainDeCartes().get(0);
        Trajet trajetOpti=gestionTrajets.trajetOpti(this,current);
        super.poseTrain(trajetOpti,moteurDeJeu);
    }
}
