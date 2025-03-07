package gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.ImageUtils;

public class TutorialPane {
	private static boolean isGuild = true;
	private static ImageView tutorialView = new ImageView();
	
	private static final Image GUILD_IMAGE = ImageUtils.createImage("subelement_image/description/guild.png");
	private static final Image POTION_IMAGE = ImageUtils.createImage("subelement_image/description/potion.png");
	
	public static HBox build(Stage primaryStage) {
		isGuild = true;
		
		HBox root = new HBox();
		root.setPrefSize(1440, 800);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(40);
		
		ImageView leftArrowView = createArrow("left");
		ImageView rightArrowView = createArrow("right");
		
		tutorialView.setFitWidth(800);
		tutorialView.setFitHeight(400);
		tutorialView.setImage(GUILD_IMAGE);
		
		root.getChildren().add(leftArrowView);
		root.getChildren().add(tutorialView);
		root.getChildren().add(rightArrowView);
			
		return root;
	}
	
	private static ImageView createArrow(String path) {
		ImageView arrowView = ImageUtils.createImageView("subelement_image/description/arrow-" + path + ".png", 80, 80);
		arrowView.setOnMouseClicked(event -> {
			isGuild = !isGuild;
			tutorialView.setImage(isGuild ? GUILD_IMAGE : POTION_IMAGE);
		});
		return arrowView;
	}
}
