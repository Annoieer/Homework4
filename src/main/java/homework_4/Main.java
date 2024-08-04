package homework_4;

import homework_4.entity.User;
import homework_4.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        UserService userService = context.getBean(UserService.class);

        User user1 = new User(1L, "User1");
        User user2 = new User(2L, "User2");
        User user3 = new User(3L, "User3");
        User user4 = new User(4L, "User4");

        System.out.println("---------------------------------------------");
        System.out.println("Очищаем таблицу пользователей");
        userService.deleteAllUsers();

        System.out.println("---------------------------------------------");
        System.out.println("Добавляем несколько пользователей");
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);

        System.out.println("---------------------------------------------");
        System.out.println("Выводим список пользователей");
        ArrayList<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user.toString());
        }

        System.out.println("---------------------------------------------");
        System.out.println("Получаем информацию о пользователе с id = 2");
        System.out.println(userService.getUser(2L).toString());

        System.out.println("---------------------------------------------");
        System.out.println("Удаляем пользователя с id = 2");
        userService.deleteUser(2L);

        System.out.println("---------------------------------------------");
        System.out.println("Выводим список пользователей");
        allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user.toString());
        }
        System.out.println("---------------------------------------------");
    }
}
