package pvpservergui.filter;

import pvpservergui.*;

public class UserFilter implements Filter<User>{
	public static enum UserState{online, offline, any};
	
	private boolean allowCharacters;
	private boolean allowPlayers;
	private UserState playerState;
	private UserState characterState;
	private String searchString;
	
	public UserFilter(boolean allowCharacters, boolean allowPlayers, UserState playerState, UserState characterState, String searchString){
		this.allowCharacters = allowCharacters;
		this.allowPlayers = allowPlayers;
		this.playerState = playerState;
		this.characterState = characterState;
		this.searchString = (searchString == null)? "" : searchString;
	}
	
	public void allowCharacters(boolean flag){
		allowCharacters = flag;
	}
	
	public void allowPlayers(boolean flag){
		allowPlayers = flag;
	}
	
	public void setPlayerState(UserState state){
		playerState = state;
	}
	
	public void setCharacterState(UserState state){
		characterState = state;
	}
	
	public void setSearchString(String searchString){
		this.searchString = searchString;
	}
	
	public boolean accept(User u){
		if(u instanceof GameCharacter){
			if(!allowCharacters)return false;
			switch(characterState){
				case online: if(!u.isOnline())return false; break;
				case offline: if(u.isOnline())return false; break;
			}
		}
		if(!allowPlayers && u instanceof Player){
			if(!allowPlayers)return false;
			switch(playerState){
				case online: if(!u.isOnline())return false; break;
				case offline: if(u.isOnline())return false; break;
			}
		}
		
		/*
		 * the == 0; part is for if we want to match all users' names that START with the specified search string.
		 * otherwise, replace it with the commented out comparison beside it to simply determine if the
		 * name contains the search string or not;
		 */
		return u.getName().indexOf(searchString) == 0;//!= -1;
	}
}
