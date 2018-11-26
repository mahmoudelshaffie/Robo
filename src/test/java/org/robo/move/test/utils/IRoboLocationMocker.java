package org.robo.move.test.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.robo.move.apis.IRoboLocation;

public class IRoboLocationMocker {
	
	private IRoboLocation mockedLocation;
	
	public IRoboLocationMocker() {
		mockedLocation = mock(IRoboLocation.class);
	}
	
	public IRoboLocationMocker returnGetX(int X) {
		when(mockedLocation.getX()).thenReturn(X);
		return this;
	}
	
	public IRoboLocationMocker returnGetY(int Y) {
		when(mockedLocation.getY()).thenReturn(Y);
		return this;
	}
	
	public IRoboLocationMocker returnGetDirection(String direction) {
		when(mockedLocation.getDirection()).thenReturn(direction);
		return this;
	}
	
	public IRoboLocation getMock() {
		return mockedLocation;
	}
	
	public static IRoboLocation mockLocation(int x, int y, String direction) {
		return new IRoboLocationMocker().returnGetDirection(direction).returnGetX(x).returnGetY(y).getMock();
	}
}
