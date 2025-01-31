package fr.cotedazur.univ.polytech.teamO.meeples;

public enum MCouleur {
    Jaune(10),
    Rouge(10),
    Noir(10),
    Blanc(10),
    Bleu(10),
    Vert(10);

    private int Quantité;

    MCouleur(int Quantité){
        this.Quantité = Quantité;
    }

    public void DecremQT(){
        this.Quantité -= 1;
    }

    public int getQuant(){
        return Quantité;
    }

    public void rstQT(){
        Quantité = 10;
    }

    public static void reset(){
         for(MCouleur color : MCouleur.values()){
             color.rstQT();
         }
    }

}

