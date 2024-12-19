import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user id from session (assuming user is logged in)
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            // Connect to the database and retrieve user profile data
            try {
                // Load MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish the database connection
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentorconnect", "root", "root")) {

                    // Create SQL query to get the user details
                    String query = "SELECT * FROM users WHERE id = ?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setInt(1, userId);

                    // Execute the query
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        // Set the retrieved user details in request attributes
                        request.setAttribute("Name", rs.getString("Name"));
                        request.setAttribute("mobileNumber", rs.getString("mobileNumber"));
                        request.setAttribute("email", rs.getString("email"));
                        request.setAttribute("gender", rs.getString("gender"));
                        request.setAttribute("age", rs.getInt("age"));
                        request.setAttribute("fieldOfInterest", rs.getString("fieldOfInterest"));

                        // Forward the request to the profile page (myProfile.jsp)
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/myProfile.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // If no user found with the given ID, show error
                        response.sendRedirect("error.jsp");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendRedirect("error.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            // If no user is logged in, redirect to login page
            response.sendRedirect("login.html");
        }
    }
}
