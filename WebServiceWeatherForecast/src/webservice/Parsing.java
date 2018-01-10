package webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parsing {
	OpenWeatherMap owm=new OpenWeatherMap();
	
	  public String[] jsonParsing(String cityName){
		  
		   String[] info=new String[5];
		   try {
			   JSONObject nesne=new JSONObject(owm.performAction(cityName));
			   
				info[0]=nesne.getString("name");
				
			JSONArray array=nesne.getJSONArray("weather");
			
			JSONObject nesne2=array.getJSONObject(0);
			
			info[1]=nesne2.getString("main");
			
			JSONObject main=nesne.getJSONObject("main");
			
			info[2]=Double.toString(main.getDouble("temp"));
			info[3]=Double.toString(main.getDouble("humidity"));
			info[4]=Double.toString(main.getDouble("pressure"));
			
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		   return info;
	   }
	
	public void parsJsonAndSaveToDateBase(String cityName,String date){
		
		Database d=new Database();
		
		 try {
			   JSONObject nesne=new JSONObject(owm.performAction(cityName, date));
			  
			   JSONObject nesne2=nesne.getJSONObject("data");
			  
			   JSONArray array=nesne2.getJSONArray("weather");
			  
			   JSONObject nesne3=array.getJSONObject(0);
			   
			   JSONArray array2=nesne3.getJSONArray("hourly");
			  
			   JSONObject nesne4=array2.getJSONObject(4);
			   
			   JSONArray array3=nesne4.getJSONArray("weatherDesc");
			   JSONObject nesne5=array3.getJSONObject(0);
			   
			   String situation=String.valueOf(nesne5.get("value"));
			   date=date.substring(3,5)+"/"+date.substring(0,2)+"/"+date.substring(6,10);
			d.saveDataToDatabase(cityName, nesne4.getDouble("tempC"),nesne4.getDouble("humidity"),nesne4.getDouble("pressure"),situation, date);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		
	}
}