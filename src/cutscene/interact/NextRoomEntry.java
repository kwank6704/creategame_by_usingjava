package cutscene.interact;

import javafx.application.Platform;
import logic.GameStateList;
import player.Player;
import utils.AlertUtils;

public class NextRoomEntry extends InteractableObject {
	private Player player = Player.getInstance();

	public NextRoomEntry(double x, double y, GameStateList gameState, String description) {
		super(x, y, DEFAULT_SPAN_X, DEFAULT_SPAN_Y, gameState, description);
	}
	
	public NextRoomEntry(double x, double y, int spanX, int spanY, GameStateList gameState, String description) {
		super(x, y, spanX, spanY, gameState, description);
	}

	@Override
	public void interact() {
		Platform.runLater(() -> {
			GameStateList gameState = player.getGameState();
			
			switch (gameState) {
			case STAGE_02__ISCALE_ROOM__GAME_FINISHED: {
				AlertUtils.setNextRoomAlert(NextRoomList.TO_ISCALE_HALL);
				break;
			}
			case STAGE_03__ISCALE_HALL: {
				AlertUtils.setNextRoomAlert(NextRoomList.TO_ELEVATOR);
				break;
			}
			case STAGE_05__ELEVATOR__GAME_FINISHED: {
				AlertUtils.setNextRoomAlert(NextRoomList.TO_LANGEAR);
				break;
			}
			case STAGE_07__LARN_GEAR__GAME_FINISHED: {
				String name = this.getName();
				if (name.equals("LarnGear => Building 4 Hall")) {
					AlertUtils.setNextRoomAlert(NextRoomList.TO_BUILD4_HALL);
				} else if (name.equals("LarnGear => Computer Center")) {
					AlertUtils.setNextRoomAlert(NextRoomList.TO_COMPUTER_CENTER);
				}
				break;
			}
			case STAGE_09__COMPUTER_CENTER__GAME_FINISHED: {
				AlertUtils.setNextRoomAlert(NextRoomList.OUT_COMPUTER_CENTER);
				break;
			}
			case STAGE_10__LARN_GEAR__AFTER__COMPUTER_CENTER: {
				AlertUtils.setNextRoomAlert(NextRoomList.TO_BUILD4_HALL);
				break;
			}
			case STAGE_11__BUILDING_4_HALL: {
				String name = this.getName();
				if (name.equals("Building 4 Hall => Database")) {
					AlertUtils.setNextRoomAlert(NextRoomList.TO_DATABASE);
				} else if (name.equals("Building 4 Hall => Guild Room")) {
					AlertUtils.setNextRoomAlert(NextRoomList.TO_GUILD_ROOM);
				} else if (name.equals("Building 4 Hall => Guild Room (Locked)")) {
					AlertUtils.setNextRoomAlert(NextRoomList.TO_GUILD_ROOM_LOCKED);
				}
				break;
			}
			case STAGE_13__DATABASE__GAME_FINISHED: {
				AlertUtils.setNextRoomAlert(NextRoomList.TO_BUILD4_HALL);
				break;
			}
			case STAGE_14__BUILDING_4_HALL__AFTER__DATABASE: {
				AlertUtils.setNextRoomAlert(NextRoomList.TO_GUILD_ROOM);
				break;
			}
			case STAGE_15__GUILD_ROOM: {
				AlertUtils.setNextRoomAlert(NextRoomList.TO_BOSS);
				break;
			}
			case STAGE_17__LAST_BOSS__GAME_FINISHED: {
				AlertUtils.setNextRoomAlert(NextRoomList.TO_END_CREDITS);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + gameState);
			}
		});
	}

	@Override
	public boolean gameStateCondition(GameStateList playerGameState) {
		return this.getGameState() == playerGameState;
	}

}
