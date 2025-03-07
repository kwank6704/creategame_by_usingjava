package gui.game_component;

import gui.SettingButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.Player;
import utils.ImageUtils;
import utils.StatusBarUtils;

public class TopPane {
	private static String DIRECTORYPATH = "subelement_image/status/";

	private static String healthPath = DIRECTORYPATH + "healthIcon.png";
	private static String energyPath = DIRECTORYPATH + "energyIcon.png";

	private static String emptyHealthPath = DIRECTORYPATH + "healthIcon-black.png";
	private static String emptyEnergyPath = DIRECTORYPATH + "energyIcon-black.png";

	private static GridPane healthPane;
	private static GridPane energyPane;

	public static BorderPane build(Stage primaryStage) {
		BorderPane topPane = new BorderPane();
		topPane.setPadding(new Insets(15));
		topPane.setLeft(setupStatusPane());
		topPane.setCenter(ImageUtils.createImageView("subelement_image/description/tutorial.gif", 600, 100));
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
