package org.robo.move.apis;

public interface IRoboService {
	boolean left();
	boolean right();
	boolean place(int x, int y, String direction);
	boolean move();
	String report();
}
