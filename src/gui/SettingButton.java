package gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SettingButton {
	public static HBox build(Stage primaryStage) {
		HBox settingButtonBox = new HBox();
		settingButtonBox.setAlignment(Pos.TOP_RIGHT);
		settingButtonBox.setPadding(new Insets(15));
		settingButtonBox.setOnMouseClicked(mouseEvent -> {
			System.out.println("setting");
		});
		
		Image settingImage = new Image(ClassLoader.getSystemResource("subelement_image/button/setting.png").toString());
		
		ImageView settingView = new ImageView(settingImage);
		Platform.runLater(() -> {
			double ratio = settingImage.getHeight() / settingImage.getWidth();
			settingView.setFitWidth(60);
			settingView.setFitHeight(60 * ratio);
		});
		
		settingButtonBox.getChildren().add(settingView);
		return settingButtonBox;
	}
}
