package org.example;

import services.userService;
import models.user;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        userService us = new userService();

        System.out.println("Connexion réussie à la base de données !");

        // ===== Nouveaux utilisateurs =====
        user[] users = {
            new user(100, "Hmani", "Ines", "ines.hmani@example.com", "0612340001", "user", "pass1", "PENDING", LocalDateTime.now()),
            new user(101, "Hmidi", "Karam", "karam.hmidi@example.com", "0612340002", "admin", "pass2", "PENDING", LocalDateTime.now()),
            new user(102, "Hajji", "Feryel", "feryel.hajji@example.com", "0612340003", "user", "pass3", "PENDING", LocalDateTime.now()),
            new user(103, "Sassi", "Kenza", "kenza.sassi@example.com", "0612340004", "user", "pass4", "PENDING", LocalDateTime.now())
        };

        // Ajouter seulement si l'utilisateur n'existe pas
        for (user u : users) {
            us.create(u);
        }

        // ===== Liste des utilisateurs =====
        List<user> all = us.findAll();
        System.out.println("\nListe des utilisateurs :");
        for (user u : all) {
            System.out.println(u.getNom() + " " + u.getPrenom() + " | Role: " + u.getRole() + " | KYC: " + u.getKycStatus());
        }

        // ===== Vérifier rôle et redirection =====
        for (user u : all) {
            String role = us.getRoleByEmail(u.getEmail());
            if ("admin".equals(role)) {
                System.out.println(u.getNom() + " est admin → redirection page ADMIN");
            } else {
                System.out.println(u.getNom() + " est utilisateur standard → redirection page USER");
            }
        }

        // ===== Modifier KYC pour Ines =====
        user ines = us.findByEmail("ines.hmani@example.com");
        if (ines != null) {
            us.changeKycStatus(ines.getId(), "VALIDATED");
            System.out.println("KYC modifié pour : " + ines.getNom());
        }

        // ===== Supprimer Feryel =====
        user feryel = us.findByEmail("feryel.hajji@example.com");
        if (feryel != null) {
            us.delete(feryel.getId());
            System.out.println("Utilisateur supprimé : " + feryel.getNom());
        }

        // ===== Liste finale =====
        all = us.findAll();
        System.out.println("\nListe finale des utilisateurs :");
        for (user u : all) {
            System.out.println(u.getNom() + " " + u.getPrenom() + " | Role: " + u.getRole() + " | KYC: " + u.getKycStatus());
        }
    }
}
