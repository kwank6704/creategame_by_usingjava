package gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.ImageUtils;

public class StartPane extends GameGui {
	private static final String FILE_PATH = "homescreen.png";
	
	public static Pane build() {
		StackPane root = new StackPane();
		root.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
		root.setStyle(BACKGROUND_STYLE);
		
		ImageView startView = ImageUtils.createImageView(SCREEN_DIRECTORY_PATH + FILE_PATH, PREF_WIDTH, PREF_HEIGHT);
		root.getChildren().add(startView);
		
		VBox startPane = new VBox(2);
		startPane.setSpacing(250);
		startPane.getChildren().add(SettingButton.build(primaryStage));
		startPane.getChildren().add(StartButton.build(primaryStage));
		
		root.getChildren().add(startPane);
		return root;
	}
}
