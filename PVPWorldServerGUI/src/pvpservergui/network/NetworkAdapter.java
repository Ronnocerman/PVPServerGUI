package pvpservergui.network;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;

import pvpservergui.*;
import pvpservergui.exception.AlreadyInitializedException;
import pvpservergui.filter.Filter;

public class NetworkAdapter{
	private static boolean initialized = false;
	private static boolean end = false;
	private static ConcurrentLinkedQueue<Request> requestQueue = new ConcurrentLinkedQueue<Request>();
	
	static final Cache cache = new Cache();
	
	
	
	public static synchronized void initialize()throws AlreadyInitializedException{
		if(initialized)throw new AlreadyInitializedException("The Network Thread has already been initialized");
		initialized = true;
		//initialize
	}
	
	public static synchronized void shutDownThreads(){
		end = true;
	}
	
	static synchronized boolean end(){
		return end;
	}
}
