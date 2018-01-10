package webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main {

	public static void main(String[] args) {
		
		Main m=new Main();
		
		m.displayBulkData();
		//System.out.println("finished");
		/*	Parsing p=new Parsing();
		String[] cities=new String[7];
		cities[0]="Ankara";
		cities[1]="Bursa";
		cities[2]="Erzurum";
		cities[3]="Istanbul";
		cities[4]="Trabzon";
		cities[5]="Konya";
		cities[6]="Tunceli";
		
		for (int i = 0; i < cities.length; i++) {
			
			p.parsJsonAndSaveToDateBase(cities[i],"16/02/2017");
		}*/
		System.out.println("finished");


}

	
	public void displayBulkData(){
		
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
				System.out.println("City Name---Situation---Temperature---Humidity---Pressure---Date");
				    PreparedStatement apply=connection.prepareStatement("select * from BulkData");
					ResultSet reultSet=apply.executeQuery();
					while(reultSet.next()){
						
						System.out.println(reultSet.getString("CityName")+"     "+reultSet.getString("Situation")+"     "+reultSet.getDouble("Temperature")+"     "+reultSet.getDouble("Humidity")+"     "+reultSet.getDouble("Pressure")+"   "+reultSet.getDate("Date"));
						
					}
					
				}catch(Exception e){
					
			
			}
		
		
	}
	
	/*public void getInformations(){
		
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
				
				
				String query = "delete from BulkData";
			      PreparedStatement preparedStmt = connection.prepareStatement(query);
			      

			      
			      preparedStmt.execute();
					
				}catch(Exception e){
			
			}
		
		
	}*/
}