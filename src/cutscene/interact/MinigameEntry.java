package cutscene.interact;

import javafx.application.Platform;
import logic.GameStateList;
import player.Player;
import utils.AlertUtils;

public class MinigameEntry extends InteractableObject {
	private Player player = Player.getInstance();
	
	public MinigameEntry(double x, double y, GameStateList gameState, String description) {
		super(x, y, DEFAULT_SPAN_X, DEFAULT_SPAN_Y, gameState, description);
	}

	public MinigameEntry(double x, double y, int spanX, int spanY, GameStateList gameState, String description) {
		super(x, y, spanX, spanY, gameState, description);
	}

	@Override
	public void interact() {
		Platform.runLater(() -> {
			GameStateList gameState = player.getGameState();
			
			switch (gameState) {
			case STAGE_01__ISCALE_ROOM:
			case STAGE_04__ELEVATOR:
			case STAGE_06__LARN_GEAR:
			case STAGE_08__COMPUTER_CENTER:
			case STAGE_12__DATABASE:
			case STAGE_16__LAST_BOSS: {
				AlertUtils.setMinigameAlert(gameState, "Are you sure? You have to collect 20 scores to pass this minigame.");
				break;
			}
			case STAGE_15__GUILD_ROOM: {
				AlertUtils.setMinigameAlert(gameState, "This is a little bit different from before. May be, we can try it.");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + gameState);
			}
		});
	}

	@Override
	public boolean gameStateCondition(GameStateList playerGameState) {
		System.out.println("Check for GameState: " + playerGameState);
		return this.getGameState() == playerGameState;
	}

}
