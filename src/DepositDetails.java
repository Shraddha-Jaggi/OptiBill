
package optibill;

/**
 *
 * @author shraddhajaggi
 */
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener{

    Choice  cmonth, cstatus;
    JTextField cphonenumber;
    JTable table;
    JButton search, print, reset;
    
    DepositDetails(){
        super("Deposit Details");
        
        setSize(700, 700);
        setLocation(400, 100);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblphonenumber = new JLabel("Search By Phone");
        lblphonenumber.setBounds(20, 20, 150, 20);
        add(lblphonenumber);
        
        cphonenumber = new JTextField();
        cphonenumber.setBounds(180, 20, 150, 20);
        add(cphonenumber);
        

        
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(400, 20, 100, 20);
        add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(520, 20, 150, 20);
        cmonth.add("None");
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
        add(cmonth);
        
        JLabel lblstatus = new JLabel("Search By Status");
        lblstatus.setBounds(20, 70, 150, 20);
        add(lblstatus);
        
        cstatus = new Choice();
        cstatus.setBounds(180, 70, 150, 20);
        cstatus.add("None");
        cstatus.add("Paid");
        cstatus.add("Pending");
        add(cstatus);
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * from bill");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 150, 700, 600);
        add(sp);
        search = new JButton("Search");
        search.setBounds(20, 120, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(120, 120, 80, 20);
        print.addActionListener(this);
        add(print);
        
        reset = new JButton("Reset");
        reset.setBounds(220, 120, 80, 20);
        reset.addActionListener(this);
        add(reset);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from bill where phonenumber = '"+cphonenumber.getText()+"' or month = '"+cmonth.getSelectedItem()+"'or status='"+cstatus.getSelectedItem()+"' ";
            //String query2= "SELECT  bill.phonenumber,customer.name, bill.totalcost,bill.month,bill.date, bill.year, bill.status FROM customer LEFT JOIN bill ON customer.phone = bill.phonenumber where phonenumber = '"+cphonenumber.getText()+"' or month = '"+cmonth.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else if (ae.getSource() == reset) {
           
            cphonenumber.setText("");
            String query = "select * from bill";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
            
 
        } 
        
        else if (ae.getSource() == print){
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
                 }
        }
    }

    public static void main(String[] args) {
        new DepositDetails();
    }
}