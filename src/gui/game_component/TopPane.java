package gui.game_component;

import gui.SettingButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ImageUtils;

public class TopPane {
	public static BorderPane build(Stage primaryStage) {
		BorderPane topPane = new BorderPane();
		topPane.setPadding(new Insets(15));
		topPane.setLeft(setupStatusPane());
		topPane.setRight(SettingButton.build(primaryStage));
		return topPane;
	}

	private static VBox setupStatusPane() {
		VBox statusPane = new VBox(5);
		statusPane.setAlignment(Pos.CENTER);
		statusPane.setPadding(new Insets(0, 0, 15, 0));
		statusPane.getChildren().addAll(setupHealthPane(), setupEnergyPane());
		return statusPane;
	}

	private static GridPane setupHealthPane() {
		GridPane healthPane = createStatusBar(220, 32, "subelement_image/healthIcon.png", 5);
		return healthPane;
	}

	private static GridPane setupEnergyPane() {
		GridPane energyPane = createStatusBar(180, 24, "subelement_image/energyIcon.png", 5);
		return energyPane;
	}

	private static GridPane createStatusBar(int width, int iconSize, String imagePath, int count) {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setPrefWidth(width);
		pane.setPadding(new Insets(0, 10, 0, 10));
		pane.setAlignment(Pos.CENTER);
		pane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
		pane.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));

		for (int i = 0; i < count; i++) {
			pane.add(ImageUtils.createImageView(imagePath, iconSize, iconSize), i, 0);
		}
		return pane;
	}
}
