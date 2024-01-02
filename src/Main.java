import Controller.UserController;
import Dao.UserDao;
import Service_Impl.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ConnexionService connexionService = new ConnexionService();

            connexionService.initDatabase();

            UserDao userDao = new UserDao(connexionService.getDatabaseConnection());
            UserServiceImpl userService = new UserServiceImpl(userDao);
            UserController userController = new UserController(userService);

            userController.select();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}