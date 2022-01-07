package application;

import java.util.ArrayList;

public class Algorithm {
	private int diaCost = 14;
	private int verCost = 10;
	private int columns = 40;
	private int rows = 40;
	private Points[][] nodes = new Points[columns][rows];
	
	public void aStarPath(Points startNode, Points endNode) {
		ArrayList<Points> openSet = new ArrayList<Points>();
		ArrayList<Points> closedSet = new ArrayList<Points>();
		openSet.add(startNode);
		while(openSet.size() > 0) {
			Points current = openSet.get(0);
			for (int i = 1; i < openSet.size(); i ++) {
				if (openSet.get(i).fCost() < current.fCost() || openSet.get(i).fCost() == current.fCost() && openSet.get(i).getH() < current.getH()) {
					current = openSet.get(i);
				}
			}
			openSet.remove(current);
			closedSet.add(current);
			
			if (current == endNode) {
				return;
			}
			
			
		}
	}
	
	public ArrayList<Points> getNeighbours(Points node){
		ArrayList<Points> neighbours = new ArrayList<Points>();
		for (int i = -1; i <= 1; i ++) {
			for (int j = -1; j <= 1; j ++) {
				if (i == 1 && j == 1) {
					continue;
					
				}
				int checkX = node.getX() + i;
				int checkY = node.getY() + j;
				
				if (checkX >= 0 && checkX < )
			}
		}
	}
}
