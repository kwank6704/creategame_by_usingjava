package application;

import gui.GenderSelectionPane;
import gui.SettingPane;
import gui.StartPane;
import gui.game_component.MainGamePane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.PlayerKeyEvent;
import map.GameMap;
import player.Gender;
import player.Player;
import sound.SoundManager;

public class MainApplication extends Application {
	private static Stage primaryStage;
	
	private static Scene startScene;
	private static Scene settingScene;
	private static Scene genderScene;

	private static BorderPane gamePane;
	private static Scene gameScene;

	private static Scene prevScene;

	@Override
	public void start(Stage primaryStage) {
		MainApplication.primaryStage = primaryStage;
		
		SoundManager.playBackgroundMusic("Background.wav", SoundManager.DEFAULT_VOLUME, true);
		SoundManager.loadSoundEffect("Alert.wav", SoundManager.DEFAULT_VOLUME);
		resetGame();
	}
	
	public void resetGame() {
		// Build Start GUI
		StackPane startPane = (StackPane) StartPane.build();
		StackPane settingPane = SettingPane.build();
		StackPane genderSelectionPane = GenderSelectionPane.build();

		// Add Scene Changing Event to Created Pane
		startAddEvents(startPane);
		settingAddEvents(settingPane);
		genderAddEvents(genderSelectionPane);

		// Build Scene from Created Pane
		startScene = new Scene(startPane);
		settingScene = new Scene(settingPane);
		genderScene = new Scene(genderSelectionPane);

		// Set default Previous Scene
		prevScene = startScene;
		
		// Setup `primaryStage`
		primaryStage.setScene(startScene);
		primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("game-logo.png").toString()));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}

	private void setupGameLoop(ImageView playerImage) {
		Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(32), event -> {
			Player player = Player.getInstance();
			playerImage.setLayoutX(player.getX());
			playerImage.setLayoutY(player.getY());
		}));
		
		gameLoop.setCycleCount(Animation.INDEFINITE);
		gameLoop.play();
	}

	private void gamePaneSetup(Stage primaryStage, Gender gender) {
		gamePane = MainGamePane.build(primaryStage, gender);
		gameScene = new Scene(gamePane);

		gameSettingAddEvent(gamePane, primaryStage);
		
		StackPane centerPane = (StackPane) gamePane.getCenter();
		
		ImageView mapImage = (ImageView) centerPane.getChildren().get(0);
		
		Pane playerPane = (Pane) centerPane.getChildren().get(1);
		ImageView playerImage = (ImageView) playerPane.getChildren().get(0);

		PlayerKeyEvent.addKeyEvent(gameScene, playerImage, (GameMap) mapImage);
		setupGameLoop(playerImage);
		
		primaryStage.setScene(gameScene);
	}

	private void startAddEvents(StackPane startPane) {
		VBox buttonPane = (VBox) startPane.getChildren().get(1);

		HBox settingButton = (HBox) buttonPane.getChildren().get(0);
		settingButton.setOnMouseClicked(mouseEvent -> {
			prevScene = primaryStage.getScene();
			primaryStage.setScene(settingScene);
		});

		HBox startButton = (HBox) buttonPane.getChildren().get(1);
		startButton.setOnMouseClicked(mouseEvent -> {
			primaryStage.setScene(genderScene);
		});
	}

	private void settingAddEvents(StackPane settingPane) {
		VBox buttonPane = (VBox) settingPane.getChildren().get(1);

		HBox backButton = (HBox) buttonPane.getChildren().get(0);
		backButton.setOnMouseClicked(mouseEvent -> {
			primaryStage.setScene(prevScene);
		});
	}

	private void genderAddEvents(StackPane genderSelectionPane) {
		HBox settingButton = (HBox) genderSelectionPane.getChildren().get(1);
		settingButton.setOnMouseClicked(mouseEvent -> {
			prevScene = primaryStage.getScene();
			primaryStage.setScene(settingScene);
		});

		HBox genderPane = (HBox) genderSelectionPane.getChildren().get(2);

		VBox malePane = (VBox) genderPane.getChildren().get(0);
		ImageView maleButton = (ImageView) malePane.getChildren().get(1);
		maleButton.setOnMouseClicked(mouseEvvent -> {
			gamePaneSetup(primaryStage, Gender.MALE);
		});

		VBox femalePane = (VBox) genderPane.getChildren().get(1);
		ImageView femaleButton = (ImageView) femalePane.getChildren().get(1);
		femaleButton.setOnMouseClicked(mouseEvvent -> {
			gamePaneSetup(primaryStage, Gender.FEMALE);
		});
	}

	private void gameSettingAddEvent(BorderPane gamePane, Stage primaryStage) {
		BorderPane topPane = (BorderPane) gamePane.getTop();

		HBox settingButton = (HBox) topPane.getRight();
		settingButton.setOnMouseClicked(mouseEvent -> {
			prevScene = primaryStage.getScene();
			primaryStage.setScene(settingScene);
		});
	}
	
	public static Stage getCurrentStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}