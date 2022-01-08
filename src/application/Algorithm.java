package application;

import java.util.ArrayList;

public class Algorithm {
	private int diaCost = 14;
	private int verCost = 10;
	private int columns = 40;
	private int rows = 40;
	private ArrayList<Points> openSet, closedSet, path;
	
	public Algorithm (Points[][] map) {
		this.openSet = new ArrayList<Points>();
		this.closedSet = new ArrayList<Points>();
		this.path = new ArrayList<Points>();
		
	}
	
	public void aStarPath(Points startNode, Points endNode) {
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
				retrace(startNode, endNode);
				return;
			}
			for(Points n : getNeighbours(current)) {
				if (n.getType() == 3 || closedSet.contains(n)) {
					continue;
				}
				int newMoveCost = current.getG() + getDistance(current, n);
				if (newMoveCost < n.getG() || !openSet.contains(n)) {
					n.setG(newMoveCost);
					n.setH(getDistance(n, endNode));
					n.setParent(current);
					
					if (!openSet.contains(n)) {
						openSet.add(n);
					}
				}
			}
			
		}
	}
	
	public void retrace(Points startNode, Points endNode) {
		Points currentNode = endNode;
		
		while(currentNode != startNode) {
			path.add(currentNode);
			currentNode = currentNode.getParent();
		}
		
	}
	
	public ArrayList<Points> getNeighbours(Points node){
		ArrayList<Points> neighbours = new ArrayList<Points>();
		for (int i = -1; i <= 1; i ++) {
			for (int j = -1; j <= 1; j ++) {
				if (i == 0 && j == 0) {
					continue;
					
				}
				int checkX = node.getX() + i;
				int checkY = node.getY() + j;
				
				if (checkX >= 0 && checkX < columns && checkY >= 0 && checkY < rows) {
					neighbours.add(node);
					
				}
			}
		}
		return neighbours;
	}
	
	public int getDistance(Points a, Points b) {
		int disX = Math.abs(a.getX() - b.getX());
		int disY = Math.abs(a.getY() - b.getY());
		
		if(disX > disY) {
			return 14*disY + 10*(disX-disY);
			

		}
		
		return 14*disX + 10*(disY-disX);
	}
}
