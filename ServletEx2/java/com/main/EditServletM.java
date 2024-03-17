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

@WebServlet("/EditServletM")
public class EditServletM extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String ScheduleId = request.getParameter("ScheduleId");

        try {
        	Connection con = Databaseconnection.initializeDataBase();
            PreparedStatement ps = con.prepareStatement("select * from MaintenanceSchedule where ScheduleID=?");
            ps.setString(1, ScheduleId);
            ResultSet rs = ps.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Edit Maintenance Data</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
            out.println("header { background-color: rgb(255, 0, 0); color: #fff; padding: 20px 0; text-align: center; }");
            out.println("header h1 { margin: 0; }");
            out.println("nav { background-color: #fff; padding: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
            out.println("nav ul { list-style-type: none; margin: 0; padding: 0; text-align: center; }");
            out.println("nav ul li { display: inline; margin-right: 20px; }");
            out.println("nav ul li a { color: #333; text-decoration: none; font-weight: bold; }");
            out.println("nav ul li a:hover { color: #555; }");
            out.println("form { margin: 20px auto; width: 50%; }");
            out.println("input[type='text'] { width: 100%; padding: 10px; margin-bottom: 10px; }");
            out.println("input[type='submit'] { background-color: #4CAF50; color: white; padding: 14px 20px; margin: 8px 0; border: none; cursor: pointer; width: 100%; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header><h1>Edit Maintenance Data</h1></header>");
            out.println("<nav><ul>");
            out.println("<li><a href=\"Mangageuser.html\">Manage Users</a></li>");
            out.println("<li><a href=\"ManageInventory.html\">Manage Inventory</a></li>");
            out.println("<li><a href=\"ManageVendors.html\">Manage Vendors</a></li>");
            out.println("<li><a href=\"ManageFault.html\">Manage Fault</a></li>");
            out.println("<li><a href=\"index.html\">Logout</a></li>");
            out.println("</ul></nav>");
            out.println("<form action='UpdateServlet' method='post'>");
            out.println("<table>");

            while (rs.next()) {
                String ComponentId = rs.getString("ComponentID");
                String MaintenanceDate = rs.getString("MaintenanceDate");
                String MaintenanceType = rs.getString("MaintenanceType");
                String Status = rs.getString("Status");
                
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #E2E6E8; }");
                out.println("header { background-color: #0C4160; color: #FFF; padding: 20px 0; text-align: center; }");
                out.println("header h1 { margin: 0; }");
                out.println("nav { background-color: #C1C3BE; padding: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
                out.println("nav ul { list-style-type: none; margin: 0; padding: 0; text-align: center; }");
                out.println("nav ul li { display: inline; margin-right: 20px; }");
                out.println("nav ul li a { color: #545B5C; text-decoration: none; font-weight: bold; }");
                out.println("nav ul li a:hover { color: #AEA885; }");
                out.println("form { margin: 20px auto; width: 50%; }");
                out.println("input[type='text'] { width: 100%; padding: 10px; margin-bottom: 10px; }");
                out.println("input[type='submit'] { background-color: #4CAF50; color: white; padding: 14px 20px; margin: 8px 0; border: none; cursor: pointer; width: 100%; }");
                out.println("table { border-collapse: collapse; width: 100%; border: 1px solid #DDD; }");
                out.println("th, td { border: 1px solid #DDD; text-align: left; padding: 8px; }");
                out.println("</style>");



                out.println("<tr><td>ScheduleId:</td><td><input type='text'  name='ScheduleId' value='" + ScheduleId
                        + "' readonly/></td></tr>");
                out.println("<tr><td>ComponentId:</td><td><input type='text' name='ComponentId' value='" + ComponentId
                        + "' readonly/></td></tr>");
                out.println("<tr><td>MaintenanceDate:</td><td><input type='text' name='MaintenanceDate' value='"
                        + MaintenanceDate + "'/></td></tr>");
                out.println("<tr><td>MaintenanceType:</td><td><input type='text' name='MaintenanceType' value='"
                        + MaintenanceType + "'/></td></tr>");
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
