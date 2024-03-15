
//final 2
package com.fault;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/FaultAdminDelete")
public class FaultAdminDelete extends HttpServlet 
{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String faultId = request.getParameter("FaultId");
	    HttpSession session = request.getSession();

	    try {
	        Connection con = JdbcFault.initializeDatabase();
	        // Begin transaction
	        con.setAutoCommit(false);

	        // Retrieve data to be moved to the other table before deletion
	        String selectQuery = "SELECT * FROM fault WHERE faultid = ?";
	        PreparedStatement ps = con.prepareStatement(selectQuery);
	        ps.setString(1, faultId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String status = rs.getString("status");
	            if (status.equalsIgnoreCase("resolved")) {
	                // Move data to the other table
	                String insertQuery = "INSERT INTO resolved (faultid, componentid, detectedon, severity, status, decrpition) VALUES (?, ?, ?, ?, ?, ?)";
	                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
	                insertStatement.setString(1, rs.getString("faultid"));
	                insertStatement.setString(2, rs.getString("componentid"));
	                insertStatement.setDate(3, rs.getDate("detectedon"));
	                insertStatement.setString(4, rs.getString("severity"));
	                insertStatement.setString(5, rs.getString("status"));
	                insertStatement.setString(6, rs.getString("decrpition"));
	                insertStatement.executeUpdate();
	                insertStatement.close(); // Close insert statement

	                // Prepare SQL statement to delete the row with the given FaultId
	                PreparedStatement ps1 = con.prepareStatement("DELETE FROM fault WHERE FaultId=?");
	                ps1.setString(1, faultId);

	                int rowsDeleted = ps1.executeUpdate();

	                if (rowsDeleted > 0) {
	                    con.commit();
	                    session.setAttribute("message", "Fault deleted successfully.");
	                } else {
	                    session.setAttribute("message", "No rows deleted!");
	                }

	                ps1.close();
	                con.close();
	            } else {
	                session.setAttribute("message", "FaultId not found!");
	            }

	            response.sendRedirect(request.getContextPath() + "/update");
	        } 
	    }
	    catch (Exception e) {
	            e.printStackTrace();
	            session.setAttribute("message", "Error occurred: " + e.getMessage());
	            response.sendRedirect(request.getContextPath() + "/update");
	        }

}
}












































































//package com.fault;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/FaultAdminDelete")
//public class FaultAdminDelete extends HttpServlet
//{
//
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	{
//		String faultId = request.getParameter("FaultId");
//
//		try	
//		{
//			Connection con = JdbcFault.initializeDatabase();
//			// Begin transaction
//			con.setAutoCommit(false);
//
//			// Retrieve data to be moved to the other table before deletion
//			String selectQuery = "SELECT * FROM fault WHERE faultid = ?";
//			PreparedStatement ps = con.prepareStatement(selectQuery);
//			ps.setString(1, faultId);
//
//			ResultSet rs=ps.executeQuery();
//
//
//			if(rs.next())
//			{                        
//
//				// Move data to the other table
//				String insertQuery="INSERT INTO resolved (faultid, componentid,detectedon,severity,status,decrpition) VALUES (?,?,?,?,?,?)";                        
//
//					PreparedStatement insertStatement = con.prepareStatement(insertQuery);
//					insertStatement.setString(1, rs.getString("faultid")); // Assuming column1 is a string
//					insertStatement.setString(2, rs.getString("componentid")); 
//					insertStatement.setDate(3, rs.getDate("detectedon")); // Assuming column1 is a string
//					insertStatement.setString(4, rs.getString("severity")); 
//					insertStatement.setString(5, rs.getString("status")); // Assuming column1 is a string
//					insertStatement.setString(6, rs.getString("decrpition")); 
//					// Assuming column2 is a string
//					insertStatement.executeUpdate();
//
//
//
//
//			
//
//				// Prepare SQL statement to delete the row with the given FaultId
//				PreparedStatement ps1 = con.prepareStatement("DELETE FROM fault WHERE FaultId=?");
//				ps1.setString(1, faultId);
//
//				// Execute the delete operation
//				int rowsDeleted = ps1.executeUpdate();
//
//
//				// Check if the deletion was successful
//				if (rowsDeleted > 0) 
//				{
//					// Redirect back to the update servlet to refresh the table
//					response.sendRedirect(request.getContextPath() + "/update");
//				} 
//
//				else 
//				{
//					// Handle case where no rows were deleted
//					// You may want to provide feedback to the user
//					response.getWriter().println("No rows deleted!");
//				}
//
//
//				// Close the database connection
//				con.close();
//
//
//			
//
//
//		}
//		}
//		catch (Exception e) 
//
//		{
//			// Handle exceptions appropriately
//			System.out.println(e);
//			response.getWriter().println("Error occurred: " + e.getMessage());
//		}
//	}
//
//}