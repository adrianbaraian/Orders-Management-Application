package presentation.controllers;

import presentation.views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the index view
 * @author Adrian Baraian
 * @see IndexView
 */
public class IndexController implements ActionListener {
    /**
     * The view that the controller interacts with
     */
    IndexView view;

    /**
     * Constructor for the IndexController class
     * @param view the view that the controller interacts with
     */
    public IndexController(IndexView view) {
        this.view = view;
    }


    /**
     * Method that listens for actions performed on the view namely presses of client, product, order, logout and view bills buttons
     * @param e the event that was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("CLIENT")) {
            new ClientView("Client Page");
        } else if (action.equals("PRODUCT")) {
            new ProductView("Product Page");
        } else if (action.equals("ORDER")) {
            new OrderView("Order Page");
        } else if (action.equals("LOGOUT")) {
            this.view.dispose();
            new LoginView("Login Page").setVisible(true);
        } else if (action.equals("VIEW_BILLS")) {
            new BillsView("Bills Page");
        }
    }
}
