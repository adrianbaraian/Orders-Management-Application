package model;

/**
 * Model class for the order table
 * @author Adrian Baraian
 */
public class Order {
    private int id;
    private int idclient;
    private int idproduct;
    private int quantity;
    private double price;

    /**
     * Default constructor for the Order class
     */
    public Order() {
    }

    /**
     * Constructor for the Order class
     * @param id the ID of the order
     * @param idclient the ID of the client that made the order
     * @param idproduct the ID of the product that was ordered
     * @param quantity the quantity of the product that was ordered
     * @param price the price of the order
     */
    public Order(int id, int idclient, int idproduct, int quantity, double price) {
        super();
        this.id = id;
        this.idclient = idclient;
        this.idproduct = idproduct;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Constructor for the Order class
     * @param idclient the ID of the client that made the order
     * @param idproduct the ID of the product that was ordered
     * @param quantity the quantity of the product that was ordered
     * @param price the price of the order
     */
    public Order(int idclient, int idproduct, int quantity, double price) {
        super();
        this.idclient = idclient;
        this.idproduct = idproduct;
        this.quantity = quantity;
        this.price = price;
    }


    /**
     * Method that gets the ID of the order
     * @return the ID of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Method that sets the ID of the order
     * @param id the ID of the order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method that gets the ID of the client that made the order
     * @return the ID of the client that made the order
     */
    public int getIdclient() {
        return idclient;
    }

    /**
     * Method that sets the ID of the client that made the order
     * @param idClient the ID of the client that made the order
     */
    public void setIdclient(int idClient) {
        this.idclient = idClient;
    }

    /**
     * Method that gets the ID of the product that was ordered
     * @return the ID of the product that was ordered
     */
    public int getIdproduct() {
        return idproduct;
    }

    /**
     * Method that sets the ID of the product that was ordered
     * @param idProduct the ID of the product that was ordered
     */
    public void setIdproduct(int idProduct) {
        this.idproduct = idProduct;
    }

    /**
     * Method that gets the quantity of the product that was ordered
     * @return the quantity of the product that was ordered
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method that sets the quantity of the product that was ordered
     * @param quantity the quantity of the product that was ordered
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method that sets the price of the order
     * @param price the price of the order
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method that gets the price of the order
     * @return the price of the order
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method that gets the string representation of the order
     * @return the string representation of the order
     */
    @Override
    public String toString() {
        return "Order [id=" + id + ", id_client=" + idclient + ", id_product=" + idproduct + ", quantity=" + quantity + ", price" + price + "]";
    }


}
