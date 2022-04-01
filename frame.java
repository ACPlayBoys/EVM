import javax.swing.*;
import java.awt.*;
public class frame extends JPanel
{
    Image image;    
    public void loadimage(ImageIcon m)
    {
        image=m.getImage();
    }
    public void paintComponent(Graphics g)
    {
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
    }
}