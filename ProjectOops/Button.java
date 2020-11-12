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
import javax.swing.*;
@SuppressWarnings("serial")
class Button extends JButton
{
    public Button(Config config,ImageIcon icon,String text)
    {
        super.setIcon(icon);
        super.setText(text);
        super.setFont(config.FontType);
        super.setBorder(config.BBorder);
        super.setForeground(config.FontColor);
        super.setSize(config.Width, config.Height);
        super.setVerticalTextPosition(config.VerButTextPos);
        super.setHorizontalTextPosition(config.HorButTextPos);
    }
}