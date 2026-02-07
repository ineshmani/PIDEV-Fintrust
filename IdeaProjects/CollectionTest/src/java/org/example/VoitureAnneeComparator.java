package org.example;

import java.util.Comparator;

public class VoitureAnneeComparator implements Comparator<Voiture> {
    @Override
    public int compare(Voiture v1, Voiture v2) {
        return Integer.compare(v1.annee, v2.annee);
    }
}

