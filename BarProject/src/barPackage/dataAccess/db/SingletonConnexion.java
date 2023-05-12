package barPackage.dataAccess.db;

import barPackage.exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection connection;

    public static Connection getConnection() throws ConnectionException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barproject",
                        "root",
                        "root");
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new ConnectionException("Impossible de se connecter à la base de données");
            }
        }
        return connection;
    }

    public static void CloseConnection() throws ConnectionException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionException("Impossible de se déconnecter de la base de données");
            }
        }
    }
}
