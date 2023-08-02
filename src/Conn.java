/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optibill;

import java.beans.Statement;
import java.sql.*;
import java.util.*;


/**
 *
 * @author shraddhajaggi
 */
public class Conn   {  
     
    Conn () throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","root1234@");
        Statement s= (Statement) c.createStatement();
    }
}

    
