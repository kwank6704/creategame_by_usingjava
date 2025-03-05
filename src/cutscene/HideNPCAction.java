package cutscene;

import javafx.scene.image.ImageView;

public class HideNPCAction implements Actionable  {
	private ImageView npcImageView;
	public HideNPCAction(ImageView npcImageView) {
		this.npcImageView = npcImageView;
	}

	@Override	
	public void execute(Runnable onComplete) {
		npcImageView.setVisible(false);
		
		try {
			onComplete.run();
			
			// Delay for 500ms
			Thread.sleep(500);  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
