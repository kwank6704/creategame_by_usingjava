package cutscene;

import player.Player;

public class MovePlayerAction implements Actionable {
	private Player player;
	private double targetX, targetY;

	public MovePlayerAction(Player player, double targetX, double targetY) {
		this.player = player;
		this.targetX = targetX;
		this.targetY = targetY;
	}

	@Override
	public void execute(Runnable onComplete) {
		player.setX(targetX);
		player.setY(targetY);
		onComplete.run(); 
	}
}
