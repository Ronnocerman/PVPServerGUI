package pvpservergui.filter;

import pvpservergui.Message;
import pvpservergui.TimeStamp;

public class MessageFilter implements Filter<Message>{
	public static final long ANY_ID = -1L;
	
	private long targetID;
	private long sourceID;
	private TimeStamp min;//if null, then no minimum bound
	private TimeStamp max;//if null, then no maximum bound
	
	public boolean accept(Message m){
		if(targetID != ANY_ID && m.getTargetID()!= targetID)return false;
		if(sourceID != ANY_ID && m.getSourceID() != sourceID)return false;
		if(min != null && m.getTimeStamp().compareTo(min) == -1)return false;
		if(max != null && m.getTimeStamp().compareTo(max) == 1)return false;
		return true;
	}
}
