package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class StatusBarUtils {
	public static GridPane createStatusBar(int width, int iconSize, String iconPath, String emptyIconPath, int currentStatus, int maxStatus) {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setPrefWidth(width);
		pane.setPadding(new Insets(0, 10, 0, 10));
		pane.setAlignment(Pos.CENTER);
		pane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));

		for (int i = 0; i < maxStatus; i++) {
			String imagePath = (i < currentStatus) ? iconPath : emptyIconPath;
			pane.add(ImageUtils.createImageView(imagePath, iconSize, iconSize), i, 0);
		}
		
		return pane;
	}
	
	public static GridPane updateStatusBar(GridPane statusBar, String iconPath, String emptyIconPath, int currentStatus, int maxStatus) {
		for (int i = 0; i < maxStatus; i++) {
			ImageView status = (ImageView) statusBar.getChildren().get(i);
			String imagePath = (i < currentStatus) ? iconPath : emptyIconPath;
			status.setImage(new Image(imagePath));
		}
		
		return statusBar;
	}
}
