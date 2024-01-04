package Dao;

import Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import Exception.UserAlreadyExistsException;


public class UserDao {

    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    //Add User
    public void addUser(User user) throws SQLException, UserAlreadyExistsException {

        try {
            String query = "INSERT INTO Users (employeNumber, lastname, firstname, email, login, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getEmployeeNb());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());

            preparedStatement.executeUpdate();

          //Personalized exception to prevent duplication
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new UserAlreadyExistsException( e.getMessage() );
        }

    }

    //Get users' list

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM Users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    //get User by id
    public User getUserById(Long id) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException ime){
            System.out.println("Only numbers are allowed!");
        }

        return user;
    }

    //Update a user
    public void updateUser(User user) {
        try {
            String query = "UPDATE Users SET employeNumber=?, lastname=?, firstname=?, email=?, login=?, password=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getEmployeeNb());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setLong(7, user.getId());

            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Delete user by id
    public void deleteUser(Long id) {
        try {
            String query = "DELETE FROM Users WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Conversion method mapping request's result to a User object
    //It converts datas from db into a User object
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

    //Pour chaque userdata on lance une requete et on gere les erreurs pour chacune d'entre-elles
    public void loadUserDataSet() throws RuntimeException {
        List.of(
                new User(48562, "Dylan", "Bob", "bob@dylan.gmail", "bobyD", "bob123"),
                new User(45454, "Dude", "Sam", "dude@sam.gmail", "Sam_D", "123456"),
                new User(15101, "Murray", "Bill", "bill@murray.gmail", "hillybilly", "bilmu01"),
                new User(78788, "Durand", "Jean-Raymond", "jraymond@durand.gmail", "JR_D", "456"),
                new User(48562, "Shaw", "Arthy", "arthy@shaw.gmail", "arthy48", "1245")
        ).forEach(userData -> {
            System.out.print( "inject : " + userData.toString() );
            try {//Lambda force the try catch here, for each query's execution
                this.addUser( userData );
                System.out.println( "   SUCCESS");
            } catch (SQLException e) {
                System.out.println( "   FAILED");
                System.out.println( e.getMessage() );
            }
        });
    }
}