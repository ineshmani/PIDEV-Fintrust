package org.example;

import models.Wallet;
import services.WalletService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        WalletService walletService = new WalletService();

        // ===============================
        // 1️⃣ Création de wallets
        // ===============================
        Wallet[] wallets = {
                new Wallet("Feryel Hajji", 1500.0, "TND", "ACTIF", LocalDateTime.now()),
                new Wallet("Ines Hmani", 2300.5, "TND", "ACTIF", LocalDateTime.now()),
                new Wallet("Firas Ben Ali", 800.0, "TND", "SUSPENDU", LocalDateTime.now()),
                new Wallet("Rana Trabelsi", 1200.75, "TND", "ACTIF", LocalDateTime.now())
        };

        for (Wallet w : wallets) {
            boolean succes = walletService.create(w);
            if (succes) {
                System.out.println("✅ Wallet de " + w.getNomProprietaire()
                        + " ajouté avec succès (ID = " + w.getId() + ")");
            } else {
                System.out.println("❌ Échec lors de l'ajout du wallet de " + w.getNomProprietaire());
            }
        }

        // ===============================
        // 2️⃣ Affichage de tous les wallets
        // ===============================
        System.out.println("\n📋 Liste des wallets dans la base :");
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
    }
}
