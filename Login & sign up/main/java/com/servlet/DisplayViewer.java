package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewViewer")
public class DisplayViewer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Output the HTML page
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Viewer List</title>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; width: 100%; }");
            out.println("th, td { text-align: left; padding: 8px; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<table>");
            
            out.println("</table>");

            // Include the HTML content from DeleteViewer.html
            RequestDispatcher rd = request.getRequestDispatcher("/DeleteViewer.html");
            rd.include(request, response);

            // Output the servlet-generated content
            try (Connection connection = Databaseconnection.initializeDataBase();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM viewer;");
                 ResultSet resultSet = preparedStatement.executeQuery()) {
            	
                out.println("<table>");
                out.println("<tr><th>Viewer List</th></tr>");
                while (resultSet.next()) {
                	
                    out.println("<tr><td>" + resultSet.getString(1) + "</td></tr>");
                }
                out.println("</table>");
            } catch (Exception e) {
                // Handle exceptions appropriately, e.g., log or display error message
                e.printStackTrace();
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            // Handle exceptions appropriately, e.g., log or display error message
            e.printStackTrace();
        }
    }
}
