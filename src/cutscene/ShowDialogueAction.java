package cutscene;

import gui.game_component.ButtomPane;

//import javafx.application.Platform;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.stage.Window;

public class ShowDialogueAction implements Actionable {
	private String dialogue;

	public ShowDialogueAction(String dialogue) {
		this.dialogue = dialogue;
	}

	@Override
	public void execute(Runnable onComplete) {
		ButtomPane.setDialogue(dialogue);
		onComplete.run();
	}
	
//	private static Scene getCurrentScene() {
//		return Platform.isFxApplicationThread()
//			? Stage.getWindows().stream()
//				  .filter(Window::isShowing)
//				  .findFirst()
//				  .map(Window::getScene)
//				  .orElse(null)
//			: null;
//	}
}
