import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class ChatServlet extends HttpServlet {
    private static List<Message> messages = new ArrayList<>();
    private Gson gson = new Gson(); // Initialize Gson instance

    // Handle GET request to fetch messages
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String role = request.getParameter("role");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        synchronized (messages) {
            List<Message> relevantMessages = new ArrayList<>();
            for (Message msg : messages) {
                if ((role.equals("mentor") && msg.getSenderRole().equals("mentee")) ||
                    (role.equals("mentee") && msg.getSenderRole().equals("mentor"))) {
                    relevantMessages.add(msg);
                }
            }
            out.println(gson.toJson(relevantMessages)); // Serialize Java objects to JSON
        }
    }

    // Handle POST request to send a message
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        Message newMessage = gson.fromJson(reader, Message.class); // Deserialize JSON to Java object

        synchronized (messages) {
            messages.add(newMessage); // Add the new message to the list
        }
    }

    // Inner class to represent a message
    private class Message {
        private String senderRole;
        private int senderId;
        private String content;

        public String getSenderRole() { return senderRole; }
        public void setSenderRole(String senderRole) { this.senderRole = senderRole; }
        public int getSenderId() { return senderId; }
        public void setSenderId(int senderId) { this.senderId = senderId; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }
}
