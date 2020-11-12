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
import java.io.IOException;
import java.util.Calendar;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.io.IOException;
import java.util.Calendar;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.Border;
public class Pic3
{
    public Pic3()
    {
        JFrame Jframe  = new JFrame("Puzzle");
        Puzzle NPuzzle = null;
        try
        {
            NPuzzle = new Puzzle(Factory.getClassicWhiteBorderConfig(2,2),"C:\\Users\\velmurugan\\Pictures\\fall.jpeg", Factory.getPopUpWinAction(Jframe, "YOU WON!!!!!!!!!!!!!!!!!!!!!!!!!"));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(Jframe, "Image Not Found");
            System.exit(1);
        }
              Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,10);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        StopWatch timer =new StopWatch(c,"******************************************************************************************************PUZZLE************************************************************************************************",Jframe);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.add(NPuzzle);
        Jframe.pack();
        Jframe.setSize(4200,4200);
        Jframe.setResizable(false);
        Jframe.setVisible(true);
        timer.start();
    }
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
           public void run()
           {new Pic3();
                   }});
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}