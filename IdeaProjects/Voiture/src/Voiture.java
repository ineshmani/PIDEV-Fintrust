public class Voiture {
    String mar;
    int nbrPortes;
    String couleur;
    int MAX_NOMBRES_PORTES;
    String URL = "location.com";

    public Voiture() {
    }

    public Voiture(String mat, int nbrPortes, String couleur) {
        this.mar = mat;
        this.nbrPortes = nbrPortes;
        this.couleur = couleur;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Voiture{" +
                "mar='" + mar + '\'' +
                ", nbrPortes=" + nbrPortes +
                ", couleur='" + couleur + '\'' +
                ", MAX_NOMBRES_PORTES=" + MAX_NOMBRES_PORTES +
                ", URL='" + URL + '\'' +
                '}';
    }

    public void voter(int x, int b){
        if(x>0){
            nbrVotes = nbrVotes+1;}
        else{
            System.out.println("entrer un nombre positif");
        }
    }
}
