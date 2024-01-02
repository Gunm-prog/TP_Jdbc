package Dao;

import TpJDBC.src.Entity.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDao {
    private Connection databaseConnection;
    private Statement statement;
    
    private long id;
    private int clientNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String adress;

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
            Class.forName("com.mysql.cj.jdbc.Driver");

            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");

            statement = databaseConnection.createStatement();
            System.out.println("Insertion de données...");
            statement.executeUpdate(insert);

            databaseConnection.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    public void readAll(){
        final String select = "SELECT * FROM Clients";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");
            
            statement = databaseConnection.createStatement();
        
            System.out.println("Lecture des données...");
            ResultSet rs = statement.executeQuery(select);
            System.out.println();
            String idData;
            String clientNumberData;
            String firstNameData;
            String lastNameData;
            String emailData;
            String adressData;
            while(rs.next()){
                idData = rs.getString("id");
                clientNumberData = rs.getString("clientNumber");
                firstNameData = rs.getString("firstName");
                lastNameData = rs.getString("lastName");
                emailData = rs.getString("email");
                adressData = rs.getString("adress");
                
                System.out.println("Id : " + idData);
                System.out.println("Numéro client : " + clientNumberData);
                System.out.println("Prénom : " + firstNameData);
                System.out.println("Nom : " + lastNameData);
                System.out.println("Email : " + emailData);
                System.out.println("Adresse : " + adressData);
                System.out.println();
            }
            databaseConnection.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return clients;
    }
    
    public void read(long id){
        final String select = "SELECT * FROM Clients WHERE id = " + id;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");
            
            statement = databaseConnection.createStatement();
        
            System.out.println("Lecture des données...");
            ResultSet rs = statement.executeQuery(select);
            System.out.println();
            String idData;
            String clientNumberData;
            String firstNameData;
            String lastNameData;
            String emailData;
            String adressData;
            while(rs.next()){
                idData = rs.getString("id");
                clientNumberData = rs.getString("clientNumber");
                firstNameData = rs.getString("firstName");
                lastNameData = rs.getString("lastName");
                emailData = rs.getString("email");
                adressData = rs.getString("adress");
                
                System.out.println("Id : " + idData);
                System.out.println("Numéro client : " + clientNumberData);
                System.out.println("Prénom : " + firstNameData);
                System.out.println("Nom : " + lastNameData);
                System.out.println("Email : " + emailData);
                System.out.println("Adresse : " + adressData);
                System.out.println();
            }
            databaseConnection.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
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
            Class.forName("com.mysql.cj.jdbc.Driver");

            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");

            statement = databaseConnection.createStatement();
            System.out.println("Insertion de données...");
            statement.executeUpdate(update);

            databaseConnection.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    public void delete(long id){
        final String delete = "DELETE FROM Clients WHERE id = " + id;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");

            statement = databaseConnection.createStatement();
            System.out.println("Insertion de données...");
            statement.executeUpdate(delete);

            databaseConnection.close();
        }catch(ClassNotFoundException | SQLException e){
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
