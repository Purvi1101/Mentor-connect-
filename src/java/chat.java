import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import com.google.gson.*;

// Servlet for all chat-related operations
@WebServlet("/chat")
public class chat extends HttpServlet {
    
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat_app";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("getChatUsers".equals(action)) {
            getChatUsers(response);
        } else if ("fetchMessages".equals(action)) {
            String senderId = request.getParameter("senderId");
            String receiverId = request.getParameter("receiverId");
            fetchMessages(senderId, receiverId, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("sendMessage".equals(action)) {
            String senderId = request.getParameter("senderId");
            String receiverId = request.getParameter("receiverId");
            String messageText = request.getParameter("messageText");
            sendMessage(senderId, receiverId, messageText, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    // Retrieve the list of mentees (mocked here, replace with database query)
    private void getChatUsers(HttpServletResponse response) throws IOException {
        List<User> users = List.of(
            new User("1", "John Doe"),
            new User("2", "Jane Smith")
        );

        sendJsonResponse(users, response);
    }

    // Retrieve messages between two users
    private void fetchMessages(String senderId, String receiverId, HttpServletResponse response) throws IOException {
        List<Message> messages = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM Messages WHERE (senderId = ? AND receiverId = ?) OR (senderId = ? AND receiverId = ?) ORDER BY timestamp";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, senderId);
            stmt.setString(2, receiverId);
            stmt.setString(3, receiverId);
            stmt.setString(4, senderId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(
                    rs.getString("senderId"),
                    rs.getString("receiverId"),
                    rs.getString("messageText"),
                    rs.getLong("timestamp")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sendJsonResponse(messages, response);
    }

    // Save a new message to the database
    private void sendMessage(String senderId, String receiverId, String messageText, HttpServletResponse response) throws IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "INSERT INTO Messages (senderId, receiverId, messageText, timestamp) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, senderId);
            stmt.setString(2, receiverId);
            stmt.setString(3, messageText);
            stmt.setLong(4, System.currentTimeMillis());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save message");
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    // Utility method to send JSON responses
    private void sendJsonResponse(Object data, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(data);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    // Inner class to represent a user
    class User {
        String id;
        String name;

        User(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    // Inner class to represent a message
    class Message {
        String senderId;
        String receiverId;
        String messageText;
        long timestamp;

        Message(String senderId, String receiverId, String messageText, long timestamp) {
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.messageText = messageText;
            this.timestamp = timestamp;
        }
    }
}
