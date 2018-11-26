package org.robo.move.tabletop.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.robo.move.test.utils.IRoboLocationValidatorMocker.*;
import static org.robo.move.test.utils.IRoboMoveHandlerMockUtils.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.robo.move.apis.IRoboLocation;
import org.robo.move.apis.IRoboLocationValidator;
import org.robo.move.apis.IRoboMoveHandler;
import org.robo.move.apis.IRoboService;
import org.robo.move.tabletop.data.TableTopLocation;

public class RoboTableTopServiceTest {
	private final boolean VALID_LOCATION = true;
	private final boolean INVALID_LOCATION = false;
	
	private final int X = 5;
	private final int Y = 2;
	private String DIRECTION = "NORTH";
	
	@Test
	public void testRotateLeftWithoutRobotPlacementShouldBeIgnoredAndReturnFalseSuccessfully() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		IRoboMoveHandler rotateRightHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		boolean actual = target.left();
		assertFalse("Expected rotate left action to be ignored", actual);
	}
	
	@Test
	public void testRotateLeftAfterRobotPlacementShouldRotatedAndReturnFalseSuccessfully() {
		IRoboLocation afterRotateLocation = new TableTopLocation(X, Y, "SOUTH");
		IRoboMoveHandler rotateLeftHandler = mockHandler(afterRotateLocation);
		
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateRightHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		
		boolean isPlaced = target.place(X, Y, DIRECTION);
		assertTrue("Expect robot to be placed successfully", isPlaced);
		
		boolean actual = target.left();
		assertTrue("Expected to be rotated to left successfully", actual);
		
		String actualReportAfterRotation = target.report();
		String expectedReportAfterRotation = "5,2,SOUTH";
		assertEquals("Expected direction of location only to be changed", expectedReportAfterRotation, actualReportAfterRotation);
	}
	
	@Test
	public void testRotateRighttWithoutRobotPlacementShouldBeIgnoredAndReturnFalseSuccessfully() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		IRoboMoveHandler rotateRightHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		boolean actual = target.right();
		assertFalse("Expected rotate right action to be ignored", actual);
	}
	
	@Test
	public void testRotateRightAfterRobotPlacementShouldRotatedAndReturnTrueSuccessfully() {
		IRoboLocation afterRotateLocation = new TableTopLocation(X, Y, "EAST");
		IRoboMoveHandler rotateRightHandler = mockHandler(afterRotateLocation);
		
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		
		boolean isPlaced = target.place(X, Y, DIRECTION);
		assertTrue("Expect robot to be placed successfully", isPlaced);
		
		boolean actual = target.right();
		assertTrue("Expected to be rotated to right successfully", actual);
		
		String actualReportAfterRotation = target.report();
		String expectedReportAfterRotation = "5,2,EAST";
		assertEquals("Expected direction of location only to be changed", expectedReportAfterRotation, actualReportAfterRotation);
	}
	
	@Test
	public void testMoveWithoutRobotPlacementShouldBeIgnoredAndReturnFalseSuccessfully() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler rotateRightHandler = mockHandler();
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		boolean actual = target.move();
		assertFalse("Expected to be not moved", actual);
	}
	
	@Test
	public void testMoveAfterPlacementValidMovementShouldBeMovedAndReturnTrueSuccessfully() {
		IRoboLocation locationAfterMove = new TableTopLocation(3, 3, DIRECTION);
		IRoboMoveHandler moveForwardHandler = mockHandler(locationAfterMove);
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler rotateRightHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		boolean isPlaced = target.place(X, Y, DIRECTION);
		assertTrue("Expect robot to be placed successfully", isPlaced);
		
		boolean actual = target.move();
		assertTrue("Expected to be moved successfully", actual);
	}
	
	@Test
	public void testMoveAfterPlacementButItIsInvalidMovementShouldNotBeMovedAndReturnFalseSuccessfully() {
		IRoboLocation firstMoveLocation = new TableTopLocation(3, 3, DIRECTION);
		IRoboMoveHandler moveForwardHandler = mockHandler(firstMoveLocation);
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler rotateRightHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		boolean isPlaced = target.place(X, Y, DIRECTION);
		assertTrue("Expect robot to be placed successfully", isPlaced);
		
		boolean actualFirstMove = target.move();
		assertTrue("Expected to be moved successfully", actualFirstMove);
		
		/* Mocking Invalid Movement Scenario */
		IRoboLocation secondMoveLocation = new TableTopLocation(3, 4, DIRECTION);
		mockHandlerUpdateLocation(moveForwardHandler, firstMoveLocation, secondMoveLocation);
		mockValidate(locationValidator, secondMoveLocation, INVALID_LOCATION);
		
		boolean actualSecondMove = target.move();
		assertFalse("Expected to be not moved", actualSecondMove);
	}
	
	@Test
	public void testPlaceForFirstTimeWithValidLocationShouldBePlacedAndLocationToBeUpdatedSuccessfully() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler rotateRightHandler = mockHandler();
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		boolean actual = target.place(X, Y, DIRECTION);
		assertTrue("Expected robot to be placed successfully", actual);
		
		String actualReport = target.report();
		String expectedReport = "5,2,NORTH";
		assertEquals("Expected robot's location to be updated with new X, Y, & Direction", expectedReport, actualReport);
	}
	
	@Test
	public void testPlaceTwiceWithValidLocationShouldBePlacedAndLocationToBeUpdatedSuccessfully() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler rotateRightHandler = mockHandler();
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		boolean actualFirstPlace = target.place(X, Y, DIRECTION);
		assertTrue("Expected robot to be placed successfully", actualFirstPlace);
		
		String actualFirstPlaceReport = target.report();
		String expectedFirstPlaceReport = "5,2,NORTH";
		assertEquals("Expected robot's location to be updated with new X, Y, & Direction", expectedFirstPlaceReport, actualFirstPlaceReport);
		
		int _X = 2;
		int _Y = 3;
		String _DIRECTION = "WEST";
		boolean actualSecondPlace = target.place(_X, _Y, _DIRECTION);
		assertTrue("Expected robot to be placed successfully", actualSecondPlace);
		
		String actualSecondPlaceReport = target.report();
		String expectedSecondPlaceReport = "2,3,WEST";
		assertEquals("Expected robot's location to be updated with new X, Y, & Direction", expectedSecondPlaceReport, actualSecondPlaceReport);
	}
	
	@Test
	public void testPlaceTwiceWithInValidLocationShouldNotBePlacedAndFirstLocationStillRemainingSuccessfully() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler rotateRightHandler = mockHandler();
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		boolean actualFirstPlace = target.place(X, Y, DIRECTION);
		assertTrue("Expected robot to be placed successfully", actualFirstPlace);
		
		String actualFirstPlaceReport = target.report();
		String expectedFirstPlaceReport = "5,2,NORTH";
		assertEquals("Expected robot's location to be updated with new X, Y, & Direction", expectedFirstPlaceReport, actualFirstPlaceReport);
		
		when(locationValidator.isValid(Mockito.any(IRoboLocation.class))).thenReturn(INVALID_LOCATION);
		
		int _X = 2;
		int _Y = 3;
		String _DIRECTION = "WEST";
		boolean actualSecondPlace = target.place(_X, _Y, _DIRECTION);
		assertFalse("Expected robot to be not placed", actualSecondPlace);
		
		String actualSecondPlaceReport = target.report();
		String expectedSecondPlaceReport = "5,2,NORTH";
		assertEquals("Expected robot's location to be not updated", expectedSecondPlaceReport, actualSecondPlaceReport);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPlaceForFirstTimeWithInvalidDirectionShouldThrowIllegalArgumentException() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler rotateRightHandler = mockHandler();
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		String INVLAID_DIRECTION = "Any Direction";
		target.place(X, Y, INVLAID_DIRECTION);
	}

	@Test
	public void testReportWithoutPlacingRobotBeforeShouldReturnRobotIsMissingReportSuccessfully() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		IRoboMoveHandler rotateRightHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		String expected = "ROBOT MISSING";
		String actual = target.report();
		assertEquals("Expected robot missing", expected, actual);
	}
	
	@Test
	public void testReportAfterPlacingRobotBeforeShouldReturnLastLocationReportSuccessfully() {
		IRoboLocationValidator locationValidator = mockValidator(VALID_LOCATION);
		IRoboMoveHandler moveForwardHandler = mockHandler();
		IRoboMoveHandler rotateLeftHandler = mockHandler();
		IRoboMoveHandler rotateRightHandler = mockHandler();
		
		IRoboService target = new RoboTableTopService(locationValidator, moveForwardHandler, rotateLeftHandler, rotateRightHandler);
		
		int x = 5;
		int y = 4;
		String direction = "NORTH";
		boolean isPlaced = target.place(x, y, direction);
		assertTrue("Expect robot to be placed successfully", isPlaced);
		
		String actual = target.report();
		String expected = "5,4,NORTH";
		assertEquals("Expected report of location contains last X, Y, and Direction", expected, actual);
	}
	
}
