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

public class GuildRoom extends GameMap {

	public GuildRoom(Pane playerPane, ImageView playerImage) {
		super("GuildRoom", "map_image/guild_room.png", new Integer[] { 40, 865, 150, 360 }, 2.7733);

		Player player = Player.getInstance();
		player.setGameState(8);
		player.setX(115);
		player.setY(330);

		Platform.runLater(() -> {
			player.updateMoveImage(Direction.UP);
			playerImage.setImage(player.getMoveImage());
		});

		player.setEnableMove(false);
		List<Actionable> cutsceneActions = Arrays.asList(		
			new ShowDialogueAction("Player: ..."),
			new ShowDialogueAction("Player: Is this like P⬛k⬛m⬛n Center?"),
			
			new ShowDialogueAction("[...]"),
			
			new ShowDialogueAction("Player: …"),
			new ShowDialogueAction("Player: OK, there is no cutscene here."),
			
			new EnablePlayerMoveAction(true)
		);
		Platform.runLater(() -> {
			CutsceneManager.startCutscene(cutsceneActions);
		});

		player.setGameState(11);

	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		
		block(180, 885, 345, 380);	/* Wall */
	}
}
