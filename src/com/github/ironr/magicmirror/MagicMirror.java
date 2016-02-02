package com.github.ironr.magicmirror;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.github.ironr.magicmirror.routes.Test;
import com.github.ironr.magicmirror.routes.User;
import com.github.ironr.magicmirror.routes.Web;
public class MagicMirror {
	
	private static MagicMirror instance;
	
	public Users users = new Users();


	public MagicMirror() {
		//Init the RESTFUL Server routes
		initServer();
	}
	
	
	
	public void initServer() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
				    context.setContextPath("/");
				 
				    Server jettyServer = new Server(8080);
				    jettyServer.setHandler(context);
				 
				    ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
				    jerseyServlet.setInitOrder(0);
				 
				        // Tells the Jersey Servlet which REST service/class to load.
				    //  
				     // Test.class.getCanonicalName(),
				    System.out.println(Web.class.getCanonicalName());
				    jerseyServlet.setInitParameter(
				      "jersey.config.server.provider.classnames",
				      //User.class.getCanonicalName(),
				      Web.class.getCanonicalName()
				    );
				 
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
		}).start();
		
		
		
	}
	
	public static MagicMirror getInstance() {
		return instance;
	}

	public static void main(String[] args) throws Exception {
		instance = new MagicMirror();
	    
	}
	
	
}
