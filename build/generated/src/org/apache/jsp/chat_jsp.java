package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class chat_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Mentor Chat</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h1>Mentor Chat</h1>\n");
      out.write("    <div id=\"chatContainer\">\n");
      out.write("        <!-- List of Mentees -->\n");
      out.write("        <div id=\"chatList\" style=\"border: 1px solid #ccc; margin-bottom: 10px; padding: 10px;\">\n");
      out.write("            <h3>Mentees</h3>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- Chat messages -->\n");
      out.write("        <div id=\"chatBox\" style=\"height:300px; overflow-y:scroll; border: 1px solid #ccc; margin-bottom: 10px; padding: 10px;\">\n");
      out.write("            <h3>Chat</h3>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- Input to send messages -->\n");
      out.write("        <input type=\"text\" id=\"messageInput\" placeholder=\"Type your message here\" style=\"width: 80%;\">\n");
      out.write("        <button type=\"button\" onclick=\"sendMessage()\">Send</button>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        const senderId = \"");
      out.print( session.getAttribute("userId") );
      out.write("\"; // Logged-in mentor's ID\n");
      out.write("        let receiverId = \"\"; // Mentee's ID, set dynamically\n");
      out.write("\n");
      out.write("        // Load mentees to chat with\n");
      out.write("        function loadMentees() {\n");
      out.write("            fetch('/chat?action=getChatUsers') // Call the \"getChatUsers\" action\n");
      out.write("                .then(response => response.json())\n");
      out.write("                .then(users => {\n");
      out.write("                    const chatList = document.getElementById('chatList');\n");
      out.write("                    chatList.innerHTML = users.map(user =>\n");
      out.write("                        `<div style=\"cursor: pointer;\" onclick=\"setReceiver('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("')\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>`\n");
      out.write("                    ).join('');\n");
      out.write("                })\n");
      out.write("                .catch(err => alert(\"Error loading mentees: \" + err));\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        // Set the receiver ID and fetch messages\n");
      out.write("        function setReceiver(id) {\n");
      out.write("            receiverId = id;\n");
      out.write("            fetchMessages();\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        // Fetch messages between the sender and receiver\n");
      out.write("        function fetchMessages() {\n");
      out.write("            if (!receiverId) return alert(\"Please select a mentee to chat with!\");\n");
      out.write("            fetch(`/chat?action=fetchMessages&senderId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${senderId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&receiverId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${receiverId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("`) // Call the \"fetchMessages\" action\n");
      out.write("                .then(response => response.json())\n");
      out.write("                .then(messages => {\n");
      out.write("                    const chatBox = document.getElementById('chatBox');\n");
      out.write("                    chatBox.innerHTML = messages.map(msg => {\n");
      out.write("                        const formattedDate = new Date(msg.timestamp).toLocaleString(); // Format timestamp\n");
      out.write("                        return `<div><strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${msg.senderId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</strong>: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${msg.messageText}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" <small>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${formattedDate}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</small></div>`;\n");
      out.write("                    }).join('');\n");
      out.write("                })\n");
      out.write("                .catch(err => alert(\"Error fetching messages: \" + err));\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        // Send a new message\n");
      out.write("        function sendMessage() {\n");
      out.write("            if (!receiverId) return alert(\"Please select a mentee to chat with!\");\n");
      out.write("            const messageText = document.getElementById('messageInput').value;\n");
      out.write("            if (messageText.trim() === \"\") return;\n");
      out.write("\n");
      out.write("            fetch('/chat?action=sendMessage', { // Call the \"sendMessage\" action\n");
      out.write("                method: 'POST',\n");
      out.write("                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },\n");
      out.write("                body: `senderId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${encodeURIComponent(senderId)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&receiverId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${encodeURIComponent(receiverId)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&messageText=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${encodeURIComponent(messageText)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("`\n");
      out.write("            })\n");
      out.write("                .then(() => {\n");
      out.write("                    document.getElementById('messageInput').value = ''; // Clear input\n");
      out.write("                    fetchMessages(); // Refresh chat\n");
      out.write("                })\n");
      out.write("                .catch(err => alert(\"Error sending message: \" + err));\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        // Load mentees when the page loads\n");
      out.write("        window.onload = loadMentees;\n");
      out.write("    </script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
