package webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Database {
	
	
	public void saveLog(String ipAddress,String behavior){
		
		try{
			  Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e){
			}

			Connection connection=null;

			try{
			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/InternetBasedProgramming","root","linuxmint");
			}catch(SQLException e){
			
			}

			try{
				
				Calendar calendar = Calendar.getInstance();
			    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
			    
			    System.out.println(ipAddress+" "+behavior+" "+ourJavaTimestampObject);
					
					PreparedStatement apply=connection.prepareStatement("INSERT INTO Logging VALUES (?, ?, ?)");
					
					apply.setString(1,ipAddress);
					apply.setString(2, behavior);
					apply.setTimestamp(3, ourJavaTimestampObject);
					
					apply.executeUpdate();	
					System.out.println("3");
				    apply.close();
				
			}catch(Exception e){
		}
	}
	
	public void saveDataToDatabase(String cityName,double temperature,double humidity,double pressure,String situation,String date){

    try{
		  Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
		}

		Connection connection=null;

		try{
		connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/InternetBasedProgramming","root","linuxmint");
		}catch(SQLException e){
		
		}

		try{
				java.util.Date myDate = new java.util.Date(date);
				java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

				PreparedStatement apply=connection.prepareStatement("INSERT INTO BulkData VALUES (?, ?, ?, ?, ?, ?)");
				apply.setString(1,cityName);
				apply.setDouble(2, temperature);
				apply.setDouble(3, humidity);
				apply.setDouble(4, pressure);
				apply.setString(5,situation);
				apply.setDate(6, sqlDate);
				apply.executeUpdate();	
				
		}catch(Exception e){
		
		}
		}


public String getInformations(String cityName, String date){
	Database d=new Database();
	String news="";
	try{
		  Class.forName("com.mysql.jdbc.Driver");
		  }catch(ClassNotFoundException e){
		}

		Connection connection=null;

		try{
		connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/InternetBasedProgramming","root","linuxmint");
		}catch(SQLException e){
		
		}

		try{
				SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
               java.util.Date utilDate=format.parse(date);
               
                java.sql.Date sqlDate = new java.sql.Date(utilDate .getTime());
System.out.println(sqlDate);
				PreparedStatement apply=connection.prepareStatement("select * from BulkData where  Date='"+sqlDate+"' and  CityName='"+cityName+"'");
				ResultSet reultSet=apply.executeQuery();
				while(reultSet.next()){
					
					 news="{\"CityName\":\""+reultSet.getString("CityName")+"\",\"Situation\":\""+reultSet.getString("Situation")+"\", \"Temperature\":\""+reultSet.getString("Temperature")+"\",\"Humidity\":\""+reultSet.getString("Humidity")+"\",\"Pressure\":\""+reultSet.getString("Pressure")+"\",\"Date\":\""+reultSet.getString("Date")+"\"";
					 
				}
				
			}catch(Exception e){
		
		}
	if(news=="")
		return "No information";
	return news+",\"MountlyAverage\":["+d.pastInformation(cityName, date)+"]}";
}


public String pastInformation(String cityName,String date){
	String news="";
	String[] mounth={"","January","February","March","April","June","July","August","September","October","November","December"};
	try{
		  Class.forName("com.mysql.jdbc.Driver");
		  }catch(ClassNotFoundException e){
		}

		Connection connection=null;

		try{
		connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/InternetBasedProgramming","root","linuxmint");
		}catch(SQLException e){
		
		}

		try{
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
             java.util.Date utilDate=format.parse(date);
             
             PreparedStatement apply=connection.prepareStatement("select avg(Temperature),avg(Humidity),avg(Pressure),avg(Date) from BulkData where CityName='"+cityName+"'"
						+"group by DATE_FORMAT(Date,\"%m-%y\")");
				ResultSet reultSet=apply.executeQuery();
				while(reultSet.next()){
					
					String mounths=Integer.toString(reultSet.getInt(4)).substring(4,6);
					String year=Integer.toString(reultSet.getInt(4)).substring(0,4);
					
				news+="{\"Mounth\":\""+year+" "+mounth[Integer.parseInt(mounths)]+"\",\"Temperature\":\""+reultSet.getString(1)+"\",\"Humidity\":\""+reultSet.getString(2)+"\",\"Pressure\":\""+reultSet.getString(3)+"\"},";
				}
				
				news=news.substring(0, news.length()-1);
				}catch(Exception e){
		
		}
		return news;
	
}

}
