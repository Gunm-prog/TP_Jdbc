package Service.impl;

import Dao.UserDao;
import Entity.User;
import Exception.UserAlreadyExistsException;
import Service.contract.IUserService;


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
