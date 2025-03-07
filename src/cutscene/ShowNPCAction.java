package cutscene;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ShowNPCAction implements Actionable {
	private Pane playerPane;
	private ImageView npcImageView;
	private int x;
	private int y;

	public ShowNPCAction(Pane PlayerPane, ImageView npcImageView, int x, int y) {
		this.playerPane = PlayerPane;
		this.npcImageView = npcImageView;
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute(Runnable onComplete) {
		npcImageView.setVisible(true);
		
		
		npcImageView.setLayoutX(x);
		npcImageView.setLayoutY(y);

		playerPane.getChildren().add(npcImageView);
		try {
			onComplete.run();
			
		
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
