import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/chat")
public class ChatWebSocketServer {

    private static Set<Session> chatSessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        chatSessions.add(session);
        System.out.println("New connection: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        JSONObject jsonMessage = new JSONObject(message);
        String senderId = jsonMessage.getString("senderId");
        String receiverId = jsonMessage.getString("receiverId");
        String messageText = jsonMessage.getString("messageText");

        System.out.println("Message received from " + senderId + ": " + messageText);

        // Broadcast the message to all connected sessions
        broadcastMessage(senderId, messageText);
    }

    @OnClose
    public void onClose(Session session) {
        chatSessions.remove(session);
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error occurred: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    private void broadcastMessage(String senderId, String messageText) {
        for (Session s : chatSessions) {
            try {
                s.getBasicRemote().sendText(new JSONObject()
                        .put("senderId", senderId)
                        .put("messageText", messageText)
                        .toString());
            } catch (IOException e) {
                System.out.println("Error broadcasting message: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
