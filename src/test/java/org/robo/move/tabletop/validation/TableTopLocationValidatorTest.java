package org.robo.move.tabletop.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.robo.move.apis.IRoboLocation;
import static org.robo.move.test.utils.IRoboLocationMocker.mockLocation;

public class TableTopLocationValidatorTest {
	private final int VALID_UNITS = 5;
	
	private final int VALID_X = 3;
	private final int VALID_Y = 4;
	private String DIRECTION = "NORTH";
	
	private final boolean EXPECTED_VALID_LOCATION = true;
	private final boolean EXPECTED_INVALID_LOCATION = false;
			
	@Test
	public void testInitializeWithValidUnitsShouldInitializedSuccessfully() {
		new TableTopLocationValidator(VALID_UNITS);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInitializeWithZeroUnitShouldThrowIllegalArgumentException() {
		int zeroUnit = 0;
		new TableTopLocationValidator(zeroUnit);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInitializeWithNegativeUnitsShouldThrowIllegalArgumentException() {
		int negativeUnits = -5;
		new TableTopLocationValidator(negativeUnits);
	}
	
	
	@Test
	public void testIsValidWithLocationWithValidXYShouldReturnTrueSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		
		IRoboLocation mockedLoc = mockLocation(VALID_X, VALID_Y, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be a valid location", EXPECTED_VALID_LOCATION, actual);
	}
	
	@Test
	public void testIsValidWithLocationWithZeroXShouldReturnTrueSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		
		int zeroX = 0;
		IRoboLocation mockedLoc = mockLocation(zeroX, VALID_Y, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be a valid location", EXPECTED_VALID_LOCATION, actual);
	}
	
	@Test
	public void testIsValidWithLocationWithNegativeXShouldReturnFalseSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		int negativeX = -10;
		IRoboLocation mockedLoc = mockLocation(negativeX, VALID_Y, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be invalid location", EXPECTED_INVALID_LOCATION, actual);
	}
	
	@Test
	public void testIsValidWithLocationWithXEqualssUnitsShouldReturnFalseSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		int exceededX = VALID_UNITS;
		IRoboLocation mockedLoc = mockLocation(exceededX, VALID_Y, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be invalid location", EXPECTED_INVALID_LOCATION, actual);
	}
	
	@Test
	public void testIsValidWithLocationWithXExceedsUnitsShouldReturnFalseSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		int exceededX = VALID_UNITS + 3;
		IRoboLocation mockedLoc = mockLocation(exceededX, VALID_Y, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be invalid location", EXPECTED_INVALID_LOCATION, actual);
	}
	
	@Test
	public void testIsValidWithLocationWithZeroYShouldReturnTrueSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		
		int zeroY = 0;
		IRoboLocation mockedLoc = mockLocation(VALID_X, zeroY, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be a valid location", EXPECTED_VALID_LOCATION, actual);
	}
	
	@Test
	public void testIsValidWithLocationWithNegativeYShouldReturnFalseSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		int negativeY = -8;
		IRoboLocation mockedLoc = mockLocation(VALID_X, negativeY, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be invalid location", EXPECTED_INVALID_LOCATION, actual);
	}
	
	@Test
	public void testIsValidWithLocationWithYEqualssUnitsShouldReturnFalseSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		int exceededY = VALID_UNITS;
		IRoboLocation mockedLoc = mockLocation(VALID_X, exceededY, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be invalid location", EXPECTED_INVALID_LOCATION, actual);
	}
	
	@Test
	public void testIsValidWithLocationWithYExceedsUnitsShouldReturnFalseSuccessfully() {
		TableTopLocationValidator target = new TableTopLocationValidator(VALID_UNITS);
		int exceededY = VALID_UNITS + 1;
		IRoboLocation mockedLoc = mockLocation(VALID_X, exceededY, DIRECTION);
		
		boolean actual = target.isValid(mockedLoc);
		assertEquals("Expected to be invalid location", EXPECTED_INVALID_LOCATION, actual);
	}

}
