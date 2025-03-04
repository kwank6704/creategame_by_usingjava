package gui.game_component;

import javafx.scene.layout.VBox;
import utils.SlotPaneUtils;

public class LeftPane {
	public static VBox build() {
		return SlotPaneUtils.createSlotsPane(3, "game-logo.png", 60);
		
	}
}
