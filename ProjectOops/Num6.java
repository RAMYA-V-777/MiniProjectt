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
import java.util.Calendar;
import javax.swing.*;
public class Num6
{
    public Num6()
    {
        JFrame Jframe = new JFrame("Puzzle");
        NumPuzzle NPuzzle = PuzzleFactory.getNumPuzzle(6, 6, PuzzleFactory.PuzzleMod.MODE_8);
        JScrollPane Jspane = new JScrollPane(NPuzzle);
           Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,10);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        StopWatch timer =new StopWatch(c,"\"******************************************************************************************************PUZZLE************************************************************************************************",Jframe);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.add(Jspane);
        Jframe.setSize(4200, 4200);
        Jframe.setVisible(true);
        timer.start();
    }
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Num6();
            }
        });
    }
    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}