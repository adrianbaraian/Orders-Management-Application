package model;

/**
 * Model class for the client table
 * @author Adrian Baraian
 */
public class Client {
    private int id;
    private String firstname;
    private String lastname;
    private String email;


    /**
     * Default Constructor for the Client class
     */
    public Client() {
    }

    /**
     * Constructor with parameters for the Client class
     * @param id the id of the client
     * @param firstname the first name of the client
     * @param lastname the last name of the client
     * @param email the email of the client
     */
    public Client(int id, String firstname, String lastname, String email) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    /**
     * Constructor with parameters for the Client class
     * @param firstname the first name of the client
     * @param lastname the last name of the client
     * @param email the email of the client
     */
    public Client(String firstname, String lastname, String email) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    /**
     * Method that returns the id of the client
     * @return the id of the client
     */

    public int getId() {
        return id;
    }

    /**
     * Method that sets the id of the client
     * @param id the id of the client
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method that returns the first name of the client
     * @return the first name of the client
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Method that sets the first name of the client
     * @param firstname the first name of the client
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Method that returns the last name of the client
     * @return the last name of the client
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Method that sets the last name of the client
     * @param lastname the last name of the client
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Method that returns the email of the client
     * @return the email of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method that sets the email of the client
     * @param email the email of the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method that returns the string representation of the client
     * @return the string representation of the client
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + "]";
    }
}
