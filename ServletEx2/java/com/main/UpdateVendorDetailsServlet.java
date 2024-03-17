package com.main;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateVendorDetailsServlet")
public class UpdateVendorDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
        	String vendorID =request.getParameter("vendorID_update");
            String name = request.getParameter("name_update");
            String contact = request.getParameter("contact_update");
            String serviceType =request.getParameter("serviceType_update");
            String SLA = request.getParameter("SLA_update");
            
            // Update user details in the database
            Connection con = Databaseconnection.initializeDataBase();
            PreparedStatement st = con.prepareStatement("UPDATE vendors SET name=?, contact=?, serviceType=?, SLA=? WHERE vendorID=?");
            st.setString(1, name);
            st.setString(2, contact);
            st.setString(3, serviceType);
            st.setString(4, SLA);
            st.setString(5, vendorID);
            st.executeUpdate();
            st.close();
            con.close();
            
            // Forward to display.jsp with updated user list
            List<VendorPojo> updatedUserList = getUserListFromDatabase();
            request.setAttribute("VendorList", updatedUserList);
            request.getRequestDispatcher("VendorMainPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while updating user details.");
        }
    }

    private List<VendorPojo> getUserListFromDatabase() throws ClassNotFoundException {
        List<VendorPojo> VendorList = new ArrayList<>();
        try {
            Connection con = Databaseconnection.initializeDataBase();
            PreparedStatement st = con.prepareStatement("SELECT * FROM vendors");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
            	String vendorID = rs.getString("vendorID");
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                String serviceType = rs.getString("serviceType");
                String SLA = rs.getString("SLA");
                VendorPojo ven = new VendorPojo(vendorID, name, contact, serviceType, SLA);
                VendorList.add(ven);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions or errors here
        }
        return VendorList;
    }
}

