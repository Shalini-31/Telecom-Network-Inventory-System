package com.main;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String scheduleId = request.getParameter("ScheduleId");
        if (scheduleId == null || scheduleId.isEmpty()) {
            response.sendRedirect("link"); // Redirect back if ScheduleId is not provided
            return;
        }
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/prodapt?useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("DELETE FROM MaintenanceSchedule WHERE ScheduleID=?");
            ps.setString(1, scheduleId);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            con.close();
 
            if (rowsAffected > 0) {
                response.sendRedirect("link"); // Redirect back after successful deletion
            } else {
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h3>No record found with ScheduleId: " + scheduleId + "</h3>");
                out.println("<a href='link'>Go back</a>");
                out.println("</body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }
}