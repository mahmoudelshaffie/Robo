package org.robo.move.test.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.robo.move.apis.IRoboLocation;
import org.robo.move.apis.IRoboMoveHandler;

public class IRoboMoveHandlerMockUtils {
	
	/**
	 * Create default mock
	 * @return mocked handler
	 */
	public static IRoboMoveHandler mockHandler() {
		IRoboMoveHandler mockedHandler = mock(IRoboMoveHandler.class);
		return mockedHandler;
	}
	
	/**
	 * Create mock and updateLocation
	 * @param newLocation to be returned when updateLocation is called with any arguments
	 * @return mocked handler
	 */
	public static IRoboMoveHandler mockHandler(IRoboLocation newLocation) {
		IRoboMoveHandler mockedHandler = mockHandler();
		mockHandlerUpdateLocation(mockedHandler, newLocation);
		return mockedHandler;
	}
	
	/**
	 * Mocking updateLocation of handler to return specific location when called with any arguments
	 * @param mockedHandler handler to be mocked, should be mock instance
	 * @param newLocation the location to be returned when updateLocation is called 
	 */
	public static void mockHandlerUpdateLocation(IRoboMoveHandler mockedHandler, IRoboLocation newLocation) {
		when(mockedHandler.updateLocation(Mockito.any(IRoboLocation.class))).thenReturn(newLocation);
	}
	
	/**
	 * Mocking updateLocation of handler to return specific location when called with specific location
	 * @param mockedHandler handler to be mocked, should be mock instance
	 * @param oldLocation the location which updateLocation called with it
	 * @param newLocation the location to be returned when updateLocation is called 
	 */
	public static void mockHandlerUpdateLocation(IRoboMoveHandler mockedHandler, IRoboLocation oldLocation, IRoboLocation newLocation) {
		when(mockedHandler.updateLocation(oldLocation)).thenReturn(newLocation);
	}
}
