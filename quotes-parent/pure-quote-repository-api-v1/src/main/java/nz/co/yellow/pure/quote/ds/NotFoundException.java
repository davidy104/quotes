package nz.co.yellow.pure.quote.ds;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
