package pvpservergui.event;

import pvpservergui.exception.EventDispatchException;
import pvpservergui.exception.IllegalThreadException;

class PSGUIEventDispatchThread extends Thread{
	private static final PSGUIEventDispatchThread thread = new PSGUIEventDispatchThread();
	private static boolean running = true;
	private static Object startLock = new Object();
	
	static boolean isEventDispatchThread(){
		return Thread.currentThread() == thread;
	}
	
	static boolean isEDTStarted(){
		return running;
	}
	
	static void startEDT()throws IllegalStateException{
		synchronized(startLock){
			if(running == false){
				running = true;
				thread.start();
			}else{
				
			}
		}
	}
	
	private static void eventLoop(){
		while(running){
			try {
				PSGUIEventQueue.nextEvent().dispatch();
			} catch (IllegalThreadException e){
				//does nothing because this IS the event dispatch thread
			} catch (InterruptedException e) {
				throw new EventDispatchException(e);
			}
		}
	}
	
	/**
	 * in case it is ever needed for some reason.
	 */
	@SuppressWarnings("unused")
	private static class ShutDownEvent extends PSGUIEvent{
		public ShutDownEvent(){}
		
		public void dispatch(){
			running = false;
		}
	}
	
	//make it so that this class cannot be instantiated outside of this class file.
	private PSGUIEventDispatchThread(){}
	
	public void run(){
		eventLoop();
	}
	
}
