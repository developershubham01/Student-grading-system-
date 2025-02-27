import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class StudentServlet extends HttpServlet {

    private Connection conn;

    public void init() throws ServletException {
        // Initialize database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/yourdatabase";
        String jdbcUsername = "username";
        String jdbcPassword = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error initializing database connection", e);
        }
    }

    public void destroy() {
        // Close database connection
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("name");
        int studentAge = Integer.parseInt(request.getParameter("age"));
        // Retrieve other parameters as needed

        try {
            // Insert student data into database
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (name, age) VALUES (?, ?)");
            stmt.setString(1, studentName);
            stmt.setInt(2, studentAge);
            // Set other parameters if necessary

            stmt.executeUpdate();

            // Optionally, you can redirect to a success page
            response.sendRedirect("success.jsp");

        } catch (SQLException e) {
            throw new ServletException("Error with SQL", e);
        }
    }
}
