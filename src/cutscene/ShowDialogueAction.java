package cutscene;

import gui.game_component.ButtomPane;

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
	
}
