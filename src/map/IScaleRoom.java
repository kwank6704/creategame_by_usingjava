package map;

import player.Player;

public class IScaleRoom extends GameMap {
	public static IScaleRoom iScaleRoom;
	
	public IScaleRoom() {
		super("iScale Room", "map_image/room_iscale.png", new Integer[] {300, 580, 80, 405});
		
		Player player = Player.getInstance();
		player.setX(300);
		player.setY(100);
	}
	
	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(),player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		
		block(415, 540, 320, 405);
	}
	
	public static IScaleRoom getInstance() {
		if (iScaleRoom == null)
			iScaleRoom = new IScaleRoom();
		return iScaleRoom;
	}
}
