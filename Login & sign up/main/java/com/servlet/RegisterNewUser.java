

package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


//Linked with page.html register user
@WebServlet("/reg")
public class RegisterNewUser extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String userName, password, role;
        userName = request.getParameter("username").trim();
        password = request.getParameter("password").trim();
        role = request.getParameter("role");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        RequestDispatcher rd=request.getRequestDispatcher("/page.html");
        if(role.equalsIgnoreCase("operator"))
        {
        	try
    		{
    			Connection connection=Databaseconnection.initializeDataBase();
    			PreparedStatement preparedStatement=connection.prepareStatement("insert into operator values(?,?);");
    			preparedStatement.setString(1, userName);
    			preparedStatement.setString(2, password);
    			int result=preparedStatement.executeUpdate();
    			if(result==1)
    			{
    				rd.include(request, response);
    				out.println("<script>alert('New Operator Registered');</script>");
    			}
    			else
    			{
    				rd.include(request, response);
    				out.println("<script>alert('Failed to Registerd');</script>");
    			}
    		}
    		catch(Exception e)
    		{
    			
    		}
        }
        if(role.equalsIgnoreCase("viewer"))
        {
        	try
    		{
    			Connection connection=Databaseconnection.initializeDataBase();
    			PreparedStatement preparedStatement=connection.prepareStatement("insert into viewer values(?,?);");
    			preparedStatement.setString(1, userName);
    			preparedStatement.setString(2, password);
    			int result=preparedStatement.executeUpdate();
    			if(result==1)
    			{
    				rd.include(request, response);
    				out.println("<script>alert('New Viewer Registered');</script>");
    			}
    			else
    			{
    				rd.include(request, response);
    				out.println("<script>alert('Failed to Registerd');</script>");
    			}
    		}
    		catch(Exception e)
    		{
    			
    		}
        }
		
	}
}
