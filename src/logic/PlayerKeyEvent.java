package logic;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import player.Direction;
import player.Player;

public class PlayerKeyEvent {
	public static void addKeyEvent(Scene scene) {
		scene.setOnKeyPressed(keyEvent -> {
			Player player = Player.getInstance();
			if (keyEvent.getCode() == KeyCode.W) player.updateMoveImage(Direction.UP);
			else if (keyEvent.getCode() == KeyCode.A) player.updateMoveImage(Direction.LEFT);
			else if (keyEvent.getCode() == KeyCode.S) player.updateMoveImage(Direction.DOWN);
			else if (keyEvent.getCode() == KeyCode.D) player.updateMoveImage(Direction.RIGHT);
			
			BorderPane root = (BorderPane) scene.getRoot();
			StackPane gamePane = (StackPane) root.getCenter();
			GridPane playerPane = (GridPane) gamePane.getChildren().get(1);
			((ImageView) playerPane.getChildren().get(0)).setImage(player.getMoveImage());
		});
		
		scene.setOnKeyPressed(keyEvent -> {
			Player player = Player.getInstance();
			if (keyEvent.getCode() == KeyCode.W) {
				player.updateMoveImage(Direction.UP);
			}
			else if (keyEvent.getCode() == KeyCode.A) { 
				player.updateMoveImage(Direction.LEFT);
			}
			else if (keyEvent.getCode() == KeyCode.S) {
				player.updateMoveImage(Direction.DOWN);
			}
			else if (keyEvent.getCode() == KeyCode.D) {
				player.updateMoveImage(Direction.RIGHT);
			}
			
			BorderPane root = (BorderPane) scene.getRoot();
			StackPane gamePane = (StackPane) root.getCenter();
			GridPane playerPane = (GridPane) gamePane.getChildren().get(1);
			((ImageView) playerPane.getChildren().get(0)).setImage(player.getMoveImage());
		});
	}
}
