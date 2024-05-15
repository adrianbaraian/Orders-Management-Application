package dao;

import connection.ConnectionFactory;
import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the database operations for the bill table
 * @author Adrian Baraian
 */
public class BillDAO {
    /**
     * Default Constructor for the BillDAO class
     */
    public BillDAO() {
    }

    /**
     * Method that creates a query to select all the bills from the database
     * @return the query to select all the bills
     */
    private String createSelectBillsQuery() {
        return "SELECT * FROM bill";
    }

    /**
     * Method that creates a query to insert a bill into the database
     * @return the query to insert a bill
     */
    private String createInsertBillQuery() {
        return "INSERT INTO bill (idorder, price) VALUES (?, ?)";
    }

    /**
     * Method that finds all the bills from the database
     * @return a list with all the bills from the database
     */
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectBillsQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idorder = resultSet.getInt("idorder");
                double price = resultSet.getDouble("price");
                Bill bill = new Bill(id, idorder, price);
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return bills;
    }

    /**
     * Method that inserts a bill into the database
     * @param bill the bill to be inserted
     * @return the id of the inserted bill
     */
    public int insert(Bill bill) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertBillQuery();
        int insertedId = -1;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bill.idorder());
            statement.setDouble(2, bill.price());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                insertedId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return insertedId;
    }
}
