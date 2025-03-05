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

public class TA_Room extends GameMap {
	private Player player = Player.getInstance();
	
	public TA_Room(Pane playerPane, ImageView playerImage) {
		super("TA ROOM", "map_image/ta_room.png", new Integer[] {-25, 900, -35, 370});
		
		player.setX(420);
		player.setY(365);
		player.setHealth(0);
		Platform.runLater(() -> {
			player.updateMoveImage(Direction.UP);
			playerImage.setImage(player.getMoveImage());	
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
		
		
		Platform.runLater(()->{
			CutsceneManager.startCutscene(actionables);
		});
		//player.setGameState(1);

	}

	@Override
	public void wall() {
		// TODO Auto-generated method stub
		
		Integer[] position = boundaryCheck(player.getX(),player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		
		block(70, 760, 0, 35); //Table
		block(600, 780, 140, 315); //left desk
		block(165, 360, 140, 315); //right desk
		block(-30, 25, -35, 5); //Tree Top
		block(845, 900, 305, 370); //Tree btm
		block(860, 900, 85, 120); //drawer 1
		block(860, 900, 180, 210); //drawer 1
		block(-50, 55, 185, 190); //room
		block(50, 60, 190, 260); //room
		block(50, 60, 330, 370); //room
		block(-25, 5, 195, 370); //
		block(525, 535, 140, 310); //table 

	}

}
