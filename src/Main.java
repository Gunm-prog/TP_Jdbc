package TpJDBC.src;


import TpJDBC.src.ConnexionService;
import TpJDBC.src.Controller.ClientController;
import TpJDBC.src.Service_Impl.ClientServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        
        try{
            ConnexionService connexionService = new ConnexionService();
            connexionService.initDatabase();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection databaseConnection;

            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");

            ClientController clientController = new ClientController(new ClientServiceImpl(databaseConnection));

            while (run) {
                System.out.println();
                System.out.println("------------------------");
                System.out.println("1. Menu Article");
                System.out.println("2. Menu Client");
                System.out.println("3. Menu Fournisseur");
                System.out.println("4. Menu Utilisateur");
                System.out.println("0. Quitter");
                System.out.println("------------------------");
                System.out.println();

                System.out.print("Choix : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        clientController.userChoice();
                        break;
                    case 2:
                        clientController.userChoice();
                        break;
                    case 3:
                        clientController.userChoice();
                        break;
                    case 4:
                        clientController.userChoice();
                        break;
                    case 0:
                        System.out.println("Retour au menu principal.");
                        run = false;
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        } 
    }
}