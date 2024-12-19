import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class mentor1 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve form data
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String expertise = request.getParameter("expertise");
        String experience = request.getParameter("experience");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<body>");

            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql:///mentorconnect?useSSL=false", "root", "root");

            // Prepare SQL query using PreparedStatement to prevent SQL injection
            String sql = "INSERT INTO mentors (name, mobile, email, password, expertise, experience) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, mobile);
            pst.setString(3, email);
            pst.setString(4, password); // Consider hashing the password
            pst.setString(5, expertise);
            pst.setString(6, experience);

            // Execute update
            int rowsInserted = pst.executeUpdate();

            if (rowsInserted > 0) {
                // Redirect to success page
                response.sendRedirect("mentor2.html");
            } else {
                out.println("<h1>Data not inserted</h1>");
            }

            // Close connection
            con.close();
        } catch (ClassNotFoundException e) {
            out.println("<h3>Error: JDBC Driver not found.</h3>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("<h3>Error: Database connection failed.</h3>");
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
