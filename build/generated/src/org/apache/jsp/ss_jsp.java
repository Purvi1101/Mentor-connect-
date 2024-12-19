package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ss_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Mentor Dashboard - Schedule Sessions</title>\n");
      out.write("    <style>\n");
      out.write("        /* General Styles */\n");
      out.write("body {\n");
      out.write("    font-family: Arial, sans-serif;\n");
      out.write("    margin: 0;\n");
      out.write("    padding: 0;\n");
      out.write("    background-color: #f4f4f4;\n");
      out.write("    color: #333;\n");
      out.write("    line-height: 1.6;\n");
      out.write("}\n");
      out.write("\n");
      out.write("h1, h2 {\n");
      out.write("    color: #444;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Header */\n");
      out.write("h1 {\n");
      out.write("    text-align: center;\n");
      out.write("    margin-top: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Form Styles */\n");
      out.write("form {\n");
      out.write("    max-width: 600px;\n");
      out.write("    margin: 20px auto;\n");
      out.write("    background: #fff;\n");
      out.write("    padding: 20px;\n");
      out.write("    border-radius: 8px;\n");
      out.write("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n");
      out.write("}\n");
      out.write("\n");
      out.write("form label {\n");
      out.write("    font-weight: bold;\n");
      out.write("    display: block;\n");
      out.write("    margin-bottom: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("form input[type=\"text\"],\n");
      out.write("form input[type=\"date\"],\n");
      out.write("form input[type=\"time\"],\n");
      out.write("form input[type=\"number\"],\n");
      out.write("form textarea {\n");
      out.write("    width: 100%;\n");
      out.write("    padding: 10px;\n");
      out.write("    margin-bottom: 15px;\n");
      out.write("    border: 1px solid #ccc;\n");
      out.write("    border-radius: 4px;\n");
      out.write("    font-size: 16px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("form textarea {\n");
      out.write("    resize: vertical;\n");
      out.write("}\n");
      out.write("\n");
      out.write("form button {\n");
      out.write("    display: inline-block;\n");
      out.write("    background: #007bff;\n");
      out.write("    color: #fff;\n");
      out.write("    border: none;\n");
      out.write("    padding: 10px 20px;\n");
      out.write("    font-size: 16px;\n");
      out.write("    border-radius: 4px;\n");
      out.write("    cursor: pointer;\n");
      out.write("    transition: background-color 0.3s ease;\n");
      out.write("}\n");
      out.write("\n");
      out.write("form button:hover {\n");
      out.write("    background: #0056b3;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Table Styles */\n");
      out.write("table {\n");
      out.write("    width: 90%;\n");
      out.write("    margin: 20px auto;\n");
      out.write("    border-collapse: collapse;\n");
      out.write("    background: #fff;\n");
      out.write("    border-radius: 8px;\n");
      out.write("    overflow: hidden;\n");
      out.write("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n");
      out.write("}\n");
      out.write("\n");
      out.write("table th,\n");
      out.write("table td {\n");
      out.write("    padding: 12px;\n");
      out.write("    text-align: left;\n");
      out.write("    border-bottom: 1px solid #ddd;\n");
      out.write("}\n");
      out.write("\n");
      out.write("table th {\n");
      out.write("    background: #007bff;\n");
      out.write("    color: #fff;\n");
      out.write("    font-weight: bold;\n");
      out.write("    text-transform: uppercase;\n");
      out.write("}\n");
      out.write("\n");
      out.write("table tr:hover {\n");
      out.write("    background: #f1f1f1;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Container for Sessions */\n");
      out.write("#sessionTable {\n");
      out.write("    text-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Buttons */\n");
      out.write("button {\n");
      out.write("    padding: 10px 20px;\n");
      out.write("    margin: 5px;\n");
      out.write("    font-size: 16px;\n");
      out.write("    border: none;\n");
      out.write("    border-radius: 4px;\n");
      out.write("    cursor: pointer;\n");
      out.write("    transition: all 0.3s ease;\n");
      out.write("}\n");
      out.write("\n");
      out.write("button:hover {\n");
      out.write("    background-color: #555;\n");
      out.write("    color: white;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Responsive Design */\n");
      out.write("@media (max-width: 768px) {\n");
      out.write("    form {\n");
      out.write("        padding: 10px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    table {\n");
      out.write("        width: 100%;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    table th,\n");
      out.write("    table td {\n");
      out.write("        padding: 8px;\n");
      out.write("    }\n");
      out.write("}\n");
      out.write("\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h1>Mentor Dashboard - Schedule Sessions</h1>\n");
      out.write("\n");
      out.write("    <h2>Schedule a New Session</h2>\n");
      out.write("    <form action=\"ScheduleSessionServlet\" method=\"POST\">\n");
      out.write("        <label for=\"sessionName\">Session Name:</label><br>\n");
      out.write("        <input type=\"text\" id=\"sessionName\" name=\"sessionName\" required><br><br>\n");
      out.write("\n");
      out.write("        <label for=\"date\">Date:</label><br>\n");
      out.write("        <input type=\"date\" id=\"date\" name=\"date\" required><br><br>\n");
      out.write("\n");
      out.write("        <label for=\"time\">Time:</label><br>\n");
      out.write("        <input type=\"time\" id=\"time\" name=\"time\" required><br><br>\n");
      out.write("\n");
      out.write("        <label for=\"duration\">Duration (in minutes):</label><br>\n");
      out.write("        <input type=\"number\" id=\"duration\" name=\"duration\" required><br><br>\n");
      out.write("\n");
      out.write("        <label for=\"description\">Description:</label><br>\n");
      out.write("        <textarea id=\"description\" name=\"description\" rows=\"4\" cols=\"50\"></textarea><br><br>\n");
      out.write("\n");
      out.write("        <button type=\"submit\">Schedule Session</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <h2>Your Scheduled Sessions</h2>\n");
      out.write("    <table>\n");
      out.write("        <tr>\n");
      out.write("            <th>Session Name</th>\n");
      out.write("            <th>Date</th>\n");
      out.write("            <th>Time</th>\n");
      out.write("            <th>Duration</th>\n");
      out.write("            <th>Description</th>\n");
      out.write("        </tr>\n");
      out.write("        <tbody id=\"sessionTable\">\n");
      out.write("            <!-- Scheduled sessions will be dynamically loaded -->\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        // Fetch and load sessions when the page loads\n");
      out.write("        window.onload = function() {\n");
      out.write("            fetch('FetchScheduledSessionsServlet')\n");
      out.write("                .then(response => response.json())\n");
      out.write("                .then(sessions => {\n");
      out.write("                    const sessionTable = document.getElementById('sessionTable');\n");
      out.write("                    sessionTable.innerHTML = sessions.map(session => `\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session.sessionName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session.date}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session.time}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session.duration}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" minutes</td>\n");
      out.write("                            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session.description}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                    `).join('');\n");
      out.write("                })\n");
      out.write("                .catch(err => alert(\"Error loading sessions: \" + err));\n");
      out.write("        };\n");
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
