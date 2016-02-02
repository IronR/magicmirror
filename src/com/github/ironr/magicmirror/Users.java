package com.github.ironr.magicmirror;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.sessions.serializers.JSONSerializer;
import org.json.JSONArray;
import org.json.JSONObject;

public class Users {
	
	//List of users that will be loaded on startup.
	private List<User> users = new ArrayList<>();
	//Current user using mirror
	private User current;
	
	//load all users
	public Users() {
		File dir = new File("users/");
		if(dir.isDirectory()) {
			for(File f : dir.listFiles()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(f));
					String file = "";
					
					String current;
					while((current = br.readLine()) != null) {
						file += current;
					}
					br.close();
					JSONObject json = new JSONObject(file);
					User u = new User(json.getString("name"), json.getJSONArray("modules"));
					users.add(u);
					setCurrentUser(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
	public void setCurrentUser(User user) {
		this.current = user;
	}
	
	public User getCurrentUser() {
		return current;
	}
	
	//get user by name
	public User getUser(String name) {
		for(User u : users) {
			if(u.name.equalsIgnoreCase(name)) {
				return u;
			}
		}
		return null;
	}
	
	
	
	
	public class User {
		private String name;
		private JSONArray modules;
		
		public User(String name, JSONArray modules) {
			this.name = name;
			this.modules = modules;
		}
		
		public String getName() {
			return name;
		}
		
		public JSONArray getModules() {
			return modules;
		}
		
	}
	
	

}
