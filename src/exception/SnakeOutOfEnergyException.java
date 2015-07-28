package exception;

public class SnakeOutOfEnergyException extends Exception {

	public SnakeOutOfEnergyException() {
		super();
	}

	public SnakeOutOfEnergyException(String message) {
		super(message);
	}

	public SnakeOutOfEnergyException(String message, Throwable cause) {
		super(message, cause);
	}

	public SnakeOutOfEnergyException(Throwable cause) {
		super(cause);
	}

}
