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
import logic.GameStateList;
import player.Direction;
import player.Player;

public class GuildRoom extends GameMap {

	public GuildRoom(Pane playerPane, ImageView playerImage) {
		super("GuildRoom", "map_image/guild_room.png", new Integer[] { 40, 865, 150, 360 }, 2.7733);

		Player player = Player.getInstance();
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
		
		player.setGameState(GameStateList.STAGE_15__GUILD_ROOM);
		
		Platform.runLater(() -> {
			CutsceneManager.startCutscene(cutsceneActions);
		});
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
