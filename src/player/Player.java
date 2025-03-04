package player;

import javafx.scene.image.Image;
import map.GameMap;

public class Player {
	private static Player playerInstance;
	private Gender gender;
	private Direction direction;
	private float moveImageNumber;
	private Image moveImage;
	private int health;
	private int energy;
	private int speed;
	private int posX;
	private int posY;
	private int velX = 0;
	private int velY = 0;

	public Player(Gender gender) {
		this(gender, 5, 5, Direction.DOWN);
	}

	public Player(Gender gender, int health, int energy, Direction direction) {
		super();
		this.gender = gender;
		setHealth(health);
		setEnergy(energy);
		setSpeed(5);
		setDirection(direction);
		setMoveImageNumber(1);
		setMoveImage();
	}
	
	public void updateMoveImage(Direction newDirection) {
		if (this.direction != newDirection)
			setDirection(newDirection);
		else
			move();

		setMoveImage();
	}

	public void updateMoveImage(Direction newDirection, GameMap currMap) {
		// Update player's position based on velocity
		double newX = getX() + getVelX();
		double newY = getY() + getVelY();

		// Set new position
		setX(newX);
		setY(newY);
		currMap.wall();

		updateMoveImage(newDirection);
	}

	public void move() {
		this.moveImageNumber = (float) (this.moveImageNumber >= 3 ? 1 : (this.moveImageNumber + 0.25));
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health < 0)
			health = 0;
		else if (health > 5)
			health = 5;

		this.health = health;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		if (energy < 0)
			energy = 0;
		else if (energy > 5)
			energy = 5;

		this.energy = energy;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		if (speed < 1)
			speed = 1;
		else if (speed > 10)
			speed = 10;

		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public float getMoveImageNumber() {
		return moveImageNumber;
	}

	public void setMoveImageNumber(float moveImageNumber) {
		this.moveImageNumber = moveImageNumber;
	}

	public Image getMoveImage() {
		return moveImage;
	}

	public void setMoveImage() {
		String imageNumberString = Integer.toString((int) Math.floor(getMoveImageNumber()));
		String imagePath = "/player_image/" + gender + "_walk_" + direction + "_" + imageNumberString + ".png";

		this.moveImage = new Image(getClass().getResource(imagePath).toExternalForm());
	}

	public Gender getGender() {
		return gender;
	}

	public int getX() {
		return posX;
	}

	public void setX(double posX) {
		this.posX = (int) posX;
	}

	public int getY() {
		return posY;
	}

	public void setY(double posY) {
		this.posY = (int) posY;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public static Player getInstance() {
		if (playerInstance == null) {
			playerInstance = new Player(Gender.MALE);
		}
		return playerInstance;
	}

	public static void buildPlayer(Gender gender) {
		playerInstance = new Player(gender);
	}

}
