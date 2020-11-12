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
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
@SuppressWarnings("serial")
abstract class Key extends AbstractAction
{
    Puzzle puzzle;
    public Key(Puzzle puzzle)
    {
        this.puzzle = puzzle;
    }
    public Point getPoint()
    {
        Point point = new Point();
        outLoop: for (int x = 0; x <= puzzle.config.Row; ++x)
        {
            for (int y = 0; y <= puzzle.config.Col; ++y)
            {
                if (puzzle.buttons[x][y].getText().equals(puzzle.lastButtonText))
                {
                    point.x = x;
                    point.y = y;
                    break outLoop;
                }
            }
        }
        return point;
    }
    public void move(int fx, int fy, int tx, int ty)
    {
        if( (tx >= 0 && tx <= puzzle.config.Row) && (ty >= 0 && ty <= puzzle.config.Col) )
        {
            puzzle.buttons[fx][fy].setText(puzzle.buttons[tx][ty].getText());
            puzzle.buttons[tx][ty].setText(puzzle.lastButtonText);
            puzzle.buttons[fx][fy].setIcon(puzzle.buttons[tx][ty].getIcon());
            puzzle.buttons[tx][ty].setIcon(puzzle.lastButtonIcon);                
        }
    }
    public void solveAction()
    {
        Integer index = Integer.valueOf(0);

        for (Button[] buttons : puzzle.buttons)
        {
            for (Button button : buttons)
            {
                if (!button.getText().equals((++index).toString()))
                {
                    if (button != puzzle.buttons[puzzle.config.Row][puzzle.config.Col])
                    {
                        return;
                    }
                }
            }
        }
        puzzle.winAction.accept(puzzle);
    }
}

@SuppressWarnings("serial")
class UpKey extends Key
{
    public UpKey(Puzzle puzzle)
    {
        super(puzzle);
    }
    @Override
    public void actionPerformed(ActionEvent Ae)
    {
        Point point = super.getPoint();
        int   fx    = point.x;
        int   fy    = point.y;
        int   tx    = fx - 1;
        int   ty    = fy + 0;
        super.move(fx, fy, tx, ty);
        super.solveAction();
    }
}
@SuppressWarnings("serial")
class DownKey extends Key
{
    public DownKey(Puzzle puzzle)
    {
        super(puzzle);
    }
    @Override
    public void actionPerformed(ActionEvent Ae)
    {
        Point point = super.getPoint();
        int   fx    = point.x;
        int   fy    = point.y;
        int   tx    = fx + 1;
        int   ty    = fy + 0;
        super.move(fx, fy, tx, ty);
        super.solveAction();
    }
}
@SuppressWarnings("serial")
class LeftKey extends Key
{
    public LeftKey(Puzzle puzzle)
    {
        super(puzzle);
    }
    @Override
    public void actionPerformed(ActionEvent Ae)
    {
        Point point = super.getPoint();
        int   fx    = point.x;
        int   fy    = point.y;
        int   tx    = fx + 0;
        int   ty    = fy - 1;
        super.move(fx, fy, tx, ty);
        super.solveAction();
    }
}
@SuppressWarnings("serial")
class RightKey extends Key
{
    public RightKey(Puzzle puzzle)
    {
        super(puzzle);
    }
    @Override
    public void actionPerformed(ActionEvent Ae)
    {
        Point point = super.getPoint();
        int   fx    = point.x;
        int   fy    = point.y;
        int   tx    = fx + 0;
        int   ty    = fy + 1;
        super.move(fx, fy, tx, ty);
        super.solveAction();
    }
}
class ListenAction implements ActionListener
{
    Puzzle puzzle;
    public ListenAction(Puzzle puzzle)
    {
        this.puzzle = puzzle;
    }
    public Point getPoint(Button button)
    {
        Point point = new Point();

        outLoop: for (int x = 0; x <= puzzle.config.Row; ++x)
        {
            for (int y = 0; y <= puzzle.config.Col; ++y)
            {
                if (puzzle.buttons[x][y] == button)
                {
                    point.x = x;
                    point.y = y;
                    break outLoop;
                }
            }
        }
        return point;
    }
    public void prediction(int px, int py, int ax, int ay)
    {
        if ((px >= 0 && px <= puzzle.config.Row) && (py >= 0 && py <= puzzle.config.Col))
        {
            if (puzzle.buttons[px][py].getText().equals(puzzle.lastButtonText))
            {
                puzzle.buttons[px][py].setText(puzzle.buttons[ax][ay].getText());
                puzzle.buttons[ax][ay].setText(puzzle.lastButtonText);
                puzzle.buttons[px][py].setIcon(puzzle.buttons[ax][ay].getIcon());
                puzzle.buttons[ax][ay].setIcon(puzzle.lastButtonIcon);
            }
        }
    }

    /**
     * solve action
     */
    public void solveAction()
    {
        Integer index = Integer.valueOf(0);

        for (Button[] buttons : puzzle.buttons)
        {
            for (Button button : buttons)
            {
                if (!button.getText().equals((++index).toString()))
                {
                    if (button != puzzle.buttons[puzzle.config.Row][puzzle.config.Col])
                    {
                        return;
                    }
                }
            }
        }

        puzzle.winAction.accept(puzzle);
    }
    @Override
    public void actionPerformed(ActionEvent Ae)
    {
        Button button = (Button) (Ae.getSource());
        Point  point  = getPoint(button);
        prediction(point.x - 1, point.y, point.x, point.y);
        prediction(point.x + 1, point.y, point.x, point.y);
        prediction(point.x, point.y - 1, point.x, point.y);
        prediction(point.x, point.y + 1, point.x, point.y);
        this.solveAction();
    }
}
@SuppressWarnings("serial")
public class Puzzle extends JPanel
{
    Config config;
    BIcons bicons;
    Button[][] buttons;
action winAction;
    String lastButtonText;
    ImageIcon lastButtonIcon;
    ListenAction LisAction;
    private void createButton()
    {
        for (int i = 0, idx = 1; i <= config.Row; ++i)
        {
            for (int j = 0; j <= config.Col; ++j, ++idx)
            {
                ImageIcon icon     = bicons.getIcon(i, j);
                String text        = String.valueOf(idx);
                this.buttons[i][j] = new Button(config, icon, text);
            }
        }
    }
    private void shuffleButton()
    {
        for (int i = 0; i < config.Row; ++i)
        {
            java.util.List<Button> Array = Arrays.asList(buttons[i]);
            Collections.shuffle(Array);
            buttons[i] = Array.toArray(buttons[i]);
        }
    }
    private void addButton()
    {
        for (Button[] buttns : buttons)
        {
            for (Button buttn : buttns)
            {
                buttn.addActionListener(LisAction);
                super.add(buttn);
            }
        }
    }
    private void KeyHear()
    {
        final String     UP = "KEY_UP";
        final String   DOWN = "KEY_DOWN";
        final String   LEFT = "KEY_LEFT";
        final String  RIGHT = "KEY_RIGHT";
        final int       IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
        final InputMap   Im = super.getInputMap(IFW);
        final ActionMap  Am = super.getActionMap();
        Im.put(KeyStroke.getKeyStroke("UP"), UP);
        Im.put(KeyStroke.getKeyStroke("W"), UP);
        Im.put(KeyStroke.getKeyStroke("DOWN"), DOWN);
        Im.put(KeyStroke.getKeyStroke("S"), DOWN);
        Im.put(KeyStroke.getKeyStroke("LEFT"), LEFT);
        Im.put(KeyStroke.getKeyStroke("A"), LEFT);
        Im.put(KeyStroke.getKeyStroke("RIGHT"), RIGHT);
        Im.put(KeyStroke.getKeyStroke("D"), RIGHT);
        Am.put(UP, new UpKey(this));
        Am.put(DOWN, new DownKey(this));
        Am.put(LEFT, new LeftKey(this));
        Am.put(RIGHT, new RightKey(this));
    }
    private void makeProcess()
    {
        this.createButton();
        this.shuffleButton();
        this.addButton();
        this.KeyHear();
        this.lastButton(true);
    }
    public Puzzle(Config config, String file, action winAction) throws IOException
    {
        this.setLayout(new GridLayout(config.Row + 1, config.Col + 1));
        this.config     = config;
        this.bicons     = new BIcons(config, file);
        this.buttons    = new Button[config.Row + 1][config.Col + 1];
        this.winAction  = winAction;
        this.LisAction  = new ListenAction(this);
        this.makeProcess();
    }
    public Puzzle(Config config, BufferedImage image, action winAction)
    {
        this.setLayout(new GridLayout(config.Row + 1, config.Col + 1));
        this.config     = config;
        this.bicons     = new BIcons(config, image);
        this.buttons    = new Button[config.Row + 1][config.Col + 1];
        this.winAction  = winAction;
        this.LisAction  = new ListenAction(this);
        this.makeProcess();
    }
    public Puzzle(Config config, Color color, action winAction)
    {
        this.setLayout(new GridLayout(config.Row + 1, config.Col + 1));
        this.config     = config;
        this.bicons     = new BIcons(config, color);
        this.buttons    = new Button[config.Row + 1][config.Col + 1];
        this.winAction  = winAction;
        this.LisAction  = new ListenAction(this);
        this.makeProcess();
    }
    public void lastButton(boolean view)
    {
        lastButtonText = "";
        lastButtonIcon = null;
        if (!view)
        {
            lastButtonText = String.valueOf((config.Row + 1) * (config.Col + 1));
            lastButtonIcon = bicons.getIcon(config.Row, config.Col);
        }
        buttons[config.Row][config.Col].setText(lastButtonText);
        buttons[config.Row][config.Col].setIcon(lastButtonIcon);
    }
}