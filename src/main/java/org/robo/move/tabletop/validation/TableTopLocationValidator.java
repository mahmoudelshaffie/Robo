package org.robo.move.tabletop.validation;

import org.robo.move.apis.IRoboLocation;
import org.robo.move.apis.IRoboLocationValidator;

/**
 * Validate location on square table top, 
 * without any surface obstructions, 
 * to avoid making robot falling from the table 
 * @author shaf3y
 */
public class TableTopLocationValidator implements IRoboLocationValidator {

	private final int MAX_UNITS;
	
	public TableTopLocationValidator(int maxUnits) {
		if (maxUnits <= 0) {
			throw new IllegalArgumentException();
		}
		
		MAX_UNITS = maxUnits;
	}
	
	@Override
	public boolean isValid(IRoboLocation location) {
		boolean valid = false;
		int X = location.getX();
		int Y = location.getY();
		if ((X >= 0 && X < MAX_UNITS)
				&& (Y >= 0 && Y < MAX_UNITS)) {
			valid = true;
		}

		return valid;
	}

}
