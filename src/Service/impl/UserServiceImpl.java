package TpJDBC.src.Service.impl;

import TpJDBC.src.Dao.UserDao;
import TpJDBC.src.Entity.User;
import TpJDBC.src.Exception.UserAlreadyExistsException;
import TpJDBC.src.Service.contract.IUserService;


import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {


    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(Long id){
        return userDao.getUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void addUser(User user) throws SQLException, UserAlreadyExistsException {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteUser(id);
    }


}
