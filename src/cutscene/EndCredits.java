package cutscene;

import application.MainApplication;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class EndCredits {
	public static Scene buildEndCredits() {
		String credits = """
				
				
				
				
END













		
CEDT Adventures








Developed by HOW TO PASS F

6733037621 Jedsada Meesuk

6733056521 Titiporn Somboon

6733065121 Nattarat Samartkit

6733172621 Patthadon Phengpinij



Programming:
EVERYONE







TRUST ME







Art:
Titiporn Somboon
Nattarat Samartkit
Jedsada Meesuk






Script:






I LET YOU GUESS







DON'T ANGRY ON US WE LOVE EVERYONE







THE CARRY:
Patthadon Phengpinij








SPECIAL THANK:



EVERYONE METIONED IN GAME







COFFEE MAKER TOO










Thank you!
""";
		
		Font pixelFontMini = Font.loadFont(EndCredits.class.getClassLoader().getResourceAsStream("font/pixelfont.otf"), 30);
		Font pixelFontLarge = Font.loadFont(EndCredits.class.getClassLoader().getResourceAsStream("font/pixelfont.otf"), 45);

		Text endText = new Text("THAT'S IT");
		endText.setTextAlignment(TextAlignment.CENTER);
		//endText.setStyle("-fx-font-size: 60; -fx-fill: white;");
		endText.setFont(pixelFontLarge); 
		endText.setFill(Color.WHITE);
		endText.setOpacity(0);

		
		Text creditText = new Text(credits);
		creditText.setTextAlignment(TextAlignment.CENTER);
		//creditText.setStyle("-fx-font-size: 45; -fx-fill: white;");
		creditText.setFont(pixelFontMini); 
		creditText.setFill(Color.WHITE);
		creditText.setWrappingWidth(1400); 
		StackPane.setAlignment(creditText, Pos.CENTER);
		
		ImageView croissantImage = new ImageView(new Image("game-logo.png"));
		croissantImage.setFitWidth(100);  
		croissantImage.setFitHeight(100);
		croissantImage.setOpacity(0);
		
		
		StackPane root = new StackPane(croissantImage, endText, creditText);
		root.setStyle("-fx-background-color: black;");
		
		

		
		Scene scene = new Scene(root, 1440, 720);

		PauseTransition showCroissant = new PauseTransition(Duration.seconds(0.5));
		showCroissant.setOnFinished(e -> croissantImage.setOpacity(1));

		RotateTransition rotateCroissant = new RotateTransition(Duration.seconds(5), croissantImage);
		rotateCroissant.setByAngle(150);
		
		rotateCroissant.setOnFinished(e -> croissantImage.setOpacity(0));

		PauseTransition showEndText = new PauseTransition(Duration.seconds(1));
		showEndText.setOnFinished(e -> endText.setOpacity(1));
		PauseTransition holdEndText = new PauseTransition(Duration.seconds(3));
		holdEndText.setOnFinished(e -> endText.setOpacity(0));
		creditText.setTranslateY(3000);

		TranslateTransition scrollAnimation = new TranslateTransition(Duration.seconds(30), creditText);
		scrollAnimation.setFromY(2500); 
		scrollAnimation.setToY(-2150);  
		scrollAnimation.setCycleCount(1);
		scrollAnimation.setOnFinished(event -> {
			Platform.runLater(() -> (new MainApplication()).resetGame());
		}); 
		
		SequentialTransition sequence = new SequentialTransition (showCroissant, rotateCroissant, showEndText, holdEndText, scrollAnimation);
		sequence.play();

		return scene;
	}
}
