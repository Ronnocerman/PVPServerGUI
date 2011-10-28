package pvpservergui.network;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import pvpservergui.GameCharacter;
import pvpservergui.Guild;
import pvpservergui.Identifiable;
import pvpservergui.Message;
import pvpservergui.Named;
import pvpservergui.Player;
import pvpservergui.User;
import pvpservergui.filter.Filter;

/**
 * this class is completely thread safe.
 */

public class Cache{
	private TreeMap<Long, Identifiable> allObjects = new TreeMap<Long, Identifiable>();
	private TreeMap<String, Long> namedObjectIDs = new TreeMap<String, Long>();
	private TreeMap<Long, Named> namedObjects = new TreeMap<Long, Named>();
	private TreeMap<Long, User> users = new TreeMap<Long, User>();
	private TreeMap<Long, GameCharacter> characters = new TreeMap<Long, GameCharacter>();
	private TreeMap<Long, Player> players = new TreeMap<Long, Player>();
	private TreeMap<Long, Guild> guilds = new TreeMap<Long, Guild>();
	private TreeSet<Message> messages = new TreeSet<Message>();
	
	public Cache(){}
	
	public synchronized void add(Identifiable i){
		if(i == null){
			throw new NullPointerException();
		}
		allObjects.put(i.getID(), i);
	}
	
	public synchronized void add(Named n){
		this.add((Identifiable) n);
		namedObjectIDs.put(n.getName(), n.getID());
		namedObjects.put(n.getID(), n);
	}
	
	public synchronized void add(User u){
		this.add((Named) u);
		users.put(u.getID(), u);
	}
	
	public synchronized void add(GameCharacter g){
		this.add((User) g);
		characters.put(g.getID(), g);
	}
	
	public synchronized void add(Player p){
		this.add((User) p);
		players.put(p.getID(), p);
	}
	
	public synchronized void add(Guild g){
		this.add((Named) g);
		guilds.put(g.getID(), g);
	}
	
	public synchronized void add(Message m){
		messages.add(m);
	}
	
	public synchronized Identifiable getIdentifiable(long id){
		return allObjects.get(id);
	}
	
	public synchronized Named getNamed(long id){
		return namedObjects.get(id);
	}
	
	public synchronized Named getNamed(String name){
		return namedObjects.get(namedObjectIDs.get(name));
	}
	
	public synchronized User getUser(Long id){
		return users.get(id);
	}
	
	public synchronized User getUser(String name){
		return users.get(namedObjectIDs.get(name));
	}
	
	public synchronized GameCharacter getCharacter(long id){
		return characters.get(id);
	}
	
	public synchronized GameCharacter getCharacter(String name){
		return characters.get(namedObjectIDs.get(name));
	}
	
	public synchronized Player getPlayer(long id){
		return players.get(id);
	}
	
	public synchronized Player getPlayer(String name){
		return players.get(namedObjectIDs.get(name));
	}
	
	public synchronized Guild getGuild(long id){
		return guilds.get(id);
	}
	
	public synchronized Guild getGuild(String name){
		return guilds.get(namedObjectIDs.get(name));
	}
	
	public synchronized Identifiable[] getObjects(Filter<Identifiable> filter){
		ArrayList<Identifiable> temp = new ArrayList<Identifiable>();
		for(Identifiable i : allObjects.values()){
			if(filter.accept(i)){
				temp.add(i);
			}
		}
		return temp.toArray(new Identifiable[0]);
	}
	
	public synchronized Named[] getNamed(Filter<Named> filter){
		ArrayList<Named> temp = new ArrayList<Named>();
		for(Named n : namedObjects.values()){
			if(filter.accept(n)){
				temp.add(n);
			}
		}
		return temp.toArray(new Named[0]);
	}
	
	public synchronized User[] getUsers(Filter<User> filter){
		ArrayList<User> temp = new ArrayList<User>();
		for(User u : users.values()){
			if(filter.accept(u)){
				temp.add(u);
			}
		}
		return temp.toArray(new User[0]);
	}
	
	public synchronized GameCharacter[] getCharacters(Filter<GameCharacter> filter){
		ArrayList<GameCharacter> temp = new ArrayList<GameCharacter>();
		for(GameCharacter g : characters.values()){
			if(filter.accept(g)){
				temp.add(g);
			}
		}
		return temp.toArray(new GameCharacter[0]);
	}
	
	public synchronized Player[] getPlayers(Filter<Player> filter){
		ArrayList<Player> temp = new ArrayList<Player>();
		for(Player p : players.values()){
			if(filter.accept(p)){
				temp.add(p);
			}
		}
		return temp.toArray(new Player[0]);
	}
	
	public synchronized Guild[] getGuilds(Filter<Guild> filter){
		ArrayList<Guild> temp = new ArrayList<Guild>();
		for(Guild g : guilds.values()){
			if(filter.accept(g)){
				temp.add(g);
			}
		}
		return temp.toArray(new Guild[0]);
	}
	
	public synchronized Message[] getMessages(Filter<Message> filter){
		ArrayList<Message> temp = new ArrayList<Message>();
		for(Message m : messages){
			if(filter.accept(m)){
				temp.add(m);
			}
		}
		return temp.toArray(new Message[0]);
	}
	
	public synchronized Identifiable[] getObjects(){
		return allObjects.values().toArray(new Identifiable[0]);
	}
	
	public synchronized Named[] getNamed(){
		return namedObjects.values().toArray(new Named[0]);
	}
	
	public synchronized User[] getUsers(){
		return users.values().toArray(new User[0]);
	}
	
	public synchronized GameCharacter[] getCharacters(){
		return characters.values().toArray(new GameCharacter[0]);
	}
	
	public synchronized Player[] getPlayers(){
		return players.values().toArray(new Player[0]);
	}
	
	public synchronized Guild[] getGuilds(){
		return guilds.values().toArray(new Guild[0]);
	}
	
	public synchronized Message[] getMessages(){
		return messages.toArray(new Message[0]);
	}
}
