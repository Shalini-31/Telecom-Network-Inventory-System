package com.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update1")
public class DisplayFaultDetailsop extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		response.setContentType("text/html");   // convert text into Html in printWriter class
		PrintWriter out=response.getWriter();
		try
		{
			
			// Database Connection
			Connection con1=Databaseconnection.initializeDataBase();
			Statement s1=con1.createStatement();
			ResultSet rs=s1.executeQuery("Select * from fault");  // retriving column form table
			
			// used to print table heading
			//out.println("<table border='1'><tr><th>FaultId</th><th>ComponentId</th><th>DetectedOn</th><th>Severity</th><th>Status</th><th>Description</th><th>Actions</th></tr>");
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>Fault Management Operator</title>");
			out.println("<style>");
			out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #E2E6E8; }");
			out.println("header { background-color: #0C4160; color: #fff; padding: 20px 0; text-align: center; }");
			out.println("nav { background-color: #C1C3BE; padding: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
			out.println("nav ul { list-style-type: none; margin: 0; padding: 0; text-align: center; }");
			out.println("nav ul li { display: inline; margin-right: 20px; }");
			out.println("nav ul li a { color: #545B5C; text-decoration: none; font-weight: bold; }");
			out.println("nav ul li a:hover { color: #AEA885; }");
			out.println("table { border-collapse: collapse; width: 100%; overflow: scroll; background-color: #fff; }");
			out.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
			out.println("th { background-color: #C1C3BE; color: #545B5C; }");
			out.println("tr:nth-child(even) { background-color: #E2E6E8; }");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
 
			out.println("<header><h1>Fault Management Operator</h1></header>");
			out.println("<nav>");
			out.println("<ul>");
			out.println("<li><a href=\"update1\">Manage Fault</a></li>");
			out.println("<li><a href=\"link\">Update Maintenance</a></li>");
			out.println("<li><a href=\"ViewServlet\">Manage Inventory</a></li>");
			out.println("<li><a href=\"index.html\">Logout</a></li>");
			out.println("</ul>");
			out.println("</nav>");
 
			// Search box
			out.println("<div id='searchContainer' style='text-align: right; margin-bottom: 20px;'>"); // Container for search box with right alignment
			out.println("  <input type='text' onkeyup='searchTable()' placeholder='Search for any field..' style='width: 200px; padding: 8px; border: 1px solid #ccc; border-radius: 4px; margin-left: auto;'>"); // Search input with inline style
			out.println("</div>");
 
			out.println("<script>");
			out.println("function searchTable() {");
			out.println("  var input, filter, table, tr, td, i, j, txtValue;");
			out.println("  input = document.getElementsByTagName('input')[0];");
			out.println("  filter = input.value.toUpperCase();");
			out.println("  table = document.getElementById('faultTable');");
			out.println("  tr = table.getElementsByTagName('tr');");
			out.println("  for (i = 0; i < tr.length; i++) {");
			out.println("    td = tr[i].getElementsByTagName('td');");
			out.println("    for (j = 0; j < td.length; j++) {");
			out.println("      txtValue = td[j].textContent || td[j].innerText;");
			out.println("      if (txtValue.toUpperCase().indexOf(filter) > -1) {");
			out.println("        tr[i].style.display = '';"); // Show the row
			out.println("        break;");
			out.println("      } else {");
			out.println("        tr[i].style.display = 'none';"); // Hide the row
			out.println("      }");
			out.println("    }");
			out.println("  }");
			out.println("}");
			out.println("</script>");
 
			out.println("<center>");
			out.println("<div>");
			out.println("<h2>Fault Details</h2>");
			out.println("<table id='faultTable'>");
			out.println("<tr><th>FaultId</th><th>ComponentId</th><th>DetectedOn</th><th>Severity</th><th>Status</th><th>Description</th><th>Action</th></tr>");
 
			while (rs.next()) {
				String FaultId = rs.getString(1);
				String ComponentId = rs.getString(2);
				Date DetectedOn = rs.getDate(3);
				String Severity = rs.getNString(4);
				String Status = rs.getString(5);
				String Description = rs.getString(6);
				out.println("<tr><td>" + FaultId + "</td><td>" + ComponentId + "</td><td>" + DetectedOn + "</td><td>" + Severity + "</td><td>" + Status + "</td><td>" + Description + "</td><td>" + "<button style='background-color: green;'><a href='UpdateServletPage1?FaultId=" + FaultId + "' style='text-decoration: none; color: white;'>Edit</a></button></td></tr>");
				
			}
		
			            out.println("</table>");
			            out.println("</center>");
			            out.println("</body>");
			            out.println("</html>");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}
