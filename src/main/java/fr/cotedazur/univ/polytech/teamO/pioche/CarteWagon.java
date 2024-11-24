package fr.cotedazur.univ.polytech.teamO.pioche;

public class CarteWagon implements Carte {
    //Instance
    private String couleur;
    private String nomCarte;

    // Constructeurs
    public CarteWagon(String couleur) {
        this.couleur = couleur;
        setNomCarteWagon(couleur);
    }


    // Accesseurs

    public void setNomCarteWagon(String Couleur){
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
    public String getCouleur() {
        return couleur;
    }

    // Méthode

    public boolean isValid(){
        String couleurCarte = couleur;
        if(couleurCarte=="Rouge" || couleurCarte=="Bleu" || couleurCarte=="Vert" || couleurCarte=="Jaune" || couleurCarte=="Noir" || couleurCarte=="Blanc" || couleurCarte=="Orange" || couleurCarte=="Rose") return true;
        return false;
    }


}

