package cutscene;

import gui.game_component.ButtomPane;
import player.Player;

public class EnablePlayerMoveAction implements Actionable {
	private Player player = Player.getInstance();
	private boolean enableMove;

	public EnablePlayerMoveAction(Boolean enableMove) {
		this.enableMove = enableMove;
	}

	@Override
	public void execute(Runnable onComplete) {
		ButtomPane.setDialogue("");
		player.setEnableMove(enableMove);
		onComplete.run();
	}

}
