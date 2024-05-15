package model;

/**
 * Record class for the bill table
 * @author Adrian Baraian
 * @param id the id of the bill
 * @param idorder the id of the order
 * @param price the price of the bill
 */
public record Bill (int id, int idorder, double price) {
}
