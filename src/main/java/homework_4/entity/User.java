package homework_4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String username;

    @Override
    public String toString() {
        return "Пользователь id = " + id + ", username = " + username;
    }
}
