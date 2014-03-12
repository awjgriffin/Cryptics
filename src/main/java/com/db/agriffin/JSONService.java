package com.db.agriffin;

import java.io.*;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

	@GET
	@Path("asText")
	@Produces(MediaType.TEXT_PLAIN)
	public Response readJSONFromFileAsText() {
		
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
		
		buffReader = new BufferedReader( new FileReader( getFile("/puzzles.json") ) );
		
		String line = "";
		while( (line = buffReader.readLine()) != null ) {
			sw.write(line);
		}
		
		json = new JSONObject( sw.toString() );
		
		buffReader.close();
		sw.close();
		
		return json;
		
	}
	
	
	@Path("save")
	@POST
	public Response saveFile(final @QueryParam("json") String json) {
	
		BufferedReader buffReader = new BufferedReader( new StringReader( json ) );
		
		BufferedWriter buffWriter;
		try {
			
			buffWriter = new BufferedWriter( new FileWriter( getFile("/test.json") ) );
			
			String line = "";
			while( (line = buffReader.readLine()) != null ) {
				buffWriter.write( line );
			}
			
			buffWriter.flush();
			buffWriter.close();
			buffReader.close();
			
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).header("error", e).build();
		} catch (URISyntaxException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).header("error", e).build();
		}
		
		return Response.ok().build();   // TODO: return json as msg?
	}
	
	
	private File getFile( final String filename ) throws URISyntaxException {
		return new File( this.getClass().getResource( filename ).toURI() ); 
	}
	
	
    public static Response okResponse(final String msg) {

    	return Response.ok(msg).
                header("Access-Control-Allow-Origin", "*").
                header("Access-Control-Allow-Methods", "GET, POST, OPTIONS").
                header("Access-Control-Allow-Headers", "Content-Type, X-Requested-With").
                build();
    }		
	
}
