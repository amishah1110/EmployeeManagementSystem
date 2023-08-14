package employee.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Splash extends JFrame implements ActionListener{
    
//Constructor of Splash class
    public Splash(){
        getContentPane().setBackground(Color.WHITE);//bg color specified white 
        setLayout(null);
        
        JLabel heading=new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(200, 30, 1200, 60); //bound of text
        Font myfont=new Font("serif", Font.BOLD, 45);//object of Font
        heading.setFont(myfont); //passing obj as paramenter 
        heading.setForeground(Color.BLACK);
        add(heading); //add heading to JFrame
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Office-Management-System.png"));
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(70, 100, 1050, 500);
        add(image);
        
        JButton clickhere = new JButton("CLICK HERE TO CONTINUE");
        clickhere.setBounds(400, 400, 300, 70);
        clickhere.setBackground(Color.BLACK);
        clickhere.setForeground(Color.WHITE);
        clickhere.addActionListener(this);
      //  clickhere.addActionListener(this);
        image.add(clickhere);
        
        
        setSize(1200, 700); //size of jframe
        setLocation(200,50);//location wrt to desktop
        setDefaultCloseOperation(EXIT_ON_CLOSE); //on exiting window, build stops
        setVisible(true); //visible or not?
        
        while(true){
            heading.setVisible(false);
            try{
                Thread.sleep(300);
            }
            catch(InterruptedException e){   
            }
            
            heading.setVisible(true);
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){   
            }
        }
    }
    
   @Override
    public void actionPerformed(ActionEvent e){
        setVisible(false);
        new Login();
    }
    //main method
    public static void main(String args[]){
        new Splash();
    }
}
