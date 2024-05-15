package dao;

import connection.ConnectionFactory;
import model.Order;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class that implements the database operations for the order table -- extends AbstractDAO class
 * @author Adrian Baraian
 * @see AbstractDAO
 */

public class OrderDAO extends AbstractDAO<Order>{
    /**
     * The logger for the OrderDAO class
     */
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

    /**
     * Default Constructor for the OrderDAO class
     */
    public OrderDAO() {
    }



}
