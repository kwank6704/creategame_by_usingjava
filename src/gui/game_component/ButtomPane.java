package gui.game_component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ButtomPane {
	private static Text dialogueText = new Text("");
	private static Font pixelFont = Font.loadFont(Thread.currentThread().getContextClassLoader().getResourceAsStream("font/pixelfont.otf"), 14);

	public static HBox build() {	
		dialogueText.setFill(Color.WHITE);
		dialogueText.setFont(pixelFont);
		
		HBox dialogueBox = new HBox(dialogueText);
		dialogueBox.setAlignment(Pos.CENTER);
		dialogueBox.setPrefSize(1200, 60);
		dialogueBox.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		dialogueBox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), Insets.EMPTY)));
		
		HBox dialoguePane = new HBox(dialogueBox);
		dialoguePane.setPadding(new Insets(15, 0, 0, 0));
		dialoguePane.setAlignment(Pos.CENTER);
		
		return dialoguePane;
	}
	
	public static void setDialogue(String newDialogue) {
		// Ensure dialogueText exists
	    if (dialogueText != null) {
	        dialogueText.setText(newDialogue);
	    } else {
	        System.out.println("Error: dialogueText is not initialized yet!");
	    }
	}
}
