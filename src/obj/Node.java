package obj;

public class Node {

	int xPoint;
	int yPoint;
	int xBox;
	int yBox;
	String name;

	public Node(int x, int y, String n) {
		this.xPoint = x;
		this.yPoint = y;
		this.name = n;
		this.xBox = x/10;
		this.yBox = y/10;
	}

	public int getxPoint() {
		return xPoint;
	}

	public void setxPoint(int xPoint) {
		this.xPoint = xPoint;
	}

	public int getyPoint() {
		return yPoint;
	}

	public void setyPoint(int yPoint) {
		this.yPoint = yPoint;
	}

	public int getxBox() {
		return xBox;
	}

	public void setxBox(int xBox) {
		this.xBox = xBox;
	}

	public int getyBox() {
		return yBox;
	}

	public void setyBox(int yBox) {
		this.yBox = yBox;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
