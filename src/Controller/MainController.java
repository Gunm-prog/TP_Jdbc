package Controller;

import Dao.ItemDao;
import Dao.UserDao;
import Service.ConnexionService;
import Controller.SupplierController;
import Service_Impl.SupplierServiceImpl;
import Controller.ItemController;
import Service.impl.ItemServiceImpl;
import Controller.UserController;
import Service.impl.UserServiceImpl;
import Service.ConnexionService;
import Service.impl.ClientServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class MainController {
    private static Scanner scanner = new Scanner(System.in); 
    private static boolean run = true;
    private static ClientController clientController;
    private static ItemController itemController;
    private static SupplierController supplierController;
    private static UserController userController;
    
    public static void init(){
        try{
            ConnexionService connexionService = new ConnexionService();
            connexionService.initDatabase();
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection databaseConnection;

            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");

            clientController = new ClientController(new ClientServiceImpl(databaseConnection));
            itemController = new ItemController(new ItemServiceImpl(new ItemDao(databaseConnection)));
            supplierController = new SupplierController(new SupplierServiceImpl(databaseConnection));
            userController = new UserController(new UserServiceImpl(new UserDao(databaseConnection)));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void select(){
        System.out.println("test");
        try{
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
