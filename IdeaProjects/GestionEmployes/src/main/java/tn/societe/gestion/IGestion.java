package tn.societe.gestion;

public interface IGestion<T> {
    void ajouterEmploye(T t);
    boolean rechercherEmploye(String nomOuPrenom);
    boolean rechercherEmploye(T t);
    void supprimerEmploye(T t);
    void displayEmploye();
    void trierEmployeParId();
    void trierEmployeParNomDépartementEtGrade();
}
