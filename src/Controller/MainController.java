package Controller;

import Dao.ItemDao;
import Dao.UserDao;
import Service.ConnexionService;
import Service_Impl.SupplierServiceImpl;
import Service.impl.ItemServiceImpl;
import Service.impl.UserServiceImpl;
import Service.impl.ClientServiceImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class MainController {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean run = true;
    private static ClientController clientController;
    private static ItemController itemController;
    private static SupplierController supplierController;
    private static UserController userController;

    public static void init(String[] args) {
        try {
            //Creation objet ConnexionService
            ConnexionService connexionService = new ConnexionService();
            //appel méthode qui crée la bdd et les tables et éventuellement injecter les données avec le dataset
            connexionService.initDatabase(args);

            Class.forName(ConnexionService.JdbcDriver);

            Connection databaseConnection;

            //    databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");

            //on demande au ConnectionService de nous donner la connexion à la bdd
            databaseConnection = connexionService.getDatabaseConnection();

            //Pour chaque controller on va les instancier et ont leurs donnent ce dont ils ont besoin
            //les services respectifs qui eux-meme ont besoin de la connexion à la bdd
            clientController = new ClientController(new ClientServiceImpl(databaseConnection));
            itemController = new ItemController(new ItemServiceImpl(new ItemDao(databaseConnection)));
            supplierController = new SupplierController(new SupplierServiceImpl(databaseConnection));
            userController = new UserController(new UserServiceImpl(new UserDao(databaseConnection)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

        public static void select(String[] args ){
            try {
                while (run) {
                    System.out.println();
                    System.out.println("------------------------");
                    System.out.println("1. Items");
                    System.out.println("2. Clients");
                    System.out.println("3. Suppliers");
                    System.out.println("4. Users");
                    System.out.println("0. Exit");
                    System.out.println("------------------------");
                    System.out.println();

                    System.out.print("Selection : ");
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
                            System.out.println("Thanks, see you !");
                            run = false;
                            break;
                        default:
                            System.out.println("Wrong choice. Please retry.");
                    }
                }

                //checks args,
                Arrays.stream(args).forEach(arg -> {
                    if (arg.equals("with_dataset")) { //for remove dataset to database.
                        ConnexionService connexionService = null;
                        try {
                            connexionService = new ConnexionService();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        connexionService.deleteDatabase();
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
