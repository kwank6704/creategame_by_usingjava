package gui.game_component.interact;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import player.Player;

public class EasterEgg extends InteractableObject {
	Player player = Player.getInstance();

	public EasterEgg(double x, double y, int gameState, String name) {
		super(x, y, gameState, name);
	}

	@Override
	public void interact() {
		Platform.runLater(() -> {
			if (player.getGameState() == 8) {
				if (this.getName().equals("CroissantPicture")) {
					CroissantPicture();
				}
			}
		});
	}

	private void CroissantPicture() {
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "GameState has been set to 10 \n (How do you know this cheat code???)", ButtonType.YES, ButtonType.NO);

			alert.setHeaderText(null);
			alert.showAndWait();
			player.setGameState(10);
		});
	}

	@Override
	public boolean gameStateCondition(int playerGameState) {
		return this.getGameState() == playerGameState;
	}
}
