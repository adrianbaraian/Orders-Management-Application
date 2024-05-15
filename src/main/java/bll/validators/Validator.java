package bll.validators;

/**
 * Interface that validates an object of type T
 * @param <T> the type of the object to be validated
 */
public interface Validator<T> {
    /**
     * Method that validates an object of type T
     * @param t the object to be validated
     * @return true if the object is valid, false otherwise
     */
    public boolean validate(T t);
}

