package org.robo.move.tabletop.handlers;

import org.robo.move.apis.IRoboLocation;
import org.robo.move.apis.IRoboMoveHandler;
import org.robo.move.tabletop.data.RoboDirection;
import org.robo.move.tabletop.data.TableTopLocation;
import org.springframework.stereotype.Component;

@Component(value="tableTopMoveHandler")
public class TableTopMoveForwardHandler implements IRoboMoveHandler {

	@Override
	public IRoboLocation updateLocation(IRoboLocation location) {
		RoboDirection direction = RoboDirection.ofValue(location.getDirection());
		int newX = location.getX();
		int newY = location.getY();

		switch (direction) {
		case EAST:
			++newX;
			break;
		case NORTH:
			++newY;
			break;
		case SOUTH:
			--newY;
			break;
		case WEST:
			--newX;
		default:
			break;
		}

		return new TableTopLocation(newX, newY, direction);
	}

}
