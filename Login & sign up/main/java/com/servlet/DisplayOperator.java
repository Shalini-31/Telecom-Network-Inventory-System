package com.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewOperator")
public class DisplayOperator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Output the HTML page
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Operators</title>");
            out.println("<style>");
            out.println("table { width: 100%; border-collapse: collapse; }");
            out.println("th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head><body>");
          
            out.println("<table>");
            out.println("<tr><th>Operator List</th></tr>");
          

            // Include the HTML content from DeleteOperator.html
            RequestDispatcher rd = request.getRequestDispatcher("/DeleteOperator.html");
            rd.include(request, response);
           
            out.println("</table>");

            // Output the servlet-generated content
            List<String> operators = getOperators();
            out.println("<table>");
            for (String operator : operators) {
                out.println("<tr><td>" + operator + "</td></tr>");
            }
            out.println("</table>");

            out.println("</body></html>");
        } catch (Exception e) {
            // Handle exceptions appropriately, e.g., log or display error message
            e.printStackTrace();
        }
    }

    private List<String> getOperators() throws SQLException, ClassNotFoundException {
        List<String> operators = new ArrayList<>();
        try (Connection connection = Databaseconnection.initializeDataBase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM operator");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                operators.add(resultSet.getString("username"));
            }
        }
        return operators;
    }
}

