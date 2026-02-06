package Globalinterface;

import java.util.List;

// Interface générique pour CRUD
public interface Interface<T> {

    // Créer
    boolean create(T t);

    // Lire
    T find(int id);
    List<T> findAll();

    // Mettre à jour
    boolean update(T t);

    // Supprimer
    boolean delete(int id);
}
