package gui.game_component;

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
		Pane playerPane = new Pane();

		Player.buildPlayer(gender);
		Player player = Player.getInstance();
		player.setGameState(Player.START_GAME_STATE);
		
		ImageView playerImage = ImageUtils.createImageView(player.getMoveImage(), 128, 128);

		playerPane.getChildren().add(playerImage);
		playerPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		
		return GameMapView.gameMapCreate(currMap, playerPane, playerImage);
	}
}
