import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/ScheduleSessionServlet")
public class ss extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionName = request.getParameter("sessionName");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String description = request.getParameter("description");
        String mentorId = (String) request.getSession().getAttribute("userId"); // Get mentor ID from session

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentor_system", "root", "password");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO sessions (mentor_id, session_name, session_date, session_time, duration, description) VALUES (?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, mentorId);
            stmt.setString(2, sessionName);
            stmt.setString(3, date);
            stmt.setString(4, time);
            stmt.setInt(5, duration);
            stmt.setString(6, description);

            stmt.executeUpdate();
            response.sendRedirect("mentor_dashboard.jsp"); // Redirect back to dashboard
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error scheduling session: " + e.getMessage());
        }
    }
}
