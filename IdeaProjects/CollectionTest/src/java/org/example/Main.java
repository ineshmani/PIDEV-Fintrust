package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Voiture> voitures = new ArrayList<>();
        voitures.add(new Voiture("BMW","black",2003));
        voitures.add(new Voiture("Fiat","red",2000));
        voitures.add(new Voiture("206","blue",1999));

        System.out.println("Liste originale : " + voitures);
        System.out.println("Contient voiture1 ? " + voitures.contains(voitures.get(0)));

        voitures.sort((v1, v2) -> Integer.compare(v1.annee, v2.annee));
        System.out.println("\nTri par année : " + voitures);


        voitures.sort((v1, v2) -> {
            boolean v1IsNumber = v1.Marque.matches("\\d+");
            boolean v2IsNumber = v2.Marque.matches("\\d+");
            if (v1IsNumber && v2IsNumber) {
                return Integer.compare(Integer.parseInt(v1.Marque), Integer.parseInt(v2.Marque));
            } else if (v1IsNumber) {
                return -1;
            } else if (v2IsNumber) {
                return 1;
            } else {
                return v1.Marque.compareTo(v2.Marque);
            }
        });
        System.out.println("\nTri par marque : " + voitures);


        voitures.sort((v1, v2) -> v1.Coleur.compareTo(v2.Coleur));
        System.out.println("\nTri par couleur : " + voitures);
    }
}
