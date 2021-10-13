package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("John", "Dow", (byte) 51);
        userService.saveUser("Ben", "Smith", (byte) 34);
        userService.saveUser("Jimm", "Simmons", (byte) 29);
        userService.saveUser("Lloyd", "Christmas", (byte) 47);

        userService.removeUserById(2);

        List<User> users = userService.getAllUsers();
        for (User item : users) {
            System.out.println(item.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
