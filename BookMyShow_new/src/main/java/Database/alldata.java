package Database;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class alldata {
	 private Connection connection;
	    
	    public alldata() {
	        try {
	            
	        	Class.forName("com.mysql.jdbc.Driver");
	           
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void connect(String url, String username, String password) {
	        try {
	          
	            connection = DriverManager.getConnection(url, username, password);
	            System.out.println("Connected to sql!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public ResultSet executeQuery(String query) {
	        ResultSet resultSet = null;
	        try {
	            Statement statement = connection.createStatement();
	            resultSet = statement.executeQuery(query);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return resultSet;
	    }
	    
	    public Connection getConnection() {
	        return connection;
	    }
	    
	    public void disconnect() {
	        try {
	            if (connection != null && !connection.isClosed()) {
	                connection.close();
	                System.out.println("Disconnected from sql database!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	    
	   public static String tblseatbooked(List<LocalDate> dates,List<String> slots,int movieid) throws SQLException {
		   try {
			   Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root",
                    "saini_dev79");
         
			
            int ttlgoldseats=20;
            int ttlsilverseats=20;

            String sql2="Insert into TBLSEATSBOOKED (MOVIEID,DATEOFSHOW,SLOTOFSHOW, TTLGOLDSEATS,TTLSILVERSEATS) values (? ,? ,? ,? ,?)";
            
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            
            for(LocalDate d:dates) {
            		for(String s:slots) {
                		System.out.println(d);

        		System.out.println(s);
        	stmt2.setInt(1, movieid);
        	
            stmt2.setString(2,d.toString());
        	stmt2.setString(3, s);
        	stmt2.setInt(4,ttlgoldseats );
        	stmt2.setInt(5, ttlsilverseats);
            stmt2.executeUpdate();

        		
        	}
        
            }
            
            return "done";
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "not done";
          
           
		   
	   }
	
	   
	   public static List<String> getinfo(int movieid){
		   try {
			   Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root",
                    "saini_dev79");
			
			
			String query = "SELECT * FROM TBLMOVIES WHERE MovieID=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, movieid);

            
            ResultSet rset=stmt.executeQuery();
           
            while(rset.next()) {
            String sdate=rset.getString("STARTDATE");
            String edate=rset.getString("ENDDATE");
            
            System.out.println("one"+sdate);
            System.out.println(edate);

            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<String> dates=new ArrayList<>();
            LocalDate sDate = LocalDate.parse(sdate, formatter);
            LocalDate eDate = LocalDate.parse(edate, formatter);
            LocalDate today = LocalDate.now();

            for(LocalDate date= today; !date.isAfter(eDate); date=date.plusDays(1)) {
               
            	
            	dates.add(date.toString());
            	
            	System.out.println(dates);
            	
            	
            }
            return dates;
            }
		   }catch(Exception e) {
			   
			   
		   }
		   System.out.println("error");
		   return null;
		
		   
	   }
	   
	   
	   public static List<String> getslots(int movieid){
		   
		   
		   try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root",
	                   "saini_dev79");
				
				
				String query = "SELECT * FROM TBLMOVIES WHERE MovieID=?";
	           PreparedStatement stmt = con.prepareStatement(query);
	           stmt.setInt(1, movieid);

	           
	           ResultSet rset=stmt.executeQuery();
	           while(rset.next()) {
	        	   
	        	   String SLOT912=rset.getString("SLOT912");
	               String SLOT1215=rset.getString("SLOT1215");
	               String SLOT1518=rset.getString("SLOT1518");
	               String SLOT1821=rset.getString("SLOT1821");
	               
	               List<String> slots = new ArrayList<>();
	               if(SLOT912!=null) {
	               	slots.add(SLOT912);
	               }
	               
	               if(SLOT1215!=null) {
	               	slots.add(SLOT1215);
	               }
	               if(SLOT1518!=null) {
	               	slots.add(SLOT1518);
	               }
	               if(SLOT1821!=null) {
	               	slots.add(SLOT1821);
	               }
	               
	               for(String s:slots) {
	            	   slots.add(s);
	               }
	               
	        	   
	               return slots;
	        	   
	           }
	           
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		   
		return null;
		   
	   }

}
