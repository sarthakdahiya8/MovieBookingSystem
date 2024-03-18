<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="Database.alldata" %>

<%@ page import="Database.DateAndTime" %>

<%@ page import ="java.util.ArrayList" %> 
<%@ page import ="java.util.List" %>


<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Select Seats</title>
   
</head>
<body>
    <h2>Select Seats</h2>

    

        <div class="seat-container">
            
            <h3>Movie Information:</h3>
            <%
            
            
            //String userId = (String) session.getAttribute("userId");

                // Retrieve movie information from the previous page
                String selectedMovie = request.getParameter("selectedMovie");
              
                int movieId = Integer.parseInt(request.getParameter("selectedMovieId"));
                int goldprice=Integer.parseInt(request.getParameter("goldprice"));
                int silverprice=Integer.parseInt(request.getParameter("silverprice"));
                // Parse MovieID as an integer
               // int goldprice=Integer.parseInt(request.getParameter("goldprice"));
                //int silverprice=Integer.parseInt(request.getParameter("silverprice"));
               List<String> ss = alldata.getinfo(movieId);
                
                
               List<String> slots = new ArrayList<>();
                try {
        			Class.forName("com.mysql.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root",
        	                   "saini_dev79");
        				
        				
        				String query = "SELECT * FROM TBLMOVIES WHERE MovieID=?";
        	           PreparedStatement stmt = con.prepareStatement(query);
        	           stmt.setInt(1, movieId);
        	           

        	           
        	           ResultSet rset=stmt.executeQuery();
        	           while(rset.next()) {
        	        	   
        	        	   String SLOT912=rset.getString("SLOT912");
        	               String SLOT1215=rset.getString("SLOT1215");
        	               String SLOT1518=rset.getString("SLOT1518");
        	               String SLOT1821=rset.getString("SLOT1821");
        	               
        	              
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
        	               
        	               
        	               
        	               
        	        	   
        	             
        	        	   
        	           }
        	           
        		} catch (ClassNotFoundException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            %>
            <p>Selected Movie: <%= selectedMovie %></p>
            
    </div>
  
      
	
	
 	    <form action="ticketbooking.jsp" method="post">
 	    
 	    			
 	     <input type="hidden" name="selectedMovieId" value="<%=movieId %>">
 	      	     <input type="hidden" name="goldprice" value="<%=goldprice %>">
 	     
 	      	     <input type="hidden" name="silverprice" value="<%=silverprice %>">
 	      	       <input type="hidden" name="moviename" value="<%=selectedMovie %>">
 	      	      
 	      	     
 	      	     
 	     
 	               
  <% 
        try {
            int index = 0; // Added an index variable to make IDs unique
            for (String se : ss) {
            %>
            <label>
            <input type="checkbox" id="slot" name="selectedMoviedate" value="<%=se%>" />
            <%=se %>
            </label>
            <br>
                    
            <% 
            	
                for (String slotss : slots) {
        %>
                    <label>
            <input type="checkbox" id="slot" name="selectedMovieslot" value="<%=slotss%>" />
            <%=slotss %>
            </label>
                   <br>
                   
        <%
                    index++;
                }
        %>
           <br>    
        <% 
       
	}
            }
        catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
        }
        %>
  <center>
        <input type="submit" value="Confirm Seats">
    </center>

			 
           
        </form>
	
	

</body>
</html>
