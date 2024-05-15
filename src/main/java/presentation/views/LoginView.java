package presentation.views;

import presentation.controllers.LoginController;

import javax.swing.*;
import java.awt.*;

/**
 * View class for the login page
 * @author Adrian Baraian
 */
public class LoginView extends JFrame {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;


    LoginController loginController = new LoginController(this);
    public LoginView(String name) {
        super(name);
        this.prepareLoginPage();
    }

    /**
     * Method that prepares the login page
     */
    private void prepareLoginPage() {
        this.setSize(800, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.contentPane = new JPanel(new GridLayout(3, 1));
        this.contentPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.setContentPane(this.contentPane);

        JPanel usernamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();

        usernamePanel.setLayout(new GridLayout(3, 1));

        JPanel tempPanel1 = new JPanel();
        JPanel tempPanel2 = new JPanel();
        JPanel tempPanel3 = new JPanel();

        this.usernameField = new JTextField();
        this.usernameField.setPreferredSize(new Dimension(300, 50));
        this.usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        usernamePanel.add(tempPanel1);
        tempPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel3.add(this.usernameField);
        usernamePanel.add(tempPanel3);
        usernamePanel.add(tempPanel2);
        this.contentPane.add(usernamePanel);


        passwordPanel.setLayout(new GridLayout(3, 1));

        JPanel tempPanel4 = new JPanel();
        JPanel tempPanel5 = new JPanel();
        JPanel tempPanel6 = new JPanel();

        this.passwordField = new JPasswordField();
        this.passwordField.setPreferredSize(new Dimension(300, 50));
        this.passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        tempPanel6.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel6.add(this.passwordField);
        passwordPanel.add(tempPanel6);

        passwordPanel.add(tempPanel4);
        passwordPanel.add(tempPanel5);
        this.contentPane.add(passwordPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.loginButton = new JButton("Login");
        this.loginButton.setActionCommand("LOGIN");
        this.loginButton.addActionListener(this.loginController);
        this.loginButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(this.loginButton);

        this.contentPane.add(buttonPanel);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    /**
     * Method that shows a popup with a message
     * @param message the message to be shown
     */
    public static void showPopup(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
