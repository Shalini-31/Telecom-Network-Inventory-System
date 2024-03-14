package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

public class RegisterViewerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username, email, password, confirmPassword;
        username = request.getParameter("username").trim();
        email = request.getParameter("email").trim();
        password = request.getParameter("password").trim();
        confirmPassword = request.getParameter("confirm_password").trim();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        if (!password.equals(confirmPassword)) {
            out.println("Passwords do not match");
            return;
        }

        try {
            Connection connection = Databaseconnection.initializeDataBase();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO viewer (username, password) VALUES (?, ?)");
            preparedStatement.setString(1, username);
            //preparedStatement.setString(2, email);
            preparedStatement.setString(2, password);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                out.println("<script>alert('Successfully registered!');</script>");
                RequestDispatcher rd=request.getRequestDispatcher("/index.html");
                response.sendRedirect("index.html");
            } else {
                out.println("Failed to register viewer");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred during registration");
        }
    }
}
