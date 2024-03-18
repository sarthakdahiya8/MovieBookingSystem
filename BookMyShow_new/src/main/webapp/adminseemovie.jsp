<%@ page import="java.sql.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminnavbar.jsp" %>
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
        String query = "SELECT * FROM TBLMOVIES";
        ResultSet resultSet = stmt.executeQuery(query);
        
       
%>

    <h2>Movies List</h2>
    <table>
        <tr>
            <th>Movie ID</th>
            <th>Movie Name</th>
            <th>Stars</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Slot 9-12</th>
            <th>Slot 12-15</th>
            <th>Slot 15-18</th>
            <th>Slot 18-21</th>
            <th>Gold Price</th>
            <th>Silver Price</th>
            <th>Image Name</th>
            <th>Language</th>
        </tr>

<%
        while (resultSet.next()) {
%>
            <tr>
                <td><%= resultSet.getInt("MOVIEID") %></td>
                <td><%= resultSet.getString("MOVIENAME") %></td>
                <td><%= resultSet.getString("STARS") %></td>
                <td><%= resultSet.getDate("STARTDATE") %></td>
                <td><%= resultSet.getDate("ENDDATE") %></td>
                <td><%= resultSet.getString("SLOT912") %></td>
                <td><%= resultSet.getString("SLOT1215") %></td>
                <td><%= resultSet.getString("SLOT1518") %></td>
                <td><%= resultSet.getString("SLOT1821") %></td>
                <td><%= resultSet.getInt("GOLDPRICE") %></td>
                <td><%= resultSet.getInt("SILVERPRICE") %></td>
                <td><%= resultSet.getString("IMAGENAME") %></td>
                <td><%= resultSet.getString("LANGUAGE") %></td>
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
