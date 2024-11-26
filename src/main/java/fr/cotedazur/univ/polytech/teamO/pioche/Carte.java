package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public abstract class Carte {
    protected Couleur couleur;
    protected String nom;

    // Constructor
    public Carte(Couleur couleur) {
        this.couleur = couleur;
        setNom();
    }

    // Set the name
    void setNom() {
        this.nom = getClass().getSimpleName() + " de Couleur : " + couleur;
    }

    // Abstract methods
    public abstract String getNom();
    public abstract Couleur getCouleur();
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass()!=getClass()) return false;
        Carte carte = (Carte) obj;
        return getCouleur().equals(carte.getCouleur());
    }
    @Override
    public String toString(){
        return getNom();
    }
}
