/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectOops;
/**
 *
 * @author velmurugan
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class StopWatch extends Thread
{
    private Calendar start,end;
    private JFrame object;
    private String title;
    private SimpleDateFormat dfmt;
    public StopWatch(Calendar End, String Title, JFrame comp)
    {
        start = Calendar.getInstance();
        end = End;
        object = comp;
        title = Title;
        dfmt = new SimpleDateFormat("HH:mm:ss");
        start.set(Calendar.HOUR_OF_DAY,0);
        start.set(Calendar.MINUTE,0);
        start.set(Calendar.SECOND,0);
        start.set(Calendar.MILLISECOND,0);
    }
    @Override
    synchronized public void run()
    {
        while (start.compareTo(end) != 0)
        {
            start.add(Calendar.SECOND, 1);
            object.setTitle(title + " " + dfmt.format(start.getTime()));
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        JFrame f=new JFrame();
        final ImageIcon O = new ImageIcon("C:\\Users\\Public\\Pictures\\g.jpeg");
        JOptionPane.showMessageDialog(null, "YOU LOST THE GAME", "PUZZLE RESULT", +
        JOptionPane.INFORMATION_MESSAGE,O);
        f.setSize(4200,4200);
    }
}