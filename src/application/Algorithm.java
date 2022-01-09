package application;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {
	private int diaCost = 14;
	private int verCost = 10;
	private int columns = 40;
	private int rows = 40;
	private ArrayList<Points> openSet, closedSet, path;
	private Points[][] map;
	
	public Algorithm (Points[][] map) {
		this.openSet = new ArrayList<Points>();
		this.closedSet = new ArrayList<Points>();
		this.path = new ArrayList<Points>();
		this.map = map;
	}
	
	public Points findingStart(Points[][] nodesMap) {
		for (int i = 0; i < columns; i ++) {
			for (int j = 0; j < rows; j ++) {
				Points n = nodesMap[i][j];
				if(n.getType() == 1) {
					return n;
				}
			}
		}
		return null;
	}
	
	public Points findingEndNode(Points[][] nodesMap) {
		for (int i = 0; i < columns; i ++) {
			for (int j = 0; j < rows; j ++) {
				Points n = nodesMap[i][j];
				if(n.getType() == 2) {
					return n;
				}
			}
		}
		return null;
	}
	
	public void aStarPath(Points startNode, Points endNode) {
		openSet.add(startNode);
		while(openSet.size() > 0) {
			Points current = openSet.get(0);
			closedSet.add(current);
			for (int i = 1; i < openSet.size(); i ++) {
				if (openSet.get(i).fCost() < current.fCost() || openSet.get(i).fCost() == current.fCost() && openSet.get(i).getH() < current.getH()) {
					current = openSet.get(i);
				}
			}
			openSet.remove(current);
			closedSet.add(current);
			
			if (current == endNode) {
				retrace(startNode, endNode);
				System.out.println("here6");
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
					System.out.println("hereas");
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
		Collections.reverse(path);
		for (Points n : path) {
			System.out.println("Node" + n.getX() + "," + n.getY());
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
