package presentation.views;

import presentation.controllers.IndexController;

import javax.swing.*;
import java.awt.*;

/**
 * View class for the index page
 * @author Adrian Baraian
 */
public class IndexView extends JFrame {
    private JPanel contentPane;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JButton logoutButton;
    private JButton viewBillsButton;
    IndexController indexController = new IndexController(this);

    /**
     * Constructor for the IndexView class
     * @param name the name of the view
     */
    public IndexView(String name) {
        super(name);
        this.prepareIndexPage();
    }

    /**
     * Method that prepares the index page
     */
    public void prepareIndexPage() {
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        this.contentPane = new JPanel();
        this.contentPane.setLayout(new BorderLayout());
        this.contentPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        this.setContentPane(this.contentPane);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setPreferredSize(new Dimension(1280, 120));
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel welcomeLabel = new JLabel("Welcome to the Index Page");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);
        this.contentPane.add(welcomePanel, BorderLayout.NORTH);

        this.clientButton = new JButton("Client");
        this.productButton= new JButton("Product");
        this.orderButton = new JButton("Order");
        this.logoutButton = new JButton("Logout");
        this.viewBillsButton = new JButton("View Bills");

        this.clientButton.setPreferredSize(new Dimension(200, 80));
        this.productButton.setPreferredSize(new Dimension(200, 80));
        this.orderButton.setPreferredSize(new Dimension(200, 80));
        this.logoutButton.setPreferredSize(new Dimension(200, 80));
        this.viewBillsButton.setPreferredSize(new Dimension(200, 80));

        this.clientButton.setActionCommand("CLIENT");
        this.productButton.setActionCommand("PRODUCT");
        this.orderButton.setActionCommand("ORDER");
        this.logoutButton.setActionCommand("LOGOUT");
        this.viewBillsButton.setActionCommand("VIEW_BILLS");

        this.clientButton.addActionListener(indexController);
        this.productButton.addActionListener(indexController);
        this.orderButton.addActionListener(indexController);
        this.logoutButton.addActionListener(indexController);
        this.viewBillsButton.addActionListener(indexController);

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(4, 1));

        JPanel buttonClientPanel = new JPanel();
        JPanel buttonProductPanel = new JPanel();
        JPanel buttonOrderPanel = new JPanel();
        JPanel buttonLogoutPanel = new JPanel();
        JPanel buttonViewBillsPanel = new JPanel();
        buttonClientPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonClientPanel.add(this.clientButton);

        buttonProductPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonProductPanel.add(this.productButton);

        buttonOrderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonOrderPanel.add(this.orderButton);

        buttonLogoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonLogoutPanel.add(this.logoutButton);

        buttonViewBillsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonViewBillsPanel.add(this.viewBillsButton);

        buttonPanel.add(buttonClientPanel);
        buttonPanel.add(buttonProductPanel);
        buttonPanel.add(buttonOrderPanel);
        buttonPanel.add(buttonLogoutPanel);
        buttonPanel.add(buttonViewBillsPanel);

        this.contentPane.add(buttonPanel, BorderLayout.CENTER);
    }
}
