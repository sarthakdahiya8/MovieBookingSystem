<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<
<%@ page import="java.sql.SQLException" %>
<%@ page import="Database.alldata" %>
<%@ page import="Database.alldata" %>

<%@ page import ="java.util.ArrayList"%>
<%@ page import= "java.util.Date"%>
<%@ page import ="java.util.List"%>
<%@ page import="Database.DateAndTime" %>
     
 <%             
 
 int movieId = Integer.parseInt(request.getParameter("selectedMovieId")); // Parse MovieID as an integer

      String movieName = request.getParameter("moviename");
   String date= request.getParameter("selectedMoviedate");
  
	 String slot= request.getParameter("selectedMovieslot"); 
	 
 int gPrice = Integer.parseInt(request.getParameter("goldprice"));
int  sPrice  =   Integer.parseInt(request.getParameter("silverprice"));		 
	


String prevGoldSeat = DateAndTime.getPrevGoldSeat(movieId, date, slot);
String prevSilverSeat = DateAndTime.getPrevSilverSeat(movieId, date, slot);

  String totalDate= prevGoldSeat + ","+ prevSilverSeat;



	 %>
   
    
    <link rel="stylesheet" href="Show.css">
    
  
    
    <title>Movie Seat Booking</title>
<h1>Book Your Seats Now</h1>
<h2> Welcome <%= session.getAttribute("userId") %> <%=movieId %></h2>
<h3>Date Of Show:<%=date %> And Slot Of Show: <%=slot %> </h3>
<h3>Gold Price:<%=gPrice %> And Silver Price: <%=sPrice %> </h3>



    <div class="movie-container">
      <label>Movie Name:</label>
      <select id="movie">
        <option value="10"><%=movieName %></optioSn>
       
      </select>
    </div>

    <ul class="showcase">
      <li>
        <div class="seat"></div>
        <small>N/A</small>
      </li>

      <li>
        <div class="seat selected"></div>
        <small>Selected</small>
      </li>

      <li>
        <div class="seat occupied"></div>
        <small>Occupied</small>
      </li>
    </ul>

    <div class="container">
      <div class="screen"></div>
      
         <form action="BookTicket"  method="get" id="seat-form"> 
        <div class="row">
            <div class="seat" data-value="g1">g1</div>
            <div class="seat" data-value="g2">g2</div>
            <div class="seat" data-value="g3">g3</div>
            <div class="seat" data-value="g4">g4</div>
            <div class="seat" data-value="g5">g5</div>
            <div class="seat" data-value="g6">g6</div>
            <div class="seat" data-value="g7">g7</div>
            <div class="seat" data-value="g8">g8</div>
        </div>
        <div class="row">
            <div class="seat" data-value="g9">g9</div>
            <div class="seat" data-value="g10">g10</div>
            <div class="seat" data-value="g11">g11</div>
            <div class="seat" data-value="g12">g12</div>
            <div class="seat" data-value="g13">g13</div>
            <div class="seat" data-value="g14">g14</div>
            <div class="seat" data-value="g15">g15</div>
            <div class="seat" data-value="g16">g16</div>
        </div>
        <div class="row">
            <div class="seat" data-value="g17">g17</div>
            <div class="seat" data-value="g18">g18</div>
            <div class="seat" data-value="g19">g19</div>
            <div class="seat" data-value="g20">g20</div>
            <div class="seat" data-value="g21">g21</div>
            <div class="seat" data-value="g22">g22</div>
            <div class="seat " data-value="g23">g23</div>
            <div class="seat " data-value="g24">g24</div>
        </div>
        <div class="row">
            <div class="seat" data-value="s1">s1</div>
            <div class="seat" data-value="s2">s2</div>
            <div class="seat" data-value="s3">s3</div>
            <div class="seat" data-value="s4">s4</div>
            <div class="seat" data-value="s5">s5</div>
            <div class="seat" data-value="s6">s6</div>
            <div class="seat" data-value="s7">s7</div>
            <div class="seat" data-value="s8">s8</div>
        </div>
        <div class="row">
            <div class="seat" data-value="s9">s9</div>
            <div class="seat" data-value="s10">s10</div>
            <div class="seat" data-value="s11">s11</div>
            <div class="seat" data-value="s12">s12</div>
            <div class="seat" data-value="s13">s13</div>
            <div class="seat" data-value="s14">s14</div>
            <div class="seat" data-value="s15">s15</div>
            <div class="seat" data-value="s16">s16</div>
        </div>
        <div class="row">
            <div class="seat" data-value="s17">s17</div>
            <div class="seat" data-value="s18">s18</div>
            <div class="seat" data-value="s19">s19</div>
            <div class="seat" data-value="s20">s20</div>
            <div class="seat" data-value="s21">s21</div>
            <div class="seat" data-value="s22">s22</div>
            <div class="seat " data-value="s23">s23</div>
      <div class="seat " data-value="s24">s24</div>
          </div>
          <form action="UserBookedTicket.jsp">
    <input type="hidden" name="selectedSeats[]" value="">
    <input type="hidden" name="dateBooked" value="<%=date%>">
    <input type="hidden" name="slotBooked" value="<%=slot%>">
      <input type="hidden" name="movieid" value="<%=movieId%>">
      <input type="hidden" name="goldPrice" value="<%=gPrice%>">
      <input type="hidden" name="silverPrice" value="<%=sPrice%>">
            
             
      
      <input type="hidden" name="userId" value="<%= (String)session.getAttribute("userId") %>">
    <button type="submit" id="submit-button"  style="margin-left: 42%;margin-top: 7%;">Book Now</button>
   </form>
    </form>
    
    </div>
 

  
    
     <script>
     
     // Get the totalDate string from the server
     var totalDate = '<%= totalDate %>';
     console.log(totalDate);

     // Split the totalDate string into an array of occupied seat values
     var occupiedSeats = totalDate.split(',');

     // Select all seats
     var seats = document.querySelectorAll('.seat');

     // Loop through the seats and disable the occupied ones
     seats.forEach(function(seat) {
         if (occupiedSeats.includes(seat.getAttribute('data-value'))) {
             seat.classList.add('occupied');
             seat.removeEventListener('click', toggleSeat);
         } else {
             seat.addEventListener('click', toggleSeat);
         }
     });

     function toggleSeat() {
         this.classList.toggle('selected');
     }
     
     
     

     
     
     document.getElementById('submit-button').addEventListener('click', function() {
    	    console.log("inside the submit");
    	    const selectedSeats = document.querySelectorAll('.row .seat.selected');
    	    const selectedSeatValues = Array.from(selectedSeats).map(seat => seat.getAttribute('data-value'));

    	    // Join the selected seat values into a single string
    	    const selectedSeatsString = selectedSeatValues.join(',');

    	    // Set the value of the hidden input field
    	    const hiddenInput = document.querySelector('input[name="selectedSeats[]"]');
    	    hiddenInput.value = selectedSeatsString;

    	    document.getElementById('seat-form').submit();
    	});
     
     
     
</script>
    
    <script src="FinalTicket.js"></script>