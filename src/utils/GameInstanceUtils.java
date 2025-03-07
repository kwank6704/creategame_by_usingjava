package utils;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GameInstanceUtils {
	public static Scene getCurrentScene() {
		return Platform.isFxApplicationThread()
				? Stage.getWindows().stream().filter(Window::isShowing).findFirst().map(Window::getScene).orElse(null)
				: null;
	}

	public static StackPane getGamePane() {
		Scene currScene = getCurrentScene();

		BorderPane currentPane = (BorderPane) currScene.getRoot();
		return (StackPane) currentPane.getCenter();
	}
}
