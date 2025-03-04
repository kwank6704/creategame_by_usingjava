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
	public static HBox build() {
		Text dialogueText = new Text("Player: Did I fall asleep? What time is it?");
		dialogueText.setFill(Color.WHITE);
		dialogueText.setFont(Font.font("PixelFont", 20));
		
		HBox dialogueBox = new HBox(dialogueText);
		dialogueBox.setAlignment(Pos.CENTER);
		dialogueBox.setPrefSize(720, 60);
		dialogueBox.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		dialogueBox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), Insets.EMPTY)));
		
		HBox dialoguePane = new HBox(dialogueBox);
		dialoguePane.setPadding(new Insets(15, 0, 0, 0));
		dialoguePane.setAlignment(Pos.CENTER);
		
		return dialoguePane;
	}
}
