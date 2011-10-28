package pvpservergui;

public class Message implements Identifiable, Comparable<Message>{
	private long id;
	private TimeStamp timeStamp;
	private long targetID;
	private long sourceID;
	private String message;
	
	public Message(long id, TimeStamp timeStamp, long targetID, long sourceID, String message){
		this.id = id;
		this.timeStamp = timeStamp;
		this.targetID = targetID;
		this.sourceID = sourceID;
		this.message = message;
	}
	
	public Message(long id, byte year, byte month, byte day, byte hour, byte minute, byte second, long targetID, long sourceID, String message)throws IllegalArgumentException{
		this(id, new TimeStamp(year, month, day, hour, minute, second), targetID, sourceID, message);
	}
	
	public long getID(){
		return id;
	}
	
	public TimeStamp getTimeStamp(){
		return timeStamp;
	}
	
	public long getTargetID(){
		return targetID;
	}
	
	public long getSourceID(){
		return sourceID;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String toString(){
		return timeStamp + " [" + targetID + "] (" + sourceID + "): " + message;
	}
	
	public int compareTo(Message m){
		return timeStamp.compareTo(m.timeStamp);
	}
}
