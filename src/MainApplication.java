import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

		// BorderPane | Main Pane (root)
		BorderPane root = new BorderPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		// BorderPane | Top Pane
		BorderPane topPane = new BorderPane();
		topPane.setPadding(new Insets(15));
		
		// VBox | Health and Energy
		VBox statusPane = new VBox();
		statusPane.setAlignment(Pos.CENTER);
		statusPane.setSpacing(5);
		statusPane.setPadding(new Insets(0, 0, 15, 0));

		// GridPane | Health Bar
		GridPane healthPane = new GridPane();
		healthPane.setHgap(10);
		healthPane.setPrefWidth(220);
		healthPane.setPadding(new Insets(0, 10, 0, 10));
		healthPane.setAlignment(Pos.CENTER);
		healthPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
		healthPane.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		
		for (int i = 0; i < 5; i++) {
			ImageView heart = new ImageView(
					new Image(getClass().getResource("subelement_image/healthIcon.png").toExternalForm()));
			heart.setFitHeight(32);
			heart.setFitWidth(32);
			healthPane.add(heart, i, 0);
		}

		// GridPane | Energy Bar
		GridPane energyPane = new GridPane();
		energyPane.setHgap(8);
		healthPane.setPrefWidth(180);
		energyPane.setPadding(new Insets(0, 8, 0, 8));
		energyPane.setAlignment(Pos.CENTER);
		energyPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
		energyPane.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		for (int i = 0; i < 5; i++) {
			ImageView bolt = new ImageView(
					new Image(getClass().getResource("subelement_image/energyIcon.png").toExternalForm()));
			bolt.setFitHeight(24);
			bolt.setFitWidth(24);
			energyPane.add(bolt, i, 0);
		}
		
		statusPane.getChildren().addAll(healthPane, energyPane);

		// Setting Button
		Button settingButton = new Button("setting");
		settingButton.setStyle("-fx-background-color: #888; -fx-text-fill: white;");
		
		topPane.setLeft(statusPane);
		topPane.setRight(settingButton);
		root.setTop(topPane);

		// Stack Pane | Game Pane
		StackPane gamePane = new StackPane();
		
		// Grid Pane | Player Pane
		GridPane playerPane = new GridPane();
		
		// Player Setup
		Player.buildPlayer(Gender.FEMALE);
		Player player = Player.getInstance();
		ImageView playerImage = new ImageView(player.getMoveImage());
		playerImage.setFitHeight(128);
		playerImage.setFitWidth(128);
		
		playerPane.getChildren().add(playerImage);

		// Background
		ImageView background = new ImageView(
				new Image(getClass().getResource("map_image/ta_room.png").toExternalForm()));
		
		gamePane.getChildren().addAll(background, playerPane);
		gamePane.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		
//		int size = (int) Math.min(gamePane.getHeight(), gamePane.getWidth() * 2);
//		System.out.println(size);
		background.setFitWidth(gamePane.getWidth());
		background.setFitHeight(gamePane.getHeight());
		
		root.setCenter(gamePane);

		// Skill Slots on Right (3 Slots Vertical)
		VBox skillSlots = new VBox(3);
		skillSlots.setAlignment(Pos.CENTER);
		skillSlots.setPadding(new Insets(0, 50, 0, 50));
		skillSlots.setSpacing(25);
		
		for (int i = 0; i < 3; i++) {
			HBox skillPane = new HBox();
			skillPane.setAlignment(Pos.CENTER);
			skillPane.setPrefSize(80, 80);
			skillPane.setBorder(new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
			skillPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
			
			ImageView skillSlot = new ImageView(
					new Image(getClass().getResource("subelement_image/guild/knight.png").toExternalForm()));
			skillSlot.setFitHeight(60);
			skillSlot.setFitWidth(60);
			
			skillPane.getChildren().add(skillSlot);
			skillSlots.getChildren().add(skillPane);
		}
		
		root.setLeft(skillSlots);

		// Inventory Slots on Left (5 Slots Vertical)
		VBox inventorySlots = new VBox(10);
		inventorySlots.setAlignment(Pos.CENTER);
		inventorySlots.setPadding(new Insets(0, 50, 0, 50));
		inventorySlots.setSpacing(15);
		
		for (int i = 0; i < 5; i++) {
			HBox inventoryPane = new HBox();
			inventoryPane.setAlignment(Pos.CENTER);
			inventoryPane.setPrefSize(80, 80);
			inventoryPane.setBorder(new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
			inventoryPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
			
			ImageView inventorySlot = new ImageView(
					new Image(getClass().getResource("subelement_image/guild/knight.png").toExternalForm()));
			inventorySlot.setFitHeight(45);
			inventorySlot.setFitWidth(45);
			
			inventoryPane.getChildren().add(inventorySlot);
			inventorySlots.getChildren().add(inventoryPane);
		}
		
		root.setRight(inventorySlots);
		
		// Dialogue Box
		Text dialogueText = new Text("Player: Did I fall asleep? What time is it?");
		dialogueText.setFill(javafx.scene.paint.Color.WHITE);
		dialogueText.setFont(Font.font("PixelFont", 20));
		
		HBox dialogueBox = new HBox();
		dialogueBox.setAlignment(Pos.CENTER);
		dialogueBox.setPrefSize(720, 60);
		dialogueBox.setBorder(new Border(
				new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		dialogueBox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), Insets.EMPTY)));
		dialogueBox.getChildren().add(dialogueText);
		
		HBox dialoguePane = new HBox();
		dialoguePane.setPadding(new Insets(15, 0, 0, 0));
		dialoguePane.setAlignment(Pos.CENTER);
		dialoguePane.getChildren().add(dialogueBox);
				
		root.setBottom(dialoguePane);

		Scene scene = new Scene(root);
		scene.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.W)
				player.updateMoveImage(Direction.UP);
			else if (keyEvent.getCode() == KeyCode.A)
				player.updateMoveImage(Direction.LEFT);
			else if (keyEvent.getCode() == KeyCode.S)
				player.updateMoveImage(Direction.DOWN);
			else if (keyEvent.getCode() == KeyCode.D)
				player.updateMoveImage(Direction.RIGHT);
			playerImage.setImage(player.getMoveImage());
		});

		primaryStage.setScene(scene);
		primaryStage.setTitle("CEDT Game");
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}

	public static void main(String[] args) {
		launch(args);
	}
}