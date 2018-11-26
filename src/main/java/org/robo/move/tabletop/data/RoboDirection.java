package org.robo.move.tabletop.data;

public enum RoboDirection {
	NORTH ("NORTH"),
	EAST ("EAST") {
		@Override
		public RoboDirection rotateLeft() {
			return NORTH;
		}
		
		@Override
		public RoboDirection rotateRight() {
			return SOUTH;
		}
	}, 
	SOUTH ("SOUTH") {
		@Override
		public RoboDirection rotateLeft() {
			return EAST;
		}
		
		@Override
		public RoboDirection rotateRight() {
			return WEST;
		}
	}, WEST ("WEST") {
		@Override
		public RoboDirection rotateLeft() {
			return SOUTH;
		}
		
		@Override
		public RoboDirection rotateRight() {
			return NORTH;
		}
	};
	
	private String face = "NORTH";
	
	public RoboDirection rotateLeft() {
		return WEST;
	}
	
	public RoboDirection rotateRight() {
		return EAST;
	}
	
	private RoboDirection(String face) {
		this.face = face;
	}
	
	public String getFace() {
		return face;
	}
	
	public static RoboDirection ofValue(String value) {
		RoboDirection direction;

		switch (value) {
		case "EAST":
			direction = RoboDirection.EAST;
			break;
		case "NORTH":
			direction = RoboDirection.NORTH;
			break;
		case "SOUTH":
			direction = RoboDirection.SOUTH;
			break;
		case "WEST":
			direction = RoboDirection.WEST;
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid direction: " + value + " is not a valid direction ['NORTH', 'SOUTH', 'EAST', 'WEST']");
		}

		return direction;
	}
}
