package gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.Gender;

public class GenderPane {
	public static HBox build(Stage primaryStage) {
		HBox genderChoosePane = new HBox();
		genderChoosePane.setAlignment(Pos.CENTER);
		genderChoosePane.setSpacing(100);
		genderChoosePane.setPadding(new Insets(200, 15, 15, 15));
		
		VBox maleBox = createGenderBox(primaryStage, Gender.MALE);
		VBox femaleBox = createGenderBox(primaryStage, Gender.FEMALE);
		
		genderChoosePane.getChildren().addAll(maleBox, femaleBox);
		return genderChoosePane;
	}
	
	private static VBox createGenderBox(Stage primaryStage, Gender gender) {
		VBox genderBox = new VBox(2);
		genderBox.setAlignment(Pos.CENTER);
		genderBox.setSpacing(25);
		
		Image genderImage = new Image(ClassLoader.getSystemResource("player_image/" + gender + ".png").toString());
		ImageView genderView = new ImageView(genderImage);
		Platform.runLater(() -> {
			double ratio = genderImage.getHeight() / genderImage.getWidth();
			genderView.setFitWidth(300);
			genderView.setFitHeight(300 * ratio);
		});
		
		Image genderButtonImage = new Image(ClassLoader.getSystemResource("subelement_image/button/" + gender + ".png").toString());
		ImageView genderButtonView = new ImageView(genderButtonImage);
		Platform.runLater(() -> {
			double ratio = genderButtonImage.getHeight() / genderButtonImage.getWidth();
			genderButtonView.setFitWidth(200);
			genderButtonView.setFitHeight(200 * ratio);
		});
		
		genderBox.getChildren().addAll(genderView, genderButtonView);
		return genderBox;
	}
}
