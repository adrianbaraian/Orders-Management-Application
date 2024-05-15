package presentation.views;

import dao.ProductDAO;
import model.Product;
import presentation.controllers.ProductController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;

/**
 * View class for the product table
 * @author Adrian Baraian
 */
public class ProductView extends JFrame{
    private JPanel fieldsPanel;
    private JTextField[] fields;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    private ProductDAO dao = new ProductDAO();
    ProductController controller = new ProductController(this);

    /**
     * Constructor for the ProductView class
     * @param name the name of the view
     */
    public ProductView(String name) {
        super(name);
        this.tableModel = new DefaultTableModel(dao.getTableData(), dao.getTableHeader());
        this.table = new JTable(tableModel);

        prepareView();
    }

    /**
     * Method that prepares the product page
     */
    public void prepareView() {
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.contentPane = new JPanel();
        this.contentPane.setLayout(new BoxLayout(this.contentPane, BoxLayout.Y_AXIS));
        this.contentPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.setContentPane(this.contentPane);

        this.fieldsPanel = new JPanel(new GridLayout(Product.class.getDeclaredFields().length,1));

        this.fields = new JTextField[Product.class.getDeclaredFields().length - 1];
        int count = 0;

        for(Field field : Product.class.getDeclaredFields()) {
            if(field.getName().equals("id")) {
                continue;
            }
            JTextField textField = new JTextField();
            textField.setBorder(BorderFactory.createTitledBorder(field.getName()));
            this.fields[count++] = textField;
            this.fieldsPanel.add(textField);
        }

        this.contentPane.add(this.fieldsPanel);
        this.table.addMouseListener(controller);
        this.contentPane.add(new JScrollPane(this.table));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.editButton = new JButton("Edit");
        this.deleteButton = new JButton("Delete");
        this.addButton = new JButton("Add");

        this.addButton.setActionCommand("ADD");
        this.editButton.setActionCommand("EDIT");
        this.deleteButton.setActionCommand("DELETE");

        this.addButton.addActionListener(controller);
        this.editButton.addActionListener(controller);
        this.deleteButton.addActionListener(controller);

        this.editButton.setEnabled(false);

        buttonPanel.add(this.addButton);
        buttonPanel.add(this.editButton);
        buttonPanel.add(this.deleteButton);


        this.contentPane.add(buttonPanel);

    }

    /**
     * Method that updates the table
     */
    public void updateTable() {
        tableModel.setDataVector(dao.getTableData(), dao.getTableHeader());
    }
    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField[] getFields() {
        return fields;
    }

    public JButton getEditButton() {
        return editButton;
    }

    /**
     * Method that shows a popup with a message
     * @param message the message to be shown
     */
    public static void showPopup(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
