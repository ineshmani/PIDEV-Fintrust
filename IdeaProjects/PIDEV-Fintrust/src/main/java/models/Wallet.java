package models;

import java.time.LocalDateTime;

public class Wallet {

    private int id;
    private String nomProprietaire;
    private double solde;
    private String devise;
    private String statut;
    private LocalDateTime dateCreation;

    // Constructeur (id généré par la base)
    public Wallet(String nomProprietaire, double solde, String devise,
                  String statut, LocalDateTime dateCreation) {
        this.nomProprietaire = nomProprietaire;
        this.solde = solde;
        this.devise = devise;
        this.statut = statut;
        this.dateCreation = dateCreation;
    }

    // Getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", nomProprietaire='" + nomProprietaire + '\'' +
                ", solde=" + solde +
                ", devise='" + devise + '\'' +
                ", statut='" + statut + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
