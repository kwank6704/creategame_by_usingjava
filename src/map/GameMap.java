package map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import player.Player;

public abstract class GameMap extends ImageView {
	protected String name;
	protected String mapPath;
	protected Integer[] boundary;
	protected double ratio;

	public GameMap(String name, String mapPath, Integer[] boundary, double ratio) {
		super(new Image(mapPath));
		this.name = name;
		this.mapPath = mapPath;
		this.boundary = boundary;
		
		this.ratio = ratio;
		
		this.setFitHeight(ratio * this.getImage().getHeight());
		this.setFitWidth(ratio * this.getImage().getWidth());
	}

	public static int snap(int min, int max, int x) {
		int mid = (min + max) / 2;
		return (x < mid) ? min - 5 : max + 5;
	}

	public void block(int minX, int maxX, int minY, int maxY) {
		Player player = Player.getInstance();
		int posX = player.getX();
		int posY = player.getY();
		int velX = player.getVelX();
		int velY = player.getVelY();
		
		if (posX >= minX && posX <= maxX && posY >= minY && posY <= maxY) {
			if (velX != 0)
				posX = snap(minX, maxX, posX);
			if (velY != 0)
				posY = snap(minY, maxY, posY);
		}
		
		player.setX(posX);
		player.setY(posY);
	}
	
	public Integer[] boundaryCheck(int posX, int posY) {
		int minX = this.boundary[0];
		int maxX = this.boundary[1];
		int minY = this.boundary[2];
		int maxY = this.boundary[3];
		
		if (posX < minX)
			posX = minX;
		if (posX > maxX)
			posX = maxX;
		if (posY < minY)
			posY = minY;
		if (posY > maxY)
			posY = maxY;
		
		return new Integer[] {posX, posY};
	}
	
	public abstract void wall();
	
	public double getRatio() {
		return ratio;
	}
}
