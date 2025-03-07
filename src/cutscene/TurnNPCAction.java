package cutscene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import player.Direction;

public class TurnNPCAction implements Actionable {
	private String NPCname;
	private Direction direction;
	private ImageView npcImageView;

	public TurnNPCAction(String NPCname, Direction direction, ImageView npcImageView) {
		this.NPCname = NPCname;
		this.direction = direction;
		this.npcImageView = npcImageView;
	}

	@Override
	public void execute(Runnable onComplete) {
		String npcURL = "npc/" + NPCname + "_" + direction.toString() + ".png";
		Image npcImage = new Image(npcURL);
		npcImageView.setImage(npcImage);

		try {
			onComplete.run();
			Thread.sleep(500); /* Delay for 500ms */
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
