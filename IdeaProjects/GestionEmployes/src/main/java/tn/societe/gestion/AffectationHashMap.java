package tn.societe.gestion;

import java.util.*;

public class AffectationHashMap {

    private HashMap<Employe, Departement> map = new HashMap<>();

    public void ajouterEmployeDepartement(Employe e, Departement d) {
        if (map.containsKey(e)) {
            System.out.println("⚠ L'employé " + e.getNom() +
                    " est déjà affecté au département " + map.get(e).getNom());
        } else {
            map.put(e, d);
        }
    }


    public void afficherEmployesEtDepartements() {
        if (map.isEmpty()) {
            System.out.println("Aucune affectation trouvée.");
            return;
        }
        for (Map.Entry<Employe, Departement> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " ---> " + entry.getValue());
        }
    }


    public void supprimerEmploye(Employe e) {
        map.remove(e);
    }


    public void supprimerEmployeEtDepartement(Employe e, Departement d) {
        if (map.containsKey(e) && map.get(e).equals(d)) {
            map.remove(e);
        } else {
            System.out.println("Aucune correspondance Employé–Département trouvée.");
        }
    }

    public void afficherEmployes() {
        System.out.println("Liste des employés :");
        map.keySet().forEach(System.out::println);
    }


    public void afficherDepartements() {
        System.out.println("Liste des départements :");
        new HashSet<>(map.values()).forEach(System.out::println);
    }


    public boolean rechercherEmploye(Employe e) {
        return map.containsKey(e);
    }


    public boolean rechercherDepartement(Departement d) {
        return map.containsValue(d);
    }


    public TreeMap<Employe, Departement> trierMap() {
        return new TreeMap<>(map);
    }
}
