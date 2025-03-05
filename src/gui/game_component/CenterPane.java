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
import map.MapList;
import player.Gender;
import player.Player;
import utils.GameMapView;
import utils.ImageUtils;

public class CenterPane {
	public static StackPane build(Gender gender, MapList currMap) {
		StackPane gamePane = new StackPane();
		Pane playerPane = new Pane();

		Player.buildPlayer(gender);
		Player player = Player.getInstance();
		ImageView playerImage = ImageUtils.createImageView(player.getMoveImage(), 128, 128);

		playerPane.getChildren().add(playerImage);
		playerPane.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));

		ImageView mapView = GameMapView.getMapView(currMap, playerImage);

		Image mapImage = mapView.getImage();

		Platform.runLater(() -> {
			double ratio = mapImage.getHeight() / mapImage.getWidth();
			double scale = Math.min(gamePane.getHeight(), gamePane.getWidth() * ratio) / mapImage.getHeight();
			mapView.setFitHeight(scale * mapImage.getHeight());
			mapView.setFitWidth(scale * mapImage.getWidth());
			
			playerImage.setFitHeight(scale * playerImage.getFitHeight() / 2);
			playerImage.setFitWidth(scale * playerImage.getFitWidth() / 2);			
		});

		gamePane.getChildren().addAll(mapView, playerPane);
		gamePane.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));

		return gamePane;
	}
}
