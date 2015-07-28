package exception;


public class SnakeBiteHimselfsException extends Exception {

	public SnakeBiteHimselfsException() {
		super();
	}

	public SnakeBiteHimselfsException(String message) {
		super(message);
	}

	public SnakeBiteHimselfsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SnakeBiteHimselfsException(Throwable cause) {
		super(cause);
	}

}
