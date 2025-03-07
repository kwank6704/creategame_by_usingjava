package gui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import utils.ImageUtils;

public class GenderSelectionPane extends GameGui {
	private static final String FILE_PATH = "character_choose.png";
	
	public static StackPane build() {	
		StackPane root = new StackPane();
		root.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
		root.setStyle(BACKGROUND_STYLE);
		
		ImageView genderView = ImageUtils.createImageView(SCREEN_DIRECTORY_PATH + FILE_PATH, PREF_WIDTH, PREF_HEIGHT);		
		root.getChildren().add(genderView);
		
		root.getChildren().add(SettingButton.build(primaryStage));
		
		HBox genderChoosePane = GenderPane.build(primaryStage);
		root.getChildren().add(genderChoosePane);
		
		StackPane.setMargin(genderChoosePane, new Insets(0, 100, 0, 100));

		return root;
	}
}
