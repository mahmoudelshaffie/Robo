package org.robo.move.tabletop.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RoboDirectionTest {
	
	private final String EAST_FACE = "EAST"; 
	private final String NORTH_FACE = "NORTH";
	private final String SOUTH_FACE = "SOUTH";
	private final String WEST_FACE = "WEST";
	
	@Test
	public void testGetFaceForEastDirectionShourdReturnNSuccessfully() {
		String actual = RoboDirection.EAST.getFace();
		assertEquals("Invalid face, expeced 'EAST' for east direction", EAST_FACE, actual);
	}
	
	@Test
	public void testGetFaceForNorthDirectionShourdReturnNSuccessfully() {
		String actual = RoboDirection.NORTH.getFace();
		assertEquals("Invalid face, expeced 'NORTH' for north direction", NORTH_FACE, actual);
	}
	
	@Test
	public void testGetFaceForSouthDirectionShourdReturnNSuccessfully() {
		String actual = RoboDirection.SOUTH.getFace();
		assertEquals("Invalid face, expeced 'SOUTH' for south direction", SOUTH_FACE, actual);
	}
	
	
	@Test
	public void testGetFaceForWestDirectionShourdReturnNSuccessfully() {
		String actual = RoboDirection.WEST.getFace();
		assertEquals("Invalid face, expeced 'WEST' for west direction", WEST_FACE, actual);
	}
	
	@Test
	public void testRotateLeftFromEastShouldRotateToNorthSuccessfully() {
		RoboDirection target = RoboDirection.EAST;
		RoboDirection actual = target.rotateLeft();
		RoboDirection expected = RoboDirection.NORTH;
		assertEquals("Expected to be rotated to the north", expected, actual);
	}
	
	@Test
	public void testRotateRightFromEastShouldRotateToSouthSuccessfully() {
		RoboDirection target = RoboDirection.EAST;
		RoboDirection actual = target.rotateRight();
		RoboDirection expected = RoboDirection.SOUTH;
		assertEquals("Expected to be rotated to the south", expected, actual);
	}

	@Test
	public void testRotateLeftFromNorthShouldRotateToWestSuccessfully() {
		RoboDirection target = RoboDirection.NORTH;
		RoboDirection actual = target.rotateLeft();
		RoboDirection expected = RoboDirection.WEST;
		assertEquals("Expected to be rotated to the west", expected, actual);
	}
	
	@Test
	public void testRotateRightFromNorthShouldRotateToEastSuccessfully() {
		RoboDirection target = RoboDirection.NORTH;
		RoboDirection actual = target.rotateRight();
		RoboDirection expected = RoboDirection.EAST;
		assertEquals("Expected to be rotated to the east", expected, actual);
	}
	
	@Test
	public void testRotateLeftFromSouthShouldRotateToEastSuccessfully() {
		RoboDirection target = RoboDirection.SOUTH;
		RoboDirection actual = target.rotateLeft();
		RoboDirection expected = RoboDirection.EAST;
		assertEquals("Expected to be rotated to the east", expected, actual);
	}
	
	@Test
	public void testRotateRightFromSouthShouldRotateToWestSuccessfully() {
		RoboDirection target = RoboDirection.SOUTH;
		RoboDirection actual = target.rotateRight();
		RoboDirection expected = RoboDirection.WEST;
		assertEquals("Expected to be rotated to the west", expected, actual);
	}
	
	@Test
	public void testRotateLeftFromWestShouldRotateToSouthSuccessfully() {
		RoboDirection target = RoboDirection.WEST;
		RoboDirection actual = target.rotateLeft();
		RoboDirection expected = RoboDirection.SOUTH;
		assertEquals("Expected to be rotated to the south", expected, actual);
	}
	
	@Test
	public void testRotateRightFromWestShouldRotateToNorthSuccessfully() {
		RoboDirection target = RoboDirection.WEST;
		RoboDirection actual = target.rotateRight();
		RoboDirection expected = RoboDirection.NORTH;
		assertEquals("Expected to be rotated to the north", expected, actual);
	}
}
