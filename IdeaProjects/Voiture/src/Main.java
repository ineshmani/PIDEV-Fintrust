public class Main {
    public static void main(String []args) {
        Voiture v = new Voiture();
        v.couleur="black";
        v.mar="bmw";
        v.nbrPortes = 2;
        Voiture v2 = new Voiture("bmw", 3, "red");
       v2.voter(x: 6, b: 3);
        System.out.println(v2.showTotal());
        System.out.println(v2);
    }
}