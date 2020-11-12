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
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.swing.*;
class NumButtonListener implements ActionListener
{
    private NumPuzzle object;
    private boolean CheckAndProcessPrediction(int px, int py, int x, int y)
    {
        boolean returnValue = false;
        if ((px >= 0 && px < object.row) && (py >= 0 && py < object.col))
        {
            if (object.jbuttons[px][py].getText().equals("1"))
            {
                object.jbuttons[px][py].setText(object.jbuttons[x][y].getText());
                object.jbuttons[x][y].setText("1");
                object.jbuttons[px][py].setBorder(UIManager.getBorder("Button.border"));
                object.jbuttons[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                returnValue = true;
            }
        }
        return returnValue;
    }
    public NumButtonListener(NumPuzzle numpuzzle)
    {
        object = numpuzzle;
    }
    @Override
    public void actionPerformed(ActionEvent Ae)
    {
        JButton Jbutton = (JButton) (Ae.getSource());
        for (int i = 0; i < object.row; ++i)
        {
            for (int j = 0; j < object.col; ++j)
            {
                if (Jbutton.getText().equals(object.jbuttons[i][j].getText()))
                {
                    this.CheckAndProcessPrediction(i - 1, j, i, j);
                    this.CheckAndProcessPrediction(i, j - 1, i, j);
                    this.CheckAndProcessPrediction(i + 1, j, i, j);
                    this.CheckAndProcessPrediction(i, j + 1, i, j);
                    if (object.mod.compareTo(PuzzleFactory.PuzzleMod.MODE_8) == 0)
                    {
                        this.CheckAndProcessPrediction(i - 1, j - 1, i, j);
                        this.CheckAndProcessPrediction(i + 1, j - 1, i, j);
                        this.CheckAndProcessPrediction(i - 1, j + 1, i, j);
                        this.CheckAndProcessPrediction(i + 1, j + 1, i, j);
                    }
                }
            }
        }
        if (object.isSolved())
        {
           final ImageIcon O = new ImageIcon("C:\\Users\\Public\\Pictures\\win1.jpeg");
        JOptionPane.showMessageDialog(null, "YOU WON THE GAME", "PUZZLE RESULT", +
        JOptionPane.INFORMATION_MESSAGE,O);
        }
    }
}
@SuppressWarnings("serial")
class NumPuzzle extends JPanel
{
    protected int row, col;
    protected PuzzleFactory.PuzzleMod mod;
    protected JButton[][] jbuttons;
    protected NumButtonListener lisbutton;
    private void InitField(int Row, int Col, PuzzleFactory.PuzzleMod Mod)
    {
        row = Row;
        col = Col;
        mod = Mod;
        jbuttons = new JButton[Row][Col];
        lisbutton = new NumButtonListener(this);
        for (int i = 0; i < Row; ++i)
        {
            for (int j = 0; j < Col; ++j)
            {
                jbuttons[i][j] = new JButton();
            }
        }
    }
    private void FilButton()
    {
        ArrayList<Integer> Array = new ArrayList<Integer>(row * col);
        for (int i = 1; i <= row * col; ++i)
        {
            Array.add(i);
        }
        Collections.shuffle(Array);
        int index = 0;
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                button.setText(Array.get(index++).toString());
            }
        }
    }
    private void FitButton()
    {
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                if (button.getText().equals("1"))
                {
                    button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                }
                button.setBackground(Color.pink);
            }
        }
    }
    private void LisButton()
    {
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                button.addActionListener(lisbutton);
            }
        }
    }
    private void addButton()
    {
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                super.add(button);
            }
        }
    }
    public NumPuzzle(int Row, int Col, PuzzleFactory.PuzzleMod Mod)
    {
        setLayout(new GridLayout(Row, Col));
        this.InitField(Row, Col, Mod);
        this.FilButton();
        this.FitButton();
        this.LisButton();
        this.addButton();
    }
    public boolean isSolved()
    {
        int value = 0;
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                if (!button.getText().equals(String.valueOf(++value)))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
class PicButtonListener implements ActionListener
{
    private PicPuzzle object;
    private boolean CheckAndProcessPrediction(int px, int py, int x, int y)
    {
        boolean returnValue = false;
        if ((px >= 0 && px < object.row) && (py >= 0 && py < object.col))
        {
            if (object.jbuttons[px][py].getActionCommand().equals("1"))
            {
                object.jbuttons[px][py].setActionCommand(object.jbuttons[x][y].getActionCommand());
                object.jbuttons[x][y].setActionCommand("1");
                object.jbuttons[px][py].setBorder(UIManager.getBorder("Button.border"));
                object.jbuttons[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

                Icon ico1 = object.jbuttons[px][py].getIcon();
                Icon ico2 = object.jbuttons[x][y].getIcon();
                object.jbuttons[px][py].setIcon(ico2);
                object.jbuttons[x][y].setIcon(ico1);
                returnValue = true;
            }
        }
        return returnValue;
    }
    public PicButtonListener(PicPuzzle picPuzzle)
    {
        object = picPuzzle;
    }
    @Override
    public void actionPerformed(ActionEvent Ae)
    {
        JButton Jbutton = (JButton) (Ae.getSource());

        for (int i = 0; i < object.row; ++i)
        {
            for (int j = 0; j < object.col; ++j)
            {
                if (Jbutton.getActionCommand().equals(object.jbuttons[i][j].getActionCommand()))
                {
                    this.CheckAndProcessPrediction(i - 1, j, i, j);
                    this.CheckAndProcessPrediction(i, j - 1, i, j);
                    this.CheckAndProcessPrediction(i + 1, j, i, j);
                    this.CheckAndProcessPrediction(i, j + 1, i, j);
                    if (object.mod.compareTo(PuzzleFactory.PuzzleMod.MODE_8) == 0)
                    {
                        this.CheckAndProcessPrediction(i - 1, j - 1, i, j);
                        this.CheckAndProcessPrediction(i + 1, j - 1, i, j);
                        this.CheckAndProcessPrediction(i - 1, j + 1, i, j);
                        this.CheckAndProcessPrediction(i + 1, j + 1, i, j);
                    }
                }
            }
        }
        if (object.isSolved())
        {
              final ImageIcon O = new ImageIcon("C:\\Users\\Public\\Pictures\\win1.jpeg");
        JOptionPane.showMessageDialog(null, "YOU WON THE GAME", "PUZZLE RESULT", +
        JOptionPane.INFORMATION_MESSAGE,O);
        }
    }
}
class PicCompListener extends ComponentAdapter
{
    private PicPuzzle object;
     private Point GetPoint(JButton button)
    {
        int   num = Integer.valueOf(button.getActionCommand());
        Point pos = new Point();
 
        for(int i = 0; i < object.row; ++i)
        {
            for(int j = 0; j < object.col; ++j)
            {
                if((--num) == 0 )
                {
                    pos.x = i;
                    pos.y = j;
                }
            }
        }
        return pos;
    }
    private ImageIcon ResizeIco(JButton button)
    {
        Point pos = this.GetPoint(button);
        Dimension siz = button.getSize();
        Insets ins = button.getInsets();
        int hei = siz.height - ins.top - ins.bottom;
        int wid = siz.height - ins.left - ins.right;
        Image img = object.imgico[pos.x][pos.y].getImage();
        if (wid > hei)
        {
            wid = -1;
        } else
        {
            hei = -1;
        }
        return new ImageIcon(img.getScaledInstance(hei, wid, Image.SCALE_FAST));
    }
    public PicCompListener(PicPuzzle picPuzzle)
    {
        object = picPuzzle;
    }
    @Override
    public void componentResized(ComponentEvent Ce)
    {
        JButton button = (JButton) (Ce.getSource());
        button.setIcon(this.ResizeIco(button));
    }
}
@SuppressWarnings("serial")
class PicPuzzle extends JPanel
{
    protected BufferedImage img;
    protected ImageIcon[][] imgico;
    protected int row, col;
    protected int ih, iw;
    protected int ph, pw;
    protected PuzzleFactory.PuzzleMod mod;
    protected JButton[][] jbuttons;
    protected PicButtonListener lisbutton;
    protected PicCompListener liscomp;
    private void InitField(String Img, int Row, int Col, PuzzleFactory.PuzzleMod Mod) throws IOException
    {
        img = ImageIO.read(new File(Img));
        imgico = new ImageIcon[Row][Col];
        row = Row;
        col = Col;
        ih = img.getHeight();
        iw = img.getWidth();
        ph = ih / row;
        pw = iw / col;
        mod = Mod;
        jbuttons = new JButton[Row][Col];
        lisbutton = new PicButtonListener(this);
        liscomp = new PicCompListener(this);
        for (int i = 0; i < Row; ++i)
        {
            for (int j = 0; j < Col; ++j)
            {
                jbuttons[i][j] = new JButton();
            }
        }
    }
    private void CropImage()
    {
        int x = 0, y = 0;
        for (int i = 0; i < row; ++i)
        {
            x = 0;
            for (int j = 0; j < col; ++j)
            {
                imgico[i][j] = new ImageIcon(img.getSubimage(x, y, pw, ph));
                x += pw;
            }

            y += ph;
        }
    }
    private void FilButton()
    {
        ArrayList<Integer> Array = new ArrayList<Integer>(row * col);
        for (int i = 1; i <= row * col; ++i)
        {
            Array.add(i);
        }
        Collections.shuffle(Array);
        int index = 0;
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                button.setActionCommand(Array.get(index++).toString());
                button.setMargin(new Insets(1, 1, 1, 1));
            }
        }
    }
    private void FitButton()
    {
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                if (button.getActionCommand().equals("1"))
                {
                    button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                }
                button.setBackground(Color.CYAN);
            }
        }
    }
    private void LisButton()
    {
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                button.addActionListener(lisbutton);
                button.addComponentListener(liscomp);
            }
        }
    }
    private void AddButton()
    {
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                super.add(button);
            }
        }
    }
    public PicPuzzle(String Img, int Row, int Col, PuzzleFactory.PuzzleMod Mod) throws IOException
    {
        setLayout(new GridLayout(Row, Col));
        this.InitField(Img, Row, Col, Mod);
        this.CropImage();
        this.FilButton();
        this.FitButton();
        this.LisButton();
        this.AddButton();
    }
    public boolean isSolved()
    {
        int value = 0;
        for (JButton[] buttons : jbuttons)
        {
            for (JButton button : buttons)
            {
                if (!button.getActionCommand().equals(String.valueOf(++value)))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
public class PuzzleFactory
{
    static enum PuzzleMod
    {
        MODE_4,// 4 button are exchangble(adjacent)
        MODE_8;  // 8 button are exchangble(cross)
    }
    public static NumPuzzle getNumPuzzle(int Row, int Col, PuzzleFactory.PuzzleMod Mod)
    {
        return new NumPuzzle(Row, Col, Mod);
    }
    public static PicPuzzle getPicPuzzle(String Image, int Row, int Col, PuzzleFactory.PuzzleMod Mod) throws IOException
    {
        return new PicPuzzle(Image, Row, Col, Mod);
    }
}