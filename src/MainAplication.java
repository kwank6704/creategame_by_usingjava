import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import player.Direction;
import player.Gender;
import player.Player;

public class MainAplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		AnchorPane root = new AnchorPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #2b2828;");

		GridPane healthPane = new GridPane();
		healthPane.setPrefSize(160, 40);
		healthPane.setHgap(10);
		healthPane.setAlignment(Pos.CENTER);
		healthPane.setStyle("-fx-border-color: #dbcece; -fx-border-insets: -1;");
		AnchorPane.setLeftAnchor(healthPane, 15.0);
		AnchorPane.setTopAnchor(healthPane, 15.0);

		ImageView healthIcon1 = new ImageView(new Image(getClass().getResource("/healthIcon.png").toExternalForm()));
		healthIcon1.setFitHeight(20);
		healthIcon1.setFitWidth(20);
		
		ImageView healthIcon2 = new ImageView(new Image(getClass().getResource("/healthIcon.png").toExternalForm()));
		healthIcon2.setFitHeight(20);
		healthIcon2.setFitWidth(20);
		
		ImageView healthIcon3 = new ImageView(new Image(getClass().getResource("/healthIcon.png").toExternalForm()));
		healthIcon3.setFitHeight(20);
		healthIcon3.setFitWidth(20);
		
		ImageView healthIcon4 = new ImageView(new Image(getClass().getResource("/healthIcon.png").toExternalForm()));
		healthIcon4.setFitHeight(20);
		healthIcon4.setFitWidth(20);
		
		ImageView healthIcon5 = new ImageView(new Image(getClass().getResource("/healthIcon.png").toExternalForm()));
		healthIcon5.setFitHeight(20);
		healthIcon5.setFitWidth(20);
		
		healthPane.add(healthIcon1, 1, 1);
		healthPane.add(healthIcon2, 2, 1);
		healthPane.add(healthIcon3, 3, 1);
		healthPane.add(healthIcon4, 4, 1);
		healthPane.add(healthIcon5, 5, 1);

		Player.buildPlayer(Gender.FEMALE);
		Player player = Player.getInstance();
		ImageView playerImage = new ImageView();
		playerImage.setImage(player.getMoveImage());
		playerImage.setFitHeight(128);
		playerImage.setFitWidth(128);

		ImageView background = new ImageView(new Image(getClass().getResource("/ta_room_floor.png").toExternalForm()));
		background.setFitWidth(720);
		background.setFitHeight(360);

		root.getChildren().add(healthPane);

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
//		primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("images/deer.png").toString()));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Platform.exit());
	}

	public static void main(String[] args) {
		launch(args);
	}

}
