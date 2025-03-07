package map;

import java.util.Arrays;
import java.util.List;

import cutscene.Actionable;
import cutscene.CutsceneManager;
import cutscene.EnablePlayerMoveAction;
import cutscene.HideNPCAction;
import cutscene.ShowDialogueAction;
import cutscene.ShowNPCAction;
import cutscene.TurnNPCAction;
import cutscene.TurnPlayerAction;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.GameStateList;
import player.Direction;
import player.Player;
import utils.ImageUtils;

public class Floor1 extends GameMap {

	public Floor1(Pane playerPane, ImageView playerImage) {
		super("Build4Floor1", "map_image/floor1.png", new Integer[] { 30, 875, 90, 385 }, 2.7733);

		Player player = Player.getInstance();
		player.setX(785);
		player.setY(320);

		Platform.runLater(() -> {
			player.updateMoveImage(Direction.UP);
			playerImage.setImage(player.getMoveImage());
		});

		String npcName = "data";
		Direction npcFirstDirection = Direction.LEFT;
		String npcURL = "npc/" + npcName + "_" + npcFirstDirection.toString() + ".png";
		Image npcImage = new Image(npcURL);
		ImageView npcImageView = ImageUtils.createImageView(npcImage, 128, 128);
		npcImageView.setFitHeight(this.getRatio() * npcImage.getHeight());
		npcImageView.setFitWidth(this.getRatio() * npcImage.getWidth());

		player.setEnableMove(false);
		List<Actionable> actionables = Arrays.asList(
				new ShowDialogueAction("Player: Why is there a database server in this room??"),

				new ShowNPCAction(playerPane, npcImageView, 235, 270),
				new ShowDialogueAction("The Data Master: This is for storing the names of students who failed my class."),

				new TurnPlayerAction(player, Direction.LEFT, playerImage),

				new TurnNPCAction(npcName, Direction.RIGHT, npcImageView),
				new ShowDialogueAction("The Data Master: Just kidding."),

				new ShowDialogueAction("Player: Professor, what was this class about?"),

				new ShowDialogueAction("The Data Master: There are database servers sitting in this room."),
				new ShowDialogueAction("The Data Master: What else did you think? A literature class?"),

				new ShowDialogueAction("Player: …"),

				new ShowDialogueAction("The Data Master: In case you haven't already noticed,"),
				new ShowDialogueAction("The Data Master: the next door is locked."),
				new ShowDialogueAction("The Data Master: If you clear this quest, I might help you by giving you a key."),

				new ShowDialogueAction("Player: A KEY????"),
				new ShowDialogueAction("Player: A Primary or Secondary??"),
				new ShowDialogueAction("Player: Or maybe a foriegn key one??"),

				new HideNPCAction(npcImageView),

				new ShowDialogueAction("Player: ???"),

				new EnablePlayerMoveAction(true)
		);

		Platform.runLater(() -> {
			CutsceneManager.startCutscene(actionables);
		});

		player.setGameState(GameStateList.STAGE_12__DATABASE);
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);

		block(185, 605, 345, 355);	/* TV */
		block(55, 215, 205, 325);	/* Table */
		block(30, 725, 350, 400);	/* Wall Right */
		block(845, 875, 365, 400);	/* Wall Left */
	}
}
