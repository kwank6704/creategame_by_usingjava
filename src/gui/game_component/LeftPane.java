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
import skill.GuildList;
import utils.SlotPaneUtils;

import java.util.ArrayList;

public class LeftPane {
	private static String defaultImagePath = "game-logo-bw.png";
	private static VBox guildPane;
	
	public static VBox build() {
		Player player = Player.getInstance();
		ArrayList<GuildList> playerGuild = player.getGuilds();

		VBox slotsPane = SlotPaneUtils.createSlotsPane(3, defaultImagePath, 60);

		int i = 0;
		for (Node slot: slotsPane.getChildren()) {
			final int index = i;

			if (index < playerGuild.size() && playerGuild.get(index) != null) {
				String guildName = playerGuild.get(index).toString();
				String guildPath = "subelement_image/guild/" + guildName + ".png";
				
				setSlot(slot, guildPath);
				
				slot.setOnMouseClicked((MouseEvent event) -> activateSkill(index, slotsPane));
			} else {
				setSlot(slot, defaultImagePath);
			}
			
			i++;
		}

		guildPane = slotsPane;
		return slotsPane;
	}
	
	public static void refreshUI(VBox slotsPane, ArrayList<GuildList> playerGuild) {
		int i = 0;
		
		for (Node slot: slotsPane.getChildren()) {
			final int index = i;
			
			if (index < playerGuild.size() && playerGuild.get(index) != null) {
				String guildName = playerGuild.get(index).toString();
				String guildPath = "subelement_image/guild/" + guildName + ".png";
				System.out.println("Add " + guildName);

				setSlot(slot, guildPath);
				
				slot.setOnMouseClicked((MouseEvent event) -> activateSkill(index, slotsPane));
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
		slotImageView.setFitWidth(60);
		slotImageView.setFitHeight(60);
		
		((Pane) slot).getChildren().clear();
		((Pane) slot).getChildren().add(slotImageView);
	}
	
	private static void activateSkill(int index, VBox slotsPane) {
    	Player player = Player.getInstance();
    	ArrayList<GuildList> playerGuild = player.getGuilds();
        
        if (index < playerGuild.size() && playerGuild.get(index) != null) {
			GuildList guild = playerGuild.get(index);
			
			if (player.useSkill(guild)) {				
				Platform.runLater(() -> {
					playerGuild.remove(index);
					refreshUI(slotsPane, playerGuild);
				});
			}
		}
    }
	
	public static VBox getGuildInstance() {
		return guildPane;
	}
}
