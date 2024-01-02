package TpJDBC.src.Dao;

import TpJDBC.src.Entity.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private final Connection databaseConnection;
    private Statement statement;
    
    private long id;
    private int clientNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String adress;
    
    public ClientDao(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    public void create(Client client){
        final String insert = "INSERT INTO Clients"
                + " VALUES ("
                + client.getClientNumber() + ", "
                + client.getLastname() +", "
                + client.getFirstname() +", "
                + client.getEmail() +", "
                + client.getAdress() +", "
                +")";
        try{
            statement = databaseConnection.createStatement();
            statement.executeUpdate(insert);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public List<Client> readAll() {
        List<Client> clients = new ArrayList<>();
        try {
            String select = "SELECT * FROM Clients";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(mapResultSetToClient(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return clients;
    }
    
    public Client read(Long id) {
        Client client = null;
        try {
            String select = "SELECT * FROM Clients WHERE id=?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(select);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client = mapResultSetToClient(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return client;
    }
    
    public void update(Client client){
        final String update = "UPDATE Clients SET"
                + " clientNumber = " + client.getClientNumber() + ", "
                + " lastName = " + client.getLastname() + ", "
                + " firstName = " + client.getFirstname() + ", "
                + " email = " + client.getEmail() + ", "
                + " adress = " + client.getAdress()
                + "WHERE id = " + client.getId();
        try{
            statement = databaseConnection.createStatement();
            statement.executeUpdate(update);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void delete(long id){
        final String delete = "DELETE FROM Clients WHERE id = " + id;
        try{
            statement = databaseConnection.createStatement();
            statement.executeUpdate(delete);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    private Client mapResultSetToClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("id"));
        client.setClientNumber(resultSet.getInt("clientNumber"));
        client.setLastname(resultSet.getString("lastName"));
        client.setFirstname(resultSet.getString("fistname"));
        client.setEmail(resultSet.getString("email"));
        client.setAdress(resultSet.getString("adress"));
        return client;
    }
}
