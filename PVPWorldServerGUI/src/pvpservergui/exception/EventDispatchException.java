package pvpservergui.exception;

public class EventDispatchException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EventDispatchException(){
		super();
	}
	
	public EventDispatchException(String message){
		super(message);
	}
	
	public EventDispatchException(Throwable cause){
		super(cause);
	}
	
	public EventDispatchException(String message, Throwable cause){
		super(message, cause);
	}
}
