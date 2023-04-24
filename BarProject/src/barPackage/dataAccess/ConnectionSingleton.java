package barPackage.dataAccess;

import barPackage.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private static Connection connection;

    public static Connection getConnection() throws ConnectionException {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                        "root",
                        "root");
            }
        } catch (SQLException e) {
            throw new ConnectionException("Impossible de se connecter à la base de données");
        }
        return connection;
    }
}
