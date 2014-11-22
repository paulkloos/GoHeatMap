package board;

public enum Direction {
	TOP(0),LEFT(1),RIGHT(2),BOTTOM(3), TOP_LEFT(4), TOP_RIGHT(5), BOTTOM_LEFT(6), BOTTOM_RIGHT(7);
	private final int value;
	Direction(int value){
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
	
	public static Direction getDirection(int value) {
		switch (value) {
		case 1:
			return Direction.LEFT;
		case 2:
			return Direction.RIGHT;
		case 3:
			return Direction.BOTTOM;
		case 4:
			return Direction.TOP_LEFT;
		case 5:
			return Direction.TOP_RIGHT;
		case 6:
			return Direction.BOTTOM_LEFT;
		case 7:
			return Direction.BOTTOM_LEFT;
		default:
			return Direction.TOP;
		}
	}
}
