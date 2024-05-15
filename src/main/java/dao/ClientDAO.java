package dao;

import model.Client;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

/**
 * Class that implements the database operations for the client table -- extends AbstractDAO class
 * @author Adrian Baraian
 * @see AbstractDAO
 */
public class ClientDAO extends AbstractDAO<Client>{
    /**
     * The logger for the ClientDAO class
     */
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());

    /**
     * Default Constructor for the ClientDAO class
     */
    public ClientDAO() {
    }


}
