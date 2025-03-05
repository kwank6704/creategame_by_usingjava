package gui.game_component;

import gui.SettingButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.Player;
import utils.StatusBarUtils;

public class TopPane {
	private static String directoryPath = "subelement_image/status/";

	private static String healthPath = directoryPath + "healthIcon.png";
	private static String energyPath = directoryPath + "energyIcon.png";

	private static String emptyHealthPath = directoryPath + "healthIcon-black.png";
	private static String emptyEnergyPath = directoryPath + "energyIcon-black.png";

	private static GridPane healthPane;
	private static GridPane energyPane;

	public static BorderPane build(Stage primaryStage) {
		BorderPane topPane = new BorderPane();
		topPane.setPadding(new Insets(15));
		topPane.setLeft(setupStatusPane());
		topPane.setRight(SettingButton.build(primaryStage));
		return topPane;
	}

	private static VBox setupStatusPane() {
		Player player = Player.getInstance();

		VBox statusPane = new VBox(5);
		statusPane.setAlignment(Pos.CENTER);
		statusPane.setPadding(new Insets(0, 0, 15, 0));
		statusPane.getChildren().addAll(setupHealthPane(player), setupEnergyPane(player));

		return statusPane;
	}

	private static GridPane setupHealthPane(Player player) {
		int playerHealth = player.getHealth();
		int maxHealth = 5;

		healthPane = StatusBarUtils.createStatusBar(220, 32, healthPath, emptyHealthPath, playerHealth, maxHealth);
		return healthPane;
	}

	private static GridPane setupEnergyPane(Player player) {
		int playerEnergy = player.getEnergy();
		int maxEnergy = 5;

		energyPane = StatusBarUtils.createStatusBar(180, 24, energyPath, emptyEnergyPath, playerEnergy, maxEnergy);
		return energyPane;
	}
	
	public static void updateHealthBar(int playerHealth, int maxHealth) {
		StatusBarUtils.updateStatusBar(healthPane, healthPath, emptyHealthPath, playerHealth, maxHealth);
	}
	
	public static void updateEnergyBar(int playerEnergy, int maxEnergy) {
		StatusBarUtils.updateStatusBar(energyPane, energyPath, emptyEnergyPath, playerEnergy, maxEnergy);
	}
}
