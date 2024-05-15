package start;

import presentation.views.LoginView;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Class that starts the application
 * @author Adrian Baraian
 */

public class Start {
    /**
     * The logger for the Start class
     */
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * Constructor for the Start class
     */
    public Start() {
        LoginView loginView = new LoginView("Login Page");
        loginView.setVisible(true);
    }

    /**
     * Main method that starts the application
     * @param args the arguments for the main method
     * @throws SQLException if an SQL exception occurs
     */
    public static void main(String[] args) throws SQLException {
        new Start();
    }
}
