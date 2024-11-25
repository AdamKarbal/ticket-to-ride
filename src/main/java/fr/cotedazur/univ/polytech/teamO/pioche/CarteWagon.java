package fr.cotedazur.univ.polytech.teamO.pioche;

import fr.cotedazur.univ.polytech.teamO.couleur.Couleur;

public class CarteWagon implements Carte {
    //Instance
    private Couleur couleur;
    private String nomCarte;

    // Constructeurs
    public CarteWagon(Couleur couleur) {
        this.couleur = couleur;
        setNomCarteWagon(couleur);
    }


    // Accesseurs

    public void setNomCarteWagon(Couleur Couleur){
        this.nomCarte = "Carte Wagon de Couleur : " + Couleur;
    }

    @Override
    public String getNom() {
        if(isValid()){
            return nomCarte;
        }
        return "Carte invalide";
    }

    @Override
    public Couleur getCouleur() {
        return couleur;
    }

    // Méthode

    public boolean isValid() {
        Couleur couleurCarte = couleur;
        if (couleurCarte.name().equals("Rouge") ||
                couleurCarte.name().equals("Bleu") ||
                couleurCarte.name().equals("Vert") ||
                couleurCarte.name().equals("Jaune") ||
                couleurCarte.name().equals("Noir") ||
                couleurCarte.name().equals("Blanc") ||
                couleurCarte.name().equals("Orange") ||
                couleurCarte.name().equals("Rose")) {
            return true;
        }
        return false;
    }



}

