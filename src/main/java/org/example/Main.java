package org.example;

import services.userService;
import models.user;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        userService userService = new userService();

        // -----------------------------
        // 1️⃣ Ajouter plusieurs utilisateurs uniquement s'ils n'existent pas
        // -----------------------------
        int kycId = 100;
        user[] utilisateurs = new user[]{
                new user(kycId++, "Hmani", "Ines", "ines.hmani@example.com", "0612340001", "user", "pass123", "pending", LocalDateTime.now()),
                new user(kycId++, "Hmidi", "Karam", "karam.hmidi@example.com", "0612340002", "user", "pass123", "pending", LocalDateTime.now()),
                new user(kycId++, "Hajji", "Feryel", "feryel.hajji@example.com", "0612340003", "user", "pass123", "pending", LocalDateTime.now())
        };

        for (user u : utilisateurs) {
            if (userService.findByKycId(u.getCurrentKycId()) == null) {
                boolean succes = userService.create(u);
                if (succes) {
                    System.out.println("✅ Utilisateur ajouté : " + u.getPrenom() + " " + u.getNom());
                } else {
                    System.out.println("❌ Échec de l'ajout de : " + u.getPrenom() + " " + u.getNom());
                }
            } else {
                System.out.println("ℹ️ Utilisateur déjà existant : " + u.getPrenom() + " " + u.getNom());
            }
        }

        // -----------------------------
        // 2️⃣ Modifier le mot de passe d'un utilisateur existant (Karam)
        // -----------------------------
        int kycToUpdate = 101; // KYC de Karam
        String nouveauMotDePasse = "newPass456";

        user utilisateurAModifier = userService.findByKycId(kycToUpdate);
        if (utilisateurAModifier != null) {
            utilisateurAModifier.setPassword(nouveauMotDePasse);
            boolean succes = userService.update(utilisateurAModifier);
            if (succes) {
                System.out.println("✅ Mot de passe modifié pour : "
                        + utilisateurAModifier.getPrenom() + " "
                        + utilisateurAModifier.getNom());
            } else {
                System.out.println("❌ Échec de la modification du mot de passe.");
            }
        } else {
            System.out.println("❌ Utilisateur non trouvé avec KYC ID=" + kycToUpdate);
        }

        // -----------------------------
        // 3️⃣ Supprimer l'utilisateur Feryel
        // -----------------------------
        int kycToDelete = 102; // KYC de Feryel
        user utilisateurASupprimer = userService.findByKycId(kycToDelete);
        if (utilisateurASupprimer != null) {
            boolean succes = userService.delete(utilisateurASupprimer.getId());
            if (succes) {
                System.out.println("✅ Utilisateur supprimé : " + utilisateurASupprimer.getPrenom() + " " + utilisateurASupprimer.getNom());
            } else {
                System.out.println("❌ Échec de la suppression de : " + utilisateurASupprimer.getPrenom());
            }
        } else {
            System.out.println("ℹ️ Utilisateur non trouvé pour suppression avec KYC ID=" + kycToDelete);
        }

        // -----------------------------
        // 4️⃣ Afficher tous les utilisateurs restants dans la base
        // -----------------------------
        System.out.println("\n📋 Liste des utilisateurs dans la base :");
        List<user> allUsers = userService.findAll();
        for (user u : allUsers) {
            System.out.println("KYC ID: " + u.getCurrentKycId() +
                    ", Nom: " + u.getNom() +
                    ", Prénom: " + u.getPrenom() +
                    ", Email: " + u.getEmail() +
                    ", NumTel: " + u.getNumTel() +
                    ", Role: " + u.getRole() +
                    ", Statut KYC: " + u.getKycStatus() +
                    ", Créé le: " + u.getCreatedAt());
        }
    }
}
