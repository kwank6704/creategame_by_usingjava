package gui.game_component;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import player.Player;
import potion.Potion;
import utils.SlotPaneUtils;

import java.util.ArrayList;

public class RightPane {
	private static String defaultImagePath = "game-logo-bw.png";
	private static VBox potionPane;
	
	public static VBox build() {
		Player player = Player.getInstance();
		ArrayList<Potion> playerPotions = player.getPotions();

		VBox slotsPane = SlotPaneUtils.createSlotsPane(5, defaultImagePath, 45);
		
		int i = 0;
		for (Node slot: slotsPane.getChildren()) {
			final int index = i;
			
			if (index < playerPotions.size() && playerPotions.get(index) != null) {
				Potion potion = playerPotions.get(index);
				String potionPath = "subelement_image/potion/" + potion.getType().toString() + ".png";
				
				setSlot(slot, potionPath);
				
				slot.setOnMouseClicked((MouseEvent event) -> activatePotion(index, slotsPane));
			} else {
				setSlot(slot, defaultImagePath);
			}
			
			i++;
		}
		
		potionPane = slotsPane;
		return slotsPane;
	}
	
	public static void refreshUI(VBox slotsPane, ArrayList<Potion> playerPotions) {
		int i = 0;
		
		for (Node slot: slotsPane.getChildren()) {
			final int index = i;
			
			if (i < playerPotions.size() && playerPotions.get(i) != null) {
				Potion potion = playerPotions.get(i);
				String potionPath = "subelement_image/potion/" + potion.getType().toString() + ".png";
				
				setSlot(slot, potionPath);
				
				slot.setOnMouseClicked((MouseEvent event) -> activatePotion(index, slotsPane));
			} else {
				setSlot(slot, defaultImagePath);
			}
			
			i++;
		}
	}
	
	private static void setSlot(Node node, String imagePath) {
		HBox slot = (HBox) node;
		
		slot.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(3))));
		slot.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
		
		Image slotImage = new Image(imagePath);
		ImageView slotImageView = new ImageView(slotImage);
		slotImageView.setFitWidth(45);
		slotImageView.setFitHeight(45);
		
		((Pane) slot).getChildren().clear();
		((Pane) slot).getChildren().add(slotImageView);
	}
	
	private static void activatePotion(int index, VBox slotsPane) {
		Player player = Player.getInstance();
		ArrayList<Potion> playerPotions = player.getPotions();
		
		if (index < playerPotions.size() && playerPotions.get(index) != null) {
			Potion selectedPotion = playerPotions.get(index);
			selectedPotion.applyEffect(player); 

			Platform.runLater(() -> {
				playerPotions.remove(index);  
				refreshUI(slotsPane, playerPotions);
			});
		}
	}
	
	public static VBox getPotionInstance() {
		return potionPane;
	}
}
