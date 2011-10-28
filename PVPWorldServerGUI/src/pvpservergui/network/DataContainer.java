package pvpservergui.network;

import java.util.ArrayList;
import pvpservergui.network.NetworkProtocol;

import static pvpservergui.network.NetworkProtocol.toShort;

public abstract class DataContainer{
	protected byte networkEventType;
	protected byte networkEventSpecific;
	protected byte[][] data;
	
	public DataContainer(byte networkEventType, byte networkEventSpecific, byte[][] data){
		this.networkEventType = networkEventType;
		this.networkEventSpecific = networkEventSpecific;
		this.data = data;
	}
	
	public DataContainer(byte[] data){
		this.networkEventType = data[0];
		this.networkEventSpecific = data[1];
		singleDimensionToDouble(NetworkProtocol.resizeByteArray(data,2,data.length-1));
	}
	
	private boolean singleDimensionToDouble(byte[] data){
		new Exception().printStackTrace();
		for(int i = 0;i<data.length;i++){
			System.out.print(data[i] + ",");
		}
		System.out.println();
		ArrayList<byte[]> parameters = new ArrayList<byte[]>();
		if(data.length>=2){
			System.out.println("Parsing Data.");
			for(int i = 0;i+1<data.length;){
				System.out.println("Next Parameter");
				byte[] byteLength = new byte[2];
				byteLength[0] = data[i];
				byteLength[1] = data[i+1];
				short paramLength = toShort(byteLength);
				System.out.println("Parameter Length: " + paramLength);
				if(data.length-i >=paramLength){
					byte[] parameter = new byte[paramLength];
					System.out.println("I: " + i);
					for(int x = 0;x<paramLength;x++){
						System.out.println("X: " + x);
						parameter[x] = data[i+2+x];
					}
					parameters.add(parameter);
				}else{
					System.out.println("False Return 1");
					System.out.println("I: "+ i);
					System.out.println("paramLength: " + paramLength);
					System.out.println("data.length: " + data.length);
					for(int x = 0;x<data.length;x++){
						System.out.print(data[x] + ",");
					}
					System.out.println();
					return false;
				}
				i += 2 + paramLength;
			}
		}else{
			System.out.println("False Return 2");
			return false;
		}
		this.data = new byte[parameters.size()][];
		for(int x = 0;x<this.data.length;x++){
			this.data[x] = parameters.get(x);
		}
		System.out.println(data.length + " Parameters");
		return true;
	}
	
	public byte[][] addParameterLengths(byte[][] parameters){
		byte[][] output;
		ArrayList<byte[]> adjusted = new ArrayList<byte[]>();
		for(int x = 0;x<parameters.length;x++){
			if(parameters[x].length<=2){
				throw new IllegalArgumentException();
			}
			adjusted.add(new byte[parameters[x].length+2]);
			adjusted.get(x)[0] = NetworkProtocol.toBytes((short)parameters[x].length)[0];
			adjusted.get(x)[1] = NetworkProtocol.toBytes((short)parameters[x].length)[1];
			for(int y = 0;y<parameters[x].length;y++){
				adjusted.get(x)[y+2] = parameters[x][y];
			}
		}
		output = new byte[adjusted.size()][];
		for(int i = 0;i<adjusted.size();i++){
			output[i] = adjusted.get(i);
		}
		return output;
	}
	
	public byte getNetworkEventType(){
		return networkEventType;
	}
	
	public byte getNetworkEvent(){
		return networkEventSpecific;
	}
	
	public byte[] getParameter(int i){
		return data[i];
	}
	
	public int getParameterCount(){
		return data.length;
	}
	
	public abstract byte[] toBytes();
}
