package presentation.controllers;

import dao.BillDAO;
import dao.OrderDAO;
import model.Bill;
import model.Order;
import model.Product;
import presentation.views.OrderView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the order view
 * @author Adrian Baraian
 * @see OrderView
 */

public class OrderController implements ActionListener {
    /**
     * The view that the controller interacts with
     */
    private OrderView view;
    private OrderDAO orderDAO = new OrderDAO();

    /**
     * Constructor for the OrderController class
     * @param view the view that the controller interacts with
     */
    public OrderController(OrderView view) {
        this.view = view;
    }

    /**
     * Method that listens for actions performed on the view namely presses of add and view buttons
     * @param e the event that was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("ADD")) {
            DefaultTableModel tMClient = view.getTableModelClient();
            DefaultTableModel tMProduct = view.getTableModelProduct();

            int selectedClientRow = view.getTableClient().getSelectedRow();
            int selectedProductRow = view.getTableProduct().getSelectedRow();

            if(selectedClientRow == -1 || selectedProductRow == -1) {
                this.view.showPopup("Please select a client and a product");
                return;
            }

            int clientId = Integer.parseInt(tMClient.getValueAt(selectedClientRow, 0).toString());
            int productId = Integer.parseInt(tMProduct.getValueAt(selectedProductRow, 0).toString());
            int currentProductQuantity = Integer.parseInt(tMProduct.getValueAt(selectedProductRow, 5).toString());
            double productPrice = Double.parseDouble(tMProduct.getValueAt(selectedProductRow, 4).toString());


            int quantity = (int) view.getSpinnerQuantity().getValue();

            int remainingQuantity = currentProductQuantity - quantity;
            if(remainingQuantity < 0) {
                this.view.showPopup("Not enough products in stock");
                return;
            }

            double orderPrice = productPrice * quantity;

            Order newOrder = orderDAO.createObject(new String[]{"0", String.valueOf(clientId), String.valueOf(productId), String.valueOf(quantity), String.valueOf(orderPrice)});

            int id = orderDAO.insert(newOrder);
            Bill bill = new Bill( 0, id, orderPrice);
            BillDAO billDao = new BillDAO();
            billDao.insert(bill);
            Product updatedProduct = new Product(productId, tMProduct.getValueAt(selectedProductRow, 1).toString(), tMProduct.getValueAt(selectedProductRow, 2).toString(), tMProduct.getValueAt(selectedProductRow, 3).toString(), productPrice, remainingQuantity);

            view.getProductDAO().update(updatedProduct);

            this.view.updateProductTable();

            this.view.showPopup("Order added successfully");
        } else if(action.equals("VIEW")) {
            this.view.viewOrders();
        }
    }
}
