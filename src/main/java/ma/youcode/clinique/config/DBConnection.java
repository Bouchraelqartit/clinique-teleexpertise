package ma.youcode.clinique.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    private static final DataSource dataSource;

    static {
        try {
            InitialContext context = new InitialContext();

            dataSource = (DataSource) context.lookup(
                    "java:comp/env/jdbc/clinique_teleexpertise"
            );

        } catch (NamingException e) {
            throw new RuntimeException("Impossible de récupérer le DataSource", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}