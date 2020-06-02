package com.g51.pokemon.view;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MusicPlayer {
    private Clip backgroundMusic;

    private Map<String, Clip> musics;

    private long clipTimePosition;

    public MusicPlayer() {
        this.clipTimePosition = 0;

        this.musics = new HashMap<>();

        loadMusic("StartMusic");
        loadMusic("Route");
        loadMusic("Battle");
    }

    public void loadMusic(String musicName) {
        try {
            File musicFile = new File(MusicPlayer.class.getResource("/soundtracks/" + musicName + ".wav").getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            musics.put(musicName, musicClip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBackgroundMusic(String musicName) {
        backgroundMusic = musics.get(musicName);
    }

    public void startMusic() {
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pauseMusic() {
        clipTimePosition = backgroundMusic.getMicrosecondPosition();
        backgroundMusic.stop();
    }

    public void resumeMusic() {
        backgroundMusic.setMicrosecondPosition(clipTimePosition);
        backgroundMusic.start();
    }

    public void stopMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    public void playSoundEffect(String sound) {
        try {
            File musicFile = new File(MusicPlayer.class.getResource("/soundtracks/" + sound + ".wav").getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip soundEffects = AudioSystem.getClip();
            soundEffects.open(audioInput);
            FloatControl gainControl = (FloatControl) soundEffects.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            soundEffects.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
