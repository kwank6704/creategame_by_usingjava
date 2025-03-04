package logic;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import player.Direction;
import player.Player;

public class PlayerKeyEvent {
	public static void addKeyEvent(Scene scene, ImageView playerImage) {
		scene.setOnKeyPressed(keyEvent -> {
			Player player = Player.getInstance();
			if (keyEvent.getCode() == KeyCode.W) {
				player.updateMoveImage(Direction.UP);
				player.setVelY(-1 * player.getSpeed());
			} else if (keyEvent.getCode() == KeyCode.A) {
				player.updateMoveImage(Direction.LEFT);
				player.setVelX(-1 * player.getSpeed());
			} else if (keyEvent.getCode() == KeyCode.S) {
				player.updateMoveImage(Direction.DOWN);
				player.setVelY(1 * player.getSpeed());
			} else if (keyEvent.getCode() == KeyCode.D) {
				player.updateMoveImage(Direction.RIGHT);
				player.setVelX(1 * player.getSpeed());
			}
			
			Platform.runLater(() -> {
				playerImage.setImage(player.getMoveImage());
			});
		});

		scene.setOnKeyReleased(keyEvent -> {
			Player player = Player.getInstance();
			
			if (keyEvent.getCode() == KeyCode.W) {
				player.setVelY(0);
			} else if (keyEvent.getCode() == KeyCode.A) {
				player.setVelX(0);
			} else if (keyEvent.getCode() == KeyCode.S) {
				player.setVelY(0);
			} else if (keyEvent.getCode() == KeyCode.D) {
				player.setVelX(0);
			}
		});
	}
}
