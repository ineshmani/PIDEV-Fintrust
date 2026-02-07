package gestionzoo.entities;

public class Terrestrial extends Animal {
    protected int nbrLegs;

    public Terrestrial() {
        super();
    }

    public Terrestrial(String nom, String espece, int age, int nbrLegs) {
        super(nom, espece, age);
        this.nbrLegs = nbrLegs;
    }

    @Override
    public String getType() {
        return "Terrestrial";
    }


    @Override
    public String toString() {
        return super.toString() + ", nbrLegs=" + nbrLegs;
    }
}
