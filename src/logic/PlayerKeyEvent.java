package logic;

import java.util.Arrays;
import java.util.List;

import gui.game_component.interact.EasterEgg;
import gui.game_component.interact.InteractableObject;
import gui.game_component.interact.MinigameEntry;
import gui.game_component.interact.NextRoomEntry;
import gui.game_component.interact.WithdrawEntry;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import map.GameMap;
import player.Direction;
import player.Player;

public class PlayerKeyEvent {

	public static void addKeyEvent(Scene scene, ImageView playerImage, GameMap mapImage) {
		List<InteractableObject> interactables = Arrays.asList(
				new MinigameEntry(410, 370, 1, "IscaleMinigame"),
				new NextRoomEntry(300, 100, 2, "toHall"), 
				new NextRoomEntry(865, 250, 3, "toElevator"),
				new MinigameEntry(435, 310, 4, "ElevatorMinigame"), 
				new NextRoomEntry(315, 310, 5, "toLangear"),
				new MinigameEntry(205, 230, 6, "LangearMinigame"), 
				new MinigameEntry(155, 180, 6, "LangearMinigame"),
				new NextRoomEntry(25, -45, 7, "toFloor1"),
				new NextRoomEntry(870, -45, 7, "toComputerCenter"),
				new MinigameEntry(530, 40, 7, "ComCenMinigame"), 
				new NextRoomEntry(260, 420, 7, "outComputerCenter"),
				new NextRoomEntry(89, 0, 8, "toDatabase"), 
				new EasterEgg(485, 35, 8, "CroissantPicture"),
				new NextRoomEntry(830, 0, 10, "toGuild"), 
				new MinigameEntry(220, 270, 9, "DatabaseMinigame"),
				new NextRoomEntry(785, 385, 10, "toHall"),
				new WithdrawEntry(340, 40, 9999) // Game Over
		);

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
