package com.main;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/VenAdd")
public class VendorAdd extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try
		{
			Connection con=Databaseconnection.initializeDataBase();
			PreparedStatement st=con.prepareStatement("insert into vendors(vendorID,name,contact,serviceType,SLA) values(?,?,?,?,?)");
			st.setString(1, req.getParameter("vendorID"));
			st.setString(2, req.getParameter("name"));
			st.setString(3, req.getParameter("contact"));
			st.setString(4, req.getParameter("serviceType"));
			st.setString(5, req.getParameter("SLA"));
			st.executeUpdate();
			st.close();
			
			//out.println("<script>alert('Data added successfully!');</script>");
			//res.sendRedirect("display");
			out.println("<script>alert('Vendor Data added successfully!');window.location.href='VendorMainPage.jsp';</script>");
			//out.println("<script>window.location.href='display.jsp';</script>"); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			out.println("<script>alert('Error occurred while adding data. Please try again later.');</script>");
            out.println("<script>window.location.href='VendorMainPage.jsp';</script>"); // Redirect to an error page
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

