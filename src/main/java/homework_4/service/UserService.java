package homework_4.service;

import homework_4.entity.IUserDao;
import homework_4.entity.User;

import java.util.ArrayList;

public class UserService {
    private final IUserDao userDao;

    public UserService(IUserDao iUserDao) {
        this.userDao = iUserDao;
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User getUser(Long id) {
        return userDao.getUserById(id);
    }

    public void deleteUser(Long id) {
        userDao.deleteUserById(id);
    }

    public ArrayList<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }
}
