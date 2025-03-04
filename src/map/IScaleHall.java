package map;

import player.Player;

public class IScaleHall extends GameMap {
	public static IScaleHall iScaleHall;
	
	public IScaleHall() {
		super("iScale Hall", "map_image/iscale.png", new Integer[] {65, 900, 195, 360});
		
		Player player = Player.getInstance();
		player.setX(240);
		player.setY(200);
	}
	
	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(),player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		
		block(280, 360, 295, 350);
		block(115, 175, 195, 245);
		block(45, 130, 215, 270);
	}
	
	public static IScaleHall getInstance() {
		if (iScaleHall == null)
			iScaleHall = new IScaleHall();
		return iScaleHall;
	}
}
