package cutscene;

import javafx.scene.layout.StackPane;
import utils.GameInstanceUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import gui.game_component.ButtomPane;

public class CutsceneManager {

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

			StackPane gamePane = GameInstanceUtils.getGamePane();
			if (gamePane != null) {
				gamePane.setOnMouseClicked(event -> {
					gamePane.setOnMouseClicked(null);				/* Remove listener after clicking */
					action.execute(CutsceneManager::runNextAction);	/* Move to next action */
				});
			}
		}
	}

	
}
