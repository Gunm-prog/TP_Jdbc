package TpJDBC.src.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionService {
    private Connection databaseConnection;
    private Statement statement;

    final String createDatabase = "CREATE DATABASE IF NOT EXISTS TpJDBC CHARACTER SET utf8;";
    final String useDatabase = "use TpJDBC";
    final String createTableUser = "CREATE TABLE IF NOT EXISTS Users ("
        + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
        + "employeNumber INT NOT NULL UNIQUE, "
        + "lastName VARCHAR(255) NOT NULL,"
        + "firstName VARCHAR(255) NOT NULL UNIQUE,"
        + "email VARCHAR(255) NOT NULL UNIQUE,"
        + "login VARCHAR(255) NOT NULL UNIQUE,"
        + "password VARCHAR(255) NOT NULL)";

    final String createTableClient = "CREATE TABLE IF NOT EXISTS Clients ("
        + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
        + "number INT NOT NULL, "
        + "lastName VARCHAR(255) NOT NULL,"
        + "firstName VARCHAR(255) NOT NULL,"
        + "email VARCHAR(255) NOT NULL,"
        + "adress VARCHAR(255) NOT NULL)";

    final String createTableSupplier = "CREATE TABLE IF NOT EXISTS Suppliers ("
        + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
        + "number INT NOT NULL, "
        + "name VARCHAR(255) NOT NULL,"
        + "email VARCHAR(255) NOT NULL,"
        + "adress VARCHAR(255) NOT NULL)";

    final String createTableItem = "CREATE TABLE IF NOT EXISTS Items ("
        + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
        + "number INT NOT NULL, "
        + "status ENUM('sold', 'for_sell') NOT NULL,"
        + "name VARCHAR(255) NOT NULL,"
        + "description VARCHAR(255) NOT NULL)";

    final String dropDatabase = "DROP DATABASE TpJDBC";

    public Connection getDatabaseConnection() throws SQLException {
       return DriverManager.getConnection("jdbc:mysql://localhost:3306/TpJDBC?useSSL=false", "root", "");
    }
    public void initDatabase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");

            statement = databaseConnection.createStatement();
            
            System.out.println("Creation de la BDD...");
            statement.executeUpdate(createDatabase);
            System.out.println("Base de donn�e cr��e avec succ�s");
            System.out.println();

            System.out.println("Utilisation de la BDD...");
            statement.executeUpdate(useDatabase);
            System.out.println();

            System.out.println("Creation des tables ...");
            statement.executeUpdate(createTableUser);
            statement.executeUpdate(createTableClient);
            statement.executeUpdate(createTableSupplier);
            statement.executeUpdate(createTableItem);
            System.out.println("Tables cr��es avec succ�s");
            System.out.println();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }

    public void deleteDatabase(){
        try{
            System.out.println("Suppression de la base de donn�es...");
            statement.executeUpdate(dropDatabase);
            System.out.println("Base de donn�es supprim�e avec succ�s");
            System.out.println();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}

