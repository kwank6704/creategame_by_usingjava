package gui.game_component;

import javafx.scene.layout.VBox;
import utils.SlotPaneUtils;

public class RightPane {
	public static VBox build() {
		return SlotPaneUtils.createSlotsPane(5, "game-logo.png", 45);
	}
}
