package cutscene.interact;

import logic.GameStateList;
import map.MapList;
import player.Player;

public enum NextRoomList {
	TO_ISCALE_HALL,
	TO_ELEVATOR,
	TO_LANGEAR,
	TO_BUILD4_HALL,
	TO_COMPUTER_CENTER,
	OUT_COMPUTER_CENTER,
	TO_DATABASE,
	TO_GUILD_ROOM,
	TO_GUILD_ROOM_LOCKED,
	TO_BOSS,
	TO_END_CREDITS;
	
	Player player = Player.getInstance();
	
	public String getConfirmationText() {
		switch (this) {
		case TO_ISCALE_HALL: {
			return "Do you want to go to hall?";
		}
		case TO_ELEVATOR: {
			return "Do you want to go to elevator?";
		}
		case TO_LANGEAR: {
			return "Do you want to use elevator?";
		}
		case TO_BUILD4_HALL: {
			return "Do you want to go to Building 4 hall?";
		}
		case TO_COMPUTER_CENTER: {
			return "Do you want to go to Computer Center?";
		}
		case OUT_COMPUTER_CENTER: {
			return "Do you want to leave Computer Center?";
		}
		case TO_DATABASE: {
			return "Do you want to get in this room?";
		}
		case TO_GUILD_ROOM: {
			if (player.getGameState() == GameStateList.STAGE_11__BUILDING_4_HALL)
				return "You think I don't know you cheated, don't you?";
			if (player.getGameState() == GameStateList.STAGE_14__BUILDING_4_HALL__AFTER__DATABASE)
				return "The door is unlocked now, you can get inside it.";
		}
		case TO_GUILD_ROOM_LOCKED: {
			return "This door is locked";
		}
		case TO_BOSS: {
			return "Are you Ready to fight the boss?";
		}
		case TO_END_CREDITS: {
			return "This is the end of the game. Press `Yes` to know more about us!";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this);
		}
	}
	
	public MapList getNextMap() {
		switch (this) {
		case TO_ISCALE_HALL: {
			return MapList.I_SCALE_HALL;
		}
		case TO_ELEVATOR: {
			return MapList.ELEVATOR;
		}
		case TO_LANGEAR: {
			return MapList.LARN_GEAR;
		}
		case TO_BUILD4_HALL: {
			return MapList.BUILD_4_HALL;
		}
		case TO_COMPUTER_CENTER: {
			return MapList.COMPUTER_CENTER;
		}
		case OUT_COMPUTER_CENTER: {
			return MapList.LARN_GEAR;
		}
		case TO_DATABASE: {
			return MapList.FLOOR_1;
		}
		case TO_GUILD_ROOM: {
			return MapList.GUILD_ROOM;
		}
		case TO_BOSS: {
			return MapList.FLOOR_4;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this);
		}
	}
}
