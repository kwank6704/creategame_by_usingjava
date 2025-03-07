package map;

import java.util.Arrays;
import java.util.List;

import cutscene.Actionable;
import cutscene.CutsceneManager;
import cutscene.EnablePlayerMoveAction;
import cutscene.ShowDialogueAction;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import player.Direction;
import player.Player;

public class TA_Room extends GameMap {

	public TA_Room(Pane playerPane, ImageView playerImage) {
		super("TA ROOM", "map_image/ta_room.png", new Integer[] { -25, 900, -35, 370 }, 2.7733);

		Player player = Player.getInstance();
		player.setX(420);
		player.setY(350);
		
		Platform.runLater(() -> {
			player.updateMoveImage(Direction.UP);
			playerImage.setImage(player.getMoveImage());
			
			player.setHealth(Player.MAX_HEALTH);
			player.setEnergy(Player.MAX_ENERGY);
		});

		player.setEnableMove(false);
		List<Actionable> actionables = Arrays.asList(
			new ShowDialogueAction("Player: Did I die????"),
			
			new ShowDialogueAction("The infamous upper Com: The failure of one class is not the failure of life."),
			new ShowDialogueAction("The infamous upper Com: You can drop it this semester."),
			
			new ShowDialogueAction("Player: I know... but..."),
			new ShowDialogueAction("Player: What if this is not just one class."),
			
			new ShowDialogueAction("The infamous upper Com: Well resigning is a choice."),
			
			new EnablePlayerMoveAction(true)
		);

		Platform.runLater(() -> {
			CutsceneManager.startCutscene(actionables);
		});
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);

		block(70, 760, 0, 35);		/* Table */
		block(600, 780, 140, 315);	/* Left Desk */
		block(165, 360, 140, 315);	/* Right Desk */
		block(-30, 25, -35, 5);		/* Tree Top */
		block(845, 900, 305, 370);	/* Tree Bottom */
		block(860, 900, 85, 120);	/* Drawer 1 */
		block(860, 900, 180, 210);	/* Drawer 1 */
		block(-50, 55, 185, 190);	/* Room */
		block(50, 60, 190, 260);	/* Room */
		block(50, 60, 330, 370);	/* Room */
		block(-25, 5, 195, 370);
		block(525, 535, 140, 310);	/* Table */
	}
}
