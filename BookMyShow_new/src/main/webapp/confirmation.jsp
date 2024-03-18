<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
    <h2>Confirmation</h2>
    <form action="bookedticket.jsp" method="post">
        <div>
            <h3>Thank you for your booking!</h3>
            <p>Details of your booking:</p>

            <%
                // Retrieve information from the request
                String selectedMovie = request.getParameter("selectedMovie");
                String selectedSlot = request.getParameter("selectedSlot");
                String selectedSeats = request.getParameter("selectedSeats");

                // Split selected seats and calculate total amount
                String[] selectedSeatsArray = selectedSeats.split(",");
                int totalAmount = 0;

                try {
                    // Load the database driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Connect to the database
                    String Url = "jdbc:mysql://localhost:3306/bookmyshow";
                    String username = "root";
                    String password = "saini_dev79";
                    Connection connection = DriverManager.getConnection(Url, username, password);

                    // Update seat booking status in the database
                    for (String seat : selectedSeatsArray) {
                        int seatId = Integer.parseInt(seat.split(" ")[1]); // Assuming the seat name format is "Seat {ID}"

                        // Update the seat status to booked
                        updateSeatStatus(connection, seatId, true);

                        int seatPrice = getSeatPrice(connection, seatId);
                        totalAmount += seatPrice;
                    }

                    // Close the database connection
                    connection.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>

            <p>Movie: <%= selectedMovie %></p>
            <p>Slot: <%= selectedSlot %></p>
            <p>Selected Seats: <%= selectedSeats %></p>
            <p>Total Amount: <%= totalAmount %></p>
        </div>
    </form>

<%! 
    // Helper method to update seat booking status
    void updateSeatStatus(Connection connection, int seatId, boolean isBooked) throws SQLException {
        String query = "UPDATE SeatBooking SET is_booked = ? WHERE seat_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, isBooked);
            preparedStatement.setInt(2, seatId);
            preparedStatement.executeUpdate();
        }
    }

    // Helper method to get seat price
    int getSeatPrice(Connection connection, int seatId) throws SQLException {
        String query = "SELECT price FROM SeatBooking WHERE seat_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, seatId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("price");
                } else {
                    // If seat price not found, return a default value or handle accordingly
                    return 0; // Default to 0 if seat price not found
                }
            }
        }
    }
%>


</body>
</html>
