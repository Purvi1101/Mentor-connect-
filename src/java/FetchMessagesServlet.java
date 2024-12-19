@WebServlet("/fetchMessages")
public class FetchMessagesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String senderId = request.getParameter("senderId");
        String receiverId = request.getParameter("receiverId");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentor_connect", "root", "password")) {
            String query = "SELECT * FROM chat_messages WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) ORDER BY timestamp";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, senderId);
            stmt.setString(2, receiverId);
            stmt.setString(3, receiverId);
            stmt.setString(4, senderId);

            ResultSet rs = stmt.executeQuery();
            List<String> messages = new ArrayList<>();
            out.write("[");
            boolean first = true;

            while (rs.next()) {
                if (!first) out.write(",");
                out.write("{");
                out.write("\"senderId\":\"" + rs.getString("sender_id") + "\",");
                out.write("\"messageText\":\"" + rs.getString("message_text") + "\",");
                out.write("\"timestamp\":\"" + rs.getTimestamp("timestamp") + "\"");
                out.write("}");
                first = false;
            }
            out.write("]");
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("[]");
        }
    }
}
