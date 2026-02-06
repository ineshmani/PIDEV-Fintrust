package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class config {

    // 🔹 Paramètres de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/PIDEV";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // mettre le mot de passe si nécessaire

    // 🔹 Instance unique de connexion (Singleton)
    private static Connection connection;

    // 🔹 Méthode pour récupérer la connexion
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Charger le driver JDBC MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Établir la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("✅ Connexion à la base de données établie avec succès.");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Driver MySQL introuvable : " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors de la connexion à la base de données : " + e.getMessage());
            }
        }
        return connection;
    }
}
