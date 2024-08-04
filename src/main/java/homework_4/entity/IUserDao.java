package homework_4.entity;

import java.util.ArrayList;

public interface IUserDao {
    void addUser(User user);

    User getUserById(Long id);

    ArrayList<User> getAllUsers();

    void deleteUserById(Long id);

    void deleteAllUsers();
}
