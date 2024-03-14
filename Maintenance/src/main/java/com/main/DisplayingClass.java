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
 
@WebServlet("/link")
public class DisplayingClass extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/prodapt?useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM MaintenanceSchedule");
            ResultSet rs = ps.executeQuery();
 
            out.println("<html><head><title>Maintenance Data Display</title>");
            out.println("</head><body>");
            out.println("<h2>Maintenance Data Display</h2>");
            out.println("<table border='1'><tr><th>ScheduleId</th><th>ComponentId</th><th>MaintenanceDate</th><th>MaintenanceType</th><th>Status</th><th>Actions1</th><th>Actions2</th></tr>");
 
            boolean recordsFound = false;
            while (rs.next()) {
                recordsFound = true;
                String ScheduleId = rs.getString(1);
                String ComponentId = rs.getString(2);
                String MaintenanceDate = rs.getString(3);
                String MaintenanceType = rs.getString(4);
                String Status = rs.getString(5);
 
                out.println("<tr><td>" + ScheduleId + "</td><td>"
                        + ComponentId + "</td><td>"
                        + MaintenanceDate + "</td><td>"
                        + MaintenanceType + "</td><td>"
                        + Status + "</td><td>"
                        + "<a href='EditServlet?ScheduleId=" + ScheduleId + "'>Update</a></td><td>"
                        +
                        "<a href='DeleteServlet?ScheduleId="
                        + ScheduleId +
                        "' onclick='return confirm(\"Are you sure you want to delete this row?\");'>Delete</a></td></tr>"
                        );
                }
            out.println("</table>");
 
            if (!recordsFound) {
                out.println("<p>No records found.</p>");
            }
 
            out.println("</body></html>");
 
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}