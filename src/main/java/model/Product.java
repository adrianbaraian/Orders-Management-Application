package model;

/**
 * Model class for the product table
 * @author Adrian Baraian
 */

public class Product {
    private int id;
    private String name;
    private String type;
    private String description;
    private double priceperunit;
    private int quantity;

    /**
     * Default constructor for the Product class
     */

    public Product() {
    }

    /**
     * Constructor for the Product class
     * @param id the ID of the product
     * @param name the name of the product
     * @param type the type of the product
     * @param description the description of the product
     * @param priceperunit the price of the product
     * @param quantity the quantity of the product
     */
    public Product(int id, String name, String type, String description, double priceperunit, int quantity) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.priceperunit = priceperunit;
        this.quantity = quantity;
    }


    /**
     * Constructor for the Product class
     * @param name the name of the product
     * @param type the type of the product
     * @param description the description of the product
     * @param priceperunit the price of the product
     * @param quantity the quantity of the product
     */
    public Product(String name, String type, String description, double priceperunit, int quantity) {
        super();
        this.name = name;
        this.type = type;
        this.description = description;
        this.priceperunit = priceperunit;
        this.quantity = quantity;
    }

    /**
     * Method that gets the ID of the product
     * @return the ID of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Method that sets the ID of the product
     * @param id the ID of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method that gets the name of the product
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Method that sets the name of the product
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that gets the type of the product
     * @return the type of the product
     */
    public String getType() {
        return type;
    }

    /**
     * Method that sets the type of the product
     * @param type the type of the product
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method that gets the description of the product
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that sets the description of the product
     * @param description the description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method that gets the price of the product
     * @return the price of the product
     */
    public double getPriceperunit() {
        return priceperunit;
    }

    /**
     * Method that sets the price of the product
     * @param priceperunit the price of the product
     */
    public void setPriceperunit(double priceperunit) {
        this.priceperunit = priceperunit;
    }

    /**
     * Method that gets the quantity of the product
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method that sets the quantity of the product
     * @param quantity the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method that returns a string representation of the product
     * @return the string representation of the product
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", type=" + type + ", description=" + description + ", pricePerUnit=" + priceperunit + ", quantity=" + quantity +  "]";
    }
}
