package com.db.agriffin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONObject;

@Path("/json")
@Produces(MediaType.APPLICATION_JSON)
public class JSONService {

	
	@GET
	public Response readJSONFromFile() {

		try {
			return okResponse( loadFile().toString() );
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).header("error", e).build();
		}
	}


	private JSONObject loadFile() throws Exception {
		
		JSONObject json = new JSONObject();
		BufferedReader buffReader;
		StringWriter sw = new StringWriter();
		
		buffReader = new BufferedReader( new InputStreamReader( JSONService.class.getResourceAsStream("/puzzles.json") ) );
		
		String line = "";
		while( (line = buffReader.readLine()) != null ) {
			sw.write(line);
		}
		
		json = new JSONObject( sw.toString() );
		
		buffReader.close();
		sw.close();
		
		return json;
		
	}
	
	
/*	private JSONObject error(String msg) {
		
		JSONObject errObject= new JSONObject();
		errObject.put("error", msg);
		return errObject;
	}
*/	
    public static Response okResponse(final String msg) {

    	return Response.ok(msg).
                header("Access-Control-Allow-Origin", "*").
                header("Access-Control-Allow-Methods", "GET, POST, OPTIONS").
                header("Access-Control-Allow-Headers", "Content-Type, X-Requested-With").
                build();
    }		
	
}
