package pvpservergui;

public abstract class User implements Named{
	private long id;
	private String name;
	private boolean online;
	
	public User(long id, String name, boolean online){
		this.id = id;
		this.name = name;
		this.online = online;
	}
	
	public long getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isOnline(){
		return online;
	}
	
	public void setOnline(boolean b){
		online = b;
	}
}
