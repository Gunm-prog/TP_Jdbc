package Dao;

import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    //Add User
    public void addUser(User user) {
        try {
            String query = "INSERT INTO Users (employeNumber, lastname, firstname, email, login, password) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, user.getEmployeeNb());
                preparedStatement.setString(2, user.getLastname());
                preparedStatement.setString(3, user.getFirstname());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getLogin());
                preparedStatement.setString(6, user.getPassword());

                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get users' list

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM Users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        users.add(mapResultSetToUser(resultSet));

                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    //get User by id
    public User getUserById(Long id) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = mapResultSetToUser(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //Update a user
    public void updateUser(User user) {
        try {
            String query = "UPDATE Users SET employeNumber=?, lastname=?, firstname=?, email=?, login=?, password=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, user.getEmployeeNb());
                preparedStatement.setString(2, user.getLastname());
                preparedStatement.setString(3, user.getFirstname());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getLogin());
                preparedStatement.setString(6, user.getPassword());
                preparedStatement.setLong(7, user.getId());

                preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Delete user by id
    public void deleteUser(Long id) {
        try {
            String query = "DELETE FROM Users WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Conversion method mapping request's result to a User object
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmployeeNb(resultSet.getInt("employeNumber"));
        user.setLastname(resultSet.getString("lastname"));
        user.setFirstname(resultSet.getString("firstname"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}