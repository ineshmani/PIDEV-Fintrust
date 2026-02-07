package org.example;

import java.util.Objects;

public class Voiture implements Comparable<Voiture> {

    public String Marque;
    public String Coleur;
    public int annee;

    public Voiture(String Marque, String Coleur, int annee) {
        this.Marque = Marque;
        this.Coleur = Coleur;
        this.annee = annee;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Voiture voiture = (Voiture) o;
        return Objects.equals(Marque, voiture.Marque) && Objects.equals(Coleur, voiture.Coleur);
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "Marque='" + Marque + '\'' +
                ", Coleur='" + Coleur + '\'' +
                ", annee=" + annee +
                '}';
    }

    @Override
    public int compareTo(Voiture o) {
        return this.Marque.compareTo(o.Marque);
    }
}
