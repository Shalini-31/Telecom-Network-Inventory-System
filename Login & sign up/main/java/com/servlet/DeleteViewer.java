package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/deleteViewer")
public class DeleteViewer extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String usernameToDelete=request.getParameter("deleteUser");
		PrintWriter out=response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("viewViewer");
		try
		{
			Connection connection=Databaseconnection.initializeDataBase();
			PreparedStatement preparedStatement=connection.prepareStatement("delete from viewer where username=?;");
			preparedStatement.setString(1,usernameToDelete);
			int result=preparedStatement.executeUpdate();
			if(result==1)
			{
				out.println("<script>alert('Viewer deleted');</script>");
				rd.include(request, response);
			}
			else
			{
				out.println("<script>alert('Unable to delete');</script>");
				rd.include(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}