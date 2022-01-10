package application;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {
	private int diaCost = 14;
	private int verCost = 10;
	private int columns = 40;
	private int rows = 40;
	private Points start, end;
	private ArrayList<Points>path, openSet, closedSet;
	private Points[][] map;

	
	public Algorithm (Points[][] NodesMap) {
		this.openSet = new ArrayList<Points>();
		this.closedSet = new ArrayList<Points>();
		this.path = new ArrayList<Points>();
		this.map = NodesMap;
		this.start = findingStart(NodesMap);
		this.end = findingTarget(NodesMap);
		start.setG(0);
		
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
	
	public Points findingTarget(Points[][] nodesMap) {
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
	
	public void aStar() {
		openSet.add(start);
		while(!openSet.isEmpty()) {
			Points current = openSet.get(0);
			for(int i = 1; i < openSet.size(); i ++) {
				if(openSet.get(i).fCost() < current.fCost() || openSet.get(i).fCost() == current.fCost() && openSet.get(i).getH() < current.getG()) {
					current = openSet.get(i);
					
				}
			}
			openSet.remove(current);
			closedSet.add(current);
			if (current == end) {
				retrace(start, end);
			}
			for (Points neighbors : getNeighbors(current)) {
				if(neighbors.getType() == 3 || closedSet.contains(neighbors)) {
					continue;
				}
				int newMove = current.getG() + getDistance(current, neighbors);
				if (newMove < neighbors.getG() || !openSet.contains(neighbors)) {
					neighbors.setG(newMove);
					neighbors.setH(getDistance(neighbors, end));
					neighbors.setParent(current);
					
					if(!openSet.contains(neighbors)) {
						openSet.add(neighbors);
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
	}
	
	public int getDistance(Points a, Points b) {
		int disX = Math.abs(a.getX() - b.getX());
		int disY = Math.abs(a.getY() - b.getY());
		
		if(disX > disY) {
			return diaCost*disY + verCost*(disX-disY);
			
		}
		
		return diaCost*disX + verCost*(disY-disX);
	}
	
	public ArrayList<Points> getNeighbors(Points current) {
		ArrayList<Points> neighbors = new ArrayList<Points>();
		for(int x = -1; x <= 1; x ++) {
			for(int y = -1; y <= 1; y ++) {
				if(x == 0 && y == 0) {
					continue;
				}
				int checkX = current.getX() + x;
				int checkY = current.getY() + y;
				
				if(checkX >=0 && checkX < map.length && checkY >= 0 && checkY < map.length) {
					neighbors.add(map[checkX][checkY]);
				}
			}
		}
		
		return neighbors;	
	}
	
    public ArrayList<Points> getPath(){
    	return path;
    }
}
