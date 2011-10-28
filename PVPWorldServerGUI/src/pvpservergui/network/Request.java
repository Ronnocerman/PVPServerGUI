package pvpservergui.network;

public abstract class Request{
	public final synchronized void addToQueue(){
		RequestProcessingThread.queue.add(this);
	}
	
	public final synchronized void addThenWait() throws InterruptedException{
		addToQueue();
		this.wait();
	}
	
	public final synchronized void checkForWait(){
		return;
	}
	
	final void execute(NetworkChannel channel){
		checkForWait();
		executeImpl(channel);
		this.notifyAll();
	}
	
	protected abstract void executeImpl(NetworkChannel channel);
}