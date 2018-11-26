package org.robo.move.test.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.robo.move.apis.IRoboLocation;
import org.robo.move.apis.IRoboLocationValidator;

public class IRoboLocationValidatorMocker {

	public static IRoboLocationValidator mockValidator(boolean locationValidity) {
		IRoboLocationValidator mockedValidator = mock(IRoboLocationValidator.class);
		when(mockedValidator.isValid(Mockito.any(IRoboLocation.class))).thenReturn(locationValidity);
		return mockedValidator;
	}
	
	public static void mockValidate(IRoboLocationValidator mockedValidator, IRoboLocation locationRef, boolean locationValidity) {
		when(mockedValidator.isValid(locationRef)).thenReturn(locationValidity);
	}
}
