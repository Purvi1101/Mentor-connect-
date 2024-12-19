import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class student extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetching form data
        String Name = request.getParameter("Name");
        String mobileNumber = request.getParameter("mobileNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String fieldOfInterest = request.getParameter("fieldOfInterest");

        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection con = DriverManager.getConnection("jdbc:mysql:///studentt?useSSL=false", "root", "root")) {

                    // Create SQL query to insert data into the students table
                    String query = "INSERT INTO student(Name, mobileNumber, email, password, gender, age, fieldOfInterest) VALUES (?, ?, ?, ?, ?, ?, ?)";

                    // Prepare the statement to insert the form data
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, Name);
                    pst.setString(2, mobileNumber);
                    pst.setString(3, email);
                    pst.setString(4, password);
                    pst.setString(5, gender);
                    pst.setString(6, age);
                    pst.setString(7, fieldOfInterest);

                    // Execute update query
                    int rowsInserted = pst.executeUpdate();

                    if (rowsInserted > 0) {
                        out.println("<h2>Registration successful!</h2>");
                    } else {
                        out.println("<h2>Registration failed. Please try again.</h2>");
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                out.println("<h2>Error: " + e.getMessage() + "</h2>");
            }
            out.println("</body></html>");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Optional: Handle GET request if needed
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>This servlet handles only POST requests. Please submit the form.</h2>");
        out.println("</body></html>");
    }
}
