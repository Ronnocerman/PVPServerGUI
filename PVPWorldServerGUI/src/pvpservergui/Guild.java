package pvpservergui;

public class Guild implements Named{
	private long id;
	private String name;
	GameCharacter guildLeader;
	GameCharacter[] guildAdmins;
	GameCharacter[] members;
	//guild bank
	//general info
	
	public long getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}
