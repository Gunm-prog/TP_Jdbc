import Controller.ItemController;
import Controller.UserController;
import Dao.ItemDao;
import Dao.UserDao;
import Service.contract.IItemService;
import Service.impl.ItemServiceImpl;
import Service.impl.UserServiceImpl;
import Service.contract.IUserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ConnexionService connexionService = new ConnexionService();

            connexionService.initDatabase();

            UserDao userDao = new UserDao(connexionService.getDatabaseConnection());
            IUserService userService = new UserServiceImpl(userDao);
            UserController userController = new UserController(userService);

            userController.select();

            ItemDao itemDao = new ItemDao(connexionService.getDatabaseConnection());
            IItemService itemService = new ItemServiceImpl(itemDao);
            ItemController itemController = new ItemController(itemService);

            itemController.select();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}