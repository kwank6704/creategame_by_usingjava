package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	public static final int DEFAULT_VOLUME = 50;
	
	private static MediaPlayer soundEffect;
	private static MediaPlayer backgroundMusic;

	public static void playSoundEffect() {
		Thread sound = new Thread(() -> {
			soundEffect.stop();
			soundEffect.seek(javafx.util.Duration.ZERO);
			soundEffect.play();
			
			try {
				Thread.sleep((long) soundEffect.getCycleDuration().toMillis());
				soundEffect.stop();				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
        
		sound.start();
	}
	
	public static void loadSoundEffect(String fileName, double volume) {
		try {
			String path = SoundManager.class.getResource("/sound/" + fileName).toExternalForm();
			Media media = new Media(path);
			soundEffect = new MediaPlayer(media);
			soundEffect.setVolume(volume);
			soundEffect.setCycleCount(MediaPlayer.INDEFINITE);
		} catch (Exception e) {
			System.out.println("Error loading music: " + fileName);
			e.printStackTrace();
		}
	}

	public static void playBackgroundMusic(String fileName, double volume, boolean loop) {
		try {
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
			backgroundMusic.setVolume(volume / 100);
		}
	}
}
