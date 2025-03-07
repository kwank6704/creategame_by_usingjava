package cutscene.interact;

import logic.GameStateList;
import utils.AlertUtils;

public class WithdrawEntry extends InteractableObject {	
	public WithdrawEntry(double x, double y, GameStateList gameState, String description) {
		super(x, y, DEFAULT_SPAN_X, DEFAULT_SPAN_Y, gameState, description);
		System.out.println("withdraw");
	}
	
	public WithdrawEntry(double x, double y, int spanX, int spanY, GameStateList gameState, String description) {
		super(x, y, spanX, spanY, gameState, description);
	}

	@Override
	public void interact() {
		AlertUtils.setAlertWithExit("See You Next Semester");
	}

	@Override
	public boolean gameStateCondition(GameStateList playerGameState) {
		return playerGameState == GameStateList.STAGE_99__GAME_OVER;
	}

}
