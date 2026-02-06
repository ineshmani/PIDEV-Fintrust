package services;

import Globalinterface.Interface;
import models.user;
import util.config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userService implements Interface<user> {

    private Connection cnx;

    // Constructeur
    public userService() {
        cnx = config.getConnection(); // Récupère la connexion depuis util.config
    }

    // CREATE
    @Override
    public boolean create(user u) {
        String sql = "INSERT INTO users(currentKycId, nom, prenom, email, numTel, role, password, kycStatus, createdAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, u.getCurrentKycId());
            pst.setString(2, u.getNom());
            pst.setString(3, u.getPrenom());
            pst.setString(4, u.getEmail());
            pst.setString(5, u.getNumTel());
            pst.setString(6, u.getRole());
            pst.setString(7, u.getPassword());
            pst.setString(8, u.getKycStatus());
            pst.setTimestamp(9, Timestamp.valueOf(u.getCreatedAt()));

            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        u.setId(generatedKeys.getInt(1)); // récupère l'id auto-incrémenté
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ by id
    @Override
    public user find(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ all
    @Override
    public List<user> findAll() {
        List<user> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean update(user u) {
        String sql = "UPDATE users SET currentKycId=?, nom=?, prenom=?, email=?, numTel=?, role=?, password=?, kycStatus=? WHERE id=?";
        try (PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, u.getCurrentKycId());
            pst.setString(2, u.getNom());
            pst.setString(3, u.getPrenom());
            pst.setString(4, u.getEmail());
            pst.setString(5, u.getNumTel());
            pst.setString(6, u.getRole());
            pst.setString(7, u.getPassword());
            pst.setString(8, u.getKycStatus());
            pst.setInt(9, u.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode utilitaire pour mapper ResultSet → user
    private user mapResultSetToUser(ResultSet rs) throws SQLException {
        user u = new user(
                rs.getInt("currentKycId"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                rs.getString("numTel"),
                rs.getString("role"),
                rs.getString("password"),
                rs.getString("kycStatus"),
                rs.getTimestamp("createdAt").toLocalDateTime()
        );
        u.setId(rs.getInt("id"));
        return u;
    }

    // Méthode pour trouver un utilisateur par currentKycId
    public user findByKycId(int kycId) {
        String sql = "SELECT * FROM users WHERE currentKycId = ?";
        try (PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, kycId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}