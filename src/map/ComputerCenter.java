package map;

import java.util.Arrays;
import java.util.List;

import cutscene.Actionable;
import cutscene.CutsceneManager;
import cutscene.EnablePlayerMoveAction;
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

public class ComputerCenter extends GameMap {

	public ComputerCenter(Pane playerPane, ImageView playerImage) {
		super("ComputerCenter", "map_image/com_room.png", new Integer[] { 260, 725, 15, 440 }, 1.3867);

		Player player = Player.getInstance();

		Platform.runLater(() -> {
			player.setX(260);
			player.setY(420);
			player.updateMoveImage(Direction.RIGHT);
			playerImage.setImage(player.getMoveImage());
		});

		String npcName = "grader";
		Direction npcFirstDirection = Direction.UP;
		String npcURL = "npc/" + npcName + "_" + npcFirstDirection.toString() + ".png";
		Image npcImage = new Image(npcURL);
		ImageView npcImageView = ImageUtils.createImageView(npcImage, 128, 128);
		npcImageView.setFitHeight(this.getRatio() * npcImage.getHeight());
		npcImageView.setFitWidth(this.getRatio() * npcImage.getWidth());

		player.setEnableMove(false);
		List<Actionable> actionables = Arrays.asList(
				new ShowDialogueAction("Player: If this is all related to CEDT, how could I not check the Computer Center?"),

				new ShowNPCAction(playerPane, npcImageView, 515, 35),
				new ShowDialogueAction("The Grader Master: Excellent. You have a good engineering sense."),

				new TurnPlayerAction(player, Direction.UP, playerImage),
				new ShowDialogueAction("The Grader Master: Keep that! Remember to always find another way."),

				new ShowDialogueAction("Player: Professor, is there a quest here too?"),

				new TurnNPCAction(npcName, Direction.DOWN, npcImageView),
				new ShowDialogueAction("The Grader Master: No, actually."),
				new ShowDialogueAction("The Grader Master: But since you asked, I might make you one."),
				new ShowDialogueAction("The Grader Master: I have been developing this project for a while."),
				new ShowDialogueAction("The Grader Master: Can you help test my virtue machine?"),

				new ShowDialogueAction("Player: That’s very kind of you, Professor."),
				new ShowDialogueAction("Player: But I think I might go. I have my project to finish in real life."),
				new ShowDialogueAction("Player: I should make haste."),
				new TurnPlayerAction(player, Direction.LEFT, playerImage),

				new ShowDialogueAction("The Grader Master: This room can be compared to Data Structures and Algorithms."),
				new ShowDialogueAction("The Grader Master: You can skip it to save time and avoid a headache."),
				new ShowDialogueAction("The Grader Master: But if you help me here, you will save performance time."),

				new TurnPlayerAction(player, Direction.UP, playerImage),
				new ShowDialogueAction("Player: What do you mean, Professor?"),

				new ShowDialogueAction("The Grader Master: I will give you a special ability."),
				new ShowDialogueAction("The Grader Master: With it, you will pass the last room much more efficiently."),
				new ShowDialogueAction("The Grader Master: If you do not want it, you can walk out."),
				new ShowDialogueAction("The Grader Master: But come here if you want."),

				new EnablePlayerMoveAction(true));

		Platform.runLater(() -> {
			CutsceneManager.startCutscene(actionables);
		});

		player.setGameState(GameStateList.STAGE_08__COMPUTER_CENTER);
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);

		block(285, 480, 75, 310);	/* Computer Left */
		block(510, 670, 75, 310);	/* Computer Left */
		block(285, 670, 355, 405);	/* Computer Bottom */
		block(225, 280, 10, 85);	/* Stand */
		block(500, 595, 50, 70);	/* Table */
		block(510, 545, 30, 50);	/* Teacher */
	}

}
