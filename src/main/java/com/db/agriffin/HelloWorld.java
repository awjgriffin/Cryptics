package com.db.agriffin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorld {

	@GET
	public Response helloWorld() {
		
		JSONObject json = new JSONObject();
		try {
			
			json.put("msg", "Hello World");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return okResponse( json.toString() );
		
	}
	
    public static Response okResponse(final String msg) {
        return Response.ok(msg).
                header("Access-Control-Allow-Origin", "*").
                header("Access-Control-Allow-Methods", "GET, POST, OPTIONS").
                header("Access-Control-Allow-Headers", "Content-Type, X-Requested-With").
                build();
    }	
	
}
