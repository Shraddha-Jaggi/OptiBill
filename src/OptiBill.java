/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package optibill;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author shraddhajaggi
 */
public abstract class OptiBill extends JFrame implements Runnable{

    /**
     * @param args the command line arguments
     */
    Thread t;
    /* 
ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/homepage bg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);    
    */
    OptiBill()
            
    {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/main_image.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);    
        setVisible(true);
        
        
           
                setSize(1366,768);
                
                setVisible(true);
             
        
       t=new Thread(this);
       t.start();
        setVisible(true);
    }
    
    public void run()
    {
        try
        {
         Thread.sleep(5000);
         setVisible(false);
         
         //login frame
         new login();
         
         
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new OptiBill() {};
    }

    
    
}
