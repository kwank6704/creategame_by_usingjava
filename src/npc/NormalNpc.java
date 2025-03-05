package npc;

public class NormalNpc {
	private int x;
	private int y;
	private String picURL;

	public NormalNpc(int x, int y, String picURL) {
		setX(x);
		setY(y);
		setPicURL(picURL);
	}

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
