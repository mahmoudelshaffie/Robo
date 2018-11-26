package org.robo.move.apis;

/**
 * Change location of robot in response to action.
 * @author shaf3y
 *
 */
public interface IRoboMoveHandler {
	/**
	 * 
	 * @param location current Robo's location
	 * @return new location of Robo
	 */
	IRoboLocation updateLocation(IRoboLocation location);
}
