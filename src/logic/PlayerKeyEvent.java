package logic;

import java.util.List;

import cutscene.interact.InteractableObject;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import map.GameMap;
import player.Direction;
import player.Player;
import utils.GameInstanceUtils;

public class PlayerKeyEvent {

	public static void addKeyEvent(Scene scene, ImageView playerImage, GameMap mapImage) {	
		List<InteractableObject> interactables = GameStateList.getInteractableList();

		scene.setOnKeyPressed(keyEvent -> {
			Player player = Player.getInstance();
			KeyCode key = keyEvent.getCode();

			if (player.getEnableMove()) {
				switch (key) {
				case KeyCode.W: {
					player.updateMoveImage(Direction.UP, mapImage);
					player.setVelY(-1 * player.getSpeed());
					break;
				}
				case KeyCode.A: {
					player.updateMoveImage(Direction.LEFT, mapImage);
					player.setVelX(-1 * player.getSpeed());
					break;
				}
				case KeyCode.S: {
					player.updateMoveImage(Direction.DOWN, mapImage);
					player.setVelY(1 * player.getSpeed());
					break;
				}
				case KeyCode.D: {
					player.updateMoveImage(Direction.RIGHT, mapImage);
					player.setVelX(1 * player.getSpeed());
					break;
				}
				case KeyCode.E: {
					System.out.println("X: " + player.getX() + " | Y: " + player.getY());
					for (InteractableObject obj: interactables) {
						if (obj.isPlayerNear(player) && obj.gameStateCondition(player.getGameState())) {
							obj.interact();
							break;
						}
					}
				}
				default:
					break;
				}

				Platform.runLater(() -> {
					playerImage.setImage(player.getMoveImage());
				});
			}

			if (key == KeyCode.SPACE) {
				StackPane gamePane = GameInstanceUtils.getGamePane();

				MouseEvent mouseEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1,
						false, false, false, false, false, false, false, false, false, false, null);
				gamePane.fireEvent(mouseEvent);
			}
		});

		scene.setOnKeyReleased(keyEvent -> {
			Player player = Player.getInstance();
			KeyCode key = keyEvent.getCode();

			if (player.getEnableMove()) {
				switch (key) {
				case KeyCode.W: {
					player.setVelY(0);
					break;
				}
				case KeyCode.A: {
					player.setVelX(0);
					break;
				}
				case KeyCode.S: {
					player.setVelY(0);
					break;
				}
				case KeyCode.D: {
					player.setVelX(0);
					break;
				}
				default:
					break;
				}
			}
		});
	}
}
