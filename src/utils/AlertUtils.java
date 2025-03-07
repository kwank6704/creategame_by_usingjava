package utils;

import application.MainApplication;
import cutscene.EndCredits;
import cutscene.interact.NextRoomList;
import gui.game_component.MainGamePane;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.GameStateList;
import logic.PlayerKeyEvent;
import map.GameMap;
import minigame.CroissantGame;
import sound.SoundManager;

public class AlertUtils {
	private static int noCount = 0;
	
	public static void setNextRoomAlert(NextRoomList nextRoom) {
		SoundManager.playSoundEffect();
		
		String comfirmationText = nextRoom.getConfirmationText();
		Platform.runLater(() -> {			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, comfirmationText, ButtonType.YES, ButtonType.NO);
			alert.setHeaderText(null);
			
			if (nextRoom.equals(NextRoomList.TO_END_CREDITS)) {
				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.NO) return;
					MainApplication.getCurrentStage().setScene(EndCredits.buildEndCredits());
				});
			} else if (nextRoom.equals(NextRoomList.TO_GUILD_ROOM_LOCKED)) {
				alert.showAndWait();
				return;
			} else {
				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.NO) return;
					
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
			}
		});
	}
	
	public static void setMinigameAlert(GameStateList currentGameState, String comfirmationText) {
		SoundManager.playSoundEffect();
		
		Platform.runLater(() -> {
			ButtonType winButton = new ButtonType("Let's Play 🎮", ButtonBar.ButtonData.YES);
			ButtonType loseButton = new ButtonType("Nah, not now", ButtonBar.ButtonData.NO);
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, comfirmationText, winButton, loseButton);
			
			alert.setTitle("🎮 Minigame Time !!");
			alert.setHeaderText("✨ Your fate is in your hands! ✨");
			
			alert.getDialogPane().lookupButton(winButton)
				.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
			alert.getDialogPane().lookupButton(loseButton)
				.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
			
			alert.showAndWait().ifPresent(response -> {
				if (response == winButton) {
					Scene mainScene = GameInstanceUtils.getCurrentScene();
						
					Scene minigameScene = (new CroissantGame()).startMinigame(currentGameState, mainScene);
					MainApplication.getCurrentStage().setScene(minigameScene);
				}
			});
		});
	}
	
	public static void setAlertWithExit(String comfirmationText) {
		SoundManager.playSoundEffect();
		
		Platform.runLater(() -> {
			if (noCount >= 5) {
				ButtonType anotherYES = new ButtonType("ALSO YES", ButtonBar.ButtonData.YES);
				
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "NOW !!!", ButtonType.YES, anotherYES);
				
				alert.getDialogPane().lookupButton(ButtonType.YES)
					.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
				alert.getDialogPane().lookupButton(anotherYES)
					.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
				
				alert.setHeaderText(null);
				alert.showAndWait().ifPresent(response -> {					
						noCount = 0;
						(new MainApplication()).resetGame();
				});
			} else {				
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION, comfirmationText, ButtonType.YES, ButtonType.NO);
				
				alert.getDialogPane().lookupButton(ButtonType.YES)
					.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
				alert.getDialogPane().lookupButton(ButtonType.NO)
					.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
			
				alert.setHeaderText(null);
				alert.showAndWait().ifPresent(response -> {					
					if (response == ButtonType.YES) {
						noCount = 0;
						(new MainApplication()).resetGame();
					} else if (response == ButtonType.NO) {
						noCount++;
						if (noCount >= 3) setAlertWithExit("JUST RESIGN !!");
						else setAlertWithExit(comfirmationText);
					}
				});
			}
		});
	}
}
