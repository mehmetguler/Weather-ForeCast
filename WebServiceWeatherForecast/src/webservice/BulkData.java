package webservice;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/bulkdata")
public class BulkData {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{cityName}")
	public String jsonInfo(@PathParam("cityName") String cityName,@Context HttpServletRequest req) throws UnknownHostException{
		
		String ip=req.getRemoteAddr();
		
		Database d=new Database();
		
        String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        String news=d.getInformations(cityName, date);
		
        String behavior="BulkData Weather: "+cityName;
		d.saveLog(ip,behavior);
		
        
		return news;
	}
	
	
}

