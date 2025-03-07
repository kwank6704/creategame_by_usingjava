package logic;

import java.util.Arrays;
import java.util.List;

import cutscene.interact.InteractableObject;
import cutscene.interact.MinigameEntry;
import cutscene.interact.NextRoomEntry;
import cutscene.interact.WithdrawEntry;

public enum GameStateList {
	STAGE_00__GAME_START,
	STAGE_01__ISCALE_ROOM,
	STAGE_02__ISCALE_ROOM__GAME_FINISHED,
	STAGE_03__ISCALE_HALL,
	STAGE_04__ELEVATOR,
	STAGE_05__ELEVATOR__GAME_FINISHED,
	STAGE_06__LARN_GEAR,
	STAGE_07__LARN_GEAR__GAME_FINISHED,
	STAGE_08__COMPUTER_CENTER,
	STAGE_09__COMPUTER_CENTER__GAME_FINISHED,
	STAGE_10__LARN_GEAR__AFTER__COMPUTER_CENTER,
	STAGE_11__BUILDING_4_HALL,
	STAGE_12__DATABASE,
	STAGE_13__DATABASE__GAME_FINISHED,
	STAGE_14__BUILDING_4_HALL__AFTER__DATABASE,
	STAGE_15__GUILD_ROOM,
	STAGE_16__LAST_BOSS,
	STAGE_17__LAST_BOSS__GAME_FINISHED,
	STAGE_18__GAME_COMPLETED,
	STAGE_99__GAME_OVER;
	
	private static List<InteractableObject> interactables;
	
	public static List<InteractableObject> getInteractableList() {
		interactables = Arrays.asList(
				new MinigameEntry(410, 370, STAGE_01__ISCALE_ROOM, "iScale Room | MiniGame"),
				new NextRoomEntry(300, 100, STAGE_02__ISCALE_ROOM__GAME_FINISHED, "iScale Room => iScale Hall"), 
				new NextRoomEntry(865, 250, 50, 100, STAGE_03__ISCALE_HALL, "iScale Hall => Elevator"),
				new MinigameEntry(435, 310, STAGE_04__ELEVATOR, "Elevator | MiniGame"), 
				new NextRoomEntry(315, 310, STAGE_05__ELEVATOR__GAME_FINISHED, "Elevator => LarnGear"),
				new MinigameEntry(205, 230, 80, 80, STAGE_06__LARN_GEAR, "Larngear | Minigame"),
				new NextRoomEntry(25, -45, STAGE_07__LARN_GEAR__GAME_FINISHED, "LarnGear => Building 4 Hall"), 
				new NextRoomEntry(840, -40, STAGE_07__LARN_GEAR__GAME_FINISHED, "LarnGear => Computer Center"),
				new NextRoomEntry(860, -40, STAGE_07__LARN_GEAR__GAME_FINISHED, "LarnGear => Computer Center"),
				new MinigameEntry(530, 40, STAGE_08__COMPUTER_CENTER, "Computer Center | MiniGame"), 
				new NextRoomEntry(260, 420, STAGE_09__COMPUTER_CENTER__GAME_FINISHED, "Computer Center => LarnGear"),
				new NextRoomEntry(25, -45, STAGE_10__LARN_GEAR__AFTER__COMPUTER_CENTER, "LarnGear => Building 4 Hall"),
				new NextRoomEntry(90, 0, STAGE_11__BUILDING_4_HALL, "Building 4 Hall => Database"), 
				new NextRoomEntry(475, 35, STAGE_11__BUILDING_4_HALL, "Building 4 Hall => Guild Room"),
				new NextRoomEntry(830, 0, STAGE_11__BUILDING_4_HALL, "Building 4 Hall => Guild Room (Locked)"),
				new MinigameEntry(220, 270, STAGE_12__DATABASE, "Database | MiniGame"),
				new NextRoomEntry(785, 385, STAGE_13__DATABASE__GAME_FINISHED, "Database => Building 4 Hall"), 
				new NextRoomEntry(830, 0, STAGE_14__BUILDING_4_HALL__AFTER__DATABASE, "Building 4 Hall => Guild Room"),
				new MinigameEntry(295, 150, STAGE_15__GUILD_ROOM, "Guild Room | MiniGame"),
				new NextRoomEntry(95, 360, STAGE_15__GUILD_ROOM, "Guild Room => Last Boss"),
				new MinigameEntry(340, 120, STAGE_16__LAST_BOSS, "Last Boss | MiniGame"),
				new NextRoomEntry(665, 330, STAGE_17__LAST_BOSS__GAME_FINISHED, "Last Boss => End Credits"),
				new WithdrawEntry(340, 40, STAGE_99__GAME_OVER, "GAME OVER")
		);
		
		return interactables;
	}
	
	public static GameStateList getNextStage(GameStateList currentGameStage) {
		switch (currentGameStage) {
		case STAGE_00__GAME_START:
			return STAGE_01__ISCALE_ROOM;
		case STAGE_01__ISCALE_ROOM:
			return STAGE_02__ISCALE_ROOM__GAME_FINISHED;
		case STAGE_02__ISCALE_ROOM__GAME_FINISHED:
			return STAGE_03__ISCALE_HALL;
		case STAGE_03__ISCALE_HALL:
			return STAGE_04__ELEVATOR;
		case STAGE_04__ELEVATOR:
			return STAGE_05__ELEVATOR__GAME_FINISHED;
		case STAGE_05__ELEVATOR__GAME_FINISHED:
			return STAGE_06__LARN_GEAR;
		case STAGE_06__LARN_GEAR:
			return STAGE_07__LARN_GEAR__GAME_FINISHED;
		case STAGE_07__LARN_GEAR__GAME_FINISHED:
			return STAGE_08__COMPUTER_CENTER;
		case STAGE_08__COMPUTER_CENTER:
			return STAGE_09__COMPUTER_CENTER__GAME_FINISHED;
		case STAGE_09__COMPUTER_CENTER__GAME_FINISHED:
			return STAGE_10__LARN_GEAR__AFTER__COMPUTER_CENTER;
		case STAGE_10__LARN_GEAR__AFTER__COMPUTER_CENTER:
			return STAGE_11__BUILDING_4_HALL;
		case STAGE_11__BUILDING_4_HALL:
			return STAGE_12__DATABASE;
		case STAGE_12__DATABASE:
			return STAGE_13__DATABASE__GAME_FINISHED;
		case STAGE_13__DATABASE__GAME_FINISHED:
			return STAGE_14__BUILDING_4_HALL__AFTER__DATABASE;
		case STAGE_14__BUILDING_4_HALL__AFTER__DATABASE:
			return STAGE_15__GUILD_ROOM;
		case STAGE_15__GUILD_ROOM:
			return STAGE_16__LAST_BOSS;
		case STAGE_16__LAST_BOSS:
			return STAGE_17__LAST_BOSS__GAME_FINISHED;
		case STAGE_17__LAST_BOSS__GAME_FINISHED:
			return STAGE_18__GAME_COMPLETED;
		case STAGE_18__GAME_COMPLETED:
			return STAGE_99__GAME_OVER;
		default:
			throw new IllegalArgumentException("Unexpected value: " + currentGameStage);
		}
	}
}
