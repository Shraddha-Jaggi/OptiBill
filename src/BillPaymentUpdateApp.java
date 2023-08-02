/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optibill;

/**
 *
 * @author shraddhajaggi
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillPaymentUpdateApp extends JFrame {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/data";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root1234@";

    // GUI components
    private JTextField phoneNumberField;
    private JComboBox<String> statusComboBox;
    private JTextArea resultArea;

    // Constructor to initialize the GUI
    public BillPaymentUpdateApp() {
        // Set up the JFrame
        setTitle("Bill Payment Status Updater");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberField = new JTextField(10);

        JLabel statusLabel = new JLabel("Payment Status:");
        String[] statusOptions = {"Paid", "Not Paid"};
        statusComboBox = new JComboBox<>(statusOptions);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPaymentData();
            }
        });

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePaymentStatus();
            }
        });

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Set up the layout
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel middlePanel = new JPanel(new FlowLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout());
        topPanel.add(phoneNumberLabel);
        topPanel.add(phoneNumberField);
        middlePanel.add(statusLabel);
        middlePanel.add(statusComboBox);
        bottomPanel.add(searchButton);
        bottomPanel.add(updateButton);

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(middlePanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        contentPane.add(scrollPane, BorderLayout.WEST);

        setContentPane(contentPane);
    }

    private void searchPaymentData() {
        String phoneNumber = phoneNumberField.getText();
        if (!phoneNumber.isEmpty()) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // 1. Establish the database connection
                conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                // 2. Prepare the SQL query
                String searchQuery = "SELECT * FROM bill WHERE phonenumber = ?";
                pstmt = conn.prepareStatement(searchQuery);
                pstmt.setString(1, phoneNumber);

                // 3. Execute the query
                rs = pstmt.executeQuery();

                // 4. Display the results
                StringBuilder resultBuilder = new StringBuilder();
                while (rs.next()) {
                    String totalCost = rs.getString("totalcost");
                    String month = rs.getString("month");
                    String year = rs.getString("year");
                    String date = rs.getString("date");
                    String status = rs.getString("status");
                    String paidBy = rs.getString("paidby");
                    resultBuilder.append("Total Cost: ").append(totalCost).append("\n");
                    resultBuilder.append("Month: ").append(month).append("\n");
                    resultBuilder.append("Year: ").append(year).append("\n");
                    resultBuilder.append("Date: ").append(date).append("\n");
                    resultBuilder.append("Status: ").append(status).append("\n");
                    resultBuilder.append("Paid By: ").append(paidBy).append("\n");
                    resultBuilder.append("-------------------------\n");
                }
                resultArea.setText(resultBuilder.toString());
            } catch (SQLException ex) {
                System.err.println("Error occurred while searching payment data: " + ex.getMessage());
            } finally {
                // 5. Close the resources
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.err.println("Error occurred while closing resources: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a phone number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePaymentStatus() {
        String phoneNumber = phoneNumberField.getText();
        String newStatus = (String) statusComboBox.getSelectedItem();

        if (!phoneNumber.isEmpty()) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                // 1. Establish the database connection
                conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                // 2. Prepare the SQL update statement
                String updateQuery = "UPDATE bill SET status = ? WHERE phonenumber = ?";
                pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, newStatus);
                pstmt.setString(2, phoneNumber);

                // 3. Execute the update statement
                int rowsAffected = pstmt.executeUpdate();

                // 4. Check if the update was successful
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Payment status updated successfully!");
                    searchPaymentData(); // Refresh the displayed data after the update
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update payment status. Phone number not found!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                System.err.println("Error occurred while updating payment status: " + ex.getMessage());
            } finally {
                // 5. Close the resources
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.err.println("Error occurred while closing resources: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a phone number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BillPaymentUpdateApp().setVisible(true);
            }
        });
    }
}
