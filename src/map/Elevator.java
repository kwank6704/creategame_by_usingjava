package map;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import player.Direction;
import player.Player;

public class Elevator extends GameMap {
	public static Elevator elevator;
	
	public Elevator(ImageView playerImage) {
		super("Elevator", "map_image/elevator.png", new Integer[] {255, 630, 0, 330});
		
		Player player = Player.getInstance();
		player.setX(260);
		player.setY(170);
		
		Platform.runLater(() -> {
			player.updateMoveImage(Direction.RIGHT);
			playerImage.setImage(player.getMoveImage());
		});
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(),player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		
		block(250, 290, 0, 90);
		block(505, 640, 260, 330);
		block(275, 435, 0, 50);
		block(465, 640, 0, 40);
		block(440, 460, 0, 20);
		block(440, 490, 325, 350);
	}

}
