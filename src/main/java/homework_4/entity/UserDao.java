package homework_4.entity;

import homework_4.connection.DataSource;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class UserDao implements IUserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(id, username) VALUES (?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id=?;";
    private static final String READ_ALL_USER_QUERY = "SELECT * FROM users;";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";
    private static final String DELETE_ALL_USER_QUERY = "TRUNCATE users";

    public void addUser(@NotNull User user) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_USER_QUERY)) {
            pst.setLong(1, user.getId());
            pst.setString(2, user.getUsername());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(@NotNull Long id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_USER_QUERY)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            ArrayList<User> users = getUsersFromResultSet(resultSet);
            if (users.size() != 0) {
                return users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("В таблице нет пользователя с id = " + id);
        return null;
    }

    public ArrayList<User> getAllUsers() {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(READ_ALL_USER_QUERY)) {
            ResultSet resultSet = pst.executeQuery();
            ArrayList<User> users = getUsersFromResultSet(resultSet);
            if (users.size() != 0) {
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ошибка получения списка пользователей");
        return null;
    }

    public void deleteUserById(@NotNull Long id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_USER_QUERY)) {
            pst.setLong(1, id);
            int deletedUsers = pst.executeUpdate();
            if (deletedUsers == 0) {
                System.out.println("В базе нет пользователя с id = " + id);
            }
            System.out.println("Пользователь с id = " + id + " удалён успешно");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAllUsers() {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_ALL_USER_QUERY)) {
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<User> getUsersFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<User> usersById = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            User user = new User(id, username);
            usersById.add(user);
        }
        return usersById;
    }

}
