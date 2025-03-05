package gui.game_component.interact;

import javafx.application.Platform;
import player.Player;
import utils.AlertUtils;

public class NextRoomEntry extends InteractableObject {
	Player player = Player.getInstance();

	public NextRoomEntry(double x, double y, int gameState, String name) {
		super(x, y, gameState, name);
	}

	@Override
	public void interact() {
		Platform.runLater(() -> {
			int gameState = player.getGameState();
			
			switch (gameState) {
			case 2: {
				AlertUtils.setNextRoomAlert(NextRoomList.IScaleRoom);
				break;
			}
			case 3: {
				AlertUtils.setNextRoomAlert(NextRoomList.IScaleHall);
				break;
			}
			case 5: {
				AlertUtils.setNextRoomAlert(NextRoomList.Elevator);
				break;
			}
			case 7: {
				String name = this.getName();
				if (name.equals("toFloor1")) {
					AlertUtils.setNextRoomAlert(NextRoomList.ToFloor1);
				} else if (name.equals("toComputerCenter")) {
					AlertUtils.setNextRoomAlert(NextRoomList.ToComputerCenter);
				} else if (name.equals("outComputerCenter")) {
					AlertUtils.setNextRoomAlert(NextRoomList.OutComputerCenter);
				}
				break;
			}
			case 8: {
				AlertUtils.setNextRoomAlert(NextRoomList.ToDatabase);
				player.setGameState(10);
				System.out.println("not cheated");
				break;
			}
			case 10: {
				String name = this.getName();
				if (name.equals("toGuild")) {
					AlertUtils.setNextRoomAlert(NextRoomList.ToGuild);
				} else if (this.getName().equals("toHall")) {
					AlertUtils.setNextRoomAlert(NextRoomList.ToFloor1);
				}
				break;
			}
			case 12: {
				AlertUtils.setNextRoomAlert(NextRoomList.ToFloor4);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + gameState);
			}
		});
	}

	@Override
	public boolean gameStateCondition(int playerGameState) {
		if (this.getGameState() == playerGameState)
			return true;
		return false;
	}

}
