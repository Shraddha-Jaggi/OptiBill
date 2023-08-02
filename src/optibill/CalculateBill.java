/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optibill;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class CalculateBill extends JFrame implements ActionListener{

    JTextField tfcost, tftotal, tfdate, tfyear, phonenumber,tfdesc ;
    JButton next, cancel, add, search;
    JLabel lblname , lblTotal, tfname, lbldesc;
    Choice  cmonth, cstatus;
    Double originalValue=0.0;
    CalculateBill() {
        setSize(700, 550);
        setLocation(400, 150);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
        JLabel heading = new JLabel("Calculate Bill");
        heading.setBounds(200, 10, 400, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(100, 80, 100, 20);
        p.add(lblphone);
        
        search = new JButton("Search");
        search.setBounds(450, 80, 80, 20);
        search.addActionListener(this); // Add ActionListener to the "Add" button
        p.add(search);
        
        phonenumber = new JTextField ();
        
        
         phonenumber.setBounds(240, 80, 200, 20);
        p.add(phonenumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 120, 100, 20);
        p.add(lblname);
        
        tfname = new JLabel("");
        tfname.setBounds(240, 120, 100, 20);
        p.add(tfname);
        
       
       
         
         JLabel lblcost = new JLabel("Price");
        lblcost.setBounds(100, 160, 100, 20);
        p.add(lblcost);
        
        tfcost = new JTextField();
        tfcost.setBounds(240, 160, 200, 20);
        p.add(tfcost);
        
        add = new JButton("Add");
        add.setBounds(450, 160, 80, 20);
        add.addActionListener(this); // Add ActionListener to the "Add" button
        p.add(add);

        
        JLabel lblTotal = new JLabel("Total Cost");
        lblTotal.setBounds(100, 200, 100, 20);
        p.add(lblTotal);
        
        tftotal = new JTextField();
        tftotal.setBounds(240, 200, 200, 20);
        p.add(tftotal) ;
                
        
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(100, 240, 100, 20);
        p.add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(240, 240, 200, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        p.add(cmonth);
        
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(100, 280, 100, 20);
        p.add(lbldate);
        
        tfdate = new JTextField();
        tfdate.setBounds(240, 280, 200, 20);
        p.add(tfdate) ;
        
        JLabel lblyear = new JLabel("Year");
        lblyear.setBounds(100, 320, 100, 20);
        p.add(lblyear);
        
        tfyear = new JTextField();
        tfyear.setBounds(240, 320, 200, 20);
        p.add(tfyear) ;
        
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(100, 360, 100, 20);
        p.add(lblstatus);
        
        cstatus = new Choice();
        cstatus.setBounds(240, 360, 200, 20);
        cstatus.add("PAID");
        cstatus.add("PENDING");
        p.add(cstatus);
        
         lbldesc = new JLabel("Paid By");
        lbldesc.setBounds(100, 400, 100, 20);
        p.add(lbldesc);
        
        tfdesc=new JTextField();
        tfdesc.setBounds(240, 400, 200, 20);
        p.add(tfdesc) ;
        
        
        
        next = new JButton("Submit");
        next.setBounds(120, 450, 100,25);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.addActionListener((ActionListener) this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(250, 450, 100,25);
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener((ActionListener) this);
        p.add(cancel);
        
        setLayout(new BorderLayout());
        
        add(p, "Center");
        
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Add")) {
            try {
                double price = Double.parseDouble(tfcost.getText());
                originalValue += price;
                tftotal.setText("" + originalValue);
                tfcost.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");
            }
       }
        if (ae.getSource() == search) {
            searchPhoneNumber();
        }
        
        else if (ae.getActionCommand().equals("Submit")) {
            String phone= phonenumber.getText();
            
            String totalcost= tftotal.getText();
            String month= cmonth.getSelectedItem();
            String date= tfdate.getText();
            String year= tfyear.getText();
            String status= cstatus.getSelectedItem();
            String description = tfdesc.getText();
            
            
            
            String query= "insert into bill values('"+phone+"', '"+totalcost+"', '"+month+"', '"+date+"', '"+year+"', '"+status+"', '"+description+"')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                
                
                JOptionPane.showMessageDialog(this, "Details Added Successfully");
                setVisible(false);
                
                // new frame
              
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
        } 
            
            else if (ae.getActionCommand().equals("Cancel")) {
                setVisible(false);
            
       }
    }
       private void searchPhoneNumber() {
        String phoneNumber = phonenumber.getText().trim();
        if (!phoneNumber.isEmpty()) {
            try (Connection con= DriverManager.getConnection("jdbc:mysql:///data", "root", "root1234@")) {
                String query = "SELECT name FROM customer WHERE phone = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, phoneNumber);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    tfname.setText(name);
                } else {
                    tfname.setText("Not Found");
                }

                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculateBill().setVisible(true));
    }
}