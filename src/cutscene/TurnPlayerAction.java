package cutscene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import player.Direction;
import player.Player;

public class TurnPlayerAction implements Actionable{
	private Player player = Player.getInstance();
	private Direction direction;
	private ImageView playerImage;
	
	public TurnPlayerAction(Player player, Direction direction, ImageView playerImage) {
		this.direction = direction;
		this.playerImage = playerImage;
	}

	@Override
	public void execute(Runnable onComplete) {	 
		String imageNumberString = Integer.toString((int) Math.floor(1.0));
		String imagePath = "/player_image/" + player.getGender() + "_walk_" + direction + "_" + imageNumberString + ".png";
	
		Image moveImage = new Image(getClass().getResource(imagePath).toExternalForm());
			
		playerImage.setImage(moveImage);
		
		try {
			onComplete.run();	
			Thread.sleep(500);  // Delay for 500ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
