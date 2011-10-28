package pvpservergui.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UpdateThread {
	private DatagramChannel channel;
	private boolean isConnected = false;
	
	private void connect(){
		try{
			channel = DatagramChannel.open();
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
					connected = channel.isConnected();
				}
			}
			if(!channel.isConnected()){
				System.err.println("Connection attempt timed out");
				System.exit(NetworkProtocol.CONNECTION_ERROR);
			}
		}catch(IOException ex){
			System.err.println("Could not connect channel:");
			ex.printStackTrace();
			System.exit(NetworkProtocol.CONNECTION_ERROR);
		}
	}
	
	public void process(UDPDataContainer message){
		switch(message.getNetworkEvent()){
			case NetworkProtocol.UPDATE_SERVER_RUNNING_STATUS:
				//TODO implement actions
				break;
			case NetworkProtocol.UPDATE_SERVER_LOGIN_STATUS:
				//TODO implement actions
				break;
			case NetworkProtocol.UPDATE_USER_LOGIN_STATUS:
				//TODO implement actions
				break;
		}
	}
	
	public void act(){
		ByteBuffer b = ByteBuffer.allocate(NetworkProtocol.MAX_PACKET_SIZE);
		try{
			channel.read(b);
			b.flip();
			if(b.limit()>=4){
				byte[] message = new byte[b.limit()];
				for(int i = 0;b.hasRemaining();i++){
					message[i] = b.get();
				}
				process(new UDPDataContainer(message));
			}
		}catch(IOException ex){
			isConnected = false;
		}
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
