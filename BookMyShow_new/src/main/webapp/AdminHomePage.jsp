<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="adminnavbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Home Page</title>
    <!-- Add any additional styling or linking to CSS files here -->
</head>
<body>
    <center>
        <h1>Welcome Admin!</h1>
        <h2>Add Movie</h2>
        <form action="AddMovieServlet" method="post">
            <label for="movieName">Movie Name:</label>
            <input type="text" id="movieName" name="movieName" required><br><br>

            <label for="stars">Stars:</label>
            <input type="text" id="stars" name="stars" required><br><br>

            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" required><br><br>

            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" required><br><br>

            <!-- Add slots and other movie details as per your requirement -->
           <label for="slot912">Slot 9-12:</label>
			<input type="checkbox" id="slot912" name="slot912" value="slot912" ><br><br>

<label for="slot1215">Slot 12-15:</label>
<input type="checkbox" id="slot1215" name="slot1215" value="slot1215" ><br><br>

<label for="slot1518">Slot 15-18:</label>
<input type="checkbox" id="slot1518" name="slot1518" value="slot1518" ><br><br>

<label for="slot1821">Slot 18-21:</label>
<input type="checkbox" id="slot1821" name="slot1821" value="slot1821" ><br><br>

            <label for="goldPrice">Gold Price:</label>
            <input type="number" id="goldPrice" name="goldPrice" required><br><br>

            <label for="silverPrice">Silver Price:</label>
            <input type="number" id="silverPrice" name="silverPrice" required><br><br>

            <label for="imageName">Image Name:</label>
            <input type="text" id="imageName" name="imageName" required><br><br>

            <label for="language">Language:</label>
            <input type="text" id="language" name="language" required><br><br>

            <button type="submit">Add Movie</button>
        </form>
    </center>
</body>
</html>
