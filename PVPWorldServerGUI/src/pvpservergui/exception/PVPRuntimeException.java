package pvpservergui.exception;

public class PVPRuntimeException extends RuntimeException {

	public PVPRuntimeException() {
		super();
	}

	public PVPRuntimeException(String message) {
		super(message);
	}

	public PVPRuntimeException(Throwable cause) {
		super(cause);
	}

	public PVPRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
