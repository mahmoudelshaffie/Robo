package org.robo.move.tabletop.data;

import org.robo.move.apis.IRoboLocation;

public class TableTopLocation implements IRoboLocation {
	private int X = 0;
	private int Y = 0;
	private RoboDirection direction = RoboDirection.NORTH;
	
	public TableTopLocation(int x, int y, String direction) {
		this(x, y, RoboDirection.ofValue(direction));
	}

	public TableTopLocation(int x, int y, RoboDirection direction) {
		this.X = x;
		this.Y = y;
		this.direction = direction;
	}

	public String toString() {
		return X + "," + Y + "," + direction.getFace();
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public String getDirection() {
		return direction.getFace();
	}
}
