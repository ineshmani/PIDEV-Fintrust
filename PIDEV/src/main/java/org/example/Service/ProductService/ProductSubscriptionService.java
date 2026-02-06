package org.example.Service.ProductService;

import org.example.Interfaces.InterfaceGblobal;
import org.example.Model.Product.ClassProduct.Product;
import org.example.Model.Product.ClassProduct.ProductSubscription;
import org.example.Model.Product.EnumProduct.ProductCategory;
import org.example.Model.Product.EnumProduct.SubscriptionStatus;
import org.example.Model.Product.EnumProduct.SubscriptionType;
import org.example.Utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSubscriptionService implements InterfaceGblobal<ProductSubscription> {

    Connection cnx = MaConnexion.getInstance().getCnx();


    @Override
    public void add(ProductSubscription p) {
        String req = "INSERT INTO `ProductSubscription`(`client`, `product`, `type`, `subscriptionDate`, `expirationDate`, `status`) " +
                "VALUES ('"+p.getClient()+"','"+p.getProduct()+"','"+p.getType()+"','"+p.getSubscriptionDate()+"','"+p.getExpirationDate()+"','"+p.getStatus()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ProduitSubscription ajoutee avec succes!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ProductSubscription p) {
        String req = "UPDATE `ProductSubscription` SET type = ?, subscriptionDate=?, expirationDate = ?, status = ? WHERE subscriptionId = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getType().name());
            ps.setTimestamp(2, Timestamp.valueOf(p.getSubscriptionDate()));
            ps.setTimestamp(3, Timestamp.valueOf(p.getExpirationDate()));
            ps.setString(4, p.getStatus().name());
            ps.setInt(5, p.getSubscriptionId());
            ps.executeUpdate();
            System.out.println("ProduitSubscription changée avec succes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(ProductSubscription p) {
        String req = "DELETE FROM `ProductSubscription` WHERE subscriptionId = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getSubscriptionId());
            ps.executeUpdate();
            System.out.println("ProductSubscription Supprimer avec succes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductSubscription> getAll() {
        List<ProductSubscription> products = new ArrayList<>();
        String req = "SELECT * FROM `ProductSubscription`";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                ProductSubscription ps =new ProductSubscription();
                ps.setSubscriptionId(res.getInt(1));
                ps.setSubscriptionId(res.getInt(2));
                ps.setSubscriptionId(res.getInt(3));
                ps.setType(SubscriptionType.valueOf(res.getString(4)));
                ps.setSubscriptionDate(res.getTimestamp(5).toLocalDateTime());
                ps.setExpirationDate(res.getTimestamp(6).toLocalDateTime());
                ps.setStatus(SubscriptionStatus.valueOf(res.getString(7)));
                products.add(ps);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<ProductSubscription> getParClient(int clientId) {
        List<ProductSubscription> products = new ArrayList<>();

        String req = "SELECT * FROM productsubscription WHERE client = ?";

        try (PreparedStatement p = cnx.prepareStatement(req)) {
            p.setInt(1, clientId);
            ResultSet res = p.executeQuery();
            while (res.next()) {
                ProductSubscription ps = new ProductSubscription();
                ps.setSubscriptionId(res.getInt("subscriptionId"));
                ps.setClient(res.getInt("client"));
                ps.setProduct(res.getInt("product"));
                ps.setType(SubscriptionType.valueOf(res.getString("type")));
                ps.setSubscriptionDate(
                        res.getDate("subscriptionDate").toLocalDate().atStartOfDay()
                );
                ps.setExpirationDate(
                        res.getDate("expirationDate").toLocalDate().atStartOfDay()
                );
                ps.setStatus(SubscriptionStatus.valueOf(res.getString("status")));

                products.add(ps);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }






}
