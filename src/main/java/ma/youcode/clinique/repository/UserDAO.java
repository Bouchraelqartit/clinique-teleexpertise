package ma.youcode.clinique.repository;

import java.sql.SQLException;

import ma.youcode.clinique.entity.User;

public interface UserDAO {

    User findByUsername(String username) throws SQLException;
}