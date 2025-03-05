package map;

import java.util.Arrays;
import java.util.List;

import gui.game_component.Actionable;
import gui.game_component.CutsceneManager;
import gui.game_component.EnablePlayerMoveAction;
import gui.game_component.ShowDialogueAction;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import player.Direction;
import player.Player;

public class Floor4 extends GameMap {

	public Floor4(Pane playerPane, ImageView playerImage) {
		super("GuildRoom", "map_image/floor4.png", new Integer[] { 60, 840, 120, 340 }, 2.7733);

		Player player = Player.getInstance();
		player.setX(665);
		player.setY(330);

		Platform.runLater(() -> {
			player.updateMoveImage(Direction.UP);
			playerImage.setImage(player.getMoveImage());
		});

		player.setEnableMove(false);
		List<Actionable> cutsceneActions = Arrays.asList(	
			new ShowDialogueAction("???: AGHHHHHHHHHHHHHHHHHHHHHH"),
			
			new ShowDialogueAction("Player: ..."),
			new ShowDialogueAction("Player: Woah."),

			new EnablePlayerMoveAction(true)
		);

		Platform.runLater(() -> {
			CutsceneManager.startCutscene(cutsceneActions);
		});

		player.setGameState(13);
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		
		block(25, 95, 70, 260);		/* Table */
		block(25, 150, 70, 155);	/* Table */
		block(825, 870, 115, 180);	/* Croissant */
	}
}
