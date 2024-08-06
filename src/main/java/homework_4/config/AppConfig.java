package homework_4.config;

import homework_4.connection.DataSource;
import homework_4.entity.UserDao;
import homework_4.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }

    @Bean
    public DataSource dataSource() {
        return new DataSource();
    }
}
