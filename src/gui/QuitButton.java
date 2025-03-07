package gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class QuitButton {
	public static HBox build(Stage primaryStage) {
		HBox quitButtonBox = new HBox();
		quitButtonBox.setAlignment(Pos.CENTER);
		quitButtonBox.setPadding(new Insets(15));
		quitButtonBox.setOnMouseClicked(mouseEvent -> {
			Platform.exit();
		});
		
		Image startImage = new Image(ClassLoader.getSystemResource("subelement_image/button/quit.png").toString());
		ImageView startView = new ImageView(startImage);
		Platform.runLater(() -> {
			double ratio = startImage.getHeight() / startImage.getWidth();
			startView.setFitWidth(100);
			startView.setFitHeight(100 * ratio);
		});
		
		quitButtonBox.getChildren().add(startView);
		return quitButtonBox;
	}
}
