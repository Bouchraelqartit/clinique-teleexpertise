package ma.youcode.clinique.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ma.youcode.clinique.config.DBConnection;
import ma.youcode.clinique.entity.User;

public class JdbcUserDAO implements UserDAO {

    @Override
    public User findByUsername(String username) throws SQLException {

        String sql = """
                SELECT id, username, password, role
                FROM utilisateur
                WHERE username = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    User user = new User();

                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));

                    return user;
                }
            }
        }

        return null;
    }
  
@Override
public void save(User user) throws SQLException {

    String sql = """
            INSERT INTO utilisateur (username, password, role)
            VALUES (?, ?, ?)
            """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getRole());

        statement.executeUpdate();
    }
}


}