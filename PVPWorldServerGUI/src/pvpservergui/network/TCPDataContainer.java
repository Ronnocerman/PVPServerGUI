package pvpservergui.network;

public class TCPDataContainer extends DataContainer{
	public TCPDataContainer(byte[] data){
		super(data);
	}
	
	public TCPDataContainer(byte commandSpecific, byte[]... commandBody){
		super(NetworkProtocol.GENERAL_COMMAND_BYTE, commandSpecific, commandBody);
	}
	
	public byte[] toBytes(){
		byte[] command = {networkEventType,networkEventSpecific};
		short shortLength = (short)(data.length*2);
		for(int i = 0;i<this.data.length;i++){
			shortLength+=data[i].length;
		}
		byte[] length = NetworkProtocol.toBytes(shortLength);
		byte[] data = NetworkProtocol.joinByteArrays(addParameterLengths(this.data));
		return NetworkProtocol.joinByteArrays(command,length,data);
	}
}
