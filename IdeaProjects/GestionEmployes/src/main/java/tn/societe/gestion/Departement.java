package tn.societe.gestion;

public class Departement {
    private int id;
    private String nom;
    private int nombreEmployes;

    public Departement(int id, String nom, int nombreEmployes) {
        this.id = id;
        this.nom = nom;
        this.nombreEmployes = nombreEmployes;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getNombreEmployes() { return nombreEmployes; }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nombreEmployes=" + nombreEmployes +
                '}';
    }
}
