package cutscene.interact;

import player.Player;

public abstract class InteractableObject {
	private double x, y;
	private int gameState;
	private String name;

	public InteractableObject(double x, double y, int gameState, String name) {
		this.x = x;
		this.y = y;
		this.gameState = gameState;
		this.name = name;
	}

	public boolean isPlayerNear(Player player) {
		return Math.abs(player.getX() - x) < 50 && Math.abs(player.getY() - y) < 50;
	}

	public abstract void interact();

	public abstract boolean gameStateCondition(int playerGameState);

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
