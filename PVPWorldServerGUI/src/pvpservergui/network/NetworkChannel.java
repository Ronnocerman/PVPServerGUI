package pvpservergui.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import pvpservergui.exception.ConnectionException;

public class NetworkChannel{
	private SocketChannel channel;
	
	public NetworkChannel(SocketChannel channel){
		if(channel == null)throw new IllegalArgumentException("Null Pointer: channel");
		this.channel = channel;
	}
	
	public int read(ByteBuffer dst){
		try {
			return channel.read(dst);
		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}
	
	public long read(ByteBuffer[] dsts){
		try{
			return channel.read(dsts);
		}catch(IOException e){
			throw new ConnectionException(e);
		}
	}
	
	public long read(ByteBuffer[] dsts, int offset, int length){
		try{
			return channel.read(dsts, offset, length);
		}catch(IOException e){
			throw new ConnectionException(e);
		}
	}
	
	public int write(ByteBuffer src){
		try{
			return channel.write(src);
		}catch(IOException e){
			throw new ConnectionException(e);
		}
	}
	
	public long write(ByteBuffer[] srcs){
		try{
			return channel.write(srcs);
		}catch(IOException e){
			throw new ConnectionException(e);
		}
	}
	
	public long write(ByteBuffer[] srcs, int offset, int length){
		try{
			return channel.write(srcs, offset, length);
		}catch(IOException e){
			throw new ConnectionException(e);
		}
	}
}
