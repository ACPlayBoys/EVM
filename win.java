import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.plaf.basic.BasicSliderUI;

class win extends JPanel 
{
    public win(JLabel l)
    {
        setLayout(new FlowLayout());
        add(l);
    }
  public void paintComponent(Graphics g){
     Image i=new ImageIcon("startup.png").getImage();
     g.drawImage(i,1,1,this.getWidth(),this.getHeight(),this);
}}

     
   
 