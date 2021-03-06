package org.robo.move.tabletop.handlers;

import static org.junit.Assert.assertEquals;
import static org.robo.move.test.utils.IRoboLocationMocker.mockLocation;

import org.junit.Test;
import org.robo.move.apis.IRoboLocation;
import org.robo.move.tabletop.data.RoboDirection;

public class TableTopRotateLeftHandlerTest {
	private final int X = 3;
	private final int Y = 4;
	
	@Test
	public void testRotateLeftFromEasthShouldChangeOnlyDirectionToNorthSuccessfully() {
		TableTopRotateLeftHandler target = new TableTopRotateLeftHandler();
		String direction = RoboDirection.EAST.getFace();
		IRoboLocation mockedLoc = mockLocation(X, Y, direction);
		
		IRoboLocation actual = target.updateLocation(mockedLoc);
		
		String expectedDirection = RoboDirection.NORTH.getFace();
		assertEquals("Expected direction should be changed to north", expectedDirection, actual.getDirection());
		assertEquals("Expected X to be not changed", X, actual.getX());
		assertEquals("Expected Y to be not changed", Y, actual.getY());
	}
	
	@Test
	public void testRotateLeftFromNorthShouldChangeOnlyDirectionToWestSuccessfully() {
		TableTopRotateLeftHandler target = new TableTopRotateLeftHandler();
		String direction = RoboDirection.NORTH.getFace();
		IRoboLocation mockedLoc = mockLocation(X, Y, direction);
		
		IRoboLocation actual = target.updateLocation(mockedLoc);
		
		String expectedDirection = RoboDirection.WEST.getFace();
		assertEquals("Expected direction should be changed to west", expectedDirection, actual.getDirection());
		assertEquals("Expected X to be not changed", X, actual.getX());
		assertEquals("Expected Y to be not changed", Y, actual.getY());
	}
	
	@Test
	public void testRotateLeftFromWestShouldChangeOnlyDirectionToSouthSuccessfully() {
		TableTopRotateLeftHandler target = new TableTopRotateLeftHandler();
		String direction = RoboDirection.WEST.getFace();
		IRoboLocation mockedLoc = mockLocation(X, Y, direction);
		
		IRoboLocation actual = target.updateLocation(mockedLoc);
		
		String expectedDirection = RoboDirection.SOUTH.getFace();
		assertEquals("Expected direction should be changed to south", expectedDirection, actual.getDirection());
		assertEquals("Expected X to be not changed", X, actual.getX());
		assertEquals("Expected Y to be not changed", Y, actual.getY());
	}
	
	@Test
	public void testRotateLeftFromSouthShouldChangeOnlyDirectionToEastSuccessfully() {
		TableTopRotateLeftHandler target = new TableTopRotateLeftHandler();
		String direction = RoboDirection.SOUTH.getFace();
		IRoboLocation mockedLoc = mockLocation(X, Y, direction);
		
		IRoboLocation actual = target.updateLocation(mockedLoc);
		
		String expectedDirection = RoboDirection.EAST.getFace();
		assertEquals("Expected direction should be changed to east", expectedDirection, actual.getDirection());
		assertEquals("Expected X to be not changed", X, actual.getX());
		assertEquals("Expected Y to be not changed", Y, actual.getY());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRotateLeftWithInvalidDirectionShouldThrowIllegalArgumentException() {
		TableTopRotateLeftHandler target = new TableTopRotateLeftHandler();

		String invalidDirection = "Any Direction";
		IRoboLocation mockedLoc = mockLocation(X, Y, invalidDirection);
		
		target.updateLocation(mockedLoc);
	}
}
