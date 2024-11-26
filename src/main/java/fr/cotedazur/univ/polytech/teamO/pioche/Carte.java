package fr.cotedazur.univ.polytech.teamO.pioche;
import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public abstract class Carte {

    // Attributs
    private Couleur couleur;
    private String nom;

    // Constructeur
    public Carte(Couleur couleur) {
        this.couleur = couleur;
        this.nom = "";
    }

    // Méthodes abstraites

    public abstract String getNom();

    public abstract boolean isValid();

    public Couleur getCouleur() {
        return couleur;
    }

}

