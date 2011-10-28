package pvpservergui.network;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import pvpservergui.exception.*;

public class NetworkProtocol {
	//----------------------------command bytes-----------------------------------------
	
	//TODO assign values
	
	//command general
	public static final byte GENERAL_COMMAND_BYTE = 126;
		//command specific
		//server
	
		/*
		 * NEVER used except as a relative place holder for the values assigned to the following commands
		 * that way I don't have to go modifying every value every time I make a change.
		 */
		public static final byte SERVER = 0;
		
		public static final byte SERVER_LOGIN_ALLOW = SERVER + 1;//list of IDs to allow to login
		public static final byte SERVER_LOGIN_UNPRIVILEGED = SERVER + 2;//enable/disable
		public static final byte SERVER_LOGIN_PRIVILEGED = SERVER + 3;//enable/disable
		public static final byte SERVER_LOGIN_ALL = SERVER + 4;//enable/disable
		public static final byte SERVER_FORCE_LOGOUT = SERVER + 5;//list of id's to force to logout
		public static final byte SERVER_FORCE_LOGOUT_UNPRIVILEGED = SERVER + 6;
		public static final byte SERVER_FORCE_LOGOUT_PRIVILEGED = SERVER + 7;
		public static final byte SERVER_FORCE_LOGOUT_ALL = SERVER + 8;//includePrivilagedAccessUsers true/false
		public static final byte SERVER_STATUS_RESET = SERVER + 9;//no additional parameters
		public static final byte SERVER_STATUS_STARTUP = SERVER + 10;//no additional parameters
		public static final byte SERVER_STATUS_INSTANT_SHUTDOWN = SERVER + 11;//no additional parameters
		public static final byte SERVER_STATUS_DELAYED_SHUTDOWN = SERVER + 12;//long for amount of time till shutdown
		
		//update
		public static final byte UPDATE = 12;//same as SERVER
		public static final byte UPDATE_SERVER_RUNNING_STATUS = UPDATE + 1;//ENABLED/DISABLED
		public static final byte UPDATE_SERVER_LOGIN_STATUS = UPDATE + 2;//UNPRIVILEGED_ENABLED/DISABLED PRIVILEGED_ENABLED/DISABLED
		public static final byte UPDATE_USER_LOGIN_STATUS = UPDATE + 3;//long USER_ID, ENABLED/DISABLED
		
		//definition
		public static final byte DEFINITION = 15;//same as SERVER
		public static final byte DEFINITION_CHARACTER_REQUEST = DEFINITION + 1;//list of  USER_ID
		public static final byte DEFINITION_CHARACTER_RESPONSE = DEFINITION + 2;//*content to be listed*
		
		public static final byte DEFINITION_PLAYER_REQUEST = DEFINITION + 3;//list of USER_IDs
		public static final byte DEFINITION_PLAYER_RESPONSE = DEFINITION + 4;//*content to be listed*
		
		public static final byte DEFINITION_GUILD_REQUEST = DEFINITION + 5;//list of GUILD_IDs
		public static final byte DEFINITION_GUILD_RESPONSE = DEFINITION + 6;//*content to be listed*
		
		public static final byte DEFINITION_MESSAGE_REQUEST = DEFINITION + 7;//list of MESSAGE_IDs
		public static final byte DEFINITION_MESSAGE_RESPONSE = DEFINITION + 8;//*content to be listed*
		
		//used to get the id of every matching object
		public static final byte MATCH = 23;//same as SERVER
		public static final byte MATCH_USER_REQUEST = MATCH + 1;//*content to be listed*
		public static final byte MATCH_USER_RESPONSE = MATCH + 2;//list of the following pair: byte USER_OR_CHARACTER, long USER_ID
		
		public static final byte MATCH_CHARACTER_REQUEST = MATCH + 3;//*content to be listed*
		public static final byte MATCH_CHARACTER_RESPONSE = MATCH + 4;//list of USER_IDs
		
		public static final byte MATCH_PLAYER_REQUEST = MATCH + 5;//*content to be listed*
		public static final byte MATCH_PLAYER_RESPONSE = MATCH + 6;//list USER_IDs
		
		public static final byte MATCH_MESSAGE_REQUEST = MATCH + 7;//*content to be listed*
		public static final byte MATCH_MESSAGE_RESPONSE = MATCH + 8;//list of IDs
	
	//--------------------------end command bytes---------------------------------------
	
	//other fields
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 5472;
	public static final SocketAddress SERVER_ADDRESS = new InetSocketAddress(SERVER_IP, SERVER_PORT);
	
	public static Charset charset = Charset.forName("UTF-8");

	public static final int CONNECTION_ERROR = 1;
	public static final int MAX_CONNECTION_ATTEMPTS = 360;
	public static final short SHORT_BYTE_SIZE = (Short.SIZE+7)/8;
	public static final short INT_BYTE_SIZE = (Integer.SIZE+7)/8;
	public static final short LONG_BYTE_SIZE = (Long.SIZE+7)/8;
	public static final int MAX_PACKET_SIZE = 65536;
	
	public static final byte ON, ENABLE, ENABLED;
	public static final byte OFF, DISABLE, DISABLED;
	
	static{
		OFF = DISABLE = DISABLED = 0;
		ON = ENABLE = ENABLED = 1;
	}
	
	//data conversion methods
	public static short signedIntToUnsignedShort(int input){
		if(input>=65536){
			input -= 65536;
		}
		if(input<0){
			input += 65536;
		}
		return (short)((input<32768&&input>=0) ? input : (input*(0-1)));
	}

	public static int unsignedShortToSignedInt(short input){
		return(input<0) ? (int)(input + MAX_PACKET_SIZE) : (int)input;
	}
	
	public static byte[] toBytes(boolean input){
		return null;
	}
	
	public static byte[] toBytes(byte input){
		return null;
	}
	
	public static byte[] toBytes(short input){
		byte[] output = new byte[SHORT_BYTE_SIZE];
		output[0] = (byte) ((input >> 8) & 0xFF); //Shift the byte to the right and zero out all the other bytes. 
		output[1] = (byte) (input & 0xFF); //zero out all other bytes except for the rightmost. 
		return output;
	}
	
	public static byte[] toBytes(int input){
		ByteBuffer b = ByteBuffer.allocate(INT_BYTE_SIZE);
		b.putInt(input);
		return b.array();
	}
	
	public static byte[] toBytes(long input){
		ByteBuffer b = ByteBuffer.allocate(8);
		b.putLong(input);
		return b.array();
	}
	
	public static byte[] toBytes(float input){
		return null;
	}
	
	public static byte[] toBytes(double input){
		return null;
	}
	
	public static byte[] toBytes(char input){
		return null;
	}
	
	public static byte[] toBytes(String input){
		CharsetEncoder encoder = charset.newEncoder();
		try{
			return encoder.encode(CharBuffer.wrap(input)).array();
		}catch(Exception e){
			e.printStackTrace();
			throw new DataConversionException(e);
		}
	}
	
	public static byte[] toBytes(ByteConvertable input){
		return null;
	}
	
	public static boolean toBoolean(byte[] data){
		return false;
	}
	
	public static byte toByte(byte[] data){
		return 0;
	}
	
	public static short toShort(byte[] data){
		return 0;
	}
	
	public static int toInt(byte[] data){
		return 0;
	}
	
	public static long toLong(byte[] data){
		return 0;
	}
	
	public static float toFloat(byte[] data){
		return 0;
	}
	
	public static double toDouble(byte[] data){
		return 0;
	}
	
	public static char toChar(byte[] data){
		return 0;
	}
	
	public static String toString(byte[] data){
		return null;
	}
	
	//returns a byte array of length endIndex-startIndex+1
	//and pads zeros for indexes out of range of input.
	public static byte[] resizeByteArray(byte[] input, int startIndex, int endIndex){
		if(startIndex > endIndex){
			//swap
			startIndex ^= endIndex;
			endIndex ^= startIndex;
			startIndex ^= endIndex;
		}

		byte[] output = new byte[endIndex-startIndex+1];
		int curIndex = 0;

		if(startIndex < 0){
			for(int j = 0; j < startIndex*(0-1) && curIndex < output.length; curIndex++, j++){
				output[curIndex] = 0;
			}

		}

		for(int j = startIndex+curIndex; j < input.length && j <= endIndex && curIndex < output.length;
				curIndex++, j++)
		{
			output[curIndex] = input[j];
		}

		if(endIndex >= input.length)		{
			for(int j = input.length; j < endIndex && curIndex < output.length;	j++, curIndex++){
				output[curIndex] = 0;
			}
		}

		return output;
	}
	
	public static byte[] joinByteArrays(byte[]...input){
		int length = 0;
		for(int i = 0; i < input.length; i++){
			length += input[i].length;
		}
		byte[] output = new byte[length];
		System.out.println(length);
		
		int curIndex = 0;
		for(int i = 0; i < input.length; i++){
			for(int j = 0; j < input[i].length; j++){
				output[curIndex] = input[i][j];
				curIndex++;
			}
		}
		
		return output;
	}
	
	public static byte[][] byteArrayArgsToMultidimensional(byte[]...input){
		return input;
	}
	
	public static byte[] byteArgsToArray(byte...input){
		return input;
	}
	
	public static boolean IPsEqual(SocketAddress a,SocketAddress b){
		String a1 = a.toString();
		String b1 = b.toString();
		for(int i = 0;i<a1.length();i++){
			if(a1.charAt(i)==':'){
				a1 = Character.toString(a1.charAt(i));
			}
		}
		for(int i = 0;i<b1.length();i++){
			if(b1.charAt(i)==':'){
				b1 = Character.toString(b1.charAt(i));
			}
		}
		return (a1.equals(b1))? true : false;
	}
	
	
}
