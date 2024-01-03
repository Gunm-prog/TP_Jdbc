package TpJDBC.src.Controller;

import TpJDBC.src.Service.ConnexionService;
import TpJDBC.src.Controller.SupplierController;
import TpJDBC.src.Service.impl.SupplierServiceImpl;
import TpJDBC.src.Controller.ItemController;
import TpJDBC.src.Service.impl.ItemServiceImpl;
import TpJDBC.src.Controller.UserController;
import TpJDBC.src.Dao.ItemDao;
import TpJDBC.src.Dao.UserDao;
import TpJDBC.src.Service.impl.UserServiceImpl;
import TpJDBC.src.Service.ConnexionService;
import TpJDBC.src.Service.impl.ClientServiceImpl;
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

            clientController = new ClientController(new ClientServiceImpl(connexionService.getDatabaseConnection()));
            itemController = new ItemController(new ItemServiceImpl(new ItemDao(connexionService.getDatabaseConnection())));
            supplierController = new SupplierController(new SupplierServiceImpl(connexionService.getDatabaseConnection()));
            userController = new UserController(new UserServiceImpl(new UserDao(connexionService.getDatabaseConnection())));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void select(){
        while (run) {
            try{
                System.out.println();
                System.out.println("----------------");
                System.out.println("1. Items");
                System.out.println("2. Clients");
                System.out.println("3. Suppliers");
                System.out.println("4. Users");
                System.out.println("0. Exit");
                System.out.println("----------------");
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
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            }catch(Exception e){
                System.out.println(e);
                scanner.nextLine(); 
            } 
        }
        
    }
    
}
