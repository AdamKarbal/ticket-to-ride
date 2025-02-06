package fr.cotedazur.univ.polytech.teamO.gestion;

import fr.cotedazur.univ.polytech.teamO.bot.Robot;
import fr.cotedazur.univ.polytech.teamO.bot.RobotNewStrategy;
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
    RobotNewStrategy robotNewStrategy;
    int taille;
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
        robotNewStrategy= new RobotNewStrategy(Couleur.Bleu);
        berlinRostock=new Trajet(Ville.Berlin,Ville.Rostock,new Route(4,Couleur.Default));
        rostockKiel=new Trajet(Ville.Rostock,Ville.Kiel,new Route(6,Couleur.Default));
        berlinSchwerin=new Trajet(Ville.Berlin,Ville.Schwerin,new Route(5, Couleur.Default));
        kielSchwerin= new Trajet(Ville.Kiel,Ville.Schwerin, new Route(3,Couleur.Default));
        trajetList=new ArrayList<>();
        trajetList.add(berlinRostock);
        trajetList.add(rostockKiel);
        berlinKielDestination = new CarteDestination(Couleur.Marron,Ville.Kiel,Ville.Berlin,22);
        taille=pioche.size();
    }

    @Test
    void areCitiesConnected(){
        assertTrue(gestionTrajets.isConnected(Ville.Berlin,Ville.Rostock,trajetList));
        assertFalse(gestionTrajets.isConnected(Ville.Berlin,Ville.Hamburg,trajetList));
    }

    @Test
    void correctPath(){
        robotRoute.chooseDestination(new CarteDestination(Couleur.Marron,Ville.Berlin,Ville.Kiel,13));
        assertEquals(gestionTrajets.findOptimalTrajet(robotRoute, robotRoute.getVilleActuelle(), robotRoute.getCurrentDestination().getVille2()),new Trajet(Ville.Berlin,Ville.Schwerin,new Route(2,Couleur.Default)));
        gestionTrajets.updateAfterTrainPlacement(robotRoute,new Trajet(Ville.Berlin,Ville.Schwerin,new Route(2,Couleur.Default)));
        assertEquals(gestionTrajets.findOptimalTrajet(robotRoute, robotRoute.getVilleActuelle(), robotRoute.getCurrentDestination().getVille2()),new Trajet(Ville.Kiel,Ville.Schwerin,new Route(2,Couleur.Default)));

    }
    @Test
    void testFindOptimalTrajet(){
        robotNewStrategy.setVilleActuelle(Ville.Berlin);
        robotNewStrategy.setCurrentDestination(berlinKielDestination);
        assertTrue(gestionTrajets.findOptimalTrajets(robotNewStrategy,berlinKielDestination).contains(new Trajet(Ville.Berlin,Ville.Schwerin,new Route(5,Couleur.Blanc))));
        assertTrue(gestionTrajets.findOptimalTrajets(robotNewStrategy,berlinKielDestination).contains(new Trajet(Ville.Kiel,Ville.Schwerin,new Route(3,Couleur.Jaune))));
        assertEquals(2,gestionTrajets.findOptimalTrajets(robotNewStrategy,berlinKielDestination).size());
    }
    @Test
    void testGetNbChemins(){
        robotNewStrategy.setVilleActuelle(Ville.Berlin);
        robotNewStrategy.setCurrentDestination(berlinKielDestination);
        assertEquals(2,gestionTrajets.getNbChemin(robotNewStrategy,berlinKielDestination));

    }

}