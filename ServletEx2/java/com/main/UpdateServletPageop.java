package com.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/UpdateServletPage1")
public class UpdateServletPageop extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String FaultId = request.getParameter("FaultId");

		try 
		{
			Connection con = Databaseconnection.initializeDataBase();

			PreparedStatement ps = con.prepareStatement("select * from fault where FaultID=?");
			ps.setString(1, FaultId);
			ResultSet rs = ps.executeQuery();

			out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Updating Details</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #E2E6E8; }");
            out.println("header { background-color: #0C4160; color: #fff; padding: 20px 0; text-align: center; }");
            out.println("nav { background-color: #C1C3BE; padding: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
            out.println("nav ul { list-style-type: none; margin: 0; padding: 0; text-align: center; }");
            out.println("nav ul li { display: inline; margin-right: 20px; }");
            out.println("nav ul li a { color: #545B5C; text-decoration: none; font-weight: bold; }");
            out.println("nav ul li a:hover { color: #AEA885; }");
            out.println("table { border-collapse: collapse; width: 50%; margin: 20px auto; border: 2px solid #ddd; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("input[type=text], select, input[type=submit] { width: 100%; padding: 12px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; margin: 8px 0; display: inline-block; }");
            out.println("input[type=submit] { background-color: #0C4160; color: white; border: none; cursor: pointer; }");
            out.println("</style>");
            out.println("</head><body>");
 
            // Header and Navigation
            out.println("<header><h1>Fault Management Admin</h1></header>");
            out.println("<nav>");
            out.println("<ul>");
            out.println("<li><a href=\"Manage Users.html\">Manage Users</a></li>");
            out.println("<li><a href=\"Manage Vendors.html\">Manage Users</a></li>");
            out.println("<li><a href=\"Manage Inventory.html\">Manage Inventory</a></li>");
            out.println("<li><a href=\"Manage Fault.html\">Manage Fault</a></li>");
            out.println("<li><a href=\"index.html\">Logout</a></li>");
            out.println("</ul>");
            out.println("</nav>");
 
            out.println("<center><h2>Updating Details</h2></center>");
            out.println("<form action='DisplayUpdate1' method='post'>");
 
            out.println("<table>");
 
            while (rs.next()) {
                FaultId = rs.getString("FaultID");
                String Severity = " ";
                String Status = " ";
 
                out.println("<tr><td>FaultId:</td><td><input type='text' name='FaultId' value='" + FaultId + "' readonly/></td></tr>");
 
                out.println("<tr><td>Severity:</td><td><select name='Severity'>");
                out.println("<option value='Low'" + (Severity.equals("Low") ? " selected" : "") + ">Low</option>");
                out.println("<option value='High'" + (Severity.equals("High") ? " selected" : "") + ">High</option>");
                out.println("<option value='Critical'" + (Severity.equals("Critical") ? " selected" : "") + ">Critical</option>");
                out.println("</select></td></tr>");
 
                out.println("<tr><td>Status:</td><td>");
                out.println("<select name='Status'>");
                out.println("<option value='Resolved'" + (Status.equals("Resolved") ? " selected" : "") + ">Resolved</option>");
                out.println("<option value='Unresolved'" + (Status.equals("Unresolved") ? " selected" : "") + ">Unresolved</option>");
                out.println("<option value='Investigating'" + (Status.equals("Investigating") ? " selected" : "") + ">Investigating</option>");
                out.println("</select></td></tr>");
            }
            out.println("</table>");
            out.println("<input type='submit' value='Update'/>");
            out.println("</form>");
            out.println("</body></html>");


			con.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
