package utils;

import gui.game_component.MainGamePane;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import map.Build4Hall;
import map.ComputerCenter;
import map.Elevator;
import map.Floor1;
import map.Floor4;
import map.GameMap;
import map.GuildRoom;
import map.IScaleHall;
import map.IScaleRoom;
import map.LarnGear;
import map.MapList;
import map.TA_Room;
import player.Gender;
import player.Player;

public class GameMapView {
	public static ImageView getMapView(MapList currMap, Pane playerPane, ImageView playerImage) {
		switch (currMap) {
		case I_SCALE_ROOM: {
			return new IScaleRoom(playerPane, playerImage);
		}
		case I_SCALE_HALL: {
			return new IScaleHall(playerPane, playerImage);
		}
		case ELEVATOR: {
			return new Elevator(playerPane, playerImage);
		}
		case LARN_GEAR: {
			return new LarnGear(playerPane, playerImage);
		}
		case COMPUTER_CENTER: {
			return new ComputerCenter(playerPane, playerImage);
		}
		case BUILD_4_HALL: {
			return new Build4Hall(playerPane, playerImage);
		}
		case FLOOR_1: {
			return new Floor1(playerPane, playerImage);
		}
		case GUILD_ROOM: {
			return new GuildRoom(playerPane, playerImage);
		}
		case FLOOR_4: {
			return new Floor4(playerPane, playerImage);
		}
		case TA_ROOM: {
			return new TA_Room(playerPane, playerImage);
		}
		default:
			return new IScaleRoom(playerPane, playerImage);
		}
	}
	
	public static StackPane gameMapCreate(MapList currMap) {		
		Pane playerPane = new Pane();
		Gender gameGender = MainGamePane.getGender();
		
		Player.buildPlayer(gameGender);
		Player player = Player.getInstance();
		ImageView playerImage = ImageUtils.createImageView(player.getMoveImage(), 128, 128);
		
		return gameMapCreate(currMap, playerPane, playerImage);
	}
	
	public static StackPane gameMapCreate(MapList currMap, Pane playerPane, ImageView playerImage) {
		StackPane gamePane = new StackPane();
		ImageView mapView = getMapView(currMap, playerPane, playerImage);
		Image player = playerImage.getImage();
		
		Platform.runLater(() -> {
			double ratio = ((GameMap) mapView).getRatio();
			playerImage.setFitHeight(ratio * player.getHeight());
			playerImage.setFitWidth(ratio * player.getWidth());
		});

		gamePane.getChildren().addAll(mapView, playerPane);
		gamePane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		
		return gamePane;
	}
	
	public static StackPane gameMapUpdate(MapList currMap) {
		BorderPane mainGamePane = MainGamePane.getInstance();
		
		StackPane gamePane = (StackPane) mainGamePane.getCenter();
		Pane playerPane = (Pane) gamePane.getChildren().get(1);
		ImageView playerImage = (ImageView) playerPane.getChildren().get(0);
		
		playerPane = new Pane();
		playerPane.getChildren().add(playerImage);
		
		gamePane = gameMapCreate(currMap, playerPane, playerImage);		
		return gamePane;
	}
}
