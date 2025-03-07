package player;

import java.util.ArrayList;

import gui.game_component.LeftPane;
import gui.game_component.MainGamePane;
import gui.game_component.RightPane;
import gui.game_component.TopPane;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.GameStateList;
import logic.PlayerKeyEvent;
import map.GameMap;
import map.MapList;
import potion.Potion;
import skill.GuildList;
import skill.Skill;
import utils.GameMapView;
import skill.GuildUtils;

public class Player {
	private static Player playerInstance = new Player();

	private static Gender gender;
	public static final Gender DEFUALT_GENDER = Gender.MALE;

	private Direction direction;
	public static final Direction DEFUALT_DIRECTION = Direction.DOWN;

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
	public static final int MAX_HEALTH = 5;
	public static final int MAX_ENERGY = 5;
	public static final int MAX_SPEED = 10;
	public static final int MIN_HEALTH = 0;
	public static final int MIN_ENERGY = 0;
	public static final int MIN_SPEED = 1;
	public static final int DEFAULT_SPEED = 5;

	private static ArrayList<GuildList> playerGuilds;
	private static final int MAX_GUILDS = 3;
	
	private static ArrayList<Potion> playerPotions;
	private static final int MAX_POTIONS = 5;

	private static GameStateList gameState;
	public static final GameStateList START_GAME_STATE = GameStateList.STAGE_00__GAME_START;

	private static boolean enableMove = true;

	public static void buildPlayer(Gender gender) {
		Player.buildPlayer(gender, DEFUALT_DIRECTION);
	}
	
	public static void buildPlayer(Gender gender, Direction direction) {
		Player.gender = gender;
		
		playerInstance.setHealth(MAX_HEALTH);
		playerInstance.setEnergy(MAX_ENERGY);
		playerInstance.setSpeed(DEFAULT_SPEED);
		
		playerInstance.setDirection(direction);
		playerInstance.setMoveImageNumber(1);
		playerInstance.setMoveImage();
		
		playerInstance.setGameState(START_GAME_STATE);
		
		playerGuilds = new ArrayList<>();
		playerPotions = new ArrayList<Potion>();
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

	public boolean addGuild(GuildList guild) {	
		if (playerGuilds.size() < MAX_GUILDS) {
			playerGuilds.add(guild);
			LeftPane.refreshUI(LeftPane.getGuildInstance(), playerGuilds);
			return true;
		}

		return false;
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
		if (playerPotions.size() < MAX_POTIONS) {
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

	public boolean changeHealth(int i) {
		return setHealth(getHealth() + i);
	}

	public boolean changeEnergy(int i) {
		return setEnergy(getEnergy() + i);
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

	public boolean setHealth(int health) {
		if (health <= MIN_HEALTH) {
			this.health = MIN_HEALTH;
			TopPane.updateHealthBar(this.health, MAX_HEALTH);
			
			Platform.runLater(() -> {
				BorderPane mainGamePane = MainGamePane.getInstance();
				
				StackPane newGamePane = GameMapView.gameMapUpdate(MapList.TA_ROOM);
				mainGamePane.setCenter(newGamePane);
				
				StackPane gamePane = (StackPane) mainGamePane.getCenter();
				
				GameMap nextGameMap = (GameMap) gamePane.getChildren().get(0);
				
				Pane playerPane = (Pane) gamePane.getChildren().get(1);
				ImageView playerImage = (ImageView) playerPane.getChildren().get(0);
				
				Scene gameScene = mainGamePane.getScene();
				PlayerKeyEvent.addKeyEvent(gameScene, playerImage, nextGameMap);
				
				Player.getInstance().setGameState(GameStateList.STAGE_99__GAME_OVER);
			});
			return false;
		} else {			
			this.health = health > MAX_HEALTH ? MAX_HEALTH : health;
			TopPane.updateHealthBar(this.health, MAX_HEALTH);
			return true;
		}
	}

	public boolean setEnergy(int energy) {
		if (energy < MIN_ENERGY) {
			this.energy = MIN_ENERGY;
			TopPane.updateEnergyBar(this.energy, MAX_ENERGY);
			return changeHealth(energy - MIN_ENERGY);
		}
		
		this.energy = energy > MAX_ENERGY ? MAX_ENERGY : energy;
		TopPane.updateEnergyBar(this.energy, MAX_ENERGY);
		return true;
	}

	public void setSpeed(int speed) {
		speed = speed < MIN_SPEED ? MIN_SPEED : speed;
		speed = speed > MAX_SPEED ? MAX_SPEED : speed;
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
		if (enableMove && velY == 0)
			this.posX = (int) posX;
	}

	public int getY() {
		return posY;
	}

	public void setY(double posY) {
		if (enableMove && velX == 0)
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

	public boolean getEnableMove() {
		return enableMove;
	}

	public void setEnableMove(Boolean enableMove) {
		Player.enableMove = enableMove;
	}

	public GameStateList getGameState() {
		return gameState;
	}

	public void setGameState(GameStateList gameState) {
		Player.gameState = gameState;
		System.out.println("Set GameState to: " + gameState);
	}

	public static Player getInstance() {
		if (playerInstance == null)
			Player.buildPlayer(DEFUALT_GENDER);

		return playerInstance;
	}
}
