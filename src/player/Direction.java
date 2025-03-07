package player;

public enum Direction {
	LEFT, RIGHT, UP, DOWN;
	
	@Override
	public String toString() {
		switch (this) {
		case LEFT: {
			return "left";
		}
		case RIGHT: {
			return "right";
		}
		case UP: {
			return "up";
		}
		case DOWN: {
			return "down";
		}
		default:
			return "down";
		}
	}
}
