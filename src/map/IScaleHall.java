package map;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.GameStateList;
import player.Direction;
import player.Player;

public class IScaleHall extends GameMap {	
	public IScaleHall(Pane playerPane, ImageView playerImage) {
		super("iScale Hall", "map_image/iscale_hall.png", new Integer[] {70, 860, 180, 335}, 2.7733);
		
		Player player = Player.getInstance();
		player.setGameState(GameStateList.STAGE_03__ISCALE_HALL);
		player.setX(200);
		player.setY(335);
		
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
		
		block(90, 190, 180, 225);
		block(70, 100, 180, 265);
		block(70, 120, 320, 335);
		block(345, 655, 320, 335);
		block(805, 860, 320, 335);
		block(255, 345, 255, 335);
		block(240, 345, 275, 335);
	}
}
