package gui;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.ImageUtils;

public class SettingPane extends GameGui {
	private static final String FILE_PATH = "pause_screen.png";
	
	public static StackPane build() {
		StackPane root = new StackPane();
		root.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
		root.setStyle(BACKGROUND_STYLE);
		
		ImageView settingView = ImageUtils.createImageView(SCREEN_DIRECTORY_PATH + FILE_PATH, PREF_WIDTH, PREF_HEIGHT);
		root.getChildren().add(settingView);
		
		VBox settingPane = new VBox();
		settingPane.setSpacing(20);
		settingPane.setAlignment(Pos.CENTER);
		
		settingPane.getChildren().add(BackButton.build(primaryStage));
		settingPane.getChildren().add(TutorialPane.build(primaryStage));
		settingPane.getChildren().add(SoundPane.build(primaryStage));
		settingPane.getChildren().add(QuitButton.build(primaryStage));
		
		root.getChildren().add(settingPane);		
		return root;
	}
}
