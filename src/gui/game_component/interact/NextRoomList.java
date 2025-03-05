package gui.game_component.interact;

import map.MapList;

public enum NextRoomList {
	IScaleRoom,
	IScaleHall,
	Elevator,
	ToFloor1,
	ToComputerCenter,
	OutComputerCenter,
	ToDatabase,
	ToGuild,
	ToGuildCheated,
	ToFloor4;
	
	public String getConfirmationText() {
		switch (this) {
		case IScaleRoom: {
			return "Do you want to go to hall?";
		}
		case IScaleHall: {
			return "Do you want to go to elevator?";
		}
		case Elevator: {
			return "Do you want to use elevator?";
		}
		case ToFloor1: {
			return "Do you want to go to Building 14 hall?";
		}
		case ToComputerCenter: {
			return "Do you want to go to Computer Center?";
		}
		case OutComputerCenter: {
			return "Do you want to leave Computer Center?";
		}
		case ToDatabase: {
			return "Do you want to get in this room?";
		}
		case ToGuild: {
			return "Door is unlocked";
		}
		case ToGuildCheated: {
			return "You think I don't know you cheated, don't you?";
		}
		case ToFloor4: {
			return "Are you Ready to fight the boss?";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this);
		}
	}
	
	public MapList getNextMap() {
		switch (this) {
		case IScaleRoom: {
			return MapList.I_SCALE_HALL;
		}
		case IScaleHall: {
			return MapList.ELEVATOR;
		}
		case Elevator: {
			return MapList.LAN_INTANIA;
		}
		case ToFloor1: {
			return MapList.BUILD_4_HALL;
		}
		case ToComputerCenter: {
			return MapList.COMPUTER_ROOM;
		}
		case OutComputerCenter: {
			return MapList.LAN_INTANIA;
		}
		case ToDatabase: {
			return MapList.FLOOR_1;
		}
		case ToGuild: {
			return MapList.GUILD_ROOM;
		}
		case ToGuildCheated: {
			return MapList.GUILD_ROOM;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this);
		}
	}
}
