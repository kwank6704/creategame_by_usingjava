package map;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import player.Direction;
import player.Player;

public class Build4Hall extends GameMap {

	public Build4Hall(Pane playerPane, ImageView playerImage) {
		super("Build4Hall", "map_image/build4_hall.png", new Integer[] {25,865,0,385});
		
		Player player = Player.getInstance();
		
		player.setGameState(8);
		player.setX(480);
		player.setY(385);
		
		Platform.runLater(() -> {
			player.updateMoveImage(Direction.UP);
			playerImage.setImage(player.getMoveImage());	
		});
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		
		block(135, 795, -10, 30); // wall
		block(25, 95, 285, 395);  // sofa
		block(95, 215, 365, 395);  // sofa
	}

}
