package Dao;

import Entity.Item;
import Entity.ItemStatus;
import Service.ConnexionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    private final Connection connection;

    public ItemDao(Connection connection) { this.connection=connection;}

    //Add Item
    public void addItem(Item item) throws SQLException {

        String query = "INSERT INTO Items (number, status, name, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, item.getNumber());
            preparedStatement.setString(2, String.valueOf(item.getStatus()));
            preparedStatement.setString(3, item.getName());
            preparedStatement.setString(4, item.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //Get items' list
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try {
            String query = "SELECT * FROM Items";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(mapResultSetToItem(resultSet));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return items;
    }



    //get Item by id
    public Item getById(Long id) {
        Item item = null;
        try {
            String query = "SELECT * FROM Items WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    item = mapResultSetToItem(resultSet);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    //Update an item
    public void updateItem(Item item) {
        try {
            String query = "UPDATE Items SET number=?, status=?, name=?, description=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item.getNumber());
            preparedStatement.setString(2, String.valueOf(item.getStatus()));
            preparedStatement.setString(3, item.getName());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setLong(5, item.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Delete item by id
    public void deleteItem(Long id) {
        try {
            String query = "DELETE FROM Items WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Conversion method mapping request's result to an Item object
    private Item mapResultSetToItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getLong("id"));
        item.setNumber(resultSet.getInt("number"));
        item.setStatus(ItemStatus.valueOf(resultSet.getString("status")));
        item.setName(resultSet.getString("name"));
        item.setDescription(resultSet.getString("description"));
        return item;
    }

}
