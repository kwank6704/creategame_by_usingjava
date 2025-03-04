package gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StartButton {
	public static HBox build(Stage primaryStage) {
		HBox startButtonBox = new HBox();
		startButtonBox.setAlignment(Pos.CENTER);
		startButtonBox.setPadding(new Insets(15));
		startButtonBox.setOnMouseClicked(mouseEvent -> {
			System.out.println("start");
		});
		
		Image startImage = new Image(ClassLoader.getSystemResource("subelement_image/button/start.png").toString());
		ImageView startView = new ImageView(startImage);
		Platform.runLater(() -> {
			double ratio = startImage.getHeight() / startImage.getWidth();
			startView.setFitWidth(300);
			startView.setFitHeight(300 * ratio);
		});
		
		startButtonBox.getChildren().add(startView);
		return startButtonBox;
	}
}
