package pvpservergui.event;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

import pvpservergui.exception.IllegalThreadException;

public class PSGUIEventQueue{
	private static final long serialVersionUID = 1L;
	private static final int MAX_NUM_PERMITS = 30;//temporary value until I can determine how many are needed.
	private static final long WAIT_TIMEOUT = 2000L;
	
	private static Semaphore permits = new Semaphore(MAX_NUM_PERMITS);
	private static ConcurrentLinkedQueue<PSGUIEvent> events = new ConcurrentLinkedQueue<PSGUIEvent>();
	private static Object waitObject = new Object();
	
	/**
	 * gets all permits, blocking until all are acquired. Blocks other threads from gaining new ones.
	 * Usable only by the event dispatch thread.
	 * (Currently) only used by the addToFront() method.
	 * 
	 * @throws IllegalThreadException if the thread that called this method is not the event dispatch thread
	 */
	private static void getAllPermits()throws IllegalThreadException{
		//check if the current thread is the event dispatch thread
		if(!PSGUIEventDispatchThread.isEventDispatchThread()){
			throw new IllegalArgumentException();
		}
		//the initializer grabs all currently available permits.
		//the conditional is only true once it has received all permits.
		for(int i = permits.drainPermits(); i < MAX_NUM_PERMITS; i++){
			permits.acquireUninterruptibly();
		}
	}
	
	public static void add(PSGUIEvent event)throws IllegalArgumentException{
		if(event == null){
			throw new IllegalArgumentException("Null Pointer");
		}
		permits.acquireUninterruptibly();
		events.add(event);
		waitObject.notify();
		permits.release();
	}
	
	/**
	 * adds the event to the beginning of the queue. 
	 * Can only be called by the event dispatch thread.
	 * Not sure why this might possibly be needed but added it just in case.
	 * 
	 * @param event the event to be added to the front of the queue
	 * @throws IllegalThreadException if the thread calling this method is not the event dispatch thread
	 */
	static void addToFront(PSGUIEvent event)throws IllegalThreadException{
		synchronized(waitObject){
			getAllPermits();
			ArrayList<PSGUIEvent> temp = new ArrayList<PSGUIEvent>();
			temp.add(event);
			temp.addAll(events);
			events.clear();
			events.addAll(temp);
			permits.release(MAX_NUM_PERMITS);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws IllegalThreadException
	 */
	static PSGUIEvent nextEvent()throws IllegalThreadException, InterruptedException{
		if(!PSGUIEventDispatchThread.isEventDispatchThread()){
			throw new IllegalThreadException("Must be called by the PSGUIEventDispatchThread");
		}
		synchronized(waitObject){
			PSGUIEvent temp = null;
			while((temp = events.poll()) == null){
				waitObject.wait(WAIT_TIMEOUT);
			}
			return temp;
		}
	}
}
