package presentation.controllers;

import bll.ClientBLL;
import bll.ProductBLL;
import dao.AbstractDAO;
import model.Client;
import model.Product;
import presentation.views.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller class for the product view
 * @author Adrian Baraian
 * @see ProductView
 */
public class ProductController implements ActionListener, MouseListener {
    /**
     * The view that the controller interacts with
     */
    private ProductView view;

    /**
     * Constructor for the ProductController class
     * @param view the view that the controller interacts with
     */
    public ProductController(ProductView view) {
        this.view = view;
    }

    /**
     * Method that listens for actions performed on the view namely presses of add, edit and delete buttons
     * @param e the event that was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("ADD")) {
            JTextField[] fields = this.view.getFields();
            String[] values = new String[fields.length + 1];
            values[0] = "0";
            for(int i = 0; i < fields.length; i++) {
                if(fields[i].getText().isEmpty()) {
                    this.view.showPopup("Please fill all the fields");
                    return;
                }
                values[i + 1] = fields[i].getText();
            }

            ProductBLL productBLL = new ProductBLL();

            Product product = productBLL.createProductObject(values);

            int res = productBLL.insertProduct(product);

            if(res == -1) {
                this.view.showPopup("Product already exists");
                return;
            } else if(res == -2 || res == -3) {
                this.view.showPopup("Invalid quantity or price");
                return;
            }

            this.view.showPopup("Added successfully");
            view.updateTable();

        } else if (action.equals("EDIT")) {
            DefaultTableModel tM = view.getTableModel();
            Object[] rowData = new Object[tM.getColumnCount()];
            for(int col = 0; col < tM.getColumnCount(); col++) {
                if(view.getTable().getSelectedRow() == -1) {
                    this.view.showPopup("Please select a row");
                    return;
                }

                rowData[col] = tM.getValueAt(view.getTable().getSelectedRow(), col);
            }
            int id = Integer.parseInt(rowData[0].toString());

            ProductBLL productBLL = new ProductBLL();

            JTextField[] textFields = this.view.getFields();
            String[] values = new String[textFields.length + 1];
            values[0] = Integer.toString(id);
            int i = 1;
            for(JTextField textField : textFields) {
                if(textField.getText().isEmpty()) {
                    this.view.showPopup("Please fill all the fields");
                    return;
                }

                values[i++] = textField.getText();
            }

            Product product = productBLL.createProductObject(values);

            int res = productBLL.updateProduct(product);

            if(res == -1) {
                this.view.showPopup("Product already exists");
                return;
            } else if(res == -2 || res == -3) {
                this.view.showPopup("Invalid quantity or price");
                return;
            }


            this.view.showPopup("Updated successfully");

            view.updateTable();
            this.view.getEditButton().setEnabled(false);



        } else if(action.equals("DELETE")) {
            DefaultTableModel tM = view.getTableModel();
            Object[] rowData = new Object[tM.getColumnCount()];
            for(int col = 0; col < tM.getColumnCount(); col++) {
                if(view.getTable().getSelectedRow() == -1) {
                    this.view.showPopup("Please select a row");
                    return;
                }


                rowData[col] = tM.getValueAt(view.getTable().getSelectedRow(), col);
            }
            int id = Integer.parseInt(rowData[0].toString());

            ProductBLL productBLL = new ProductBLL();

            productBLL.deleteProduct(id);

            this.view.showPopup("Deleted successfully");

            view.updateTable();
            this.view.getEditButton().setEnabled(false);
        }
    }

    /**
     * Method that listens for mouse clicks on the table
     * @param e the event that was performed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = view.getTable().rowAtPoint(e.getPoint());
        if(row >= 0) {
            this.view.getEditButton().setEnabled(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
