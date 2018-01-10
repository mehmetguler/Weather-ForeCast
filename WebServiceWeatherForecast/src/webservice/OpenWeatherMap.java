package webservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class OpenWeatherMap {
	
	
	public String performAction(String cityName) {
		Client client=ClientBuilder.newClient();
		
		StringBuffer sb=new StringBuffer();
		sb.append("http://api.openweathermap.org/data/2.5/weather?q=");
		sb.append(cityName);
		sb.append("&appid=6cfcaad57ca2a44b10bcabfd2b6e1fed");
		
		WebTarget target=client.target(sb.toString());
	
		return target.request().get(String.class);
	}
	public String performAction(String cityName,String date) {
		
		Client client=ClientBuilder.newClient();
		
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=70fdc5f9448949a0a8d202500171812&q=");
		sb.append(cityName);
		sb.append("&format=json&date="+date);
		
		WebTarget target=client.target(sb.toString());
	
		return target.request().get(String.class);
	}
}