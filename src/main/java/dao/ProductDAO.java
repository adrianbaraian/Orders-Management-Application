package dao;

import model.Product;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class that implements the database operations for the product table -- extends AbstractDAO class
 * @author Adrian Baraian
 * @see AbstractDAO
 */
public class ProductDAO extends AbstractDAO<Product>{
    /**
     * The logger for the ProductDAO class
     */
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

    /**
     * Default Constructor for the ProductDAO class
     */
    public ProductDAO() {
    }


}
