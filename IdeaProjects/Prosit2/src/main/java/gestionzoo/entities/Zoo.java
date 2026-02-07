package gestionzoo.entities;

public class Zoo {
    private String nom;
    private Animal[] animals;
    private String city;
    private int nbrCages;
    private int n;

    private Aquatic[] aquaticAnimals = new Aquatic[10];
    private int aquaticCount = 0;
    public void displayZoo() {
        System.out.println("gestionzoo.entities.Zoo's name:" + this.getNom() + "City:" + this.city + "nbrCages:" + this.nbrCages);
    }

    public Zoo() {
    }
    public class ZooPleinException extends Exception {
        public ZooPleinException(String message) {
            super(message);
        }
    }
    public class AnimalDejaExistantException extends Exception {
        public AnimalDejaExistantException(String message) {
            super(message);
        }
    }


    public Zoo(String nom, String city, int capacite) {
        setNom(nom);        // validation du nom
        this.city = city;    // ville du zoo
        animals = new Animal[capacite];
        n = 0;
    }

    @Override
    public String toString() {
        return "gestionzoo.entities.Zoo{" +
                "nom='" + nom + '\'' +
                ", ville='" + city + '\'' +
                ", capacite=" + animals.length +
                ", nbAnimaux=" + n +
                '}';
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            System.out.println("Erreur : le nom du zoo ne doit pas être vide !");
            this.nom = "ZooSansNom";
        } else {
            this.nom = nom;
        }
    }


    public boolean isZooFull() {
        return n >= animals.length;
    }


    public void addAnimal(Animal animal) throws ZooPleinException, AnimalDejaExistantException {
        // Vérifie si l’animal existe déjà
        if (searchAnimal(animal) != -1) {
            throw new AnimalDejaExistantException("L’animal " + animal.getNom() + " existe déjà dans le zoo !");
        }

        // Vérifie si le zoo est plein
        if (isZooFull()) {
            throw new ZooPleinException("Le zoo est plein ! Impossible d’ajouter un nouvel animal.");
        }

        // Si tout est bon → ajout
        animals[n] = animal;
        n++;
        System.out.println(animal.getNom() + " a été ajouté au zoo avec succès !");
    }


    public void displayAnimals() {
        for (int i = 0; i < n; i++) {
            System.out.println(animals[i]);
        }
    }

    public int searchAnimal(Animal a) {
        for (int i = 0; i < n; i++) {
            if (animals[i].getNom().equals(a.getNom()) &&
                    animals[i].getEspece().equals(a.getEspece())) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeAnimal(Animal animal) {
        int index = searchAnimal(animal);
        if (index == -1) {
            System.out.println("gestionzoo.entities.Animal n'existe pas");
            return false;
        } else {
            for (int i = index; i < n - 1; i++) {
                animals[i] = animals[i + 1];
            }
            animals[n - 1] = null;
            n--;
            System.out.println("gestionzoo.entities.Animal supprimé avec succès");
            return true;
        }
    }
    public void addAquaticAnimal(Aquatic aquatic) {
        if (aquaticCount >= aquaticAnimals.length) {
            System.out.println("Le tableau des animaux aquatiques est plein !");
            return;
        }
        aquaticAnimals[aquaticCount] = aquatic;
        aquaticCount++;
        System.out.println("Animal aquatique ajouté : " + aquatic.getNom());
    }
    public float maxPenguinSwimmingDepth() {
        float maxDepth = 0;
        for (int i = 0; i < aquaticCount; i++) {
            if (aquaticAnimals[i] instanceof Penguin) {      // Vérifie que c’est un pingouin
                Penguin p = (Penguin) aquaticAnimals[i];     // Downcast pour accéder à swimmingDepth
                if (p.swimmingDepth > maxDepth) {
                    maxDepth = p.swimmingDepth;             // Met à jour la profondeur maximale
                }
            }
        }
        return maxDepth;
    }
    public void makeAllAquaticSwim() {
        for (Animal animal : animals) {
            if (animal instanceof Aquatic) {
                ((Aquatic) animal).swim();
            }
        }
    }

    public void displayNumberOfAquaticByType() {
        int dolphinCount = 0;
        int penguinCount = 0;

        for (int i = 0; i < aquaticCount; i++) {
            if (aquaticAnimals[i] instanceof Dolphin) {
                dolphinCount++;
            } else if (aquaticAnimals[i] instanceof Penguin) {
                penguinCount++;
            }
        }

        System.out.println("Nombre de dauphins : " + dolphinCount);
        System.out.println("Nombre de pingouins : " + penguinCount);
    }



}



