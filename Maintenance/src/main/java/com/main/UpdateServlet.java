package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        String ScheduleId = request.getParameter("ScheduleId");
        String MaintenanceDate = request.getParameter("MaintenanceDate");
        String MaintenanceType = request.getParameter("MaintenanceType");
        String Status = request.getParameter("Status");
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/prodapt?useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("update MaintenanceSchedule set MaintenanceDate=?, MaintenanceType=?, Status=? where ScheduleID=?");
            ps.setString(1, MaintenanceDate);
            ps.setString(2, MaintenanceType);
            ps.setString(3, Status);
            ps.setString(4, ScheduleId);
 
            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("link");
            }
 
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}