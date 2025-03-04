package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingPane {
	public static StackPane build(Stage primaryStage) {
		StackPane root = new StackPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		Image genderBackground = new Image(ClassLoader.getSystemResource("screen/pause_screen.png").toString());
		ImageView genderView = new ImageView(genderBackground);
		root.getChildren().add(genderView);
		
		VBox genderPane = new VBox(2);
		genderPane.setSpacing(250);
		genderPane.getChildren().add(BackButton.build(primaryStage));
		genderPane.getChildren().add(QuitButton.build(primaryStage));
		
		root.getChildren().add(genderPane);		
		return root;
	}
}
