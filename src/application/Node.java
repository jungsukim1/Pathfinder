package application;

public class Node {
	private int x,y,size,type;		//type: 0 - regular node, 1-start node, 2-end node, 3-wall,
											//4-open node, 5-closed node, 6-final path
	private String color;
	
	public Node (int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.setColor("WHITE");		//default is white
		this.type = 0;				//default is 0 (regular node)
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
