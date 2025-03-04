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
import map.Elevator;
import map.IScaleHall;
import map.IScaleRoom;
import map.MapList;
import player.Gender;
import player.Player;
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

		ImageView mapView = getMapView(currMap, playerImage);

		Image mapImage = mapView.getImage();

		Platform.runLater(() -> {
			double ratio = mapImage.getHeight() / mapImage.getWidth();
			int size = (int) Math.min(gamePane.getHeight(), gamePane.getWidth() * ratio);
			mapView.setFitHeight(size);
			mapView.setFitWidth(size / ratio);
		});

		gamePane.getChildren().addAll(mapView, playerPane);
		gamePane.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));

		return gamePane;
	}

	public static ImageView getMapView(MapList currMap, ImageView playerImage) {
		switch (currMap) {
		case IScaleRoom: {
			return new IScaleRoom();
		}
		case IScaleHall: {
			return new IScaleHall();
		}
		case Elevator: {
			return new Elevator(playerImage);
		}
		case LanIntania: {
			return new IScaleHall();
		}
		case ComRoom: {
			return new IScaleHall();
		}
		case TA_ROOM: {
			return new IScaleHall();
		}
		case GuildRoom: {
			return new IScaleHall();
		}
		case Floor1: {
			return new IScaleHall();
		}
		case Floor4: {
			return new IScaleHall();
		}
		default:
			return new IScaleRoom();
		}
	}
}
