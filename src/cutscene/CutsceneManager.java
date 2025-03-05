package cutscene;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import gui.game_component.ButtomPane;
import javafx.application.Platform;

public class CutsceneManager {
	private static List<String> dialogues;
	private static int currentDialogueIndex = 0;
	private static Runnable onCutsceneEnd;
	private static Queue<Actionable> actionQueue = new LinkedList<>();

	public static void startCutscene(List<Actionable> actions) {
		actionQueue.clear();
		actionQueue.addAll(actions);
		runNextAction();
	}

	private static void runNextAction() {
		if (!actionQueue.isEmpty()) {
			Actionable action = actionQueue.poll();
			
			System.out.println("Executing action: " + action.getClass().getSimpleName());
			
			if (getCurrentScene() != null) {
				getCurrentScene().setOnMouseClicked(event -> {
					// Remove listener after clicking
					getCurrentScene().setOnMouseClicked(null);
					
					// Move to next action
					action.execute(CutsceneManager::runNextAction); 
				});
			}
		}
	}

	public static void showNextDialogue() {
		if (dialogues == null || dialogues.isEmpty()) {
			System.out.println("Error: No dialogues found!");
			return;
		}

		if (currentDialogueIndex < dialogues.size()) {
			ButtomPane.setDialogue(dialogues.get(currentDialogueIndex));
			currentDialogueIndex++;
		} else {
			Scene currentScene = getCurrentScene();
			if (currentScene != null) {
				// Remove listener
				currentScene.setOnMouseClicked(null);
			}

			if (onCutsceneEnd != null) {
				onCutsceneEnd.run();
			}
		}
	}

	private static Scene getCurrentScene() {
		return Platform.isFxApplicationThread()
			? Stage.getWindows().stream()
				  .filter(Window::isShowing)
				  .findFirst()
				  .map(Window::getScene)
				  .orElse(null)
			: null;
	}
}
