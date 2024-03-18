package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateAndTime {

    public static String getPrevGoldSeat(int movieId, String date, String slot) {
        // Placeholder method, implement your logic to get the previous gold seat for a given movie, date, and slot
        // For example, querying the database
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
            String query = "SELECT * FROM TBLSEATSBOOKED WHERE movieId=? AND DATEOFSHOW=? AND SLOTOFSHOW=?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, movieId);
                stmt.setString(2, date);
                stmt.setString(3, slot);

                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getString("GOLDSEATSBOOKED");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return ""; // Default value or handle the case when no seat is found
    }

    public static String getPrevSilverSeat(int movieId, String date, String slot) {
        // Placeholder method, implement your logic to get the previous silver seat for a given movie, date, and slot
        // For example, querying the database
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
           String query = "SELECT * FROM TBLSEATSBOOKED WHERE movieId=? AND DATEOFSHOW=? AND SLOTOFSHOW=?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, movieId);
                stmt.setString(2, date);
                stmt.setString(3, slot);

                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getString("SILVERSEATSBOOKED");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return ""; // Default value or handle the case when no seat is found
    }

	public static int getGoldAvailSeats(int movieId, String dateBooked, String slotBooked) {
		// For example, querying the database
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
        	String query = "SELECT * FROM TBLSEATSBOOKED WHERE movieId=? AND DATEOFSHOW=? AND SLOTOFSHOW=?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, movieId);
                stmt.setString(2, dateBooked);
                stmt.setString(3, slotBooked);

                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("GOLDAVAILSEATS");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int getSilverAvailSeats(int movieId, String dateBooked, String slotBooked) {
		try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
        	String query = "SELECT * FROM TBLSEATSBOOKED WHERE movieId=? AND DATEOFSHOW=? AND SLOTOFSHOW=?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, movieId);
                stmt.setString(2, dateBooked);
                stmt.setString(3, slotBooked);

                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("SILVERAVAILSEATS");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void updatingtblseatsbooked(int movieId, String dateBooked, String slotBooked,
			String seatsStartingWithG, String seatsStartingWithS, int remainGoldSeat, int remainSilverSeat) {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
        	 String updateQuery = "UPDATE TBLSEATSBOOKED SET GOLDSEATSBOOKED=?, SILVERSEATSBOOKED=?, GOLDAVAILSEATS=?, SILVERAVAILSEATS=? " +
                                "WHERE MOVIEID=? AND DATEOFSHOW=? AND SLOTOFSHOW=?";
            
            // Create the prepared statement
            PreparedStatement stmt = con.prepareStatement(updateQuery);

            // Set the parameters
            stmt.setString(1, seatsStartingWithG);
            stmt.setString(2, seatsStartingWithS);
            stmt.setInt(3, remainGoldSeat);
            stmt.setInt(4, remainSilverSeat);
            stmt.setInt(5, movieId);
            stmt.setString(6, dateBooked);
            stmt.setString(7, slotBooked);

            // Execute the update
            int rowsUpdated = stmt.executeUpdate();
           
            // Check if the update was successful
            if (rowsUpdated > 0) {
                System.out.println("Seats information updated successfully.");
            } else {
                System.out.println("Seats information update failed.");
            }
            
            // Close the resources
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static String getUserName(String userId) {
		

        if (userId != null) {
            try {
                // Load the JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // Connect to the database
                String url = "jdbc:mysql://localhost:3306/bookmyshow";
                String username = "root";
                String password = "saini_dev79";
                Connection connection = DriverManager.getConnection(url, username, password);

                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM userregister WHERE USERID = ?");
                preparedStatement.setString(1, userId);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Retrieve user information from the result set
                    String userName = resultSet.getString("UNAM");
                    String userEmail = resultSet.getString("UEID");
                    String userMobile = resultSet.getString("UMOB");
                    int userAge = resultSet.getInt("AGE");
                    String userGender = resultSet.getString("GENDER");
                    return userName;
                }
            }catch(Exception e) {
            	
            }
        }
		return null;
	}
	
	public static int getmaxid() {
        int maxId = 0;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
        	 String query = "SELECT MAX(ID) FROM TBLTICKET";
            
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    maxId = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly in a real-world scenario
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly in a real-world scenario
        }

        return maxId+1;
    }
	
	
	public static int getMaxTicketId() {
		int maxId = 0;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
        	 String query = "SELECT MAX(ID) FROM TBLTICKET";
            
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    maxId = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly in a real-world scenario
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly in a real-world scenario
        }

        return maxId+2;
    }
    

	public static void inertingIntoTblTicket(int maxId, int movieId, int maxTicketId, String userName, String uemail,
			String seatsStartingWithG, String seatsStartingWithS, String dateBooked, String slotBooked,
			int totalGoldAndSilver) {
		try {
            // Replace the following with your database connection logic
			Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
        	String query = "INSERT INTO TBLTICKET (ID, MOVIEID, TICKETID, UNM, uid, GOLDSEATSBOOKED, SILVERSEATSBOOKED, DATEOFSHOW, SLOT, TOTALPRICE,MOB) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, maxId);
                preparedStatement.setInt(2, movieId);
                preparedStatement.setInt(3, maxTicketId);
                preparedStatement.setString(4, userName);
                preparedStatement.setString(5, uemail);
                preparedStatement.setString(6, seatsStartingWithG);
                preparedStatement.setString(7, seatsStartingWithS);
                preparedStatement.setString(8, dateBooked);
                preparedStatement.setString(9, slotBooked);
                preparedStatement.setInt(10, totalGoldAndSilver);
                preparedStatement.setString(11, "none"); // Replace with actual mobile data


                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly in a real-world scenario
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly in a real-world scenario
        }
		
	}
}
        
