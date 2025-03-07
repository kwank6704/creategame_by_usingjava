package gui.game_component;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import map.MapList;
import player.Gender;

public class MainGamePane {
	private static BorderPane mainGamePane;
	private static Gender gameGender;
	
	public static BorderPane build(Stage primaryStage, Gender gender) {
		gameGender = gender;
		
		BorderPane root = new BorderPane();
		root.setPrefSize(1440, 720);
		root.setStyle("-fx-background-color: #D0C8C8;");
		
		root.setTop(TopPane.build(primaryStage));
		root.setCenter(CenterPane.build(gender, MapList.I_SCALE_ROOM));
		root.setLeft(LeftPane.build());
		root.setRight(RightPane.build());
		root.setBottom(ButtomPane.build());

		mainGamePane = root;
		return root;
	}
	
	public static BorderPane getInstance() {
		return mainGamePane;
	}
	
	public static Gender getGender() {
		return gameGender;
	}
}
