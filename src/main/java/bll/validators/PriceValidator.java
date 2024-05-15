package bll.validators;

import model.Product;


/**
 * Class that validates the price of a product
 * @author Adrian Baraian
 */
public class PriceValidator implements Validator<Product>{

    /**
     * Method that validates the price of a product
     * @param p the product to be validated
     * @return true if the price is valid, false otherwise
     */
    public boolean validate(Product p) {
        if(p == null) {
            return false;
        }
        return p.getPriceperunit() >= 0;
    }
}
