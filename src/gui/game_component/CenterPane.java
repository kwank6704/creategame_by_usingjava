package gui.game_component;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import player.Gender;
import player.Player;
import utils.ImageUtils;

public class CenterPane {
	public static StackPane build(Gender gender) {
		StackPane gamePane = new StackPane();
		Pane playerPane = new Pane();

		Player.buildPlayer(gender);
		Player player = Player.getInstance();
		ImageView playerImage = ImageUtils.createImageView(player.getMoveImage(), 128, 128);
		
		playerPane.getChildren().add(playerImage);
		playerPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));

		String mapPath = "map_image/room_iscale.png";
		Image mapImage = new Image(ClassLoader.getSystemResource(mapPath).toString());
		ImageView mapView = ImageUtils.createImageView(mapImage, 0, 0);

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
}
