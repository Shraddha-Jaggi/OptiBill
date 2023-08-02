/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optibill;

import java.sql.Statement;
import java.sql.*;
import java.util.*;


/**
 *
 * @author shraddhajaggi
 */
public class Conn   {  
     Connection c;
    Statement s;
    
    Conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");    
            c = DriverManager.getConnection("jdbc:mysql:///data", "root", "root1234@");
            s = (Statement) c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}    
