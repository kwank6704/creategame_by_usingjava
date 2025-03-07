package minigame;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.GameStateList;
import player.Gender;
import player.Player;
import potion.Potion;
import potion.PotionList;
import skill.GuildList;
import utils.ImageUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import application.MainApplication;

public class CroissantGame {
	private static final int GAME_WIDTH = 1440;
	private static final int GAME_HEIGHT = 720;
	
	private static final int PLAYER_SIZE = 108;
	private static final int BASKET_SIZE = 168;
	private static final int OBSTACLE_SIZE = 108;
	
	private static final int CEILING_LEVEL = 36;
	private static final int FLOOR_LEVEL = 504;
	
	private static final int GRAVITY = 2;
	private static final int JUMP_STRENGTH = -12;
	private static final int MAX_JUMP_TIME = 90;
	
	private static final int WINING_SCORE = 20;
	
	private static final String PATH_DIRECTORY = "minigame/";
	
	private static final String PLAYER_MALE_PATH = PATH_DIRECTORY + "PLAYER_MALE.gif";
	private static final String PLAYER_FEMALE_PATH = PATH_DIRECTORY + "PLAYER_FEMALE.gif";
	
	private static final String CROISSANT_REGULAR_PATH = PATH_DIRECTORY + "CROISSANT_REGULAR.png";
	private static final String CROISSANT_LARGE_PATH = PATH_DIRECTORY + "CROISSANT_LARGE.png";
	private static final String CROISSANT_FLY_PATH = PATH_DIRECTORY + "CROISSANT_FLY.gif";
	
	private static final String BASKET_PATH = PATH_DIRECTORY + "Basket.png";
	private static final String BACKGOUND_PATH = PATH_DIRECTORY + "Background.png";
	private static final String BACKGOUND_REWARD_PATH = PATH_DIRECTORY + "Background-Reward.png";
	
	private static Font pixelFont = Font.loadFont(Thread.currentThread().getContextClassLoader().getResourceAsStream("font/pixelfont.otf"), 40);
	private static Font pixelFontLarge = Font.loadFont(Thread.currentThread().getContextClassLoader().getResourceAsStream("font/pixelfont.otf"), 60);

	private Player player = Player.getInstance();
	private Gender gender = player.getGender();
	
	private int playerVelocityY = 0;
	private ImageView playerImage;
	
	private boolean isJumping = false;
	private boolean isHoldingJump = false;
	private int jumpCounter = 0;
	
	private ArrayList<ImageView> obstacles = new ArrayList<>();
	private long lastObstacleTime = 0;
	
	private Random random = new Random();

	private ImageView background1;
	private ImageView background2;

	private int level = 1;
	private int score = 0;
	private Text scoreText;
	
	private Scene mainScene;
	
	private PotionList potion = null;
	private GuildList selectedGuild = null;
	private final GuildList[] guildOptions = new GuildList[3];
	private final ImageView[] guildImages = new ImageView[3];
	
	private static GameStateList currentGameState;

	AnimationTimer timer;

	public Scene startMinigame(GameStateList currentGameState, Scene mainScene) {
		this.mainScene = mainScene;
		CroissantGame.currentGameState = currentGameState;
		
		BorderPane root = new BorderPane();
		root.setPrefSize(GAME_WIDTH, GAME_HEIGHT);

		updateBackground(root);

		level = getLevel();

		playerImage = ImageUtils.createImageView(gender == Gender.MALE ? PLAYER_MALE_PATH : PLAYER_FEMALE_PATH, PLAYER_SIZE, PLAYER_SIZE);
		playerImage.setX(100);
		playerImage.setY(FLOOR_LEVEL);
		
		root.getChildren().add(playerImage);

		Scene scene = new Scene(root);
		
		scene.setOnKeyPressed(e -> {
			if (e.getCode().toString().equals("SPACE") && !isJumping) {
				isJumping = true;
				isHoldingJump = true;
				jumpCounter = 0;
				playerVelocityY = JUMP_STRENGTH;
				
				scene.setOnKeyPressed(null);
			}
		});

		scene.setOnKeyReleased(e -> {
			if (e.getCode().toString().equals("SPACE")) {
				isHoldingJump = false;
				
				scene.setOnKeyPressed(e2 -> {
					isJumping = true;
					isHoldingJump = true;
					jumpCounter = 0;
					playerVelocityY = JUMP_STRENGTH;
					
					scene.setOnKeyPressed(null);
				});
			}
		});
		
		scoreText = new Text(40, 60, "Score: 0");
		scoreText.setFont(pixelFont);
		root.getChildren().add(scoreText);
		
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
				checkCollisions();

				if (score >= WINING_SCORE) {
					checkWinCondition((BorderPane) playerImage.getParent());
				}
				
				if (now - lastObstacleTime > 8000000000L / Math.min(level, 4)) {
					spawnObstacles(root);
					lastObstacleTime = now;
				}

				moveBackground();
				scoreText.setText("Score: " + score);
			}
		};
		
		timer.start();

		return scene;
	}
	
	private static int getLevel() {
		switch (currentGameState) {
		case STAGE_01__ISCALE_ROOM:
			return 1;
		case STAGE_04__ELEVATOR:
			return 2;
		case STAGE_06__LARN_GEAR:
		case STAGE_15__GUILD_ROOM:
			return 3;
		case STAGE_12__DATABASE:
			return 4;
		case STAGE_08__COMPUTER_CENTER:
			return 5;
		case STAGE_16__LAST_BOSS:
			return 6;
		default:
			return 1;
		}
	}

	private void update() {
		if (isJumping) {
			if (isHoldingJump && jumpCounter < MAX_JUMP_TIME) {
				playerVelocityY = JUMP_STRENGTH;
				jumpCounter++;
			} else {
				isJumping = false;
			}
		}

		playerVelocityY += GRAVITY;
		playerImage.setY(playerImage.getY() + playerVelocityY);

		if (playerImage.getY() < CEILING_LEVEL) {
			playerImage.setY(CEILING_LEVEL);
			playerVelocityY = 0;
		}

		if (playerImage.getY() >= FLOOR_LEVEL) {
			playerImage.setY(FLOOR_LEVEL);
			isJumping = false;
		}

		for (ImageView obstacle : obstacles) {
			obstacle.setX(obstacle.getX() - 4 * level);
		}
	}
	
	private void moveBackground() {
		background1.setX(background1.getX() - 4 * level);
		background2.setX(background2.getX() - 4 * level);
		
		if (background1.getX() + GAME_WIDTH <= 0) {
			background1.setX(background2.getX() + GAME_WIDTH);
		}
		
		if (background2.getX() + GAME_WIDTH <= 0) {
			background2.setX(background1.getX() + GAME_WIDTH);
		}
	}
	
	private void updateBackground(Pane root) {
		background1 = ImageUtils.createImageView(BACKGOUND_PATH, GAME_WIDTH, GAME_HEIGHT);
		background2 = ImageUtils.createImageView(BACKGOUND_PATH, GAME_WIDTH, GAME_HEIGHT);
		background2.setX(GAME_WIDTH);
		
		root.getChildren().addAll(background1, background2);
	}

	private void checkCollisions() {
		ArrayList<ImageView> toRemove = new ArrayList<>();
		for (ImageView obstacle : obstacles) {
			if (playerImage.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
				System.out.println("Game Over!");

				timer.stop();
				if (score < WINING_SCORE) player.changeHealth(-1);
				Platform.runLater(() -> {					
					MainApplication.getCurrentStage().setScene(mainScene);
					player.changeEnergy(-2);
				});
			}
			
			if (obstacle.getX() + OBSTACLE_SIZE < 100 && !toRemove.contains(obstacle)) {
				score++;
				toRemove.add(obstacle);
			}
		}
		obstacles.removeAll(toRemove);
	}
	
	private void spawnObstacles(Pane root) {
	    boolean spawnFlying = (level > 2);
	    boolean spawnFlyingLvl2 = (level > 4);
	    int maxRand;
	    
	    if (spawnFlyingLvl2)
	    	maxRand = 3;
	    else if (spawnFlying)
	    	maxRand = 2;
	    else
	    	maxRand = 1;
	    
	    int obstacleLevel = random.nextInt(maxRand);
	    
	    if (obstacleLevel == 0)
	        spawnCroissantObstacle(root);
	    else if (obstacleLevel == 1)
	        spawnFlyingCroissant(root, GAME_HEIGHT / 2 - OBSTACLE_SIZE, 108, 90);
	    else
	        spawnFlyingCroissant(root, GAME_HEIGHT / 3 - OBSTACLE_SIZE, 168, 90);

	    spawnBasket(root);
	}

	private void spawnCroissantObstacle(Pane root) {
	    String obstaclePath = random.nextBoolean() ? CROISSANT_REGULAR_PATH : CROISSANT_LARGE_PATH;
	    ImageView obstacle = ImageUtils.createImageView(obstaclePath, OBSTACLE_SIZE, OBSTACLE_SIZE);
	    obstacle.setX(GAME_WIDTH);
	    obstacle.setY(FLOOR_LEVEL);
	    
	    obstacles.add(obstacle);
	    root.getChildren().add(obstacle);
	}

	private void spawnFlyingCroissant(Pane root, double yPosition, double width, double height) {
	    ImageView obstacle = ImageUtils.createImageView(CROISSANT_FLY_PATH, width, height);
	    obstacle.setX(GAME_WIDTH);
	    obstacle.setY(yPosition);
	    
	    obstacles.add(obstacle);
	    root.getChildren().add(obstacle);
	}

	private void spawnBasket(Pane root) {
	    ImageView basket = ImageUtils.createImageView(BASKET_PATH, BASKET_SIZE, BASKET_SIZE);
	    basket.setX(-10);
	    basket.setY(FLOOR_LEVEL);
	    
	    root.getChildren().add(basket);
	}

	public void checkWinCondition(Pane root) {
		if (score >= WINING_SCORE) {
			timer.stop();
		
			root.getChildren().clear();
			
			Platform.runLater(() -> {				
				root.setStyle("-fx-background-image: url('" + BACKGOUND_REWARD_PATH + "'); -fx-background-size: cover;");
			});

			Random random = new Random();
			
			List<GuildList> allGuilds = new ArrayList<>(Arrays.asList(GuildList.values()));
			Collections.shuffle(allGuilds);
			
			for (int i = 0; i < 3; i++)
				guildOptions[i] = allGuilds.get(i);

			HBox guildBox = new HBox(3);
			guildBox.setSpacing(60);
			guildBox.setAlignment(Pos.CENTER);
			guildBox.setPrefSize(800, 400);

			for (int i = 0; i < 3; i++) {
				GuildList guild = guildOptions[i];
				guildImages[i] = createGuildImage(guild, root);

				guildBox.getChildren().add(guildImages[i]);
			}
			
			((BorderPane) root).setCenter(guildBox);

			PauseTransition selectionTimer = new PauseTransition(Duration.seconds(15));
			
			selectionTimer.setOnFinished(event -> {
				if (selectedGuild == null)
					selectGuild(root, guildOptions[random.nextInt(3)]);
			});
			
			selectionTimer.play();
		}
	}

	private ImageView createGuildImage(GuildList guild, Pane root) {
		System.out.println(guild.name().toString());
		ImageView guildImage = ImageUtils.createImageView("subelement_image/guild/" + guild.name().toString() + ".png", 200, 200);
		guildImage.setOnMouseClicked(e -> selectGuild(root, guild));
		return guildImage;
	}

	private void selectGuild(Pane root, GuildList guild) {
		selectedGuild = guild;
		potion = PotionList.values()[new Random().nextInt(PotionList.values().length)];

		new Thread(() -> {
			Platform.runLater(() -> {
				root.getChildren().clear();
				root.setPadding(new Insets(0, 0, 100, 0));
				
				Platform.runLater(() -> {
					root.setStyle("-fx-background-image: url('" + BACKGOUND_PATH + "'); -fx-background-size: cover;");
				});
				
				HBox topBox = new HBox();
				topBox.setAlignment(Pos.CENTER);
				topBox.setPadding(new Insets(50, 0, 0, 0));
				
				Text winText = new Text("You win!");
				winText.setFont(pixelFontLarge);
				
				topBox.getChildren().add(winText);
				
				((BorderPane) root).setTop(topBox);

				
				HBox rewardBox = new HBox(2);
				rewardBox.setAlignment(Pos.CENTER);
				rewardBox.setSpacing(80);
				
				VBox guildBox = addImageWithText("subelement_image/guild/" + selectedGuild.name().toUpperCase() + ".png", selectedGuild.name().toUpperCase());
				VBox potionBox = addImageWithText("subelement_image/potion/" + potion.name().toUpperCase() + ".png", potion.name().toUpperCase());
				
				rewardBox.getChildren().addAll(guildBox, potionBox);
				
				((BorderPane) root).setCenter(rewardBox);
			});
			
			try {
				Thread.sleep(5000);
				
				Platform.runLater(() -> {
					player.changeEnergy(-2);
					
					player.addGuild(selectedGuild);
					player.addPotion(new Potion(potion));
					
					MainApplication.getCurrentStage().setScene(mainScene);
					
					if (currentGameState == GameStateList.STAGE_15__GUILD_ROOM) return;
					player.setGameState(GameStateList.getNextStage(currentGameState));
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private VBox addImageWithText(String imagePath, String name) {
		VBox box = new VBox(2);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(40);
		
		ImageView rewardImageView = ImageUtils.createImageView(imagePath, 200, 200);

		Text labelText = new Text(name);
		labelText.setFont(pixelFont);
		
		box.getChildren().addAll(rewardImageView, labelText);
		return box;
	}
}
