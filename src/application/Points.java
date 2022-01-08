package application;

import javafx.scene.Parent;

public class Points {
	private int x,y,type,h,g;		//type: 0 - regular node, 1-start node, 2-end node, 3-wall,
											//4-open node, 5-closed node, 6-final path
	private String color;
	private Points parent;
	
	public Points (int x, int y) {
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

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}
	
	public int fCost() {
		return g + h;
		
	}

	public Points getParent() {
		return parent;
	}

	public void setParent(Points parent) {
		this.parent = parent;
	}
	
}
