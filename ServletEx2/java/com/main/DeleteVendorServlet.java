
package com.main;


import java.io.IOException;


import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteVendorServlet")
public class DeleteVendorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            Connection con = Databaseconnection.initializeDataBase();
            PreparedStatement st = con.prepareStatement("DELETE FROM vendors WHERE vendorID = ?");
            st.setString(1,request.getParameter("vendorID"));
            st.executeUpdate();
            st.close();
            con.close();

            // After deletion, retrieve the updated user list and set it in the request scope
            request.getRequestDispatcher("VendorMainPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while deleting user data.");
        }
    }
}

