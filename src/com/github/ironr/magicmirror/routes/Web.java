package com.github.ironr.magicmirror.routes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.github.ironr.magicmirror.MagicMirror;
import com.github.ironr.magicmirror.Users;

@Path("/")
public class Web {
	
	private Users users;
	
	
	@GET
	@Produces("application/json")
	@Path("user/{id}/modules")
	public Response getModules(@PathParam("id") String name) {
		checkUsers();
		System.out.println(users.getUser(name));
		return Response.status(200).entity(users.getUser(name).getModules().toString()).build();
		
	}
	
	
	
	
	//Return the current use logged in
	@GET
	@Produces("application/json")
	@Path("user/identified")
	public Response getIdentified() {
		checkUsers();
		//return the username of the person logged in
		System.out.println(users.getCurrentUser().getName());
		JSONObject json = new JSONObject();
		json.put("name", users.getCurrentUser().getName());
		System.out.println(json);
		return Response.status(200).entity(json.toString()).build();
	}
	
	public void checkUsers() {
		users = MagicMirror.getInstance().users;
	}
	
	
	@GET
	@Path("{id}")
	public Response service(@PathParam("id") String theID) {
		//Try to send a file
		try {
			return Response.status(200).entity(Files.readAllBytes(Paths.get("web/"+theID))).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//If we have gotten here 404.
		return Response.status(404).entity("404 not found").build();
	}
	

}
