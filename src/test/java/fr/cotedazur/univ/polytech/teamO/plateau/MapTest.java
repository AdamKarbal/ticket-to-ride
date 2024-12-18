package fr.cotedazur.univ.polytech.teamO.plateau;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import fr.cotedazur.univ.polytech.teamO.wagon.Wagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MapTest {

    Map map;


    //Initialisation

    @BeforeEach
    public void testInit(){
        map = new Map();
        System.out.println(map);
    }
}
