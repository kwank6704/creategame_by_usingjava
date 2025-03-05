package cutscene;

import player.Player;

public class SetGameStateAction implements Actionable{
	private int gameState;
	private Player player = Player.getInstance();
	
	public SetGameStateAction(int gameState) {
		this.gameState = gameState;
		
	}

	@Override
	public void execute(Runnable onComplete) {
		player.setGameState(gameState);
		onComplete.run();
	}

}
