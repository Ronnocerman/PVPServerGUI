package pvpservergui.network;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;

class RequestProcessingThread implements Runnable{
	static final ConcurrentLinkedQueue<Request> queue = new ConcurrentLinkedQueue<Request>();
	private SocketChannel channel;
	private boolean isConnected = false;
	
	
	private void connect(){
		try{
			channel = SocketChannel.open();
			channel.socket().bind(null);
			channel.configureBlocking(true);
			boolean connected = false;
			boolean exception = false;
			try{
				channel.connect(NetworkProtocol.SERVER_ADDRESS);
			}catch(IOException e){
				exception = true;
			}
			for(int i = 0; !connected && i < NetworkProtocol.MAX_CONNECTION_ATTEMPTS; i++){
				if(connected == false && exception ==true){
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					try{
						channel.connect(NetworkProtocol.SERVER_ADDRESS);
					}catch(IOException e){
						connected = false;
						exception = true;
					}
				}
				if(connected == false && exception == false){
					try{
						connected = channel.finishConnect();
					}catch(ConnectException e){
						exception = true;
						connected = false;
					}
				}
			}
			if(!channel.finishConnect()){
				System.err.println("Connection attempt timed out");
				System.exit(NetworkProtocol.CONNECTION_ERROR);
			}
		}catch(IOException ex){
			System.err.println("Could not connect channel:");
			ex.printStackTrace();
			System.exit(NetworkProtocol.CONNECTION_ERROR);
		}
	}
	
	public void act(){
		//TODO implement
	}
	
	public void run(){
		while(!NetworkAdapter.end()){
			if(!isConnected){
				connect();
			}
			act();
		}
	}
}
