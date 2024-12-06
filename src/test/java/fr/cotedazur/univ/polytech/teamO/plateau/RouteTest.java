package fr.cotedazur.univ.polytech.teamO.plateau;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RouteTest {

    Route<Case> route = new Route(3, Couleur.Default);
    Route<Case> route2 = new Route(6, Couleur.Default);
    Route<Case> route3 = new Route(3, Couleur.Default);

    @BeforeEach
            void setUp() {
       route.addAllCases(Arrays.asList(new Case(Couleur.Default),new Case(Couleur.Default)));
        route2.addAllCases(Arrays.asList(new Case(Couleur.Blanc),new Case(Couleur.Default),new Case(Couleur.Default)));
    }
    @Test
    void testCheckLengthRouteThrowsException() {
        // Création d'une instance de la classe

        // Ajouter une liste de cases incohérente avec le nombre attendu
        List<Case> cases = List.of(new Case(Couleur.Default), new Case(Couleur.Default));
        List<Case> cases1= List.of(new Case(Couleur.Default), new Case(Couleur.Default), new Case(Couleur.Default), new Case(Couleur.Default));

        // Simuler un nbCases incorrect via une surcharge logique
        route3.addAllCases(cases);
        route3.addCase(new Case(Couleur.Default));
        assertDoesNotThrow(route3::finalCheck);
        assertThrows(IllegalArgumentException.class, () -> route.finalCheck());

        // Vérifier qu'une exception est levée si nbCases != taille réelle
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                route::checkLengthRoute
        );

        assertEquals(
                "La taille de la liste de cases doit être la même que celle indiqué par nbCases.",
                exception.getMessage()
        );

        // Vérifier que la liste est vide et imprimer son contenu
        assertTrue(route.getCases().isEmpty(), "La liste de cases a été vidé !");
        System.out.println("Contenu de la liste : " + route3.getCases());
        System.out.println("Contenu de la liste : " + route.getCases());
    }
    @Test
    void testConstructorRoute(){
        System.out.println(route.getCases());
        assertEquals(2, route.getCases().size());
        assertEquals(Couleur.Default, route.getCouleur());
        assertEquals(2, route2.getCases().size());
        assertEquals(Couleur.Default, route2.getCouleur());
        assertEquals(3,route.getNbCases());
        assertEquals(6,route2.getNbCases());


    }


}