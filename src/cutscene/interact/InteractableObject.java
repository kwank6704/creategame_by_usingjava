package cutscene.interact;

import logic.GameStateList;
import player.Player;

public abstract class InteractableObject {
	protected double x, y;
	protected GameStateList gameState;
	protected String description;
	protected int spanX;
	protected int spanY;
	
	protected static final int DEFAULT_SPAN_X = 50;
	protected static final int DEFAULT_SPAN_Y = 50;

	public InteractableObject(double x, double y, int spanX, int spanY, GameStateList gameState, String description) {
		this.setX(x);
		this.setY(y);
		this.setSpanX(spanX);
		this.setSpanY(spanY);
		this.setGameState(gameState);
		this.setDescription(description);
	}

	public boolean isPlayerNear(Player player) {
		return Math.abs(player.getX() - x) < this.spanX && Math.abs(player.getY() - y) < this.spanY;
	}

	public abstract void interact();

	public abstract boolean gameStateCondition(GameStateList playerGameState);

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

	public GameStateList getGameState() {
		return gameState;
	}

	public void setGameState(GameStateList gameState) {
		this.gameState = gameState;
	}

	public String getName() {
		return description;
	}

	public void setDescription(String name) {
		this.description = name;
	}

	public int getSpanX() {
		return spanX;
	}

	public void setSpanX(int spanX) {
		this.spanX = spanX;
	}

	public int getSpanY() {
		return spanY;
	}

	public void setSpanY(int spanY) {
		this.spanY = spanY;
	}
}
