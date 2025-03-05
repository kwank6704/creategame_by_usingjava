package cutscene.interact;

import player.Player;
import utils.AlertUtils;

public class WithdrawEntry extends InteractableObject {
	private Player player = Player.getInstance();

	public WithdrawEntry(double x, double y, int gameState) {
		super(x, y, gameState, "Withdraw");
	}

	@Override
	public void interact() {
//		Platform.runLater(() -> {
//			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "See You Next Semester", ButtonType.YES, ButtonType.NO);
//			
//			alert.setHeaderText(null);
//			alert.showAndWait().ifPresent(response -> {
//				Platform.exit();
//			});
//		});
		AlertUtils.setAlertWithExit("See You Next Semester");
	}

	@Override
	public boolean gameStateCondition(int playerGameState) {
		return player.getHealth() == 0 || player.getEnergy() == 0;
	}

}
