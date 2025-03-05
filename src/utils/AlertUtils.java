package utils;

import cutscene.interact.NextRoomList;
import gui.game_component.MainGamePane;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.PlayerKeyEvent;
import map.GameMap;

public class AlertUtils {
	public static void setNextRoomAlert(NextRoomList nextRoom) {
		String comfirmationText = nextRoom.getConfirmationText();
		Platform.runLater(() -> {			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, comfirmationText, ButtonType.YES, ButtonType.NO);
			alert.setHeaderText(null);
			alert.showAndWait().ifPresent(response -> {
				BorderPane mainGamePane = MainGamePane.getInstance();
				
				StackPane newGamePane = GameMapView.gameMapUpdate(nextRoom.getNextMap());
				mainGamePane.setCenter(newGamePane);
				
				StackPane gamePane = (StackPane) mainGamePane.getCenter();
				
				GameMap nextGameMap = (GameMap) gamePane.getChildren().get(0);
				
				Pane playerPane = (Pane) gamePane.getChildren().get(1);
				ImageView playerImage = (ImageView) playerPane.getChildren().get(0);
				
				Scene gameScene = mainGamePane.getScene();
				PlayerKeyEvent.addKeyEvent(gameScene, playerImage, nextGameMap);
			});
		});
	}
	
	public static void setAlertWithExit(String comfirmationText) {
		Platform.runLater(() -> {			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, comfirmationText, ButtonType.YES, ButtonType.NO);
			alert.setHeaderText(null);
			alert.showAndWait().ifPresent(response -> {
				Platform.exit();
			});
		});
	}
}
