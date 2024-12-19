import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet("/FetchScheduledSessionsServlet")
public class ss2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mentorId = (String) request.getSession().getAttribute("userId"); // Get mentor ID from session

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentor_system", "root", "password");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sessions WHERE mentor_id = ?")) {

            stmt.setString(1, mentorId);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Session> sessions = new ArrayList<>();
            while (rs.next()) {
                sessions.add(new Session(
                    rs.getString("session_name"),
                    rs.getString("session_date"),
                    rs.getString("session_time"),
                    rs.getInt("duration"),
                    rs.getString("description")
                ));
            }

            String json = new Gson().toJson(sessions);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching sessions: " + e.getMessage());
        }
    }

    // Inner class for session representation
    class Session {
        String sessionName, date, time, description;
        int duration;

        public Session(String sessionName, String date, String time, int duration, String description) {
            this.sessionName = sessionName;
            this.date = date;
            this.time = time;
            this.duration = duration;
            this.description = description;
        }
    }
}
