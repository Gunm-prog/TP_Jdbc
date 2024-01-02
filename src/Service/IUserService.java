package Service;

import Dao.UserDao;
import Entity.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User getById(Long id);

    void  addUser (User user);

    void updateUser(User user);

    void deleteById(Long id);

}
