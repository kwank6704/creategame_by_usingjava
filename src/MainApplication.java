import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		BorderPane root = setupRootPane();
		Scene scene = new Scene(root);

		setupKeyEvents(scene);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("CEDT Game");
		primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("game-logo.png").toString()));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}

	private BorderPane setupRootPane() {
		BorderPane root = new BorderPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");

		root.setTop(setupTopPane());
		root.setCenter(setupGamePane());
		root.setLeft(setupSkillSlots());
		root.setRight(setupInventorySlots());
		root.setBottom(setupDialoguePane());

		return root;
	}

	private BorderPane setupTopPane() {
		BorderPane topPane = new BorderPane();
		topPane.setPadding(new Insets(15));
		topPane.setLeft(setupStatusPane());
		
		Button settingButton = new Button("Setting");
		settingButton.setStyle("-fx-background-color: #888; -fx-text-fill: white;");
		topPane.setRight(settingButton);
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

	private StackPane setupGamePane() {
		StackPane gamePane = new StackPane();
		GridPane playerPane = new GridPane();

		Player.buildPlayer(Gender.FEMALE);
		Player player = Player.getInstance();
		ImageView playerImage = createImageView(player.getMoveImage(), 128, 128);
		playerPane.getChildren().add(playerImage);

		String mapPath = "map_image/room_iscale.png";
		Image mapImage = new Image(getClass().getResource(mapPath).toExternalForm());
		ImageView background = createImageView(mapImage, 0, 0);

		Platform.runLater(() -> {
			double ratio = mapImage.getHeight() / mapImage.getWidth();
			int size = (int) Math.min(gamePane.getHeight(), gamePane.getWidth() * ratio);
			background.setFitHeight(size);
			background.setFitWidth(size / ratio);
		});

		gamePane.getChildren().addAll(background, playerPane);
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
