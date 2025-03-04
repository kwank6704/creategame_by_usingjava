package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartPane {
	public static StackPane build(Stage primaryStage) {		
		StackPane root = new StackPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		Image startBackground = new Image(ClassLoader.getSystemResource("screen/homescreen.png").toString());
		ImageView startView = new ImageView(startBackground);
		root.getChildren().add(startView);
		
		VBox startPane = new VBox(2);
		startPane.setSpacing(250);
		startPane.getChildren().add(SettingButton.build(primaryStage));
		startPane.getChildren().add(StartButton.build(primaryStage));
		
		root.getChildren().add(startPane);
		return root;
	}
}
