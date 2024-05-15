package bll;

import bll.validators.EmailValidator;
import dao.ClientDAO;
import model.Client;

/**
 * Class that handles the business logic of the client entity
 * @author Adrian Baraian
 */

public class ClientBLL {
    /**
     * The DAO object that handles the client entity
     */
    private ClientDAO clientDAO;
    /**
     * The validator object that validates the email of a client
     */
    private EmailValidator emailValidator;

    /**
     * Constructor for the ClientBLL class
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
        emailValidator = new EmailValidator();
    }

    /**
     * Method that inserts a client in the database
     * @param c the client object to be inserted
     * @return the ID of the inserted client (-1 in case of duplicate) or -2 if the email is invalid
     */
    public int insertClient(Client c) {
        if (emailValidator.validate(c)) {
            return clientDAO.insert(c);
        }
        return -2;
    }

    /**
     * Method that updates a client in the database
     * @param c the client object to be updated
     * @return the ID of the updated client (-1 in case of duplicate) or -2 if the email is invalid
     */
    public int updateClient(Client c) {
        if (emailValidator.validate(c)) {
            return clientDAO.update(c);
        }
        return -2;
    }

    /**
     * Method that deletes a client from the database
     * @param clientID the ID of the client to be deleted
     */
    public void deleteClient(int clientID) {
        clientDAO.delete(clientID);
    }


    /**
     * Method that creates a client object from a string array
     * @param values the string array from which the object is created
     * @return the client object
     */
    public Client createClientObject(String[] values) {
        return clientDAO.createObject(values);
    }
}
