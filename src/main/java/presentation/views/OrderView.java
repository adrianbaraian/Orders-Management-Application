package presentation.views;

import dao.AbstractDAO;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;
import presentation.controllers.OrderController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * View class for the order table
 * @author Adrian Baraian
 */
public class OrderView extends JFrame {
    private JPanel contentPane;
    private JPanel contentPaneOrder;
    private JTable tableClient;
    private DefaultTableModel tableModelClient;
    private JTable tableProduct;
    private DefaultTableModel tableModelProduct;
    private JSpinner spinnerQuantity;

    private ClientDAO clientDAO = new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private JButton addButton;
    private JButton viewButton;
    private JTable tableOrder;
    private DefaultTableModel tableModelOrder;

    OrderController orderController = new OrderController(this);

    /**
     * Constructor for the OrderView class
     * @param name the name of the view
     */
    public OrderView(String name) {
        super(name);

        this.tableModelClient = new DefaultTableModel(clientDAO.getTableData(), clientDAO.getTableHeader());
        this.tableClient = new JTable(this.tableModelClient);

        this.tableModelProduct = new DefaultTableModel(productDAO.getTableData(), productDAO.getTableHeader());
        this.tableProduct = new JTable(this.tableModelProduct);

        prepareView();
    }

    /**
     * Method that prepares the order page
     */
    private void prepareView() {
        setSize(1280, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        this.contentPane.add(new JScrollPane(this.tableClient));
        this.contentPane.add(new JScrollPane(this.tableProduct));

        this.spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));

        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        quantityPanel.add(new JLabel("Quantity: "));
        quantityPanel.add(this.spinnerQuantity);
        this.contentPane.add(quantityPanel);

        this.addButton = new JButton("Add Order");
        this.addButton.setActionCommand("ADD");
        this.addButton.addActionListener(orderController);

        this.viewButton = new JButton("View Orders");
        this.viewButton.setActionCommand("VIEW");
        this.viewButton.addActionListener(orderController);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(this.addButton);

        buttonPanel.add(this.viewButton);

        this.contentPane.add(buttonPanel);

        setVisible(true);
    }

    /**
     * Method that prepares the view orders page
     */
    public void viewOrders() {
        OrderView orderView = new OrderView("Orders");
        orderView.setSize(1280, 720);
        orderView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderView.setVisible(true);

        this.contentPaneOrder = new JPanel();
        this.contentPaneOrder.setLayout(new BoxLayout(this.contentPaneOrder, BoxLayout.Y_AXIS));
        this.contentPaneOrder.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        orderView.setContentPane(this.contentPaneOrder);

        this.tableModelOrder = new DefaultTableModel(orderDAO.getTableData(), orderDAO.getTableHeader());
        this.tableOrder = new JTable(this.tableModelOrder);
        this.contentPaneOrder.add(new JScrollPane(this.tableOrder));

    }

    /**
     * Method that updates the product table
     */
    public void updateProductTable() {
        this.tableModelProduct.setDataVector(productDAO.getTableData(), productDAO.getTableHeader());
    }

    public JTable getTableClient() {
        return tableClient;
    }

    public JTable getTableProduct() {
        return tableProduct;
    }

    public JSpinner getSpinnerQuantity() {
        return spinnerQuantity;
    }

    public DefaultTableModel getTableModelClient() {
        return tableModelClient;
    }

    public DefaultTableModel getTableModelProduct() {
        return tableModelProduct;
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    /**
     * Method that shows a popup with a message
     * @param message the message to be shown
     */
    public static void showPopup(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
