package Service.contract;

import Entity.User;
import Exception.UserAlreadyExistsException;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {

    List<User> findAll();

    User getById(Long id);

    void  addUser (User user) throws SQLException, UserAlreadyExistsException;

    void updateUser(User user);

    void deleteById(Long id);

}
