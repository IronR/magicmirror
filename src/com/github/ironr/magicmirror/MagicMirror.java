package com.github.ironr.magicmirror;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.json.JSONObject;

import com.github.ironr.magicmirror.routes.Test;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MagicMirror {

	public MagicMirror() {

		String imageURL = "http://www.puu.sh/mPkMr/a34900100c.jpg";
		try {
			HttpResponse<JsonNode> response = Unirest
					.get("https://faceplusplus-faceplusplus.p.mashape.com/detection/detect?attribute=glass%2Cpose%2Cgender%2Cage%2Crace%2Csmiling&url="
							+ imageURL)
					.header("X-Mashape-Key", "rdesnHDacOmshI3buW7gxLXaLOHep1ulY8PjsndD4wbXQbblRm")
					.header("Accept", "application/json").asJson();
			System.out.println(response.getBody());

			//
			JsonNode res = response.getBody();
			JSONObject face = res.getObject().getJSONArray("face").getJSONObject(0);
			System.out.println(face);

			JSONObject attributes = face.getJSONObject("attribute");
			// atribute campris

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Init the RESTFUL Server routes
		// initServer();

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
			jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", Test.class.getCanonicalName());

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
