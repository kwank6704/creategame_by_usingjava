import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import player.Direction;
import player.Gender;
import player.Player;

public class MainApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		StackPane startRoot = setupStartRootPane(primaryStage);
		
		Scene scene = new Scene(startRoot);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("CEDT Game");
		primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("game-logo.png").toString()));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}
	
	private StackPane setupStartRootPane(Stage primaryStage) {
		StackPane root = new StackPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		Image startBackground = new Image(ClassLoader.getSystemResource("screen/homescreen.png").toString());
		ImageView startView = new ImageView(startBackground);
		root.getChildren().add(startView);
		
		VBox startPane = new VBox(2);
		startPane.setSpacing(250);
		startPane.getChildren().add(setupSettingButton(primaryStage));
		startPane.getChildren().add(setupStartButton(primaryStage));
		
		root.getChildren().add(startPane);
		return root;
	}
	
	private HBox setupStartButton(Stage primaryStage) {
		HBox startButtonBox = new HBox();
		startButtonBox.setAlignment(Pos.CENTER);
		startButtonBox.setPadding(new Insets(15));
		startButtonBox.setOnMouseClicked(mouseEvent -> {
			setupSettingRootPane(primaryStage);
		});
		
		Image startImage = new Image(ClassLoader.getSystemResource("subelement_image/button/start.png").toString());
		ImageView startView = new ImageView(startImage);
		Platform.runLater(() -> {
			double ratio = startImage.getHeight() / startImage.getWidth();
			startView.setFitWidth(300);
			startView.setFitHeight(300 * ratio);
		});
		
		startButtonBox.getChildren().add(startView);
		return startButtonBox;
	}
	
	private HBox setupSettingButton(Stage primaryStage) {
		HBox settingButtonBox = new HBox();
		settingButtonBox.setAlignment(Pos.TOP_RIGHT);
		settingButtonBox.setPadding(new Insets(15));
		settingButtonBox.setOnMouseClicked(mouseEvent -> {
			setupGameRootPane(primaryStage, Gender.MALE);
		});
		
		Image settingImage = new Image(ClassLoader.getSystemResource("subelement_image/button/setting.png").toString());
		ImageView settingView = new ImageView(settingImage);
		Platform.runLater(() -> {
			double ratio = settingImage.getHeight() / settingImage.getWidth();
			settingView.setFitWidth(100);
			settingView.setFitHeight(100 * ratio);
		});
		
		settingButtonBox.getChildren().add(settingView);
		return settingButtonBox;
	}
	
	private StackPane setupSettingRootPane(Stage primaryStage) {
		StackPane root = new StackPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		Image genderBackground = new Image(ClassLoader.getSystemResource("screen/character_choose.png").toString());
		ImageView genderView = new ImageView(genderBackground);
		root.getChildren().add(genderView);
		
		VBox genderPane = new VBox(2);
		genderPane.setSpacing(250);
		genderPane.getChildren().add(setupSettingButton(primaryStage));
		genderPane.getChildren().add(setupGenderChoosePane(primaryStage));

		root.getChildren().add(genderPane);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		return root;
	}
	
	private StackPane setupGenderRootPane(Stage primaryStage) {
		StackPane root = new StackPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		Image genderBackground = new Image(ClassLoader.getSystemResource("screen/character_choose.png").toString());
		ImageView genderView = new ImageView(genderBackground);
		root.getChildren().add(genderView);
		
		VBox genderPane = new VBox(2);
		genderPane.setSpacing(250);
		genderPane.getChildren().add(setupSettingButton(primaryStage));
		genderPane.getChildren().add(setupGenderChoosePane(primaryStage));

		root.getChildren().add(genderPane);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		return root;
	}
	
	private HBox setupGenderChoosePane(Stage primaryStage) {
		HBox genderChoosePane = new HBox();
		genderChoosePane.setAlignment(Pos.CENTER);
		genderChoosePane.setSpacing(100);
		genderChoosePane.setPadding(new Insets(15));
		
		VBox maleBox = createGenderBox(primaryStage, Gender.MALE);
		VBox femaleBox = createGenderBox(primaryStage, Gender.FEMALE);
		
		genderChoosePane.getChildren().addAll(maleBox, femaleBox);
		return genderChoosePane;
	}
	
	private VBox createGenderBox(Stage primaryStage, Gender gender) {
		VBox genderBox = new VBox(2);
		genderBox.setAlignment(Pos.CENTER);
		genderBox.setSpacing(25);
		
		Image genderImage = new Image(ClassLoader.getSystemResource("player_image/" + gender + ".png").toString());
		ImageView genderView = new ImageView(genderImage);
		Platform.runLater(() -> {
			double ratio = genderImage.getHeight() / genderImage.getWidth();
			genderView.setFitWidth(300);
			genderView.setFitHeight(300 * ratio);
		});
		
		Image genderButtonImage = new Image(ClassLoader.getSystemResource("subelement_image/button/" + gender + ".png").toString());
		ImageView genderButtonView = new ImageView(genderButtonImage);
		Platform.runLater(() -> {
			double ratio = genderButtonImage.getHeight() / genderButtonImage.getWidth();
			genderButtonView.setFitWidth(200);
			genderButtonView.setFitHeight(200 * ratio);
		});
		genderButtonView.setOnMouseClicked(mouseEvent -> {
			setupGameRootPane(primaryStage, gender);
		});
		
		genderBox.getChildren().addAll(genderView, genderButtonView);
		return genderBox;
	}
	
	private BorderPane setupGameRootPane(Stage primaryStage, Gender gender) {
		BorderPane root = new BorderPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		root.setTop(setupTopPane(primaryStage));
		root.setCenter(setupGamePane(gender));
		root.setLeft(setupSkillSlots());
		root.setRight(setupInventorySlots());
		root.setBottom(setupDialoguePane());
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		setupKeyEvents(scene);
		
		return root;
	}

	private BorderPane setupTopPane(Stage primaryStage) {
		BorderPane topPane = new BorderPane();
		topPane.setPadding(new Insets(15));
		topPane.setLeft(setupStatusPane());
		topPane.setRight(setupSettingButton(primaryStage));
		return topPane;
	}

	private VBox setupStatusPane() {
		VBox statusPane = new VBox(5);
		statusPane.setAlignment(Pos.CENTER);
		statusPane.setPadding(new Insets(0, 0, 15, 0));
		statusPane.getChildren().addAll(setupHealthPane(), setupEnergyPane());
		return statusPane;
	}

	private GridPane setupHealthPane() {
		GridPane healthPane = createStatusBar(220, 32, "subelement_image/healthIcon.png", 5);
		return healthPane;
	}

	private GridPane setupEnergyPane() {
		GridPane energyPane = createStatusBar(180, 24, "subelement_image/energyIcon.png", 5);
		return energyPane;
	}

	private GridPane createStatusBar(int width, int iconSize, String imagePath, int count) {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setPrefWidth(width);
		pane.setPadding(new Insets(0, 10, 0, 10));
		pane.setAlignment(Pos.CENTER);
		pane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		
		for (int i = 0; i < count; i++) {
			pane.add(createImageView(imagePath, iconSize, iconSize), i, 0);
		}
		return pane;
	}

	private StackPane setupGamePane(Gender gender) {
		StackPane gamePane = new StackPane();
		GridPane playerPane = new GridPane();

		Player.buildPlayer(gender);
		Player player = Player.getInstance();
		ImageView playerImage = createImageView(player.getMoveImage(), 128, 128);
		playerPane.getChildren().add(playerImage);

		String mapPath = "map_image/room_iscale.png";
		Image mapImage = new Image(getClass().getResource(mapPath).toExternalForm());
		ImageView mapView = createImageView(mapImage, 0, 0);

		Platform.runLater(() -> {
			double ratio = mapImage.getHeight() / mapImage.getWidth();
			int size = (int) Math.min(gamePane.getHeight(), gamePane.getWidth() * ratio);
			mapView.setFitHeight(size);
			mapView.setFitWidth(size / ratio);
		});

		gamePane.getChildren().addAll(mapView, playerPane);
		gamePane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		
		return gamePane;
	}

	private VBox setupSkillSlots() {
		return createSlotsPane(3, "game-logo.png", 60);
	}

	private VBox setupInventorySlots() {
		return createSlotsPane(5, "game-logo.png", 45);
	}

	private VBox createSlotsPane(int count, String imagePath, int iconSize) {
		VBox slotsPane = new VBox(10);
		slotsPane.setAlignment(Pos.CENTER);
		slotsPane.setPadding(new Insets(0, 50, 0, 50));
		slotsPane.setSpacing(15);
		
		for (int i = 0; i < count; i++) {
			HBox slot = new HBox();
			slot.setAlignment(Pos.CENTER);
			slot.setPrefSize(80, 80);
			slot.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
			slot.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
			slot.getChildren().add(createImageView(imagePath, iconSize, iconSize));
			slotsPane.getChildren().add(slot);
		}
		return slotsPane;
	}

	private HBox setupDialoguePane() {
		Text dialogueText = new Text("Player: Did I fall asleep? What time is it?");
		dialogueText.setFill(Color.WHITE);
		dialogueText.setFont(Font.font("PixelFont", 20));
		
		HBox dialogueBox = new HBox(dialogueText);
		dialogueBox.setAlignment(Pos.CENTER);
		dialogueBox.setPrefSize(720, 60);
		dialogueBox.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		dialogueBox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), Insets.EMPTY)));
		
		HBox dialoguePane = new HBox(dialogueBox);
		dialoguePane.setPadding(new Insets(15, 0, 0, 0));
		dialoguePane.setAlignment(Pos.CENTER);
		
		return dialoguePane;
	}

	private void setupKeyEvents(Scene scene) {
		scene.setOnKeyPressed(keyEvent -> {
			Player player = Player.getInstance();
			if (keyEvent.getCode() == KeyCode.W) player.updateMoveImage(Direction.UP);
			else if (keyEvent.getCode() == KeyCode.A) player.updateMoveImage(Direction.LEFT);
			else if (keyEvent.getCode() == KeyCode.S) player.updateMoveImage(Direction.DOWN);
			else if (keyEvent.getCode() == KeyCode.D) player.updateMoveImage(Direction.RIGHT);
			
			BorderPane root = (BorderPane) scene.getRoot();
			StackPane gamePane = (StackPane) root.getCenter();
			GridPane playerPane = (GridPane) gamePane.getChildren().get(1);
			((ImageView) playerPane.getChildren().get(0)).setImage(player.getMoveImage());
		});
	}
	
	private ImageView createImageView(String path, double width, double height) {
		Image image = new Image(getClass().getResource(path).toExternalForm());
		return createImageView(image, width, height);
	}
	
	private ImageView createImageView(Image image, double width, double height) {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
