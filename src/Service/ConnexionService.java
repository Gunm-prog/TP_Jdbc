package Service;

import Dao.UserDao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

public class ConnexionService {
    private Connection databaseConnection;
    private Statement statement;

    public static String JdbcDriver;
    public static String URL;

    public static String dbname;

    public static String username;
    public static String password;

    public String createDatabase = "CREATE DATABASE IF NOT EXISTS " + dbname + " CHARACTER SET utf8;";
    static String useDatabase = "use " + dbname;
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
        + "number INT NOT NULL UNIQUE, "
        + "status ENUM('sold', 'for_sell') NOT NULL,"
        + "name VARCHAR(255) NOT NULL,"
        + "description VARCHAR(255) NOT NULL)";

    String dropDatabase = "DROP DATABASE " + dbname;

    public ConnexionService() throws SQLException {

        //on parcours les properties chargées pour les placer dans les attributs properties
        Properties properties = getDbProperties();
        JdbcDriver = properties.getProperty("jdbc.driver.class");
        URL = properties.getProperty("jdbc.url");
        dbname = properties.getProperty("jdbc.dbname");
        username = properties.getProperty("jdbc.username");

        String pass = properties.getProperty("jdbc.password");

        //si pas de password necessaire,
        // la valeur renvoyée par getProperty sera Null, qui fera bugger le sql, je remplace alors le Null par une String vide.
        if( pass != null ) {
            password = pass;
        } else { password = ""; }

            //je renseigne ici les constantes, parce qu'il faut les completer avec le db.properties
        createDatabase = "CREATE DATABASE IF NOT EXISTS " + dbname + " CHARACTER SET utf8;";
        useDatabase = "use " + dbname;
        dropDatabase = "DROP DATABASE " + dbname;

        databaseConnection = DriverManager.getConnection(URL + "?allowPublicKeyRetrieval=true&useSSL=false", username, password);
        statement = databaseConnection.createStatement();
    }
    /*
     la methode getDatabaseConnection a besoin de ppointer vers la table de données
     // les controller en ont besoin dans le mainController
     */
    public Connection getDatabaseConnection() throws SQLException {
       //return DriverManager.getConnection("jdbc:mysql://localhost:3306/tpjdbc?useSSL=false", "root", "");
       return DriverManager.getConnection(URL + "/" + dbname + "?allowPublicKeyRetrieval=true&useSSL=false", username, password);
    }
    public void initDatabase(String[] args){
        try{
            Class.forName(JdbcDriver);

            //ici il faut se connecter sans pointer vers la bdd tpjdbc parce qu'elle n'existe pas encore
       //     databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");
            databaseConnection = DriverManager.getConnection(URL + "?allowPublicKeyRetrieval=true&useSSL=false", username, password);
         //   databaseConnection = this.getDatabaseConnection();

            statement = databaseConnection.createStatement();
            
            System.out.println("Database creation...");
            statement.executeUpdate(createDatabase);
            System.out.println("Database successfully created");
            System.out.println();

            System.out.println("Use of database...");
            statement.executeUpdate(useDatabase);
            System.out.println();

            System.out.println("Tables creation ...");
            statement.executeUpdate(createTableUser);
            statement.executeUpdate(createTableClient);
            statement.executeUpdate(createTableSupplier);
            statement.executeUpdate(createTableItem);
            System.out.println("Tables successfully created");
            System.out.println();

            Arrays.stream(args).forEach(arg -> {
                if (arg.equals("with_dataset")) {
                    this.loadDataset( databaseConnection );
                }
                //add more args for init database here, in another if
            });
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }

    public void deleteDatabase(){
        try{
            System.out.println("Delete database...");
            statement.executeUpdate(dropDatabase);
            System.out.println("Database successfully deleted");
            System.out.println();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    /**
     * method reading file db.properties to return a properties array (key-value)
     * @return
     */
    private static Properties getDbProperties() {
        Properties properties = new Properties();
        //FileInputStream object for reading the file
        try (FileInputStream fis = new FileInputStream("db.properties")){
            properties.load(fis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }

    public void loadDataset(Connection dbConnection) {
        System.out.println("Dataset injection...");

        UserDao userDao = new UserDao( dbConnection );
        userDao.loadUserDataSet();

    }
}

