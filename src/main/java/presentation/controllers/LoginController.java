package presentation.controllers;

import presentation.views.IndexView;
import presentation.loginvalidator.AdminLoginValidate;
import presentation.views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the login view
 * @author Adrian Baraian
 * @see LoginView
 */
public class LoginController implements ActionListener {
    /**
     * The view that the controller interacts with
     */
    private final LoginView loginView;
    private final AdminLoginValidate adminLoginValidate = new AdminLoginValidate();

    /**
     * Constructor for the LoginController class
     * @param loginView the view that the controller interacts with
     */
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Method that listens for actions performed on the view namely presses of login button
     * @param e the event that was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = loginView.getUsernameField().getText();
        String password = String.valueOf(loginView.getPasswordField().getPassword());

        if (adminLoginValidate.validate(username, password)) {
            new IndexView("Index Page");
            this.loginView.setVisible(false);
        } else {
            this.loginView.showPopup("Invalid username or password");
        }
    }
}
