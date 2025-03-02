package player;

import javafx.scene.image.Image;

public class Player {
	private static Player playerInstance;
	private Gender gender;
	private Direction direction;
	private int moveImageNumber;
	private Image moveImage;
	private int health;
	private int energy;
	private int speed;

	public Player(Gender gender) {
		this(gender, 5, 5, Direction.DOWN);
	}

	public Player(Gender gender, int health, int energy, Direction direction) {
		super();
		this.gender = gender;
		setHealth(health);
		setEnergy(energy);
		setSpeed(10);
		setDirection(direction);
		setMoveImageNumber(1);
		setMoveImage();
	}

	public void updateMoveImage(Direction newDirection) {
		if (this.direction != newDirection) {
			setDirection(newDirection);
		} else {
			move();
		}
		
		setMoveImage();
	}

	public void move() {
		this.moveImageNumber = this.moveImageNumber == 3 ? 1 : this.moveImageNumber + 1;
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
		if (energy < 5)
			energy = 5;
		else if (energy > 20)
			energy = 20;
		
		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getMoveImageNumber() {
		return moveImageNumber;
	}

	public void setMoveImageNumber(int moveImageNumber) {
		this.moveImageNumber = ((moveImageNumber - 1) % 3) + 1;
	}

	public Image getMoveImage() {
		return moveImage;
	}

	public void setMoveImage() {
		String imageNumberString = Integer.toString(getMoveImageNumber());
		String imagePath = "/player_image/" + gender + "_walk_" + direction + "_" + imageNumberString + ".png";

		this.moveImage = new Image(getClass().getResource(imagePath).toExternalForm());
	}

	public Gender getGender() {
		return gender;
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
