package utilities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;

public class SoundManager {
    public static ArrayList<Clip> MUSIC;
    public static ArrayList<Clip> HIT;
    public static ArrayList<Clip> STUN;
    public static Clip KNOCKDOWN;
    public static Clip HEAVYSTUN;
    public static Clip ATTACK;
    public static Clip FIGHT;
    public static Clip DEATH;

    public static void playClip(Clip clip) {
        clip.setFramePosition(0);
        clip.start();
    }

    public static void stopClip(Clip clip) {
        clip.stop();
    }

    private Clip getClip(String file) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Code/sounds/" + file + ".wav"));
            clip.open(audio);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public SoundManager() {
        MUSIC = new ArrayList<>();
        MUSIC.add(getClip("theme1"));
        MUSIC.add(getClip("theme2"));
        MUSIC.add(getClip("theme3"));
        MUSIC.add(getClip("theme4"));

        HIT = new ArrayList<>();
        HIT.add(getClip("hit1"));
        HIT.add(getClip("hit2"));

        STUN = new ArrayList<>();
        STUN.add(getClip("stun1"));
        STUN.add(getClip("stun2"));
        STUN.add(getClip("stun3"));

        KNOCKDOWN = getClip("knockdown");
        HEAVYSTUN = getClip("heavystun");
        ATTACK = getClip("attack");
        FIGHT = getClip("fight");
        DEATH = getClip("death");
    }
}
