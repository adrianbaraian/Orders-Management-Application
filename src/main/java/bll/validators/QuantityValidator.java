package bll.validators;

import model.Product;

/**
 * Class that validates the price of a product
 * @author Adrian Baraian
 */
public class QuantityValidator implements Validator<Product>{
    /**
     * Method that validates the quantity of a product
     * @param p the product to be validated
     * @return true if the quantity is valid, false otherwise
     */
    public boolean validate(Product p) {
        if(p == null) {
            return false;
        }
        return p.getQuantity() >= 0;
    }
}
