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

public class Elevator extends GameMap {
	public Elevator(Pane playerPane, ImageView playerImage) {
		super("Elevator", "map_image/elevator.png", new Integer[] { 240, 630, 20, 290 }, 2.7733);

		Player player = Player.getInstance();
		player.setX(260);
		player.setY(170);

		Platform.runLater(() -> {
			player.updateMoveImage(Direction.RIGHT);
			playerImage.setImage(player.getMoveImage());
		});

		String npcName = "pee_chai_yai";
		Direction npcFirstDirection = Direction.RIGHT;
		String npcURL = "npc/" + npcName + "_" + npcFirstDirection.toString() + ".png";
		Image npcImage = new Image(npcURL);
		ImageView npcImageView = ImageUtils.createImageView(npcImage, 128, 128);
		npcImageView.setFitHeight(this.getRatio() * npcImage.getHeight());
		npcImageView.setFitWidth(this.getRatio() * npcImage.getWidth());

		player.setEnableMove(false);
		List<Actionable> actionables = Arrays.asList(new ShowNPCAction(playerPane, npcImageView, 600, 200),

				new ShowDialogueAction("Player: TA.Big bro!"),

				new TurnNPCAction(npcName, Direction.LEFT, npcImageView),
				new ShowDialogueAction("TA.Big bro: So you passed the comprog quest."),

				new ShowDialogueAction("Player: Yes. Are you stuck here too?"),

				new ShowDialogueAction("TA.Big bro: Not quite. Not in the same way as you."),

				new ShowDialogueAction("Player: So. Do you know where I should go?"),

				new ShowDialogueAction("TA.Big bro: You must go down by this elevator."),
				new ShowDialogueAction("TA.Big bro: But since it’s old and we don’t have money to fix it"),
				new ShowDialogueAction("TA.Big bro: because the insurance does not cover the crack of dimension."),
				new ShowDialogueAction("TA.Big bro: We have to fix it per use."),

				new ShowDialogueAction("Player: OK. I will go by stairs then."),
				new TurnPlayerAction(player, Direction.UP, playerImage),

				new ShowDialogueAction("TA.Big bro: It’s not recommended. Those croissants on the stairs are poisoned."),

				new TurnPlayerAction(player, Direction.RIGHT, playerImage),
				new ShowDialogueAction("Player: So this is the main quest."),

				new ShowDialogueAction("TA.Big bro: You could call that."),

				new ShowDialogueAction("Player: What’s it about now?"),

				new HideNPCAction(npcImageView),

				new ShowDialogueAction("Player: Should not have asked."),

				new EnablePlayerMoveAction(true));

		Platform.runLater(() -> {
			CutsceneManager.startCutscene(actionables);
		});

		player.setGameState(GameStateList.STAGE_04__ELEVATOR);
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);

		block(240, 280, 20, 70);
		block(460, 620, 230, 290);
		block(530, 600, 225, 230);
	}

}
