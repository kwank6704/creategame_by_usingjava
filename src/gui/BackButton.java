package gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BackButton {
	public static HBox build(Stage primaryStage) {
		HBox backButtonBox = new HBox();
		backButtonBox.setAlignment(Pos.TOP_RIGHT);
		backButtonBox.setPadding(new Insets(15));
		backButtonBox.setOnMouseClicked(mouseEvent -> {
			System.out.println("back");
		});
		
		Image settingImage = new Image(ClassLoader.getSystemResource("subelement_image/button/back.png").toString());
		ImageView settingView = new ImageView(settingImage);
		Platform.runLater(() -> {
			double ratio = settingImage.getHeight() / settingImage.getWidth();
			settingView.setFitWidth(100);
			settingView.setFitHeight(100 * ratio);
		});
		
		backButtonBox.getChildren().add(settingView);
		return backButtonBox;
	}
}
