package pvpservergui.event;


public abstract class PSGUIEvent{
	public PSGUIEvent(){}
	
	public abstract void dispatch();

	public PSGUIEvent addToQueue(){
		PSGUIEventQueue.add(this);
		return this;
	}
}
