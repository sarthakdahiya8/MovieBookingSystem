package Connect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DateAndTime;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet("/BookTicket")
public class BookTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public BookTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String dateBooked, slotBooked ,uemail;
		int movieId;
		dateBooked = request.getParameter("dateBooked");
		slotBooked = request.getParameter("slotBooked");

		uemail = request.getParameter("userId");
		
		String movieid2 = request.getParameter("movieid");
		movieId = Integer.parseInt(movieid2);
		
		String gPrice = request.getParameter("goldPrice");
		int goldPrice = Integer.parseInt(gPrice);
		
		String sPrice = request.getParameter("silverPrice");
	    int silverPrice = Integer.parseInt(sPrice);
	    
		System.out.println("Gold Price is:"+goldPrice+ "And Silver Price is "+ silverPrice);

		System.out.println("date is "+ dateBooked + "slot is "+slotBooked +"and movie id is "+movieId);
		
		
		
		
		String[] selectedSeats = request.getParameterValues("selectedSeats[]");
		
		String joinedSeats = String.join(",", selectedSeats);
		System.out.println(joinedSeats);


String seatsStartingWithS = "";
String seatsStartingWithG = "";

String[] seatValues = joinedSeats.split(",");

for (String seat : seatValues) {
    if (seat.startsWith("s")) {
        if (!seatsStartingWithS.isEmpty()) {
            seatsStartingWithS += ",";
        }
        seatsStartingWithS += seat;
    } else if (seat.startsWith("g")) {
        if (!seatsStartingWithG.isEmpty()) {
            seatsStartingWithG += ",";
        }
        seatsStartingWithG += seat;
    }
}

System.out.println("Seats starting with 's': " + seatsStartingWithS);
System.out.println("Seats starting with 'g': " + seatsStartingWithG);

// ******* calculating the gold and silver seat price***********

String[] seats = joinedSeats.split(",");
int countS = 0;
int countG = 0;

for (String seat : seats) {
    if (seat.startsWith("s")) {
        countS++;
    } else if (seat.startsWith("g")) {
        countG++;
    }
}



System.out.println("Count of seats starting with 's': " + countS);
System.out.println("Count of seats starting with 'g': " + countG);

int totalGoldPrice = (countG*goldPrice); 
int totalSilverPrice = (countS*silverPrice);

int totalGoldAndSilver = totalGoldPrice + totalSilverPrice;

System.out.println("Total gold Price:" +totalGoldPrice);

System.out.println("Total Silver Price:" +totalSilverPrice);

System.out.println("Total gold And Silver Price:" +totalGoldAndSilver);

// ******************************


//  **************  geting the aviallable gold and silver seats***********



int goldAvailSeats =DateAndTime.getGoldAvailSeats(movieId, dateBooked, slotBooked);
System.out.println("Available gold seats: " + goldAvailSeats);


int silverAvailSeats =DateAndTime.getSilverAvailSeats(movieId, dateBooked, slotBooked);
System.out.println("Available Silver seats: " + silverAvailSeats);



String prevGoldSeat = DateAndTime.getPrevGoldSeat(movieId, dateBooked, slotBooked);
String afterGoldSeat;

if(prevGoldSeat==null) {
	afterGoldSeat = seatsStartingWithG;
	
}else {
	afterGoldSeat = seatsStartingWithG + "," + prevGoldSeat;
	
}
String prevSilverSeat = DateAndTime.getPrevSilverSeat(movieId, dateBooked, slotBooked);
String afterSilverSeat;
if(prevSilverSeat==null) {
	afterSilverSeat = seatsStartingWithS;
	 
	
}else {
afterSilverSeat = seatsStartingWithS + "," + prevSilverSeat;	

}
//
String totalInsert = afterGoldSeat + "," + afterSilverSeat;

String[] seat1 = totalInsert.split(",");
int countS1 = 0;
int countG1 = 0;

for (String seat : seat1) {
    if (seat.startsWith("s")) {
        countS1++;
    } else if (seat.startsWith("g")) {
        countG1++;
    }
}



int remainGoldSeat = (24-countG1); 
int remainSilverSeat = (24-countS1);;

System.out.println(" "+remainGoldSeat+ "gold seats " + "silver seats" + remainSilverSeat);


System.out.println(prevGoldSeat+"the gold prevvvvvvvvvvvvv");
if(prevGoldSeat==null && prevSilverSeat==null) {
	DateAndTime.updatingtblseatsbooked(movieId, dateBooked, slotBooked, seatsStartingWithG, seatsStartingWithS, remainGoldSeat, remainSilverSeat);
	
}
else {
	DateAndTime.updatingtblseatsbooked(movieId, dateBooked, slotBooked, afterGoldSeat, afterSilverSeat, remainGoldSeat, remainSilverSeat);
	
	System.out.println("inside else statemnt of goldddddddddddd seat");
}










int maxId1 =  DateAndTime.getmaxid();
int maxTicketId2 = DateAndTime.getMaxTicketId();
int maxId = maxId1+1;
int maxTicketId= maxTicketId2+1;
out.println(maxId1 + maxTicketId2+maxId + maxTicketId);
String userName=DateAndTime.getUserName(uemail);

System.out.println("user name is "+ userName);


 DateAndTime.inertingIntoTblTicket(maxId, movieId, maxTicketId, userName, uemail, 
		seatsStartingWithG, seatsStartingWithS, dateBooked, slotBooked, totalGoldAndSilver);




 request.setAttribute("movieId", movieId);
 request.setAttribute("dateBooked", dateBooked);
 request.setAttribute("slotBooked", slotBooked);
 request.setAttribute("goldPrice", goldPrice);
 request.setAttribute("silverPrice", silverPrice);
 


 request.getRequestDispatcher("UserBookedTicket.jsp").forward(request, response);


	}
	


	

}