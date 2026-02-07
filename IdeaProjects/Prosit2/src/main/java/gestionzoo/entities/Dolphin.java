package gestionzoo.entities;

public class Dolphin extends Aquatic {
    protected float swimmingSpeed;

    public Dolphin() {
        super();
    }

    public Dolphin(String nom, String espece, int age, String habitat, float swimmingSpeed) {
        super(nom, espece, age, habitat);
        this.swimmingSpeed = swimmingSpeed;
    }

    @Override
    public String getType() {
        return "Dolphin";
    }

    @Override
    public String toString() {
        return super.toString() + ", swimmingSpeed=" + swimmingSpeed;
    }


    @Override
    public void swim() {
        System.out.println("This dolphin is swimming.");
    }
}
