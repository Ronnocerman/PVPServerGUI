package pvpservergui.exception;

public class DataConversionException extends PVPRuntimeException {
	public DataConversionException() {
		super();
	}

	public DataConversionException(String message) {
		super(message);
	}

	public DataConversionException(Throwable cause) {
		super(cause);
	}

	public DataConversionException(String message, Throwable cause) {
		super(message, cause);
	}

}
