package bll;

import bll.validators.PriceValidator;
import bll.validators.QuantityValidator;
import dao.ProductDAO;
import model.Product;

/**
 * Class that handles the business logic of the product entity
 * @author Adrian Baraian
 */
public class ProductBLL {
    /**
     * The DAO object that handles the product entity
     */
    private ProductDAO productDAO;
    /**
     * The validator object that validates the quantity of a product
     */
    private QuantityValidator quantityValidator;
    /**
     * The validator object that validates the price of a product
     */
    private PriceValidator priceValidator;

    /**
     * Constructor for the ProductBLL class
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
        quantityValidator = new QuantityValidator();
        priceValidator = new PriceValidator();
    }

    /**
     * Method that inserts a product in the database
     * @param p the product object to be inserted
     * @return the ID of the inserted product (-1 in case of duplicate) or -2 if the quantity is invalid or -3 if the price is invalid
     */
    public int insertProduct(Product p) {
        if(!quantityValidator.validate(p)) {
            return -2;
        }
        if(!priceValidator.validate(p)) {
            return -3;
        }
        return productDAO.insert(p);
    }

    /**
     * Method that updates a product in the database
     * @param p the product object to be updated
     * @return the ID of the updated product (-1 in case of duplicate) or -2 if the quantity is invalid or -3 if the price is invalid
     */
    public int updateProduct(Product p) {
        if (!quantityValidator.validate(p)) {
            return -2;
        }
        if (!priceValidator.validate(p)) {
            return -3;
        }
        return productDAO.update(p);
    }

    /**
     * Method that deletes a product from the database
     * @param productID the ID of the product to be deleted
     */
    public void deleteProduct(int productID) {
        productDAO.delete(productID);
    }

    /**
     * Method that creates a product object from a string array
     * @param values the string array from which the object is created
     * @return the product object
     */
    public Product createProductObject(String[] values) {
        return productDAO.createObject(values);
    }

}
