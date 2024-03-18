<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.text.SimpleDateFormat" %>    
    <%@ include file="adminnavbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>See Movies</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<%
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");

        Statement stmt = con.createStatement();
        String query = "SELECT * FROM TBLTICKET";
        ResultSet resultSet = stmt.executeQuery(query);
        
       
%>

    <h2>Movies List</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Movie ID</th>
            <th>Ticket ID</th>
            <th>User name </th>
            <th>USer id</th>
            <th>Gold seats booked</th>
            <th>Silver seats booked</th>
            <th>Date of show</th>
            <th>Slot</th>
            <th>Total</th>
            <th>Mobile no</th>
            
        </tr>

<%
        while (resultSet.next()) {
%>
            <tr>
                <td><%= resultSet.getInt("ID") %></td>
                <td><%= resultSet.getString("MOVIEID") %></td>
                <td><%= resultSet.getString("TICKETID") %></td>
                <td><%= resultSet.getString("UNM") %></td>
                <td><%= resultSet.getString("uid") %></td>
                <td><%= resultSet.getString("GOLDSEATSBOOKED") %></td>
                <td><%= resultSet.getString("SILVERSEATSBOOKED") %></td>
                <td><%= resultSet.getDate("DATEOFSHOW") %></td>
                <td><%= resultSet.getString("SLOT") %></td>
                <td><%= resultSet.getInt("TOTALPRICE") %></td>
                <td><%= resultSet.getString("MOB") %></td>
                
            </tr>
<%
        }
%>
    </table>

<%
        // Close resources
        resultSet.close();
        stmt.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

</body>
</html>
