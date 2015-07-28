package model;

public enum Direction {

	DOWN (1),
	UP (2),
	RIGHT (3),
	LEFT (4);
	
	private int value;
	
	private Direction(int directionCode) {
		this.value = directionCode;
	}
	
	public int getDirectionCode() {
		return value;
	}
	
	public static Direction getDirection(int directionCode) {
		Direction[] arrayOfValues = values();
		for(int i = 0; i < arrayOfValues.length; i++) {
			if(arrayOfValues[i].getDirectionCode() == directionCode) {
				return arrayOfValues[i];
			}
		}
		throw new IllegalArgumentException("No such direction code known: " + directionCode);
	}
}