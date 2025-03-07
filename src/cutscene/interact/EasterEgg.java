package cutscene.interact;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import logic.GameStateList;
import player.Player;

public class EasterEgg extends InteractableObject {
	private Player player = Player.getInstance();

	public EasterEgg(double x, double y, GameStateList gameState, String description) {
		super(x, y, DEFAULT_SPAN_X, DEFAULT_SPAN_Y, gameState, description);
	}
	
	public EasterEgg(double x, double y, int spanX, int spanY, GameStateList gameState, String description) {
		super(x, y, spanX, spanY, gameState, description);
	}

	@Override
	public void interact() {
		Platform.runLater(() -> {
			if (player.getGameState() == GameStateList.STAGE_11__BUILDING_4_HALL) {
				if (this.getName().equals("CroissantPicture")) {
					CroissantPicture();
				}
			}
		});
	}

	private void CroissantPicture() {
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "(How do you know this cheat code???)", ButtonType.YES, ButtonType.NO);

			alert.setHeaderText(null);
			alert.showAndWait();
			player.setGameState(GameStateList.STAGE_15__GUILD_ROOM);
		});
	}

	@Override
	public boolean gameStateCondition(GameStateList playerGameState) {
		return this.getGameState() == playerGameState;
	}
}
