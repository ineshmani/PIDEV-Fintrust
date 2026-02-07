package gestionzoo.main;

import gestionzoo.entities.*;

public class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo("Zoo de Tunis", "Tunis", 20);

        // Animaux terrestres
        Terrestrial lion = new Terrestrial("Felidae", "Lion", 5, 4);
        Terrestrial elephant = new Terrestrial("Elephantidae", "Éléphant", 10, 4);
        Terrestrial fox = new Terrestrial("Canidae", "Renard", 3, 4);
        Terrestrial parrot = new Terrestrial("Psittacidae", "Perroquet", 2, 2);

        // Animaux aquatiques
        Dolphin dolphin = new Dolphin("Delphinidae", "Dauphin", 6, "océan", 25.5f);
        Penguin penguin = new Penguin("Spheniscidae", "Pingouin", 4, "bassin", 10f);

        try {
            // Ajout des animaux terrestres
            zoo.addAnimal(lion);
            zoo.addAnimal(elephant);
            zoo.addAnimal(fox);
            zoo.addAnimal(parrot);

            // Ajout d'autres animaux terrestres
            for (int i = 0; i < 12; i++) {
                Terrestrial temp = new Terrestrial("Fam" + i, "Animal" + i, i, 4);
                zoo.addAnimal(temp);
            }

            // Ajout des animaux aquatiques
            zoo.addAquaticAnimal(dolphin);
            zoo.addAquaticAnimal(penguin);

        } catch (Zoo.ZooPleinException | Zoo.AnimalDejaExistantException e) {
            System.out.println("⚠️ Erreur lors de l’ajout : " + e.getMessage());
        }

        // Affichage des informations du zoo
        zoo.displayZoo();
        System.out.println("\nListe des animaux terrestres :");
        zoo.displayAnimals();

        // Test de recherche
        int indexElephant = zoo.searchAnimal(elephant);
        System.out.println("\nIndice de l'éléphant : " + indexElephant);

        Terrestrial lion2 = new Terrestrial("Felidae", "Lion", 5, 4);
        System.out.println("Recherche du nouvel animal identique : " + zoo.searchAnimal(lion2));

        // Appel de swim() pour tous les animaux aquatiques
        System.out.println("\nTous les animaux aquatiques nagent :");
        zoo.makeAllAquaticSwim();

        // Profondeur maximale des pingouins
        System.out.println("\nProfondeur maximale des pingouins : " + zoo.maxPenguinSwimmingDepth() + " mètres");

        // Nombre de dauphins et de pingouins
        System.out.println("\nStatistiques des animaux aquatiques :");
        zoo.displayNumberOfAquaticByType();

        // Test equals() sur deux dauphins
        Dolphin dolphin2 = new Dolphin("Delphinidae", "Dauphin", 6, "océan", 30f);
        System.out.println("\nComparaison entre deux dauphins : " + dolphin.equals(dolphin2));
    }
}
