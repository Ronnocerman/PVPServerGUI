package pvpservergui.exception;

public class AlreadyInitializedException extends PVPRuntimeException{

	public AlreadyInitializedException(){
		super();
	}

	public AlreadyInitializedException(String message){
		super(message);
	}

	public AlreadyInitializedException(Throwable cause){
		super(cause);
	}

	public AlreadyInitializedException(String message, Throwable cause){
		super(message, cause);
	}

}
