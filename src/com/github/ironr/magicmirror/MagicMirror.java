package com.github.ironr.magicmirror;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.github.ironr.magicmirror.routes.Test;
public class MagicMirror {
	
	public MagicMirror() {
		//Init the RESTFUL Server routes
		initServer();
	}
	
	
	
	public void initServer() {
		try {
			ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		    context.setContextPath("/");
		 
		    Server jettyServer = new Server(8080);
		    jettyServer.setHandler(context);
		 
		    ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		    jerseyServlet.setInitOrder(0);
		 
		        // Tells the Jersey Servlet which REST service/class to load.
		    jerseyServlet.setInitParameter(
		      "jersey.config.server.provider.classnames",
		      Test.class.getCanonicalName());
		 
		     try {
		         jettyServer.start();
		         jettyServer.join();
		     } finally {
		         jettyServer.destroy();
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) throws Exception {
		new MagicMirror();
	    
	}
	
	
}
