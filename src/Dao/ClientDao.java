package Dao;

import Entity.Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private final Connection databaseConnection;
    private Statement statement;
     
    public ClientDao(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    //Insert le client en paramètre dans la base de donnée
    public void create(Client client){
        final String insert = "INSERT INTO Clients (number,lastName,firstName,email,adress)"
                + " VALUES ("
                + client.getClientNumber() + ", "
                + "'" + client.getLastname() + "'" +", "
                + "'" + client.getFirstname() + "'" +", "
                + "'" + client.getEmail()+ "'"  +", "
                + "'" + client.getAdress() + "'" +")";
        System.out.println(insert);
        try{
            statement = databaseConnection.createStatement();
            statement.executeUpdate(insert);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //Récupère la liste des clients présent en base de donnée
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
    
    //Récupère le client dont l'id est donné en paramètre
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
    
    //Met à jour dans la base de données le client donné en paramètre
    public void update(Client client){
        final String update = "UPDATE Clients SET"
                + " number = " + client.getClientNumber() + ", "
                + " lastName = " + "'" + client.getLastname() + "'" + ", "
                + " firstName = " + "'" + client.getFirstname() + "'" + ", "
                + " email = " + "'" + client.getEmail() + "'" + ", "
                + " adress = " + "'" + client.getAdress() + "'"
                + "WHERE id = " + "'" + client.getId() + "'";
        try{
            statement = databaseConnection.createStatement();
            statement.executeUpdate(update);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //Supprime de la base de données le client dont l'id est donné en paramètre
    public void delete(long id){
        final String delete = "DELETE FROM Clients WHERE id = " + id;
        try{
            statement = databaseConnection.createStatement();
            statement.executeUpdate("use TpJDBC");
            statement.executeUpdate(delete);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //Converti les données d'un resultSet en un objet Client
    private Client mapResultSetToClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("id"));
        client.setClientNumber(resultSet.getInt("number"));
        client.setLastname(resultSet.getString("lastName"));
        client.setFirstname(resultSet.getString("firstName"));
        client.setEmail(resultSet.getString("email"));
        client.setAdress(resultSet.getString("adress"));
        return client;
    }
}
