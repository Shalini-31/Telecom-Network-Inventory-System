package com.fault;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class DisplayFaultPage extends HttpServlet{

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");   // convert text into Html in printWriter class
		PrintWriter out = response.getWriter();
		try {

			// Database Connection
			Connection con1 = JdbcFault.initializeDatabase();
			Statement s1 = con1.createStatement();
			ResultSet rs = s1.executeQuery("Select * from fault");  // retrieving column from table

			//out.println("<h2>rep Table</h2>");

			// used to print table heading
			//out.println("<table border='1'><tr><th>FaultId</th><th>ComponentId</th><th>DetectedOn</th><th>Severity</th><th>Status</th><th>Description</th><th>Actions1</th><th>Actions2</th></tr>");
			
		    		    out.println("<style>"
		    		            + "div {"
		    		            + "  width: 1000px;"
		    		            + "  height: 800px;"
		    		            + "  border: 1px solid black;"
		    		            + "  overflow: scroll;"
		    		            + "}"
		    		            + "table { border-collapse: collapse; width: 100%;Overflow: scroll }"
		    		            + "th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }"
		    		            + "th { background-color: #f2f2f2; }"
		    		            + "tr:nth-child(even) { background-color: #f9f9f9; }"
		    		            + "#filterInput { margin-bottom: 10px; }" // Adjust the margin bottom here
		    		            + "</style>"
		    		            + "<script>"
		    		            + "function filterTable() {"
		    		            + "  var input, filter, table, tr, td, i, txtValue;"
		    		            + "  input = document.getElementById('filterInput');"
		    		            + "  filter = input.value.toUpperCase();"
		    		            + "  table = document.getElementById('dataTable');"
		    		            + "  tr = table.getElementsByTagName('tr');"
		    		            + "  for (i = 0; i < tr.length; i++) {"
		    		            + "    td = tr[i].getElementsByTagName('td');"
		    		            + "    for (var j = 0; j < td.length; j++) {"
		    		            + "      if (td[j]) {"
		    		            + "        txtValue = td[j].textContent || td[j].innerText;"
		    		            + "        if (txtValue.toUpperCase().indexOf(filter) > -1) {"
		    		            + "          tr[i].style.display = '';"
		    		            + "          break;"
		    		            + "        } else {"
		    		            + "          tr[i].style.display = 'none';"
		    		            + "        }"
		    		            + "      }"
		    		            + "    }"
		    		            + "  }"
		    		            + "}"
		    		            + "</script>"
		    		            + "</head><body>");
		    		    out.println("<center><div><input type='text' id='filterInput' onkeyup='filterTable()' placeholder='Search for names..' />");
		    		    out.println("<h2><center>Fault Details</center></h2>");
		    		    out.println("<table id='dataTable'><tr><th>FaultId</th><th>ComponentId</th><th>DetectedOn</th><th>Severity</th><th>Status</th><th>Description</th><th>Action1</th></th><th>Action2</th></tr>");
			
			// used to insert column values in table data
			while (rs.next()) {
				String FaultId = rs.getString(1);
				String ComponentId = rs.getString(2);
				Date DetectedOn = rs.getDate(3);
				String Severity = rs.getNString(4);
				String Status = rs.getString(5);
				String Description = rs.getString(6);
				
				
	    
				out.println("<tr><td>" + FaultId + "</td><td>"
						+ ComponentId + "</td><td>"
						+ DetectedOn + "</td><td>"
						+ Severity + "</td><td>"
						+ Status + "</td><td>"
						+ Description + "</td><td>"
						+ "<a href='UpdateServletPage?FaultId=" + FaultId + "'>Edit</a></td><td>"
						+ "<a href='FaultAdminDelete?FaultId=" + FaultId + "' onclick='return confirm(\"Are you sure you want to delete this item?\");'>Delete</a></td></tr>");
			}

			out.println("</table>"); // Close the first table

			// Heading for the Resolved table
			out.println("<h2>Resolved Fault Details</h2>");

			// Retrieve and display data from the Resolved table
			ResultSet rs1 = s1.executeQuery("Select * from resolved");
			out.println("<table border='1'><tr><th>FaultId</th><th>ComponentId</th><th>DetectedOn</th><th>Severity</th><th>Status</th><th>Description</th></tr>");

			
			
			while (rs1.next()) {
				String FaultId = rs1.getString(1);
				String ComponentId = rs1.getString(2);
				Date DetectedOn = rs1.getDate(3);
				String Severity = rs1.getNString(4);
				String Status = rs1.getString(5);
				String Description = rs1.getString(6);

				out.println("<tr><td>" + FaultId + "</td><td>"
						+ ComponentId + "</td><td>"
						+ DetectedOn + "</td><td>"
						+ Severity + "</td><td>"
						+ Status + "</td><td>"
						+ Description + "</td></tr>");
			}
			out.println("</table>"); // Close the second table

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
//	{
//		
//		response.setContentType("text/html");   // convert text into Html in printWriter class
//		PrintWriter out=response.getWriter();
//		try
//		{
//			
//			// Database Connection
//			Connection con1=JdbcFault.initializeDatabase();
//			Statement s1=con1.createStatement();
//			ResultSet rs=s1.executeQuery("Select * from fault");  // retriving column form table
//			
//			out.println("<h2>rep Table</h2>");
//			
//			// used to print table heading
//			out.println("<table border='1'><tr><th>FaultId</th><th>ComponentId</th><th>DetectedOn</th><th>Severity</th><th>Status</th><th>Description</th><th>Actions1</th><th>Actions2</th></tr>");
//			
//			// used to insert column values in table data
//			while(rs.next())
//			{
//				String FaultId=rs.getString(1);
//				String ComponentId=rs.getString(2);
//				Date DetectedOn=rs.getDate(3);
//				String Severity=rs.getNString(4);
//				String Status=rs.getString(5);
//				String Description=rs.getString(6);
//				
//		
//				
//				out.println("<tr><td>" + FaultId + "</td><td>"
//			            + ComponentId + "</td><td>"
//			            + DetectedOn + "</td><td>"
//			            + Severity + "</td><td>"
//			            + Status + "</td><td>"
//			            + Description + "</td><td>"
//			            + "<a href='UpdateServletPage?FaultId=" + FaultId + "'>Edit</a></td><td>"
//				+ "<a href='FaultAdminDelete?FaultId=" + FaultId + "' onclick='return confirm(\"Are you sure you want to delete this item?\");'>Delete</a></td></tr>");
//				
//				
//				
//				
//			}
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//		}
//		out.println("<br><br>");
//		
//		try
//		{
//			Connection con1=JdbcFault.initializeDatabase();
//			Statement s1=con1.createStatement();
//			ResultSet rs1=s1.executeQuery("Select * from resolved");
//			
//			out.println("<h2>Resolved Table</h2>");
//			out.println("<table border='1'><tr><th>FaultId</th><th>ComponentId</th><th>DetectedOn</th><th>Severity</th><th>Status</th><th>Description</th></tr>");
//			
//			
//			while(rs1.next())
//			{
//				String FaultId=rs1.getString(1);
//				String ComponentId=rs1.getString(2);
//				Date DetectedOn=rs1.getDate(3);
//				String Severity=rs1.getNString(4);
//				String Status=rs1.getString(5);
//				String Description=rs1.getString(6);
//				
//		
//				
//				out.println("<tr><td>" + FaultId + "</td><td>"
//			            + ComponentId + "</td><td>"
//			            + DetectedOn + "</td><td>"
//			            + Severity + "</td><td>"
//			            + Status + "</td><td>"
//			            + Description + "</td></tr>");
//			
//		}
//		
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//		}
//		
//	}


