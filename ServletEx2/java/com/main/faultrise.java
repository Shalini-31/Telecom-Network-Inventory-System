package com.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
 
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/faultdisplay")
 
public class faultrise extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String comp=req.getParameter("component_id");
		comp=comp.toUpperCase();
		String desp=req.getParameter("description");
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		RequestDispatcher rd=req.getRequestDispatcher("/viewer.html");
		//RequestDispatcher rs=req.getRequestDispatcher("/NewFile.html");
			try {
			Connection c1=Databaseconnection.initializeDataBase();
		    Statement s1=c1.createStatement();
		    LocalDate myObj= LocalDate.now();
		    
		    String n4=null;
		    String n5=null;
		    String s3 = "INSERT INTO fault ( componentId, detectedon,severity,status,decrpition) VALUES ('"+ comp +"','" + myObj + "', '" + n4 + "','" + n5+ "' , '" + desp+ "')";
			int result=s1.executeUpdate(s3);
			if(result==1) {
			 out.println("<script>alert(\"Successfully added\");</script>");
			//rs.include(req,res);
			 rd.forward(req, res);
			}
			else
			{
				out.println("<script>alert(\"Unable add\");</script>");
				//rs.include(req,res);
				 rd.forward(req, res);
			}
			 s1.close();
			 c1.close();
		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
 
 
}
