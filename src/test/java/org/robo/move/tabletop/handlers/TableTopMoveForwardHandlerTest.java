package org.robo.move.tabletop.handlers;

import static org.junit.Assert.assertEquals;
import static org.robo.move.test.utils.IRoboLocationMocker.mockLocation;

import org.junit.Test;
import org.robo.move.apis.IRoboLocation;
import org.robo.move.tabletop.data.RoboDirection;

public class TableTopMoveForwardHandlerTest {
	
	private final int ONE_STEP = 1;
	
	@Test
	public void testMoveTowardsEastShouldIncrementXByOneStepSuccesffully() {
		TableTopMoveForwardHandler target = new TableTopMoveForwardHandler();
		
		int X = 7;
		int Y = 3;
		String direction = RoboDirection.EAST.getFace();
		IRoboLocation mockedLoc = mockLocation(X, Y, direction);
		
		IRoboLocation actual = target.updateLocation(mockedLoc);
		
		assertEquals("Expected that X incremented by one step", X + ONE_STEP, actual.getX());
		assertEquals("Expected Y to be not changed", Y, actual.getY());
		assertEquals("Expected direction to be not changed", direction, actual.getDirection());
	}
	
	@Test
	public void testMoveTowardsWestShouldDecrementXByOneStepSuccesffully() {
		TableTopMoveForwardHandler target = new TableTopMoveForwardHandler();
		
		int X = 2;
		int Y = 3;
		String direction = RoboDirection.WEST.getFace();
		IRoboLocation mockedLoc = mockLocation(X, Y, direction);
		
		IRoboLocation actual = target.updateLocation(mockedLoc);
		
		assertEquals("Expected that X incremented by one step", X - ONE_STEP, actual.getX());
		assertEquals("Expected Y to be not changed", Y, actual.getY());
		assertEquals("Expected direction to be not changed", direction, actual.getDirection());
	}

	@Test
	public void testMoveTowardsNorthShouldIncrementYByOneStepSuccesffully() {
		TableTopMoveForwardHandler target = new TableTopMoveForwardHandler();
		
		int X = 3;
		int Y = 4;
		String direction = RoboDirection.NORTH.getFace();
		IRoboLocation mockedLoc = mockLocation(X, Y, direction);
		
		IRoboLocation actual = target.updateLocation(mockedLoc);
		
		assertEquals("Expected that Y incremented by one step", Y + ONE_STEP, actual.getY());
		assertEquals("Expected X to be not changed", X, actual.getX());
		assertEquals("Expected direction to be not changed", direction, actual.getDirection());
	}
	
	@Test
	public void testMoveTowardsSouthShouldDecrementYByOneStepSuccesffully() {
		TableTopMoveForwardHandler target = new TableTopMoveForwardHandler();
		
		int X = 3;
		int Y = 0;
		String direction = RoboDirection.SOUTH.getFace();
		IRoboLocation mockedLoc = mockLocation(X, Y, direction);
		
		IRoboLocation actual = target.updateLocation(mockedLoc);
		
		assertEquals("Expected that Y incremented by one step", Y - ONE_STEP, actual.getY());
		assertEquals("Expected X to be not changed", X, actual.getX());
		assertEquals("Expected direction to be not changed", direction, actual.getDirection());
	}
}
