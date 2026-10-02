package ma.youcode.clinique.service;

import org.mindrot.jbcrypt.BCrypt;

import ma.youcode.clinique.entity.User;
import ma.youcode.clinique.repository.UserDAO;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) throws Exception {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            return null;
        }

        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }

        return null;
    }

    public void createUser( String username, String password, String role) throws Exception { 
        String hashedPassword = BCrypt.hashpw( password, BCrypt.gensalt() );
         User user = new User(); 
         user.setUsername(username);
          user.setPassword(hashedPassword); 
          user.setRole(role);
           userDAO.save(user); }
}