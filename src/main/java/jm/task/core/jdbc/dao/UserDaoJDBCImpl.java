package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = null;

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users "
                    + "(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void dropUsersTable() {
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection = Util.getConnection();
            PreparedStatement state = connection.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)");
            state.setString(1, name);
            state.setString(2, lastName);
            state.setByte(3, age);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        try {
            connection = Util.getConnection();
            PreparedStatement state = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            state.setLong(1, id);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            connection = Util.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("last_name"),
                                    rs.getByte("age"));
                user.setId(rs.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}