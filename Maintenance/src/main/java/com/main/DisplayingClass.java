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
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/prodapt?useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM MaintenanceSchedule");
            ResultSet rs = ps.executeQuery();
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Maintenance Data Display</title>");
            out.println("<style>");
            out.println("table {");
            out.println("  border-collapse: collapse;");
            out.println("  width: 100%;");
            out.println("}");
            out.println("th, td {");
            out.println("  border: 1px solid #dddddd;");
            out.println("  text-align: left;");
            out.println("  padding: 8px;");
            out.println("}");
            out.println("tr:nth-child(even) {");
            out.println("  background-color: #f2f2f2;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
          //  out.println("<h2>Maintenance Data Display</h2>");
            out.println("<table>");
//            out.println("<tr>");
//            out.println("<th>ScheduleId</th>");
//            out.println("<th>ComponentId</th>");
//            out.println("<th>MaintenanceDate</th>");
//            out.println("<th>MaintenanceType</th>");
//            out.println("<th>Status</th>");
//            out.println("<th>Actions</th>");
//            out.println("</tr>");
 
            out.println("<html><head><title>Maintenance Data Display</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
            out.println("header { background-color: rgb(255, 0, 0); color: #fff; padding: 20px 0; text-align: center; }");
            out.println("header h1 { margin: 0; }");
            out.println("nav { background-color: #fff; padding: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
            out.println("nav ul { list-style-type: none; margin: 0; padding: 0; text-align: center; }");
            out.println("nav ul li { display: inline; margin-right: 20px; }");
            out.println("nav ul li a { color: #333; text-decoration: none; font-weight: bold; }");
            out.println("nav ul li a:hover { color: #555; }");
            out.println("</style>");
            out.println("</head><body>");
 
            out.println("<header><h1>Maintenance Data Display</h1></header>");
            out.println("<nav><ul>");
            out.println("<li><a href=\"Mangageuser.html\">Manage Users</a></li>");
            out.println("<li><a href=\"ManageInventory.html\">Manage Inventory</a></li>");
            out.println("<li><a href=\"ManageVendors.html\">Manage Vendors</a></li>");
            out.println("<li><a href=\"ManageFault.html\">Manage Fault</a></li>");
            out.println("<li><a href=\"index.html\">Logout</a></li>");
            out.println("</ul></nav>");
 
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
                        + "<button style='background-color: green; color: white;' onclick='window.location.href=\"EditServlet?ScheduleId=" + ScheduleId + "\";'>Update</button></td><td>"
                        +"<button style='background-color: red;' onclick='return confirm(\"Are you sure you want to delete this item?\");'><a href='DeleteServlet?ScheduleId=" + ScheduleId + "' class='delete-button' style='color: white; text-decoration: none;'>Delete</a></button></td></tr>"
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
        } finally {
            out.close(); // Close PrintWriter
        }
    }
}