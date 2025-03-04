package gui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GenderSelectionPane {
	public static StackPane build(Stage primaryStage) {		
		StackPane root = new StackPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		Image genderBackground = new Image(ClassLoader.getSystemResource("screen/character_choose.png").toString());
		ImageView genderView = new ImageView(genderBackground);
		
		root.getChildren().add(genderView);
		root.getChildren().add(SettingButton.build(primaryStage));
		
		HBox genderChoosePane = GenderPane.build(primaryStage);
		root.getChildren().add(genderChoosePane);
		
		StackPane.setMargin(genderChoosePane, new Insets(0, 100, 0, 100));
				
		return root;
	}
}
