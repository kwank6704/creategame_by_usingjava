package map;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import player.Direction;
import player.Player;

public class Elevator extends GameMap {
	public static Elevator elevator;
	
	public Elevator(ImageView playerImage) {
		super("Elevator", "map_image/elevator.png", new Integer[] {240, 630, 20, 290});
		
		Player player = Player.getInstance();
		player.setX(240);
		player.setY(140);
		
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
		
		block(240, 280, 20, 70);
		block(460, 620, 230, 290);
		block(530, 600, 225, 230);
	}

}
