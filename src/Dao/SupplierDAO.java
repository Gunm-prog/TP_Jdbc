package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Supplier;

public class SupplierDAO {
    private Connection connection;

    public SupplierDAO(Connection connection) {
        this.connection = connection;
    }

    //  ajouter un fournisseur
    public void addSupplier(Supplier supplier) {
        try {
            String query = "INSERT INTO Suppliers (number, name, email, adress) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                preparedStatement.executeUpdate("use TpJDBC");
                preparedStatement.setInt(1, supplier.getSupplierNb());
                preparedStatement.setString(2, supplier.getName());
                preparedStatement.setString(3, supplier.getEmail());
                preparedStatement.setString(4, supplier.getAdress());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //  récupérer tous les fournisseurs
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            String query = "SELECT * FROM Suppliers";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate("use TpJDBC");
                ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        suppliers.add(mapResultSetToSupplier(resultSet));
                    }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return suppliers;
    }

    //  récupérer un fournisseur par son ID
    public Supplier getSupplierById(Long id) {
        Supplier supplier = null;
        try {
            String query = "SELECT * FROM Suppliers WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate("use TpJDBC");
                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        supplier = mapResultSetToSupplier(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return supplier;
    }

    //  mettre à jour un fournisseur
    public void updateSupplier(Supplier supplier) {
        try {
            String query = "UPDATE Suppliers SET number=?, name=?, email=?, adress=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate("use TpJDBC");
                preparedStatement.setInt(1, supplier.getSupplierNb());
                preparedStatement.setString(2, supplier.getName());
                preparedStatement.setString(3, supplier.getEmail());
                preparedStatement.setString(4, supplier.getAdress());
                preparedStatement.setLong(5, supplier.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // supprimer un fournisseur
    public void deleteSupplier(Long id) {
        try {
            String query = "DELETE FROM Suppliers WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate("use TpJDBC");
                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
         }
    }

    public boolean isSupplierNumberExists(int supplierNumber) {
        try {
            String query = "SELECT COUNT(*) FROM Suppliers WHERE number=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, supplierNumber);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }


    //  mapper le résultat de la requête à un objet Supplier
    private Supplier mapResultSetToSupplier(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(resultSet.getLong("id"));
        supplier.setSupplierNb(resultSet.getInt("number"));
        supplier.setName(resultSet.getString("name"));
        supplier.setEmail(resultSet.getString("email"));
        supplier.setAdress(resultSet.getString("adress"));
        return supplier;
    }
}