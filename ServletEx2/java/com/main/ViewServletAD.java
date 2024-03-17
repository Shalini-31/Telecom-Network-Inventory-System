package com.main;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewServletAD")
public class ViewServletAD extends HttpServlet {
    /**
     *
     */
	private static final long serialVersionUID = 1L;
	 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
 
        // Output HTML content with embedded CSS styles
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Network Component List</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #E2E6E8; }");
        out.println("header { background-color: #0C4160; color: #FFF; padding: 20px 0; text-align: center; }"); // Modified header background color and text color
        out.println("nav { background-color: #C1C3BE; padding: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
        out.println("nav ul { list-style-type: none; margin: 0; padding: 0; text-align: center; }");
        out.println("nav ul li { display: inline; margin-right: 20px; }");
        out.println("nav ul li a { color: #545B5C; text-decoration: none; font-weight: bold; }"); // Modified navigation link text color
        out.println("nav ul li a:hover { color: #AEA885; }"); // Modified navigation link hover color
        out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; border-color: #DDD; }"); // Modified table border color
        out.println("th { background-color: #C1C3BE; color: #545B5C; }"); // Modified table heading color
        out.println("th, td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }");
        out.println(".btn { padding: 10px 20px; border: none; cursor: pointer; border-radius: 4px; text-decoration: none; font-family: Arial, sans-serif; font-size: 14px; }");
        out.println(".btn-edit { background-color: green; color: white; }"); // Modified Edit button background color
        out.println(".btn-delete { background-color: #f44336; color: white; }");
        out.println(".btn:hover { opacity: 0.8; }");
        out.println(".search-form { margin: 20px auto; text-align: center; }");
        out.println(".search-form input[type='text'], .search-form select { padding: 8px; margin-right: 10px; border-color: #CCC; }"); // Modified input border color
        out.println(".search-form input[type='submit'], .search-form .btn { padding: 10px 20px; background-color: #0C4160; color: white; border: none; border-radius: 4px; cursor: pointer; font-family: Arial, sans-serif; font-size: 14px; }"); // Modified button background color and text color
        out.println(".filter-dropdown { position: relative; display: inline-block; }");
        out.println(".filter-dropdown-content { display: none; position: absolute; background-color: #fff; min-width: 160px; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); z-index: 1; padding: 10px; border-radius: 4px; border: 1px solid #ddd; }");
        out.println(".filter-dropdown-content a { display: block; padding: 8px 0; text-decoration: none; color: #333; transition: background-color 0.3s; }");
        out.println(".filter-dropdown-content a:hover { background-color: #f2f2f2; }");
        out.println(".filter-dropdown:hover .filter-dropdown-content { display: block; }");
        out.println(".filter-dropdown-content.horizontal { display: flex; flex-direction: row; }");
        out.println(".even-row { background-color: #f2f2f2; }"); // Background color for even rows
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Admin Panel</h1>");
        out.println("</header>");
        out.println("<nav>");
        out.println("<ul>");
        out.println("<li><a href=\"Mangageuser.html\">Manage Users</a></li>");
        out.println("<li><a href=\"VendorMainPage.jsp\">Manage Vendors</a></li>");
        out.println("<li><a href=\"ViewServletAD\">Manage Inventory</a></li>");
        out.println("<li><a href=\"update\">Manage Fault</a></li>");
        out.println("<li><a href=\"index.html\">Logout</a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("<script>");
        out.println("function searchOnChange() {");
        out.println("document.getElementById('searchForm').submit();");
        out.println("}");
        out.println("function filterTable() {");
        out.println("var input, filter, table, tr, td, i, j, txtValue;");
        out.println("input = document.getElementById('searchInput');");
        out.println("filter = input.value.toUpperCase();");
        out.println("table = document.getElementById('componentTable');");
        out.println("tr = table.getElementsByTagName('tr');");
        out.println("for (i = 0; i < tr.length; i++) {");
        out.println("td = tr[i].getElementsByTagName('td');");
        out.println("for (j = 0; j < td.length; j++) {");
        out.println("if (td[j]) {");
        out.println("txtValue = td[j].textContent || td[j].innerText;");
        out.println("if (txtValue.toUpperCase().indexOf(filter) > -1) {");
        out.println("tr[i].style.display = '';");
        out.println("break;");
        out.println("} else {");
        out.println("tr[i].style.display = 'none';");
        out.println("}");
        out.println("}");
        out.println("}");
        out.println("}");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
 
        out.println("<h1 style='text-align: center;'>Network Component List</h1>");
 
 
        // Search parameters
        String search = request.getParameter("search");
 
        // Filter parameters
        String typeFilter = request.getParameter("typeFilter");
        String statusFilter = request.getParameter("statusFilter");
        String locationFilter = request.getParameter("locationFilter"); // Added location filter
 
        // Search and filter form
        out.println("<div class='search-form'>");
        out.println("<form method='get' id='searchForm'>");
        out.println("<input type='text' id='searchInput' name='search' placeholder='Search...' value='" + (search != null ? search : "") + "' onkeyup='filterTable()'>");
        // Filter dropdown for Type
        out.println("<div class='filter-dropdown'>");
        out.println("<button class='btn'>Filter By Type</button>");
        out.println("<div class='filter-dropdown-content'>");
        out.println("<a href='?filterBy=type&typeFilter=Router'>Router</a>");
        out.println("<a href='?filterBy=type&typeFilter=Switch'>Switch</a>");
        out.println("<a href='?filterBy=type&typeFilter=Base Station'>Base Station</a>");
        out.println("<a href='?filterBy=type&typeFilter=Firewall'>Firewall</a>");
        out.println("</div>");
        out.println("</div>");
        
        // Filter dropdown for Status
        out.println("<div class='filter-dropdown'>");
        out.println("<button class='btn'>Filter By Status</button>");
        out.println("<div class='filter-dropdown-content'>");
        out.println("<a href='?filterBy=status&statusFilter=Active'>Active</a>");
        out.println("<a href='?filterBy=status&statusFilter=Inactive'>Inactive</a>");
        out.println("<a href='?filterBy=status&statusFilter=Maintenance'>Maintenance</a>"); // Corrected spelling
        out.println("</div>");
        out.println("</div>");
        
        // Filter dropdown for Location
        out.println("<div class='filter-dropdown'>");
        out.println("<button class='btn'>Filter By Location</button>");
        out.println("<div class='filter-dropdown-content'>");
        out.println("<a href='?filterBy=location&locationFilter=Atlanta,GA'>Atlanta, GA</a>");
        out.println("<a href='?filterBy=location&locationFilter=Chicago,IL'>Chicago, IL</a>");
        out.println("<a href='?filterBy=location&locationFilter=Dallas,TX'>Dallas, TX</a>");
        out.println("<a href='?filterBy=location&locationFilter=San Jose,CA'>San Jose, CA</a>");
        out.println("</div>");
        out.println("</div>");
 
        // out.println("<input type='submit' value='Search' class='btn'>"); // Search button
        out.println("<a href='sam.html' class='btn'>Add</a>");
        out.println("<a href='ViewServletAD' class='btn'>Back</a>");
        out.println("</form>");
        out.println("</div>");
 
        // Retrieve filtered list of components
        List<EmpAD> list = EmpDaoAD.getAllComponents(search, typeFilter, statusFilter, locationFilter); // Added location filter
 
        // Display table
        out.print("<table border='1' width='100%' id='componentTable'>");
        out.print("<tr><th>ComponentID</th><th>Type</th><th>Model</th><th>Status</th><th>Location</th><th>VendorID</th><th>Edit</th><th>Delete</th></tr>");
 
        // Display table rows
        for (int i = 0; i < list.size(); i++) {
            EmpAD e = list.get(i);
            String rowClass = (i % 2 == 0) ? "even-row" : ""; // Assigning even-row class for even rows
            out.print("<tr class='" + rowClass + "'><td>" + e.getCid() + "</td><td>" + e.getType() + "</td><td>" + e.getModel() + "</td><td>" + e.getStatus() + "</td><td>" + e.getLocation() + "</td><td>" + e.getVid() + "</td><td><a href='EditServletAD?cid=" + e.getCid() + "' class='btn btn-edit'>Edit</a></td><td><a href='DeleteServletAD?cid=" + e.getCid() + "' class='btn btn-delete' onclick='return confirmDelete()'>Delete</a></td></tr>");
        }
        out.print("</table>");
 
        // JavaScript for delete confirmation
        out.print("<script>");
        out.print("function confirmDelete() {");
        out.print("return confirm('Are you sure you want to delete?');");
        out.print("}");
        out.print("</script>");
        out.println("</body>");
        out.println("</html>");
 
        out.close();
    }
}
