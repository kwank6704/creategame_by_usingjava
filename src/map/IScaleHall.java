package map;

import player.Player;

public class IScaleHall extends GameMap {
	public static IScaleHall iScaleHall;
	
	public IScaleHall() {
		super("iScale Hall", "map_image/iscale_hall.png", new Integer[] {70, 860, 180, 310});
		
		Player player = Player.getInstance();
		player.setX(225);
		player.setY(180);
	}
	
	@Override
	public void wall() {
		Player player = Player.getInstance();

		Integer[] position = boundaryCheck(player.getX(),player.getY());
		player.setX(position[0]);
		player.setY(position[1]);
		
		block(90, 190, 180, 225);
		block(70, 100, 180, 265);
		block(240, 350, 250, 305);
	}
	
	public static IScaleHall getInstance() {
		if (iScaleHall == null)
			iScaleHall = new IScaleHall();
		return iScaleHall;
	}
}
