import Controller.ClientController;
import Controller.ItemController;
import Controller.SupplierController;
import Controller.UserController;
import Dao.ItemDao;
import Dao.UserDao;
import Service.impl.ClientServiceImpl;
import Service.impl.ItemServiceImpl;
import Service.impl.UserServiceImpl;
import Service_Impl.SupplierServiceImpl;

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

         //   databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");
            databaseConnection =  connexionService.getDatabaseConnection();

            ClientController clientController = new ClientController( new ClientServiceImpl( databaseConnection ) );
            UserController userController = new UserController( new UserServiceImpl( new UserDao( databaseConnection ) ) );
            ItemController itemController = new ItemController( new ItemServiceImpl( new ItemDao( databaseConnection ) ) );
            SupplierController supplierController = new SupplierController( new SupplierServiceImpl( databaseConnection ) );

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
                        itemController.select();
                        break;
                    case 2:
                        clientController.userChoice();
                        break;
                    case 3:
                        supplierController.userChose();
                        break;
                    case 4:
                        userController.select();
                        break;
                    case 0:
                        System.out.println("Retour au menu principal.");
                        run = false;
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez r�essayer.");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}