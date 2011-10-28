package pvpservergui.exception;

public class PVPError extends Error {

	public PVPError() {
		super();
	}

	public PVPError(String message) {
		super(message);
	}

	public PVPError(Throwable cause) {
		super(cause);
	}

	public PVPError(String message, Throwable cause) {
		super(message, cause);
	}
}
