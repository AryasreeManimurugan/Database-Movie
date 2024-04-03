import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class movieJdbc extends HttpServlet {

    private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
    private static final String USERNAME = "CSI3450";
    private static final String PASSWORD = "AryaMani1";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM MOVIE")) {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>Movie Table Report</title></head><body>");
            out.println("<br /><b><center><font color=\"RED\"><H2>Movie Table Report</H2></font></center><br />");
            out.println("<center><table border=\"1\"><tr BGCOLOR=\"#cccccc\">");
            out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movieID</td>");
            out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movieTitle</td>");
            out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movieDescription</td>");
            out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movieLengthMin</td>");
            out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movieReleaseDate</td>");
            out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movieGenre</td>");
            out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movieRating</td>");
            out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">movieLanguage</td>");
            out.println("</tr>");

            while (resultSet.next()) {
                out.println("<tr>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">" + resultSet.getString(1) + "</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">" + resultSet.getString(2) + "</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">" + resultSet.getString(3) + "</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">" + resultSet.getString(4) + "</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">" + resultSet.getString(5) + "</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">" + resultSet.getString(6) + "</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">" + resultSet.getString(7) + "</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">" + resultSet.getString(8) + "</td>");
                out.println("</tr>");
            }

            out.println("</table></CENTER></body></html>");

        } catch (SQLException e) {
            throw new ServletException("Error accessing database", e);
        }
    }
}
