<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Booked Tickets</title>
</head>
<body>
<%
String uid = (String) session.getAttribute("userId");

// Method to get movie name based on movie ID

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
    String query = "SELECT * FROM TBLTICKET WHERE uid=?";
    
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, uid);
    ResultSet rs = pstmt.executeQuery();
%>

<h2>Booked Tickets</h2>
<table border="1">
    <tr>
        <th>Movie ID</th>
        <th>Ticket ID</th>
        <th>User Name</th>
        <th>Movie Name</th>
        <th>Gold Seats Booked</th>
        <th>Silver Seats Booked</th>
        <th>Date of Show</th>
        <th>Slot</th>
    </tr>

<%
    while (rs.next()) {
        int movieId = rs.getInt("MOVIEID");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
            String query1 = "SELECT MOVIENAME FROM TBLMOVIES WHERE MOVIEID=?";
            
            PreparedStatement pstmt1 = conn.prepareStatement(query1);
            pstmt1.setInt(1, movieId);
            ResultSet rst = pstmt1.executeQuery();

            if (rst.next()) {
                String movieName = rst.getString("MOVIENAME");
%>
                <tr>
                    <td><%= rs.getInt("MOVIEID") %></td>
                    <td><%= rs.getInt("TICKETID") %></td>
                    <td><%= rs.getString("UNM") %></td>
                    <td><%= movieName %></td>
                    <td><%= rs.getString("GOLDSEATSBOOKED") %></td>
                    <td><%= rs.getString("SILVERSEATSBOOKED") %></td>
                    <td><%= rs.getDate("DATEOFSHOW") %></td>
                    <td><%= rs.getString("SLOT") %></td>
                </tr>
<%
            }
            
            // Close resources
            rst.close();
            pstmt1.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>
</table>

<%
    // Close the resources
    rs.close();
    pstmt.close();
    con.close();
} catch (Exception e) {
    out.println("<p>Error: " + e.getMessage() + "</p>");
    e.printStackTrace();
}
%>
</body>
</html>
