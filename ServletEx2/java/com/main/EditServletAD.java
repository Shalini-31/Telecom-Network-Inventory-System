package com.main;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/EditServletAD")
public class EditServletAD extends HttpServlet {
    /**
     *
     */
	private static final long serialVersionUID = 1L;
	 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Edit Components</title>");
        out.println("<link rel='stylesheet' type='text/css' href='EditServletstyle.css'>"); // Link to CSS file
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
        out.println("header { background-color: #0C4160; color: #FFF; padding: 20px 0; text-align: center; }"); // Modified header background color and text color
        out.println("header h1 { margin: 0; }");
        out.println("nav { background-color: #C1C3BE; padding: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }"); // Modified navigation background color
        out.println("nav ul { list-style-type: none; margin: 0; padding: 0; text-align: center; }");
        out.println("nav ul li { display: inline; margin-right: 20px; }");
        out.println("nav ul li a { color: #545B5C; text-decoration: none; font-weight: bold; }"); // Modified navigation link text color
        out.println("nav ul li a:hover { color: #AEA885; }"); // Modified navigation link hover color
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Admin Panel</h1>");
        out.println("</header>");
        out.println("<nav>");
        out.println("<ul>");
        out.println("<li><a href=\"Mangageuser.html\">Manage Users</a></li>");
        out.println("<li><a href=\"ManageVendors\">Manage Vendors</a></li>");
        out.println("<li><a href=\"ManageInventory\">Manage Inventory</a></li>");
        out.println("<li><a href=\"ManageFault\">Manage Fault</a></li>");
        out.println("<li><a href=\"index.html\">Logout</a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>"); // Added a container for styling
        String cid=request.getParameter("cid");
        EmpAD e=EmpDaoAD.getComponentById(cid);
 
        out.print("<form action='EditServlet2AD' method='post'>");
        out.print("<table>");
        out.println("<h1 class='heading'>Update Components</h1>");
        out.print("<tr><td></td><td><input type='hidden' name='cid' value='"+e.getCid()+"'/></td></tr>");
        out.print("<tr><td>Type:</td><td><input type='text' name='type' value='"+e.getType()+"'/></td></tr>");
        out.print("<tr><td>Model:</td><td><input type='text' name='model' value='"+e.getModel()+"'/></td></tr>");
        out.print("<tr><td>Status:</td><td>");
        out.print("<select name='status'>");
        out.print("<option>Active</option>");
        out.print("<option>Inactive</option>");
        out.print("<option>Maintenance</option>");
        out.print("</select>");
        out.print("</td></tr>");
        out.print("<tr><td>Location:</td><td><input type='text' name='location' value='"+e.getLocation()+"'/></td></tr>");
        out.print("<tr><td>VendorID:</td><td><input type='text' name='vid' value='"+e.getVid()+"'readonly/></td></tr>");
        out.print("<tr><td colspan='2'><input type='submit' value='Edit &amp; Save '/></td></tr>");
        out.print("</table>");
        out.print("</form>");
        out.println("</div>"); // Closing the container
        out.println("</body>");
        out.println("</html>");
 
        out.close();
    }
}

 