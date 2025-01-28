package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.bot.RobotRoute;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.pioche.*;
import fr.cotedazur.univ.polytech.teamO.plateau.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionTrajetsTest {
    GameMap gameMap;
    Trajet berlinRostock;
    Trajet rostockKiel;
    Trajet berlinSchwerin;
    Trajet kielSchwerin;
    List<Trajet> trajetList;
    RobotRoute robotRoute;
    CarteDestination berlinKielDestination;
    MoteurDeJeu moteurDeJeu;
    GestionTrajets gestionTrajets;
    GestionEtatJeu gestionEtatJeu;
    Pioche pioche;
    PiocheSurTable piocheSurTable;
    @BeforeEach
    void setUp() {
        pioche=new Pioche();
        pioche.creerPiocheComplete();
        piocheSurTable=new PiocheSurTable();
        moteurDeJeu=new MoteurDeJeu(pioche,piocheSurTable,new PiocheDestinationLongue(),new PiocheDestinationCourte());
        gameMap =new GameMap();
        gestionTrajets=new GestionTrajets(gameMap);
        gestionEtatJeu= new GestionEtatJeu(gestionTrajets);
        robotRoute = new RobotRoute(Couleur.Rouge);
        berlinRostock=new Trajet(Ville.Berlin,Ville.Rostock,new Route(4,Couleur.Default));
        rostockKiel=new Trajet(Ville.Rostock,Ville.Kiel,new Route(6,Couleur.Default));
        berlinSchwerin=new Trajet(Ville.Berlin,Ville.Schwerin,new Route(5, Couleur.Default));
        kielSchwerin= new Trajet(Ville.Kiel,Ville.Schwerin, new Route(3,Couleur.Default));
        trajetList=new ArrayList<>();
        trajetList.add(berlinRostock);
        trajetList.add(rostockKiel);
        berlinKielDestination = new CarteDestination(Couleur.Marron,Ville.Kiel,Ville.Berlin,22);
    }

    @Test
    void areCitiesConnected(){
        assertTrue(gestionTrajets.isConnected(Ville.Berlin,Ville.Rostock,trajetList));
        assertFalse(gestionTrajets.isConnected(Ville.Berlin,Ville.Hamburg,trajetList));
    }

    @Test
    void shortestTrip() throws NotEnoughWagonCard {
        for(int i=0;i<moteurDeJeu.getPioche().size();) robotRoute.piocher(moteurDeJeu);
        robotRoute.chooseDestination(berlinKielDestination);
        //assertEquals(gestionTrajets.trajetOpti(robotRoute, robotRoute.getDestinations().getMainDeCartes().get(0)),kielSchwerin);
        robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu);
        //assertEquals(gestionTrajets.trajetOpti(robotRoute, robotRoute.getDestinations().getMainDeCartes().get(0)),berlinSchwerin);
        robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu);
    }
    @Test
    void noCitiesStep() throws NotEnoughWagonCard {
        for(int i=0;i<moteurDeJeu.getPioche().size();i++) {
            robotRoute.piocher(moteurDeJeu);
        }
        robotRoute.chooseDestination(new CarteDestination(Couleur.Marron,Ville.Emden,Ville.Düsseldorf,21));
        robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu);
        robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu);
        robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu);
        assertThrows(NoAvailableTrip.class, ()-> robotRoute.poseTrainOpti(gestionTrajets,moteurDeJeu));
    }

}