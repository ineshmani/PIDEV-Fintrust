package gestionzoo.entities;

public abstract class Animal {
    protected String nom;
    protected String espece;
    protected int age;

    public Animal() {
    }

    public Animal(String nom, String espece, int age) {
        this.nom = nom;
        this.espece = espece;
        setAge(age);
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEspece() { return espece; }
    public void setEspece(String espece) { this.espece = espece; }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age < 0) {
            System.out.println("Erreur : un animal ne peut pas avoir un âge négatif !");
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public abstract String getType();


    @Override
    public String toString() {
        return getType() + " {nom='" + nom + "', espece='" + espece + "', age=" + age + "}";
    }
}
