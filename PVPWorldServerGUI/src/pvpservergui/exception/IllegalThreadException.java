package pvpservergui.exception;

public class IllegalThreadException extends PVPException{
	private static final long serialVersionUID = 1L;

	public IllegalThreadException(){
		super();
	}
	
	public IllegalThreadException(String message){
		super(message);
	}
}
