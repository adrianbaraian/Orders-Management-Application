package presentation.loginvalidator;

/**
 * Class that validates the admin login credentials
 * @author Adrian Baraian
 */
public class AdminLoginValidate {
    /**
     * Method that validates the admin login credentials
     * @param username the username to be validated
     * @param password the password to be validated
     * @return true if the credentials are valid, false otherwise
     */
    public boolean validate(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
