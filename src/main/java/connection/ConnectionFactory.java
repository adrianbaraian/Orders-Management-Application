package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that creates a connection to the database
 * @author Adrian Baraian
 */
public class ConnectionFactory {
    /**
     * Logger for the current class
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    /**
     * The driver used to connect to the database
     */
    private static final String DRIVER = "org.postgresql.Driver";
    /**
     * The URL of the database
     */
    private static final String DBURL = "jdbc:postgresql://localhost:5432/OtakuTasticTreasures";
    /**
     * The username used to connect to the database
     */
    private static final String DBUSERNAME = "postgres";
    /**
     * The password used to connect to the database
     */
    private static final String DBPASSWORD = "1234";

    /**
     * The instance of the ConnectionFactory class
     */
    private static ConnectionFactory instance = new ConnectionFactory();


    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that creates a connection to the database
     * @return the connection to the database
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, DBUSERNAME, DBPASSWORD);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Method that returns the connection to the database
     * @return the connection to the database
     */
    public static Connection getConnection() {
        return instance.createConnection();
    }

    /**
     * Method that closes the connection to the database
     * @param connection the connection to be closed
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * Method that closes the statement
     * @param statement the statement to be closed
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Method that closes the ResultSet
     * @param resultSet the ResultSet to be closed
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
