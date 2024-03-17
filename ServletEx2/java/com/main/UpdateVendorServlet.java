package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateVendorServlet")
public class UpdateVendorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            String vendorID = request.getParameter("vendorID");
            
            // Retrieve user details from the database
            Connection con = Databaseconnection.initializeDataBase();
            PreparedStatement st = con.prepareStatement("SELECT * FROM vendors WHERE vendorID = ?");
            st.setString(1, vendorID);
            ResultSet rs = st.executeQuery();

            VendorPojo ven = null;
            if (rs.next()) {
                String vendorIDFromDB = rs.getString("vendorID");
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                String serviceType = rs.getString("serviceType");
                String SLA = rs.getString("SLA");

                ven = new VendorPojo(vendorIDFromDB, name, contact, serviceType, SLA);
            }

            rs.close();
            st.close();
            con.close();

            // Forward the request to update.jsp with user details
            request.setAttribute("ven1", ven);
            // Forward to the main page with the vendor details
            request.getRequestDispatcher("VendorMainPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while retrieving user data for update.");
        }
    }
}
