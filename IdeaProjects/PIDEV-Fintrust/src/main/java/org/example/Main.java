package org.example;

import models.Wallet;
import services.WalletService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        WalletService walletService = new WalletService();

        // ===============================
        // 1️ Création de wallets
        // ===============================
        Wallet[] wallets = {

                new Wallet("Iram Trabelsi", 2300.0, "TND", "SUSPENDU", LocalDateTime.now()),
                new Wallet("Douaa Chakroun", 1500.75, "TND", "ACTIF", LocalDateTime.now())
        };

        for (Wallet w : wallets) {
            boolean succes = walletService.create(w);
            if (succes) {
                System.out.println(" Wallet de " + w.getNomProprietaire()
                        + " ajouté avec succès (ID = " + w.getId() + ")");
            } else {
                System.out.println(" Échec lors de l'ajout du wallet de " + w.getNomProprietaire());
            }
        }

        // ===============================
        // 2️ Affichage de tous les wallets
        // ===============================
        System.out.println("\n Liste des wallets dans la base :");
        List<Wallet> allWallets = walletService.findAll();
        for (Wallet w : allWallets) {
            System.out.println(
                    "ID: " + w.getId()
                            + " | Propriétaire: " + w.getNomProprietaire()
                            + " | Solde: " + w.getSolde() + " " + w.getDevise()
                            + " | Statut: " + w.getStatut()
                            + " | Créé le: " + w.getDateCreation()
            );
        }

        // ===============================
        // 4️ Suppression d'un wallet
        // ===============================
        System.out.println("\n Suppression du wallet ID = 3...");
        boolean deleted = walletService.delete(3);
        System.out.println(deleted ? " Wallet supprimé avec succès." : " Échec de la suppression.");

        // ===============================
        // 5 Recherche d'un wallet par ID
        // ===============================
        System.out.println("\n Recherche du wallet ID = 2...");
        Wallet walletFound = walletService.find(2);
        if (walletFound != null) {
            System.out.println("Wallet trouvé : " + walletFound.getNomProprietaire()
                    + " avec solde " + walletFound.getSolde()
                    + " " + walletFound.getDevise());
        } else {
            System.out.println(" Aucun wallet trouvé avec cet ID.");
        }
    }
}
