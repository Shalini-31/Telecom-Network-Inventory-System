package com.main;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/add")
public class CheckData extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try
		{
		
		Connection c1=Databaseconnection.initializeDataBase();
		Statement s1=c1.createStatement();
		ResultSet rs=s1.executeQuery("Select * from details;" );
		out.println("<html><head><title>Subject Data</title>");
	    out.println("<style>"
	    		
	    		+"body{"
	            +"border:none;"
	            + "}"
	    		
	            + "div {"
	            + "  width: 100%;"
	            + "  height: 100%;"
	          
	            + "  overflow: scroll;"
	           
	            + "}"
	            + "table { border-collapse: collapse; width: 100%;Overflow: scroll;position: relative;  box-shadow: 9px 9px 13px #cbced1; margin-left:10px;}"
	            + "th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }"
	            + "th { background-color: #f2f2f2; }"
	            + "tr:nth-child(even) { background-color: #f9f9f9; }"
	            + "#filterInput { margin-bottom: 10px;"
	            + "  padding: 10px;\n"
	            + " width: 90%;border-radius: 20px;border: 1px solid #ccc;\n"
	            + "  box-sizing: border-box;\n"
	            + "  font-size: 16px;margin-top: 10px;"
	            + " box-shadow: inset 6px 6px 6px #cbced1, inset -6px -6px 6px white;"
	            + " font-size: 14px;"
	            + ""
	            + " }" // Adjust the margin bottom here
	           
	            
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
	    out.println("<table id='dataTable'><th>Component_id</th><th>Type</th><th>Model</th><th>Status</th><th>Location</th><th>Vendor_id</th></tr>");
	  
	    while (rs.next())
		{
			 out.println("<tr><td>" + rs.getString("ComponentId") + "</td><td>" + rs.getString("Type") + "</td><td>"
                     + rs.getString("Model")+"</td><td>" + rs.getString("Status")+ "</td><td>"+ rs.getString("Location")+"</td><td>"+ rs.getString("VendorID")+"</td></tr>");
		}
		  out.println("</table></body></html>");
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
}




