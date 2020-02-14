import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public  class Music {

    File fire, laser;

    Music(){

        fire = new File("fire.wav");
        laser = new File("laser.wav")

    }


    static void playSound(File s){
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(s);
            clip.open(ais);
            clip.start();


        } catch (Exception e) {
            System.out.println("oops");
            e.printStackTrace();

        }
    }

}
