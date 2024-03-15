package com.main;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;


//Linked with index.html
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName, password, role;
        userName = request.getParameter("username").trim();
        password = request.getParameter("password").trim();
        role = request.getParameter("role");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        if (role.equalsIgnoreCase("admin")) {
            try {
                Connection connection = Databaseconnection.initializeDataBase();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from admin where username=? and password=?");
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    response.sendRedirect("page.html");
                } else {
                	   out.println("<script>alert('Wrong password!');</script>");
                       RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                       rd.include(request, response);
                }
            } catch (Exception e) {
                // Handle exception
            }
        }
        if (role.equalsIgnoreCase("operator")) {
            try {
                Connection connection = Databaseconnection.initializeDataBase();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from operator where username=? and password=?");
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    response.sendRedirect("operator.html");
                } else {
                	out.println("<script>alert('Wrong password!');</script>");
                    RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                    rd.include(request, response);
                }
            } catch (Exception e) {
                // Handle exception
            }
        }
        if (role.equalsIgnoreCase("viewer")) {
            try {
                Connection connection = Databaseconnection.initializeDataBase();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from viewer where username=? and password=?");
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    response.sendRedirect("viewer.html");
                } else {
                	out.println("<script>alert('Wrong password!');</script>");
                    RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                    rd.include(request, response);
                }
            } catch (Exception e) {
                // Handle exception
            }
        }
    }
}



