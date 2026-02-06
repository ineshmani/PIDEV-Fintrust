package services;

import Globalinterface.Interface;
import models.Wallet;
import util.config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletService implements Interface<Wallet> {

    private Connection cnx;

    // Constructeur
    public WalletService() {
        cnx = config.getConnection(); // Connexion à la BD pidev
    }

    // CREATE
    @Override
    public boolean create(Wallet w) {

        String sql = "INSERT INTO wallet(nom_proprietaire, solde, devise, statut, date_creation) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pst = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, w.getNomProprietaire());
            pst.setDouble(2, w.getSolde());
            pst.setString(3, w.getDevise());
            pst.setString(4, w.getStatut());
            pst.setTimestamp(5, Timestamp.valueOf(w.getDateCreation()));

            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        w.setId(generatedKeys.getInt(1));
                        System.out.println("Wallet " + w.getId() + " est enregistré avec succès");
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
    public Wallet find(int id) {

        String sql = "SELECT * FROM wallet WHERE id_wallet = ?";

        try (PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToWallet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ all
    @Override
    public List<Wallet> findAll() {

        List<Wallet> list = new ArrayList<>();
        String sql = "SELECT * FROM wallet";

        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToWallet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean update(Wallet w) {

        String sql = "UPDATE wallet SET nom_proprietaire=?, solde=?, devise=?, statut=? WHERE id_wallet=?";

        try (PreparedStatement pst = cnx.prepareStatement(sql)) {

            pst.setString(1, w.getNomProprietaire());
            pst.setDouble(2, w.getSolde());
            pst.setString(3, w.getDevise());
            pst.setString(4, w.getStatut());
            pst.setInt(5, w.getId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM wallet WHERE id_wallet = ?";

        try (PreparedStatement pst = cnx.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode utilitaire : ResultSet → Wallet
    private Wallet mapResultSetToWallet(ResultSet rs) throws SQLException {

        Wallet w = new Wallet(
                rs.getString("nom_proprietaire"),
                rs.getDouble("solde"),
                rs.getString("devise"),
                rs.getString("statut"),
                rs.getTimestamp("date_creation").toLocalDateTime()
        );

        w.setId(rs.getInt("id_wallet"));
        return w;
    }
}
