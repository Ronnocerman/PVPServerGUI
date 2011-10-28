package pvpservergui.exception;

public class PVPException extends Exception{
	public PVPException(){
		super();
	}
	
	public PVPException(String message){
		super(message);
	}
	
	public PVPException(Throwable cause){
		super(cause);
	}
	
	public PVPException(String message, Throwable cause){
		super(message, cause);
	}
}
