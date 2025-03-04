package utils;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SlotPaneUtils {
	public static VBox createSlotsPane(int count, String imagePath, int iconSize) {
		VBox slotsPane = new VBox(10);
		slotsPane.setAlignment(Pos.CENTER);
		slotsPane.setPadding(new Insets(0, 50, 0, 50));
		slotsPane.setSpacing(15);
		
		for (int i = 0; i < count; i++) {
			HBox slot = new HBox();
			slot.setAlignment(Pos.CENTER);
			slot.setPrefSize(80, 80);
			slot.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
			slot.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
			slot.getChildren().add(ImageUtils.createImageView(imagePath, iconSize, iconSize));
			slotsPane.getChildren().add(slot);
		}
		return slotsPane;
	}
}
