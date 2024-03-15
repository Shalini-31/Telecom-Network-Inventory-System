package com.FaultOperatorActions;

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


@WebServlet("/UpdateServletPage")
public class UpdateServletPage extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String FaultId = request.getParameter("FaultId");

		try 
		{
			Connection con = JdbcFault.initializeDatabase();

			PreparedStatement ps = con.prepareStatement("select * from fault where FaultID=?");
			ps.setString(1, FaultId);
			ResultSet rs = ps.executeQuery();

			out.println("<html><head><title>Updating Details</title></head><body>");
			out.println("<h2>Updating Details</h2>");
			out.println("<form action='DisplayUpdate' method='post'>"); 
			// which forward based on displayupdate.java


			out.println("<table>");
			while (rs.next())
			{
				FaultId = rs.getString("FaultID");
				String Severity =" ";
				String Status= " "; 


				out.println("<tr><td>FaultId:</td><td><input type='text' name='FaultId' value='" + FaultId + "' readonly/></td></tr>");

				// here we create dropdown for column and compares with values
				out.println("<tr><td>Severity:</td><td><select name='Severity'>");
				out.println("<option value='Low'" + (Severity.equals("Low") ? " selected" : "") + ">Low</option>");
				out.println("<option value='High'" + (Severity.equals("High") ? " selected" : "") + ">High</option>");
				out.println("<option value='Critical'" + (Severity.equals("Critical") ? " selected" : "") + ">Critical</option>");
				out.println("</select></td></tr>");

				// here we create dropdown for column and compares with values
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
