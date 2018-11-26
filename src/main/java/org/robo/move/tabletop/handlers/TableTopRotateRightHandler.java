package org.robo.move.tabletop.handlers;

import org.robo.move.apis.IRoboLocation;
import org.robo.move.apis.IRoboMoveHandler;
import org.robo.move.tabletop.data.RoboDirection;
import org.robo.move.tabletop.data.TableTopLocation;
import org.springframework.stereotype.Component;

@Component(value="tableTopRightHandler")
public class TableTopRotateRightHandler implements IRoboMoveHandler {

	@Override
	public IRoboLocation updateLocation(IRoboLocation location) {
		RoboDirection direction = RoboDirection.ofValue(location.getDirection());
		return new TableTopLocation(location.getX(), location.getY(), direction.rotateRight());
	}

}
