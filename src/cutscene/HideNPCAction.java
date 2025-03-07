package cutscene;

import javafx.scene.image.ImageView;

public class HideNPCAction implements Actionable {
	private ImageView npcImageView;

	public HideNPCAction(ImageView npcImageView) {
		this.npcImageView = npcImageView;
	}

	@Override
	public void execute(Runnable onComplete) {
		npcImageView.setVisible(false);

		try {
			onComplete.run();
			Thread.sleep(500); /* Delay for 500ms */
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
