/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optibill;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.beans.Statement;
import java.sql.*;
/**
 *
 * @author shraddhajaggi
 */
public class NewCustomer extends JFrame implements ActionListener {
    JTextField tfname, tfaddress,tflcyl, tfladd,tflaxis,tfcyl, tfsph, tfaxis, tfphone, tfadd, tflsph;
    JButton next, cancel;
    
    NewCustomer()
    {
        
        setVisible(true);
    
        setSize(700, 700);
        setLocation(400, 100);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
        JLabel heading= new JLabel("Customer Details");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 80, 100, 20);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(240, 80, 200, 20);
        p.add(tfname);
       
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 120, 100, 20);
        lbladdress.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(240, 120, 200, 20);
        p.add(tfaddress);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(100, 160, 100, 20);
        lblphone.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(240, 160, 200, 20);
        p.add(tfphone);
        
        JLabel re= new JLabel("RIGHT EYE");
        re.setBounds(250,190,200,25);
        re.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(re);
        
        
        JLabel lblsph = new JLabel("Spherical");
        lblsph.setBounds(100, 220, 100, 20);
        lblsph.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblsph);
        
        tfsph = new JTextField();
        tfsph.setBounds(240, 220, 200, 20);
        p.add(tfsph);
        
        JLabel lblcyl = new JLabel("Cylindrical");
        lblcyl.setBounds(100, 260, 100, 20);
        lblcyl.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblcyl);
        
        tfcyl = new JTextField();
        tfcyl.setBounds(240, 260, 200, 20);
        p.add(tfcyl);
        
        JLabel lblaxis = new JLabel("Axis");
        lblaxis.setBounds(100, 300, 100, 20);
        lblaxis.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblaxis);
        
        tfaxis = new JTextField();
        tfaxis.setBounds(240, 300, 200, 20);
        p.add(tfaxis);
        
        JLabel lbladd = new JLabel("Addition");
        lbladd.setBounds(100, 340, 100, 20);
        lbladd.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lbladd);
        
        tfadd = new JTextField();
        tfadd.setBounds(240, 340, 200, 20);
        p.add(tfadd);      
        
        
        
        
        JLabel le= new JLabel("LEFT EYE");
        le.setBounds(250,370,200,25);
        le.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(le);
        
        
        JLabel lbllsph = new JLabel("Spherical");
        lbllsph.setBounds(100, 400, 100, 20);
        lbllsph.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lbllsph);
        
        tflsph = new JTextField();
        tflsph.setBounds(240, 400, 200, 20);
        p.add(tflsph);
        
        JLabel lbllcyl = new JLabel("Cylindrical");
        lbllcyl.setBounds(100, 440, 100, 20);
        lbllcyl.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lbllcyl);
        
        tflcyl = new JTextField();
        tflcyl.setBounds(240, 440, 200, 20);
        p.add(tflcyl);
        
        JLabel lbllaxis = new JLabel("Axis");
        lbllaxis.setBounds(100, 480, 100, 20);
        lbllaxis.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lbllaxis);
        
        tflaxis = new JTextField();
        tflaxis.setBounds(240, 480, 200, 20);
        p.add(tflaxis);
        
        JLabel lblladd = new JLabel("Addition");
        lblladd.setBounds(100, 520, 100, 20);
        lblladd.setFont(new Font("Tahoma",Font.BOLD,14));
        p.add(lblladd);
        
        tfladd = new JTextField();
        tfladd.setBounds(240, 520, 200, 20);
        p.add(tfladd);
        
        
        
        
        next = new JButton("Submit");
        next.setBounds(120, 600, 100,25);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.addActionListener(this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(250, 600, 100,25);
        cancel.setBackground(Color.white);
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setLayout(new BorderLayout());
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String name= tfname.getText();
            String address= tfaddress.getText();
            String phone= tfphone.getText();
            String rightsph= tfsph.getText();
            String rightcyl= tfcyl.getText();
            String rightaxis= tfaxis.getText();
            String rightadd= tfadd.getText();
            
            String leftsph= tflsph.getText();
            String leftcyl= tflcyl.getText();
            String leftaxis= tflaxis.getText();
            String lefttadd= tfadd.getText();
            
            String query= "insert into customer values('"+name+"', '"+address+"', '"+phone+"',"
                    + " '"+rightsph+"', '"+rightcyl+"', '"+rightaxis+"', '"+rightadd+"',"
                    + " '"+leftsph+"','"+leftcyl+"', '"+leftaxis+"', '"+lefttadd+"')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                
                
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);
                
                // new frame
              
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new NewCustomer();
    }
}
                