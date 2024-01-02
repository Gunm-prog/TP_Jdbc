package TpJDBC.src;


import TpJDBC.src.ConnexionService;
import TpJDBC.src.Controller.ClientController;
import TpJDBC.src.Service_Impl.ClientServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try{
            ConnexionService cs = new ConnexionService();
            cs.initDatabase();
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Hello world2!");
            Connection databaseConnection;

            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");

            ClientController cc = new ClientController(new ClientServiceImpl(databaseConnection));

            cc.userChose();
        
        }catch(Exception e){
            System.out.println(e);
        }
            
        
    }
}