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
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
class BIcons
{
    private ImageIcon[][] icons;
    private void initFields(int row, int col)
    {
        this.icons = new ImageIcon[row][col];
    }
    private static BufferedImage getImage(String file) throws IOException
    {
        return ImageIO.read(new File(file));
    }
    private static BufferedImage getImage(Color color)
    {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D gra = img.createGraphics();
        gra.setPaint(color);
        gra.fillRect(0, 0, 100, 100);
        return img;
    }
    private void makeIcons(BufferedImage image, Config config)
    {
        int h = image.getHeight()/(config.Row + 1);
        int w = image.getWidth()/(config.Col + 1);
        int x = 0, y = 0;
        for (int i = 0; i <= config.Row; ++i, x = 0, y += h)
        {
            for (int j = 0; j <= config.Col; ++j, x += w)
            {
                BufferedImage RImg = image.getSubimage(x, y, w, h);
                Image SImg = RImg.getScaledInstance(config.Height, config.Width, BufferedImage.TYPE_INT_RGB);
                icons[i][j] = new ImageIcon(SImg);
            }
        }
    }
    public BIcons(Config config,BufferedImage image)
    {
        this.initFields(config.Row + 1, config.Col + 1);
        this.makeIcons(image, config);
    }
    public BIcons(Config config, String file) throws IOException
    {
        this(config, getImage(file));
    }
    public BIcons(Config config, Color color)
    {
        this(config, getImage(color));
    }
    public ImageIcon getIcon(int x, int y)
    {
        return this.icons[x][y];
    }
}
