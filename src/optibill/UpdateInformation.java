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
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class UpdateInformation extends JFrame implements ActionListener{
    
    JLabel phonenumber, totalcost, status;
        JTextField tfphone, tftotal,cstatus;
        JButton search, update;
        
        JTextArea infoLabel;
    UpdateInformation(){
        
        getContentPane().setBackground(new Color(173, 216, 230));
        setSize(700, 300);
        setLocation(400, 100);
        
        
        
        
        
        
        setLayout(null);
       
        
        JLabel lblphonenumber = new JLabel("Phone");
        lblphonenumber.setBounds(20, 20, 150, 20);
        add(lblphonenumber);
        
        tfphone = new JTextField();
        tfphone.setBounds(80, 20, 150, 20);
        add(tfphone);
        
         totalcost = new JLabel("Amount");
        totalcost.setBounds(300, 20, 150, 20);
        add(totalcost);
        
        tftotal = new JTextField();
        tftotal.setBounds(360, 20, 150, 20);
        add(tftotal);
        
        search = new JButton("Search");
        search.setBounds(550, 20, 110, 20);
        search.addActionListener(this);
        add(search);
        
        infoLabel = new JTextArea("");
        infoLabel.setBackground(new Color(173, 216, 230));
        infoLabel.setBounds(80, 100, 250, 20);
        add(infoLabel);
        
        status = new JLabel("Status");
        status.setBounds(20, 180, 40, 20);
        add(status);
        
        cstatus = new JTextField();
        cstatus.setBounds(100, 180, 200, 20);
        
        add(cstatus);
        
        
        
        
        update = new JButton("Update");
        update.setBounds(320, 180, 110, 20);
        update.addActionListener(this);
        add(update);
        
        
        setVisible(true);
        
        
        
    }
    
     public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data",
                "root", "root1234@")){
            String phoneNumber = tfphone.getText();
            double amount = Double.parseDouble(tftotal.getText());

            String query = "SELECT * FROM bill WHERE phonenumber=? AND totalcost=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, phoneNumber);
            stmt.setDouble(2, amount);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                infoLabel.setText("Payment Status: " + status);
                cstatus.getText();
            } else {
                infoLabel.setText("No payment found for the given details.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        } 
        
        if (ae.getSource() == update) {
             int phone = Integer.parseInt(tfphone.getText());
            int amount = Integer.parseInt(tftotal.getText());
       try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data",
                "root", "root1234@")) {
            String sql = "UPDATE bill SET status = ? WHERE totalcost = ? AND phonenumber=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cstatus.getText());
            statement.setInt(2, amount);
            statement.setInt(3, phone);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Data updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update data.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while updating data.");
        }
        
    }
     }
    
    

   
    public static void main(String args[])
    {
        new UpdateInformation();
    }

    
}
