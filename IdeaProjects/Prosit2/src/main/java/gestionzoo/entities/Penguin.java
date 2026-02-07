package gestionzoo.entities;

public class Penguin extends Aquatic {
    protected float swimmingDepth;

    public Penguin() {
        super();
    }

    public Penguin(String nom, String espece, int age, String habitat, float swimmingDepth) {
        super(nom, espece, age, habitat);
        this.swimmingDepth = swimmingDepth;
    }

    @Override
    public String getType() {
        return "Penguin";
    }

    @Override
    public String toString() {
        return super.toString() + ", swimmingDepth=" + swimmingDepth;
    }

    @Override
    public void swim() {
        System.out.println(getNom() + " nage jusqu'à " + swimmingDepth + " mètres de profondeur.");
    }
}
