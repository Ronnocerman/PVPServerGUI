package pvpservergui.network;

public interface ByteConvertable {
	public byte[] toBytes();
	public ByteConvertable toObject(byte[] data);
}
