package tn.societe.gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        /* ===============================
           GESTION DES EMPLOYÉS
           =============================== */

        GestionEmploye gestion = new GestionEmploye();

        Employe e1 = new Employe(3, "Ben", "Ahmed", "Informatique", 2);
        Employe e2 = new Employe(1, "Trabelsi", "Sana", "RH", 1);
        Employe e3 = new Employe(2, "Hassine", "Ali", "Finance", 3);
        Employe e4 = new Employe(4, "Saidi", "Amira", "Informatique", 1);

        gestion.ajouterEmploye(e1);
        gestion.ajouterEmploye(e2);
        gestion.ajouterEmploye(e3);
        gestion.ajouterEmploye(e4);

        System.out.println("=== Tous les employés ===");
        gestion.displayEmploye();

        String deptRecherche = "Informatique";
        System.out.println("\n=== Employés du département '" + deptRecherche + "' ===");
        gestion.rechercherParDepartement(deptRecherche)
                .forEach(System.out::println);


        /* ===============================
           GESTION DES DÉPARTEMENTS
           =============================== */

        DepartementHashSet gestionDept = new DepartementHashSet();

        Departement d1 = new Departement(1, "Informatique", 25);
        Departement d2 = new Departement(5, "RH", 12);
        Departement d3 = new Departement(3, "Finance", 18);

        gestionDept.ajouterDepartement(d1);
        gestionDept.ajouterDepartement(d2);
        gestionDept.ajouterDepartement(d3);

        System.out.println("\n=== Tous les départements ===");
        gestionDept.afficherDepartements();

        System.out.println("\n=== Tri par ID ===");
        gestionDept.trierDepartementsParId();

        System.out.println("\n=== Tri par Nom + Nombre Employés ===");
        gestionDept.trierDepartementsParNomEtNombreEmployes();


        /* ===============================
           AFFECTATION EMPLOYÉ → DÉPARTEMENT
           =============================== */

        System.out.println("\n============================");
        System.out.println("   AFFECTATION DES EMPLOYÉS");
        System.out.println("============================");

        AffectationHashMap affectation = new AffectationHashMap();

        affectation.ajouterEmployeDepartement(e1, d1);
        affectation.ajouterEmployeDepartement(e2, d2);
        affectation.ajouterEmployeDepartement(e3, d3);

        System.out.println("\n=== Test: réaffecter e1 dans Finance ===");
        affectation.ajouterEmployeDepartement(e1, d3); // Doit refuser

        System.out.println("\n=== Affichage des affectations ===");
        affectation.afficherEmployesEtDepartements();

        System.out.println("\n=== Suppression employé e2 ===");
        affectation.supprimerEmploye(e2);
        affectation.afficherEmployesEtDepartements();

        System.out.println("\n=== Suppression liaison e3 ↔ Finance ===");
        affectation.supprimerEmployeEtDepartement(e3, d3);
        affectation.afficherEmployesEtDepartements();

        System.out.println("\n=== Liste des employés dans la map ===");
        affectation.afficherEmployes();

        System.out.println("\n=== Liste des départements dans la map ===");
        affectation.afficherDepartements();

        System.out.println("\n=== Recherche employés ===");
        System.out.println("e1 existe ? " + affectation.rechercherEmploye(e1));
        System.out.println("e3 existe ? " + affectation.rechercherEmploye(e3));

        System.out.println("\n=== Recherche départements ===");
        System.out.println("Finance existe ? " + affectation.rechercherDepartement(d3));

        System.out.println("\n=== Map triée selon ID employé ===");
        System.out.println(affectation.trierMap());


    }


}
