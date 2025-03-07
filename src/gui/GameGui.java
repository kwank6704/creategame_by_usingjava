package gui;

import application.MainApplication;
import javafx.stage.Stage;
import player.Player;

public class GameGui {
	protected static Player player = Player.getInstance();
	protected static Stage primaryStage = MainApplication.getCurrentStage();
	
	protected static final int PREF_WIDTH = 1440;
	protected static final int PREF_HEIGHT = 720;
	
	protected static final String BACKGROUND_STYLE = "-fx-background-color: #D0C8C8;";
	
	protected static final String SCREEN_DIRECTORY_PATH = "screen/";
}