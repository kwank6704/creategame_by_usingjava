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

public class Lanintania extends GameMap {

	public Lanintania(Pane playerPane, ImageView playerImage) {
		super("iScale Hall", "map_image/lanintania.png", new Integer[] {25, 870, -45, 380});
		
		Image paithongImage = new Image("npc/pee_chai_yai.png");
		ImageView npcImage = ImageUtils.createImageView(paithongImage, 160, 160);
		
		Player player = Player.getInstance();
		player.setGameState(4);
		
		player.setX(35);
		player.setY(330);
		Platform.runLater(() -> {
			
			player.updateMoveImage(Direction.RIGHT);
			playerImage.setImage(player.getMoveImage());	
		});
		
		player.setEnableMove(false);
		List<Actionable> actionables = Arrays.asList(
				//new EnablePlayerMoveAction(false),
				
				new ShowDialogueAction("Player: Well. The sky here is red too. I’m still stuck here."),
				new ShowNPCAction(playerPane, npcImage,210,155),
				new TurnPlayerAction(player, Direction.UP, playerImage),
				new ShowDialogueAction("Player: OH! There he is."),
				new ShowDialogueAction("Player: Are you the boss of this quest?"),
				new ShowDialogueAction("The Bamboo Merchant: What do you mean the boss, dear?"),
				new ShowDialogueAction("Player: I am stuck in a crack of dimension"),
				new ShowDialogueAction("Player: whatever that is."),
				new ShowDialogueAction("Player: I need to play your game to go to the next quest."),
				new ShowDialogueAction("Player: Am I wrong?"),
				new ShowDialogueAction("The Bamboo Merchant: You might be misunderstanding."),
				new ShowDialogueAction("The Bamboo Merchant: I’m just here to sell ice cream."),
				new ShowDialogueAction("The Bamboo Merchant: But you look tired."),
				new ShowDialogueAction("The Bamboo Merchant: I might give you one if you can help me with something."),
				new ShowDialogueAction("Player: What thing?"),
				new ShowDialogueAction("The Bamboo Merchant: Come here. I will show you."),

			    new EnablePlayerMoveAction(true)
				);
			Platform.runLater(()->{
				CutsceneManager.startCutscene(actionables);
			});

			player.setGameState(6);
	
	}

	@Override
	public void wall() {
		// TODO Auto-generated method stub
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(),player.getY());
		player.setX(position[0]);
		player.setY(position[1]);


		block(230, 330, 70, 215);
		block(115, 380, -95, 55);
		block(340, 410, -35, 35);
		block(170, 225, 95, 225); //NPC
	}

}
