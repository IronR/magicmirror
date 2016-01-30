package com.github.ironr.magicmirror;

import java.util.ArrayList;
import java.util.List;

public class Users {
	
	//List of users that will be loaded on startup.
	private List<User> users = new ArrayList<>();
	//Current user using mirror
	private User current;
	
	public void setCurrentUser(User user) {
		this.current = user;
	}
	
	public User getCurrentUsers() {
		return current;
	}
	
	
	
	public class User {
		private String name;
		
		public User(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
	

}
