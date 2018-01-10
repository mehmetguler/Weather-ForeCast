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

@Path("/instant-weather")
public class InstantWeather {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{cityName}")
	public String jsonInfo(@PathParam("cityName") String cityName,@Context HttpServletRequest req) throws UnknownHostException{
		Parsing p=new Parsing(); 
		
		Database d=new Database();
		String[] info=new String[5];
		
		String ip=req.getRemoteAddr();
	    
		info=p.jsonParsing(cityName);
		info[2]=String.format("%.2f",Double.parseDouble(info[2])-273);
		String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		//make json format weather news
		String news="{\"CityName\":\""+info[0]+"\",\"Situation\":\""+info[1]+"\", \"Temperature\":\""+info[2]+"\",\"Humidity\":\""+info[3]+"\",\"Pressure\":\""+info[4]+"\",\"Date\":\""+date+"\"}";
		
		String behavior="Instant Weather: "+info[0];
		
		d.saveLog(ip,behavior );
		
		return news;
	}

}

