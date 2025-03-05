package utils;

import javafx.scene.image.ImageView;
import map.Elevator;
import map.IScaleHall;
import map.IScaleRoom;
import map.MapList;

public class GameMapView {
	public static ImageView getMapView(MapList currMap, ImageView playerImage) {
		switch (currMap) {
		case IScaleRoom: {
			return new IScaleRoom();
		}
		case IScaleHall: {
			return new IScaleHall();
		}
		case Elevator: {
			return new Elevator(playerImage);
		}
		case LanIntania: {
			return new IScaleHall();
		}
		case ComRoom: {
			return new IScaleHall();
		}
		case TA_ROOM: {
			return new IScaleHall();
		}
		case GuildRoom: {
			return new IScaleHall();
		}
		case Floor1: {
			return new IScaleHall();
		}
		case Floor4: {
			return new IScaleHall();
		}
		default:
			return new IScaleRoom();
		}
	}
}
