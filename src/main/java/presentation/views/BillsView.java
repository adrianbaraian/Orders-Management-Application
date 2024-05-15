package presentation.views;

import dao.BillDAO;
import model.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * View class for the bills table
 * @author Adrian Baraian
 */
public class BillsView extends JFrame {
    private JPanel contentPane;
    private JTable billsTable;
    private DefaultTableModel billsTableModel;

    /**
     * Constructor for the BillsView class
     * @param name the name of the view
     */
    public BillsView(String name) {
        super(name);
        this.prepareBillsPage();
    }

    /**
     * Method that prepares the bills page
     */
    public void prepareBillsPage() {
        BillDAO billDao = new BillDAO();
        List<Bill> bills = billDao.findAll();

        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.contentPane = new JPanel();
        this.contentPane.setLayout(new BoxLayout(this.contentPane, BoxLayout.Y_AXIS));

        this.billsTableModel = new DefaultTableModel();
        this.billsTableModel.addColumn("ID");
        this.billsTableModel.addColumn("Order ID");
        this.billsTableModel.addColumn("Price");

        for(Bill bill : bills) {
            this.billsTableModel.addRow(new Object[]{bill.id(), bill.idorder(), bill.price()});
        }

        this.billsTable = new JTable(this.billsTableModel);
        this.billsTable.setFillsViewportHeight(true);
        this.billsTable.setPreferredScrollableViewportSize(new Dimension(1280, 600));

        JScrollPane scrollPane = new JScrollPane(this.billsTable);
        this.contentPane.add(scrollPane);

        this.setContentPane(this.contentPane);
    }

}
