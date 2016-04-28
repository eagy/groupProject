package cards;

/**
 * 
 * @author daniel eagy
 *
 */
public enum FaceValue {
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5), 
	SIX(6), 
	SEVEN(7), 
	EIGHT(8), 
	NINE(9), 
	TEN(10), 
	JACK(10), 
	QUEEN(10), 
	KING(10), 
	ACE(11);

	private int faceValue;

	private FaceValue(int value) {
		this.faceValue = value;
	}

	/**
	 * 
	 * @param value the value to change the faceValue to.
	 */
	public void setFaceValue(int value) {
		faceValue = value;
	}

	public int getFaceValue() {
		return faceValue;
	}
}
