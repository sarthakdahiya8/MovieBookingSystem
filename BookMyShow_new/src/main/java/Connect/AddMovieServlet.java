package Connect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Database.alldata;
import Database.maxid;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root",
                    "saini_dev79");
            
          
            maxid idGenerator = new maxid();

            // Get the next user ID
            int userId = idGenerator.movieid() + 1;
            
            
            
            String movieName = request.getParameter("movieName");
            String stars = request.getParameter("stars");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String slot912 = request.getParameter("slot912");
            String slot1215 = request.getParameter("slot1215");
            String slot1518 = request.getParameter("slot1518");
            String slot1821 = request.getParameter("slot1821");
            int goldPrice = Integer.parseInt(request.getParameter("goldPrice"));
            int silverPrice = Integer.parseInt(request.getParameter("silverPrice"));
            String imageName = request.getParameter("imageName");
            String language = request.getParameter("language");

            // Prepare the SQL statement
            String sql = "INSERT INTO TBLMOVIES (MOVIENAME, STARS, STARTDATE, ENDDATE, SLOT912, SLOT1215, SLOT1518, SLOT1821, GOLDPRICE, SILVERPRICE, IMAGENAME, LANGUAGE,MOVIEID) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            
            
            PreparedStatement stmt = con.prepareStatement(sql);
          
            stmt.setString(1, movieName);
            stmt.setString(2, stars);
            stmt.setString(3, startDate);
            stmt.setString(4, endDate);
            stmt.setString(5, slot912);
            stmt.setString(6, slot1215);
            stmt.setString(7, slot1518);
            stmt.setString(8, slot1821);
            stmt.setInt(9, goldPrice);
            stmt.setInt(10, silverPrice);
            stmt.setString(11, imageName);
            stmt.setString(12, language);
            stmt.setInt(13, userId);

            // Execute the query
            int rowsAffected = stmt.executeUpdate();
            
            List<String> slots = new ArrayList<>();
            if(slot912!=null) {
            	slots.add(slot912);
            }
            
            if(slot1215!=null) {
            	slots.add(slot1215);
            }
            if(slot1518!=null) {
            	slots.add(slot1518);
            }
            if(slot1821!=null) {
            	slots.add(slot1821);
            }
            
            
           
           
           
            
           
            
            
            
          
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            
            LocalDate sDate = LocalDate.parse(startDate, formatter);
            LocalDate eDate = LocalDate.parse(endDate, formatter);
            
            List<LocalDate> dates=new ArrayList<>();
            
            
            for(LocalDate date= sDate; !date.isAfter(eDate); date=date.plusDays(1)) {
               
            	
            	dates.add(date);
            	
            	
            	
            	
            }
            
            try {
				String Result=alldata.tblseatbooked(dates, slots, userId);
				System.out.println(Result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
           
            if (rowsAffected > 0) {
                // Movie added successfully
                response.sendRedirect("/BookMyShow_new/AdminHomePage.jsp");
            } else {
                // Handle the case when the movie insertion fails
                response.sendRedirect("/BookMyShow_new/error.jsp");
            }

        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}