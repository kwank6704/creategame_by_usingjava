package map;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.GameStateList;
import player.Direction;
import player.Player;

public class Build4Hall extends GameMap {

	public Build4Hall(Pane playerPane, ImageView playerImage) {
		super("Build4Hall", "map_image/build4_hall.png", new Integer[] { 25, 865, 0, 385 }, 2.7733);

		Player player = Player.getInstance();

		if (player.getGameState() == GameStateList.STAGE_07__LARN_GEAR__GAME_FINISHED
				|| player.getGameState() == GameStateList.STAGE_10__LARN_GEAR__AFTER__COMPUTER_CENTER) {
			player.setGameState(GameStateList.STAGE_11__BUILDING_4_HALL);

			Platform.runLater(() -> {
				player.setX(480);
				player.setY(385);
				player.updateMoveImage(Direction.UP);
				playerImage.setImage(player.getMoveImage());
			});
		} else if (player.getGameState() == GameStateList.STAGE_13__DATABASE__GAME_FINISHED) {
			player.setGameState(GameStateList.STAGE_14__BUILDING_4_HALL__AFTER__DATABASE);

			Platform.runLater(() -> {
				player.setX(90);
				player.setY(0);
				player.updateMoveImage(Direction.DOWN);
				playerImage.setImage(player.getMoveImage());
			});
		}
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);

		block(135, 795, -10, 30);	/* Wall */
		block(25, 95, 285, 395);	/* Sofa */
		block(95, 215, 365, 395);	/* Sofa */
	}

}
