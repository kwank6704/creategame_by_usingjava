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

public class IScaleRoom extends GameMap {
	public IScaleRoom(Pane playerPane, ImageView playerImage) {
		super("iScale Room", "map_image/iscale_room.png", new Integer[] { 300, 580, 80, 405 }, 1.9968);

		Player player = Player.getInstance();
		player.setGameState(GameStateList.STAGE_00__GAME_START);
		player.setX(300);
		player.setY(100);

		Platform.runLater(() -> {
			player.updateMoveImage(Direction.DOWN);
			playerImage.setImage(player.getMoveImage());
		});

		String npcName = "gandum";
		Direction npcFirstDirection = Direction.DOWN;
		String npcURL = "npc/" + npcName + "_" + npcFirstDirection.toString() + ".png";
		System.out.println(npcURL);
		Image npcImage = new Image(npcURL);
		ImageView npcImageView = ImageUtils.createImageView(npcImage, 128, 128);
		npcImageView.setFitHeight(this.getRatio() * npcImage.getHeight());

		player.setEnableMove(false);
		List<Actionable> actionables = Arrays.asList(new ShowDialogueAction("???"),

				new TurnPlayerAction(player, Direction.LEFT, playerImage),
				new TurnPlayerAction(player, Direction.RIGHT, playerImage),
				new ShowDialogueAction("Did I fall asleep? What time is it?"),

				new ShowNPCAction(playerPane, npcImageView, 400, 400),

				new TurnPlayerAction(player, Direction.DOWN, playerImage),
				new ShowDialogueAction("Player: Professor !"),

				new TurnNPCAction(npcName, Direction.UP, npcImageView),
				new ShowDialogueAction("Gandam: Hello."),

				new ShowDialogueAction("Player: I’m sorry! I fell asleep! I didn’t mean to stay this late. I will go back home now!"),

				new ShowDialogueAction("Gandam: No. Calm down. You are not where you used to know. You are in the crack of dimension now."),

				new ShowDialogueAction("Player: What do you mean the crack of dimension? Is this a prank?"),
				new ShowDialogueAction("Player: I have a project to be done, Professor. I must go now."),

				new ShowDialogueAction("Gandam: To go to your real life is to escape this place. It’s quite a story."),

				new ShowDialogueAction("Player: This is surely my real life."),

				new ShowDialogueAction("Gandam: It is not. Look at the window."),

				new TurnPlayerAction(player, Direction.UP, playerImage),
				new ShowDialogueAction("[ You looked at the door window. There are red light glow out of it. ]"),
				new TurnPlayerAction(player, Direction.DOWN, playerImage),
				new ShowDialogueAction("Player: I see. This is a cliche plot. So is this going to be a horror or survival game one?"),

				new ShowDialogueAction("Gandam: It depends. To escape from here you will have to go through quests."),
				new ShowDialogueAction("Gandam: If you have properly studied in class you sure will be able to escape."),

				new ShowDialogueAction("Player: And you are the first quest."),

				new ShowDialogueAction("Gandam: It’s true."),

				new ShowDialogueAction("Player: Okay what I have to do?"),

				new ShowDialogueAction("Gandam: Look at my computer."),
				new ShowDialogueAction("Gandam: You have studied Computer Programming and you shall show your ability in problem solving in this game."),
				new ShowDialogueAction("Gandam: Good luck."),

				new ShowDialogueAction("Player: But, I didn't know how to play it."),

				new HideNPCAction(npcImageView),

				new ShowDialogueAction("Player: ..."),
				new ShowDialogueAction("Player: Very well."),

				new EnablePlayerMoveAction(true));

		Platform.runLater(() -> {
			CutsceneManager.startCutscene(actionables);
		});

		player.setGameState(GameStateList.STAGE_01__ISCALE_ROOM);
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(), player.getY());
		player.setX(position[0]);
		player.setY(position[1]);

		block(415, 530, 335, 405);
		block(415, 480, 320, 405);
	}
}
