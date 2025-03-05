package player;

import java.util.ArrayList;

import gui.game_component.RightPane;
import javafx.scene.image.Image;
import map.GameMap;
import potion.Potion;
import potion.PotionList;
import skill.GuildList;
import skill.Skill;
import skill.GuildUtils;

public class Player {
	private static Player playerInstance;

	private Gender gender;
	public static final Gender defaultGender = Gender.MALE;

	private Direction direction;
	public static final Direction defaultDirection = Direction.DOWN;

	private Image moveImage;
	private double moveImageNumber;
	public static final double MoveStep = 0.25;

	private int posX;
	private int posY;
	private int velX = 0;
	private int velY = 0;

	private int health;
	private int energy;
	private int speed;
	public static final int MaxHealth = 5;
	public static final int MaxEnergy = 5;
	public static final int MaxSpeed = 10;
	public static final int MinHealth = 0;
	public static final int MinEnergy = 0;
	public static final int MinSpeed = 1;
	public static final int DefaultSpeed = 5;

	private ArrayList<GuildList> playerGuilds;
	private static final int MaxGuilds = 3;
	
	private ArrayList<Potion> playerPotions;
	private static final int MaxPotions = 5;

	private static int gameState;
	public static final int StartGameState = 0;

	private static boolean enableMove = true;
	private static boolean hasGoToDatabase = false;
	private static boolean hasGoToComCenter = false;

	public Player(Gender gender) {
		this(gender, MaxHealth, MaxEnergy, defaultDirection);
	}

	public Player(Gender gender, int health, int energy, Direction direction) {
		super();
		this.gender = gender;
		this.health = health;
		this.energy = energy;

		setSpeed(DefaultSpeed);
		setDirection(direction);
		setMoveImageNumber(1);
		setMoveImage();

		setGameState(StartGameState);

		playerGuilds = new ArrayList<>();
		playerGuilds.add(GuildList.HEALER);
		playerGuilds.add(GuildList.MAGE);
		playerGuilds.add(GuildList.NECROMANCER);

		playerPotions = new ArrayList<Potion>();
		playerPotions.add(new Potion(PotionList.LARGE_HEAL));
		playerPotions.add(new Potion(PotionList.LARGE_ENERGY));
	}

	public static void buildPlayer(Gender gender) {
		playerInstance = new Player(gender);
	}

	public void move() {
		setMoveImageNumber(this.moveImageNumber > 3 ? 1 : (this.moveImageNumber + MoveStep));
	}

	public void updateMoveImage(Direction newDirection) {
		if (this.direction != newDirection) {
			setDirection(newDirection);
		} else {
			move();
		}

		setMoveImage();
	}

	public void updateMoveImage(Direction newDirection, GameMap currMap) {
		setX(getX() + getVelX());		/* Update Player's X-position */
		setY(getY() + getVelY());		/* Update Player's Y-position */
		currMap.wall();					/* Initialized the Wall in the Current GameMap */

		updateMoveImage(newDirection);	/* Update Player Image by Direction */
	}

	public void addGuild(GuildList guild) {
		playerGuilds.add(guild);
	}

	public boolean useSkill(GuildList guild) {
		Skill activeSkill = GuildUtils.createGuild(guild);
		if (activeSkill != null) {
			return activeSkill.activate(this);
		} else {
			return false;
		}
	}

	public boolean addPotion(Potion potion) {
		if (playerPotions.size() < MaxPotions) {
			playerPotions.add(potion);
			RightPane.refreshUI(RightPane.getPotionInstance(), playerPotions);
			return true;
		}

		return false;
	}

	public void usePotion(int index) {
		if (index >= 0 && index < playerPotions.size()) {
			Potion potion = playerPotions.get(index);
			potion.applyEffect(this);
			playerPotions.remove(index);
		}
	}

	public void changeHealth(int i) {
		setHealth(getHealth() + i);
	}

	public void changeEnergy(int i) {
		setEnergy(getEnergy() + i);
	}

	public void changeSpeed(int i) {
		setSpeed(getSpeed() + i);
	}

	public int getHealth() {
		return health;
	}

	public int getEnergy() {
		return energy;
	}

	public int getSpeed() {
		return speed;
	}

	public void setHealth(int health) {
		health = health < MinHealth ? MinHealth : health;
		health = health > MaxHealth ? MaxHealth : health;
		this.health = health;
	}

	public void setEnergy(int energy) {
		energy = energy < MinEnergy ? MinEnergy : energy;
		energy = energy > MaxEnergy ? MaxEnergy : energy;
		this.energy = energy;
	}

	public void setSpeed(int speed) {
		speed = speed < MinSpeed ? MinSpeed : speed;
		speed = speed > MaxSpeed ? MaxSpeed : speed;
		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public double getMoveImageNumber() {
		return moveImageNumber;
	}

	public void setMoveImageNumber(double moveImageNumber) {
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
		if (enableMove)
			this.posX = (int) posX;
	}

	public int getY() {
		return posY;
	}

	public void setY(double posY) {
		if (enableMove)
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

	public ArrayList<GuildList> getGuilds() {
		return playerGuilds;
	}

	public ArrayList<Potion> getPotions() {
		return playerPotions;
	}

	public Boolean getEnableMove() {
		return enableMove;
	}

	public void setEnableMove(Boolean enableMove) {
		Player.enableMove = enableMove;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		Player.gameState = gameState;
	}

	public Boolean getHasGoToDatabase() {
		return hasGoToDatabase;
	}

	public void setHasGoToDatabase(Boolean hasGoToDatabase) {
		Player.hasGoToDatabase = hasGoToDatabase;
	}

	public Boolean getHasGoToComCenter() {
		return hasGoToComCenter;
	}

	public void setHasGoToComCenter(Boolean hasGoToComCenter) {
		Player.hasGoToComCenter = hasGoToComCenter;
	}

	public static Player getInstance() {
		if (playerInstance == null)
			playerInstance = new Player(defaultGender);

		return playerInstance;
	}
}
