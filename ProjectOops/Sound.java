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
import javax.sound.sampled.*;

class Sound1
{
    private Clip clip;
    public Sound1(String FileName) throws Exception
    {
        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(FileName)));
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void start()
    {
        clip.start();
    }
}

public class Sound
{
    public static void main(String []args) throws Exception
    {
        Sound1 sound = new Sound1("C:\\Users\\Public\\Music\\Sample Music\\f.wav");
        sound.start();
    }
}
