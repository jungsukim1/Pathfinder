package application;

import javafx.scene.Parent;

public class Points {
	private int x,y,type,h,g;		//type: 0 - regular node, 1-start node, 2-end node, 3-wall,
	private Points parent;
	private int minDistance;
	private double cost;
	
	public Points (int x, int y) {
		this.x = x;
		this.y = y;	
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
		

	public Points getParent() {
		return parent;
	}

	public void setParent(Points parent) {
		this.parent = parent;
	}

	public int fCost() {
		return g + h;
	}
	
}
