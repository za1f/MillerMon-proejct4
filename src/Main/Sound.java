package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;


public class
Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/item_pickup1.wav");
        soundURL[1] = getClass().getResource("/sound/town1music.wav");
        soundURL[2] = getClass().getResource("/sound/titleMusic1.wav");
        soundURL[3] = getClass().getResource("/sound/homeMusic.wav");
        soundURL[4] = getClass().getResource("/sound/select.wav");
        soundURL[5] = getClass().getResource("/sound/select.wav");
        soundURL[6] = getClass().getResource("/sound/girlSpeak.wav");
        soundURL[7] = getClass().getResource("/sound/girlSpeak1.wav");
        soundURL[8] = getClass().getResource("/sound/lab.wav");
        soundURL[9] = getClass().getResource("/sound/pokemoncenter.wav");
        soundURL[10] = getClass().getResource("/sound/noSilenceHome.wav");
        soundURL[11] = getClass().getResource("/sound/mart.wav");
        soundURL[12] = getClass().getResource("/sound/pauseMusic.wav");

    }

    public void setFile(int i){
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e){
        }
    }
    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
