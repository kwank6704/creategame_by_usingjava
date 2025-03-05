package map;

import java.util.Arrays;
import java.util.List;

import gui.game_component.Actionable;
import gui.game_component.CutsceneManager;
import gui.game_component.EnablePlayerMoveAction;
import gui.game_component.HideNPCAction;
import gui.game_component.ShowDialogueAction;
import gui.game_component.ShowNPCAction;
import gui.game_component.TurnPlayerAction;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import player.Direction;
import player.Player;
import utils.ImageUtils;

public class Floor1 extends GameMap {

	public Floor1(Pane playerPane , ImageView playerImage) {
		super("Build4Floor1", "map_image/floor1_database.png", new Integer[] {30,875,90,385});
		// TODO Auto-generated constructor stub
		
		Image gandamImage = new Image("npc/gandam.png");
		ImageView npcImage = ImageUtils.createImageView(gandamImage, 156, 156);
		
		
		Player player = Player.getInstance();
		
		player.setGameState(8);
		player.setX(785);
		player.setY(320);
		
		Platform.runLater(() -> {
			player.updateMoveImage(Direction.UP);
			playerImage.setImage(player.getMoveImage());	
		});
		
		player.setEnableMove(false);
		List<Actionable> actionables = Arrays.asList(
				
				new ShowDialogueAction("Player: Why is there a database server in this room??"),
				new ShowNPCAction(playerPane, npcImage,235,270),
				new ShowDialogueAction("The Data Master: This is for storing the names of students who failed my class."),
				new TurnPlayerAction(player, Direction.LEFT, playerImage),
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
				new HideNPCAction(npcImage),
				new ShowDialogueAction("Player: ???"),

			    
			    new EnablePlayerMoveAction(true)
				);
			Platform.runLater(()->{
				CutsceneManager.startCutscene(actionables);
			});

			player.setGameState(9);
	
	}

	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(),player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		// TODO Auto-generated method stub
		block(185, 605, 345, 355); // tv
		block(55, 215, 205, 325); // table
		block(30, 725, 350, 400); // wall right
		block(845, 875, 365, 400); // wall left
	}

}
