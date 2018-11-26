package org.robo.move.tabletop.services;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.robo.move.apis.IRoboLocation;
import org.robo.move.apis.IRoboLocationValidator;
import org.robo.move.apis.IRoboMoveHandler;
import org.robo.move.apis.IRoboService;
import org.robo.move.tabletop.data.TableTopLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="tableTopService")
public class RoboTableTopService implements IRoboService {
	private final Lock lock = new ReentrantLock();
	private final String MISSING_ROBOT_REPORT = "ROBOT MISSING";
	
	private volatile IRoboLocation location;
	private IRoboLocationValidator locationValidator;
	private IRoboMoveHandler moveForwaredHandler;
	private IRoboMoveHandler rotateLeftHandler;
	private IRoboMoveHandler rotateRightHandler;
	
	@Autowired
	public RoboTableTopService(
			@Qualifier("tableTopValidator") IRoboLocationValidator locationValidator, 
			@Qualifier("tableTopMoveHandler") IRoboMoveHandler moveForwardHandler, 
			@Qualifier("tableTopLeftHandler") IRoboMoveHandler rotateLeftHandler, 
			@Qualifier("tableTopRightHandler") IRoboMoveHandler rotateRightHandler) {
		this.moveForwaredHandler = moveForwardHandler;
		this.rotateLeftHandler = rotateLeftHandler;
		this.rotateRightHandler = rotateRightHandler;
		this.locationValidator = locationValidator;
	}
	
	@Override
	public boolean left() {
		return handleRotationAction(rotateLeftHandler);
	}

	@Override
	public boolean right() {
		return handleRotationAction(rotateRightHandler);
	}

	@Override
	public boolean place(int x, int y, String direction) {
		lock.lock();
		
		boolean updated = false;
		try {
			updated = updateLocation(new TableTopLocation(x, y, direction));
		} finally {
			lock.unlock();
		}
		
		return updated;
	}

	@Override
	public boolean move() {
		lock.lock();
		
		boolean updated = false;
		try {
			if (location != null) {			
				IRoboLocation newLocation = moveForwaredHandler.updateLocation(location);
				updated = updateLocation(newLocation);
			}
		} finally {
			lock.unlock();
		}
		
		return updated;
	}
	
	@Override
	public String report() {
		return location == null ? MISSING_ROBOT_REPORT : location.toString();
	}
	
	private boolean handleRotationAction(IRoboMoveHandler rotationHandler) {
		lock.lock();
		
		boolean updated = false;
		try {
			if (location != null) {			
				IRoboLocation newLocation = rotationHandler.updateLocation(location);
				this.location = newLocation;
				updated = true;
			}
		} finally {
			lock.unlock();
		}
		
		return updated;
	}
	
	private boolean updateLocation(IRoboLocation newLocation) {
		boolean updated = false;
		
		boolean validLocation = locationValidator.isValid(newLocation);
		if (validLocation) {
			this.location = newLocation;
			updated = true;
		}
		
		return updated;
	}
}
