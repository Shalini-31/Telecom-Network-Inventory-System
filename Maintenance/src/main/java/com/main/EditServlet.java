package com.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
 
        String ScheduleId = request.getParameter("ScheduleId");
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/prodapt?useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("select * from MaintenanceSchedule where ScheduleID=?");
            ps.setString(1, ScheduleId);
            ResultSet rs = ps.executeQuery();
 
            out.println("<html><head><title>Edit Maintenance Data</title></head><body>");
            out.println("<h2>Edit Maintenance Data</h2>");
            out.println("<form action='UpdateServlet' method='post'>");
            out.println("<table>");
            while (rs.next()) {
                String ComponentId = rs.getString("ComponentID");
                String MaintenanceDate = rs.getString("MaintenanceDate");
                String MaintenanceType = rs.getString("MaintenanceType");
                String Status = rs.getString("Status");
 
                out.println("<tr><td>ScheduleId:</td><td><input type='text' name='ScheduleId' value='" + ScheduleId + "' readonly/></td></tr>");
                out.println("<tr><td>ComponentId:</td><td><input type='text' name='ComponentId' value='" + ComponentId + "' readonly/></td></tr>");
                out.println("<tr><td>MaintenanceDate:</td><td><input type='text' name='MaintenanceDate' value='" + MaintenanceDate + "'/></td></tr>");
                out.println("<tr><td>MaintenanceType:</td><td><input type='text' name='MaintenanceType' value='" + MaintenanceType + "'/></td></tr>");
                out.println("<tr><td>Status:</td><td><input type='text' name='Status' value='" + Status + "'/></td></tr>");
            }
            out.println("</table>");
            out.println("<input type='submit' value='Update'/>");
            out.println("</form>");
            out.println("</body></html>");
 
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}