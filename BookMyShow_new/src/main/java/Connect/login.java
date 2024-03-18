package Connect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmyshow", "root", "saini_dev79");

            PreparedStatement stmt = con.prepareStatement("select * from userregister where UEID=? and UPWD =?");

            String uemail, upwd;
            uemail = request.getParameter("name");
            upwd = request.getParameter("pass");

            HttpSession session = request.getSession();
            session.setAttribute("UEID", uemail);
            session.setAttribute("UPWD", upwd);

            stmt.setString(1, uemail);
            stmt.setString(2, upwd);

            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {
                // Set the user ID as a session attribute
                int userId = rset.getInt("USERID");
                session.setAttribute("userId", String.valueOf(userId));

                if ("admin@gmail.com".equals(uemail) && "admin".equals(upwd)) {
                    // Redirect to AdminHomePage.jsp if admin credentials
                    response.sendRedirect("/BookMyShow_new/AdminHomePage.jsp");
                } else {
                    // Redirect to Homepg.jsp for regular users
                    response.sendRedirect("/BookMyShow_new/Homepg.jsp");
                }
            } else {
                response.sendRedirect("/BookMyShow_new/register.jsp");
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Handle the exception or log it
            e.printStackTrace();
        }
    }

    public void destroy() {
        // You don't need to close the connection and statement here
    }
}
