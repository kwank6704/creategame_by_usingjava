package gui.game_component;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import map.MapList;
import player.Gender;

public class GamePane {
	public static BorderPane build(Stage primaryStage, Gender gender) {
		BorderPane root = new BorderPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		root.setTop(TopPane.build(primaryStage));
		root.setCenter(CenterPane.build(gender, MapList.IScaleHall));
		root.setLeft(LeftPane.build());
		root.setRight(RightPane.build());
		root.setBottom(ButtomPane.build());

		return root;
	}
}
