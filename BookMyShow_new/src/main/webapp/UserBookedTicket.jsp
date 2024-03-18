<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<%@ page import="java.sql.SQLException" %>
<%@ page import="Database.alldata" %>
<%@ page import="Database.alldata" %>

<%@ page import ="java.util.ArrayList"%>
<%@ page import= "java.util.Date"%>
<%@ page import ="java.util.List"%>
<%@ page import="Database.DateAndTime" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Show Information</title>
</head>
<body>

<h2>Selected Seats:</h2>
<%


String uid = (String) session.getAttribute("userId");

    String[] selectedSeats = request.getParameterValues("selectedSeats[]");
    if (selectedSeats != null) {
        for (String seat : selectedSeats) {
            out.println("<p>" + seat + "</p>");
        }
    }
    
    String dateofshow=request.getParameter("dateBooked");
    int movieId = Integer.parseInt(request.getParameter("movieid")); 
    String slotBooked=request.getParameter("slotBooked");
    //int ticketId = Integer.parseInt(request.getParameter("ticketId"));

%>

<h2>Date Booked:</h2>
<p><%= request.getParameter("dateBooked") %></p>

<h2>Slot Booked:</h2>
<p><%= request.getParameter("slotBooked") %></p>

<h2>Movie ID:</h2>
<p><%= request.getParameter("movieid") %></p>

<h2>Gold Price:</h2>
<p><%= request.getParameter("goldPrice") %></p>

<h2>Silver Price:</h2>
<p><%= request.getParameter("silverPrice") %></p>

<h2>Total price</h2>
<%
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");
    String query = "SELECT TOTALPRICE FROM TBLTICKET WHERE MOVIEID=? AND uid=? AND DATEOFSHOW=? AND SLOT=? ORDER BY TICKETID DESC LIMIT 1";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, movieId);
        stmt.setString(2, uid);
        stmt.setString(3, dateofshow);
        stmt.setString(4, slotBooked);
       //stmt.setInt(5,ticketId);

        try (ResultSet rset = stmt.executeQuery()) {
            while(rset.next()) {
                int totalprice = rset.getInt("TOTALPRICE");
%>
                <p><%= totalprice %></p>
<%
            } 
        }
    }
} catch (Exception e) {
    out.println("<p>Error: " + e.getMessage() + "</p>");
}
%>

</body>
</html>
