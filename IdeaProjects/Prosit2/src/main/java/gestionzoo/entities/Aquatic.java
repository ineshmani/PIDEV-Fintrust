package gestionzoo.entities;

public abstract class Aquatic extends Animal {
    protected String habitat;

    public Aquatic() {
        super();
    }

    public Aquatic(String nom, String espece, int age, String habitat) {
        super(nom, espece, age);
        this.habitat = habitat;
    }

    @Override
    public String getType() {
        return "Aquatic";
    }

    @Override
    public String toString() {
        return super.toString() + ", habitat=" + habitat;
    }


    public abstract void swim() ;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // même référence
        if (obj == null || getClass() != obj.getClass()) return false;

        Aquatic other = (Aquatic) obj;

        return this.getNom().equals(other.getNom()) &&
                this.getAge() == other.getAge() &&
                this.habitat.equals(other.habitat);
    }

}
