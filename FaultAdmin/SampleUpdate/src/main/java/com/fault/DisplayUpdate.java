package com.fault;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DisplayUpdate")

public class DisplayUpdate extends HttpServlet 
{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String faultId = request.getParameter("FaultId"); 

		String severity = request.getParameter("Severity");

		String status = request.getParameter("Status");


		try 
		{
			
			Connection con = JdbcFault.initializeDatabase();

			PreparedStatement ps = con.prepareStatement("UPDATE fault SET Severity=?, Status=? WHERE faultId=?");
			ps.setString(1, severity);
			ps.setString(2, status);
			ps.setString(3, faultId);

			int rowsUpdated = ps.executeUpdate();

			System.out.println(rowsUpdated+" Successfully updated");

			// this page moves to DisplayFaultPage.java
			response.sendRedirect(request.getContextPath() + "/update");
		} 

		catch (Exception e) 
		{
			System.out.println(e);
			// Handle exceptions appropriately
		}
	}
}


