package pvpservergui.network;

public class UDPDataContainer extends DataContainer{
	public UDPDataContainer(byte[] data){
		super(data);
	}
	
	public UDPDataContainer(byte commandSpecific, byte[]... commandBody){
		super(NetworkProtocol.GENERAL_COMMAND_BYTE, commandSpecific, commandBody);
	}
	
	public byte[] toBytes(){
		byte[] command = {networkEventType,networkEventSpecific};
		byte[] data = NetworkProtocol.joinByteArrays(addParameterLengths(this.data));
		return NetworkProtocol.joinByteArrays(command,data);
	}
}
