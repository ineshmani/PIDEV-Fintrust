package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class config {

    // Paramètres de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/PIDEV";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // mettre votre mot de passe si nécessaire

    // Variable Singleton pour la connexion
    private static Connection connection;

    // Méthode pour obtenir la connexion
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Charger le driver JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Établir la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("Connexion réussie à la base de données !");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver MySQL introuvable : " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Erreur de connexion : " + e.getMessage());
            }
        }
        return connection;
    }
}
