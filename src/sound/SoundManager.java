package sound;


import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static Map<String, AudioClip> soundEffects = new HashMap<>();
    private static MediaPlayer backgroundMusic;

    public static void loadSoundEffect(String name, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Sound file not found: " + filePath);
            return;
        }
        soundEffects.put(name, new AudioClip(file.toURI().toString()));
    }

    public static void playSoundEffect(String name) {
        AudioClip sound = soundEffects.get(name);
        if (sound != null) {
            sound.play();
        } else {
            System.out.println("Sound effect not found: " + name);
        }
    }

    public static void playBackgroundMusic(String fileName, double volume, boolean loop) {
        try {
            // Use getResource to load from src/
            String path = SoundManager.class.getResource("/sound/" + fileName).toExternalForm();
            Media media = new Media(path);
            backgroundMusic = new MediaPlayer(media);
            backgroundMusic.setVolume(volume);
            if (loop) {
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            }
            backgroundMusic.play();
        } catch (Exception e) {
            System.out.println("Error loading music: " + fileName);
            e.printStackTrace();
        }
    }


    public static void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }


    public static void setMusicVolume(double volume) {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(volume);
        }
    }
}
