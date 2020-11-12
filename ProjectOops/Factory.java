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
import javax.swing.*;
public class Factory
{
    public static Config getClassicEmptyBorderConfig(int row, int col)
    {
        Config CLASSIC_EMPTY_BORDER_CONFIG = new Config();
        CLASSIC_EMPTY_BORDER_CONFIG.Row = row;
        CLASSIC_EMPTY_BORDER_CONFIG.Col = col;
        CLASSIC_EMPTY_BORDER_CONFIG.Height = 250;
        CLASSIC_EMPTY_BORDER_CONFIG.Width = 140;
        CLASSIC_EMPTY_BORDER_CONFIG.FontType = new Font("Arial", Font.BOLD, 20);
        CLASSIC_EMPTY_BORDER_CONFIG.FontColor = Color.WHITE;
        CLASSIC_EMPTY_BORDER_CONFIG.HorButTextPos = JButton.CENTER;
        CLASSIC_EMPTY_BORDER_CONFIG.VerButTextPos = JButton.CENTER;
        CLASSIC_EMPTY_BORDER_CONFIG.BBorder = BorderFactory.createEmptyBorder();
        return CLASSIC_EMPTY_BORDER_CONFIG;
    }
    public static Config getClassicWhiteBorderConfig(int row, int col)
    {
        Config CLASSIC_WHITE_BORDER = new Config();
        CLASSIC_WHITE_BORDER.Row = row;
        CLASSIC_WHITE_BORDER.Col = col;
        CLASSIC_WHITE_BORDER.Height = 250;
        CLASSIC_WHITE_BORDER.Width = 140;
        CLASSIC_WHITE_BORDER.FontType = new Font("Arial", Font.BOLD, 20);
        CLASSIC_WHITE_BORDER.FontColor = Color.WHITE;
        CLASSIC_WHITE_BORDER.HorButTextPos = JButton.CENTER;
        CLASSIC_WHITE_BORDER.VerButTextPos = JButton.CENTER;
        CLASSIC_WHITE_BORDER.BBorder = BorderFactory.createLineBorder(Color.WHITE);
        return CLASSIC_WHITE_BORDER;
    }
    public static action getPopUpWinAction(final JFrame jframe,final  String msg)
    {
        return new action(){
            @Override
            public void accept(Puzzle puzzle)
            {
                puzzle.lastButton(false);
                JOptionPane.showMessageDialog(jframe,msg);
                System.exit(0);
            }
        };
    }
}